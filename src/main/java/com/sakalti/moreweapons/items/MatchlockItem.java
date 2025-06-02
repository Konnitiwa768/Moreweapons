package com.sakalti.moreweapons.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import com.sakalti.moreweapons.MoreWeaponsMod;

public class MatchlockItem extends Item {

    private long lastUsed = 0;

    public MatchlockItem() {
        super(new Settings().group(MoreWeaponsMod.COMBAT).maxDamage(950));
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        long currentTime = world.getTime();
        ItemStack stack = user.getStackInHand(hand);

        if (currentTime - lastUsed < 60) {
            user.sendMessage(Text.literal("まだ撃てません！クールダウン中です。"), true);
            return ActionResult.FAIL;
        }

        lastUsed = currentTime;

        Vec3d lookDirection = user.getRotationVec(1.0F);
        Vec3d eyePos = user.getCameraPosVec(1.0F);
        Vec3d endPos = eyePos.add(lookDirection.multiply(25));
        Box box = new Box(eyePos, endPos).expand(1.0D, 1.0D, 1.0D);

        // RaycastContextのパッケージを修正
        RaycastContext raycastContext = new RaycastContext(
            eyePos, endPos, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, user
        );
        EntityHitResult hit = net.minecraft.util.hit.RaycastUtil.raycast(user, eyePos, endPos, box, (entity) -> entity instanceof LivingEntity, 25);

        if (hit != null && hit.getType() == HitResult.Type.ENTITY) {
            if (hit.getEntity() instanceof LivingEntity target) {
                target.damage(user.getDamageSources().player(user), 23.0F);
                target.timeUntilRegen = 0;
                user.sendMessage(Text.literal("敵に命中しました！"), true);
                stack.damage(1, user, (p) -> p.sendToolBreakStatus(hand));
            }
        } else {
            user.sendMessage(Text.literal("敵が範囲内にいません。"), true);
        }

        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.PLAYERS, 1.0F, 1.0F);

        return ActionResult.SUCCESS;
    }
}
