package com.sakalti.moreweapons.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Box;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class NormalRifleItem extends Item {

    private long lastUsed = 0;

    public NormalRifleItem() {
        super(new Settings().group(ItemGroup.COMBAT).maxDamage(1345)); // 耐久値1345
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, net.minecraft.util.Hand hand) {
        long currentTime = world.getTime();
        ItemStack stack = user.getStackInHand(hand);

        // 5ティックのクールダウン
        if (currentTime - lastUsed < 5) {
            user.sendMessage(net.minecraft.text.Text.literal("まだ撃てません！クールダウン中です。"), true);
            return ActionResult.FAIL;
        }

        lastUsed = currentTime;

        // プレイヤーの視点先にいる敵を探す
        Vec3d lookDirection = user.getRotationVec(1.0F);
        Vec3d eyePos = user.getCameraPosVec(1.0F);
        Vec3d endPos = eyePos.add(lookDirection.multiply(13)); // 13マスまで

        // 敵に当たるかをチェック
        Box box = new Box(eyePos, endPos).expand(1.0D, 1.0D, 1.0D);
        EntityHitResult hit = world.raycast(new net.minecraft.util.hit.RaycastContext(eyePos, endPos, net.minecraft.util.hit.RaycastContext.ShapeType.OUTLINE, net.minecraft.util.hit.RaycastContext.FluidHandling.NONE, user));

        if (hit.getType() == HitResult.Type.ENTITY) {
            // 見つかった敵にダメージを与える
            if (hit.getEntity() instanceof LivingEntity) {
                LivingEntity target = (LivingEntity) hit.getEntity();
                target.damage(user.getDamageSources().player(user), 4.0F); // ダメージ4
                user.sendMessage(net.minecraft.text.Text.literal("敵に命中しました！"), true);

                // 耐久値を1減らす
                stack.damage(1, user, (p) -> p.sendToolBreakStatus(hand));
            }
        } else {
            user.sendMessage(net.minecraft.text.Text.literal("敵が範囲内にいません。"), true);
        }

        return ActionResult.SUCCESS;
    }
}
