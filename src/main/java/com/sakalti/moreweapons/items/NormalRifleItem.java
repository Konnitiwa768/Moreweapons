package com.sakalti.moreweapons.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Box;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.item.Items;
import net.minecraft.text.Text;

public class NormalRifleItem extends Item {

    private long lastUsed = 0;

    public NormalRifleItem() {
        super(new Settings().group(ItemGroup.COMBAT));
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, net.minecraft.hand.Hand hand) {
        long currentTime = world.getTime();
        
        // 5ティックのクールダウン
        if (currentTime - lastUsed < 5) {
            return ActionResult.FAIL; // クールダウン中は撃てない
        }
        
        lastUsed = currentTime;

        // プレイヤーの視点先にいる敵を探す
        Vec3d lookDirection = user.getRotationVec(1.0F);
        Vec3d eyePos = user.getCameraPosVec(1.0F);
        Vec3d endPos = eyePos.add(lookDirection.multiply(13)); // 13マスまで

        // 敵に当たるかをチェック
        Box box = new Box(eyePos, endPos).expand(1.0D, 1.0D, 1.0D); // 判定範囲の設定
        EntityHitResult hit = world.raycast(new net.minecraft.util.hit.RaycastContext(eyePos, endPos, net.minecraft.util.hit.RaycastContext.ShapeType.OUTLINE, net.minecraft.util.hit.RaycastContext.FluidHandling.NONE, user));

        if (hit.getType() == HitResult.Type.ENTITY) {
            // 見つかった敵にダメージを与える
            if (hit.getEntity() instanceof LivingEntity) {
                LivingEntity target = (LivingEntity) hit.getEntity();
                target.damage(user.getDamageSources().player(user), 4.0F); // ダメージ4
                target.sendMessage(Text.of("You have been hit by the Normal Rifle!"), true);
            }
        }
        
        return ActionResult.SUCCESS;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 0; // 無限に使用可能（クールダウンで制御）
    }
}
