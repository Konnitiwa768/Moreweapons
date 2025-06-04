package com.sakalti.moreweapons;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class NormalRifleItem extends Item {

    // 連射速度のクールダウン（tick）
    private static final int COOLDOWN_TICKS = 4;

    // 射程距離
    private static final double RANGE = 30.0;

    // 与ダメージ
    private static final float DAMAGE = 6.0F;

    // 最大装填弾数
    private static final int MAX_BULLETS = 10;

    // NBTキー
    private static final String NBT_BULLET_COUNT = "BulletCount";

    public NormalRifleItem(Settings settings) {
        super(settings);
    }

    // 弾数取得（NBTに無ければ最大弾数を返す）
    private int getBulletCount(ItemStack stack) {
        if (!stack.hasNbt()) return MAX_BULLETS;
        return stack.getNbt().getInt(NBT_BULLET_COUNT);
    }

    // 弾数設定（最大弾数を超えないようにセット）
    private void setBulletCount(ItemStack stack, int count) {
        if (!stack.hasNbt()) stack.setNbt(new NbtCompound());
        stack.getNbt().putInt(NBT_BULLET_COUNT, Math.min(count, MAX_BULLETS));
    }

    /**
     * 銃を使ったときの処理。右クリックで射撃。
     * シフト押しながら右クリックでリロード。
     */
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (user.isSneaking()) {
            // シフト押しながら右クリック → リロード
            if (!world.isClient) {
                boolean reloaded = reload(user, stack);
                if (reloaded) {
                    user.sendMessage(Text.literal("リロードしました。"), true);
                    world.playSound(null, user.getX(), user.getY(), user.getZ(),
                            SoundEvents.ITEM_ARMOR_EQUIP_IRON, user.getSoundCategory(), 1.0F, 1.0F);
                } else {
                    user.sendMessage(Text.literal("リロードに必要なアイテム（鉄と火薬）がありません。"), true);
                    world.playSound(null, user.getX(), user.getY(), user.getZ(),
                            SoundEvents.BLOCK_DISPENSER_FAIL, user.getSoundCategory(), 1.0F, 0.5F);
                }
            }
            return TypedActionResult.success(stack);
        }

        // 通常右クリック → 射撃処理
        if (!world.isClient) {
            int bullets = getBulletCount(stack);

            if (bullets <= 0) {
                // 弾切れ
                user.sendMessage(Text.literal("弾がありません！リロードしてください。"), true);
                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        SoundEvents.BLOCK_DISPENSER_FAIL, user.getSoundCategory(), 1.0F, 0.5F);
                return TypedActionResult.fail(stack);
            }

            // 視点の方向を取得
            Vec3d eyePos = user.getEyePos();
            Vec3d lookVec = user.getRotationVec(1.0F);
            Vec3d targetPos = eyePos.add(lookVec.multiply(RANGE));

            // プレイヤーの当たり判定を伸ばした箱（射線検出用）
            Box box = user.getBoundingBox().stretch(lookVec.multiply(RANGE)).expand(1.0, 1.0, 1.0);

            EntityHitResult targetResult = null;
            double closestDistance = RANGE;

            // 箱内のエンティティを列挙し、射線にヒットする最も近いLivingEntityを見つける
            for (Entity entity : world.getOtherEntities(user, box)) {
                if (!(entity instanceof LivingEntity)) continue;

                Box entityBox = entity.getBoundingBox().expand(0.3);
                var hit = entityBox.raycast(eyePos, targetPos);

                if (hit != null) {
                    double dist = eyePos.distanceTo(hit.getPos());
                    if (dist < closestDistance) {
                        closestDistance = dist;
                        targetResult = new EntityHitResult(entity, hit.getPos());
                    }
                }
            }

            if (targetResult != null) {
                LivingEntity target = (LivingEntity) targetResult.getEntity();

                // 攻撃を与える
                target.damage(net.minecraft.entity.damage.DamageSource.player(user), DAMAGE);

                // 攻撃音を鳴らす
                world.playSound(null, target.getX(), target.getY(), target.getZ(),
                        SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, user.getSoundCategory(), 1.0F, 1.0F);

                // 弾数を1減らす
                setBulletCount(stack, bullets - 1);

                // クールダウン設定（連射速度調整）
                user.getItemCooldownManager().set(this, COOLDOWN_TICKS);
            } else {
                // ヒットなし → 空撃ち音
                world.playSound(null, user.getX(), user.getY(), user.getZ(),
                        SoundEvents.BLOCK_WOOD_BUTTON_CLICK_ON, user.getSoundCategory(), 0.5F, 1.0F);
            }
        }

        return TypedActionResult.success(stack);
    }

    /**
     * リロード処理。プレイヤーのインベントリから鉄と火薬を消費して弾数をMAXに戻す。
     * @param player プレイヤー
     * @param stack 銃のItemStack
     * @return リロード成功したか
     */
    private boolean reload(PlayerEntity player, ItemStack stack) {
        int bullets = getBulletCount(stack);
        if (bullets >= MAX_BULLETS) {
            // 既に満タン
            return false;
        }

        // 鉄1個と火薬1個を消費できるかチェックし、消費
        boolean hasIron = consumeItem(player, Items.IRON_INGOT, 1);
        boolean hasGunpowder = consumeItem(player, Items.GUNPOWDER, 1);

        if (hasIron && hasGunpowder) {
            setBulletCount(stack, MAX_BULLETS);
            return true;
        } else {
            // 片方でも足りなければ戻す（消費済み分を返却）
            if (hasIron) giveItem(player, Items.IRON_INGOT, 1);
            if (hasGunpowder) giveItem(player, Items.GUNPOWDER, 1);
            return false;
        }
    }

    /**
     * 指定アイテムをインベントリから消費する。
     * @param player プレイヤー
     * @param item 消費するアイテム
     * @param count 消費個数
     * @return 消費できたか（全て消費できればtrue）
     */
    private boolean consumeItem(PlayerEntity player, Item item, int count) {
        int remaining = count;

        for (int i = 0; i < player.inventory.size(); i++) {
            ItemStack slotStack = player.inventory.getStack(i);
            if (slotStack.getItem() == item) {
                int consumeCount = Math.min(remaining, slotStack.getCount());
                slotStack.decrement(consumeCount);
                remaining -= consumeCount;
                if (remaining <= 0) break;
            }
        }
        return remaining == 0;
    }

    /**
     * アイテムをプレイヤーのインベントリに戻す（ドロップしないように）
     * @param player プレイヤー
     * @param item 戻すアイテム
     * @param count 個数
     */
    private void giveItem(PlayerEntity player, Item item, int count) {
        ItemStack stack = new ItemStack(item, count);
        if (!player.inventory.insertStack(stack)) {
            // インベントリに空きがない場合は地面にドロップ（念のため）
            player.dropItem(stack, false);
        }
    }
}
