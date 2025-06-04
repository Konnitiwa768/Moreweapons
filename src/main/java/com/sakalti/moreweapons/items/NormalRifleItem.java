package com.sakalti.moreweapons.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.RaycastContext;

import java.util.List;

public class NormalRifleItem extends Item {
    private static final int COOLDOWN_TICKS = 5;
    private static final int MAX_AMMO = 10;

    public NormalRifleItem(Settings settings) {
        super(settings);
    }

    // プレイヤー右クリック時の挙動
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (user.isSneaking()) {
            // リロード
            if (reload(user, stack)) {
                if (!world.isClient) {
                    user.getItemCooldownManager().set(this, 20); // リロードに1秒かかる
                    user.playSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F, 1.0F);
                }
            }
            return TypedActionResult.success(stack, world.isClient());
        }

        // 弾がない
        if (!stack.hasNbt() || stack.getOrCreateNbt().getInt("ammo") <= 0) {
            user.playSound(SoundEvents.BLOCK_NOTE_BLOCK_BASS, 0.5F, 0.5F);
            return TypedActionResult.fail(stack);
        }

        // クールダウン中
        if (user.getItemCooldownManager().isCoolingDown(this)) {
            return TypedActionResult.fail(stack);
        }

        user.getItemCooldownManager().set(this, COOLDOWN_TICKS); // 連射速度
        if (!world.isClient) {
            shoot(world, user);
            decrementAmmo(stack);
        }

        user.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1.0F, 1.2F);
        return TypedActionResult.success(stack, world.isClient());
    }

    // 弾を撃つ処理（視線先の敵を攻撃）
    private void shoot(World world, PlayerEntity user) {
        Vec3d start = user.getCameraPosVec(1.0F);
        Vec3d direction = user.getRotationVec(1.0F);
        Vec3d end = start.add(direction.multiply(30)); // 最大30ブロック先まで

        EntityHitResult hitResult = raycastEntity(world, user, start, end, 1.0F);

        if (hitResult != null) {
            Entity entity = hitResult.getEntity();
            if (entity instanceof LivingEntity target) {
                target.damage(user.getDamageSources().playerAttack(user), 6.0F); // 6ダメージ
            }
        }
    }

    // 視線上にいるエンティティを検出
    private EntityHitResult raycastEntity(World world, PlayerEntity user, Vec3d start, Vec3d end, double radius) {
        Box box = user.getBoundingBox().stretch(user.getRotationVec(1.0F).multiply(30)).expand(radius);
        List<Entity> entities = world.getOtherEntities(user, box, e -> e instanceof LivingEntity && e.isAlive());
        Entity closest = null;
        double closestDist = Double.MAX_VALUE;

        for (Entity entity : entities) {
            Box entityBox = entity.getBoundingBox().expand(0.3);
            HitResult hit = entityBox.raycast(start, end);
            if (hit != null) {
                double distance = start.squaredDistanceTo(hit.getPos());
                if (distance < closestDist) {
                    closest = entity;
                    closestDist = distance;
                }
            }
        }

        if (closest != null) {
            Vec3d pos = closest.getPos();
            return new EntityHitResult(closest, pos);
        }

        return null;
    }

    // リロード処理（鉄1+火薬1を消費）
    private boolean reload(PlayerEntity player, ItemStack stack) {
        if (!player.isCreative()) {
            boolean hasIron = false;
            boolean hasGunpowder = false;
            PlayerInventory inv = player.getInventory();

            for (int i = 0; i < inv.size(); i++) {
                ItemStack is = inv.getStack(i);
                if (!hasIron && is.isOf(Items.IRON_INGOT)) {
                    is.decrement(1);
                    hasIron = true;
                } else if (!hasGunpowder && is.isOf(Items.GUNPOWDER)) {
                    is.decrement(1);
                    hasGunpowder = true;
                }

                if (hasIron && hasGunpowder) break;
            }

            if (!hasIron || !hasGunpowder) return false;
        }

        stack.getOrCreateNbt().putInt("ammo", MAX_AMMO);
        return true;
    }

    // 弾数減らす
    private void decrementAmmo(ItemStack stack) {
        int ammo = stack.getOrCreateNbt().getInt("ammo");
        stack.getOrCreateNbt().putInt("ammo", Math.max(0, ammo - 1));
    }

    // ツールチップに弾数表示
    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        int ammo = stack.getOrCreateNbt().getInt("ammo");
        tooltip.add(Text.of("弾数: " + ammo + " / " + MAX_AMMO));
        tooltip.add(Text.of("Shift+右クリックでリロード"));
    }
}
