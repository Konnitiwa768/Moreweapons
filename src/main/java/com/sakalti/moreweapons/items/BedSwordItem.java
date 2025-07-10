package com.sakalti.moreweapons.items;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BedSwordItem extends SwordItem {

    public BedSwordItem(Settings settings) {
        super(new ToolMaterial() {
            @Override public int getDurability() { return 250; }
            @Override public float getMiningSpeedMultiplier() { return 1.0F; }
            @Override public float getAttackDamage() { return 6.0F; }
            @Override public int getMiningLevel() { return 2; }
            @Override public int getEnchantability() { return 10; }
            @Override public Ingredient getRepairIngredient() { return Ingredient.ofItems(Items.WHITE_BED); }
        }, 0, -2.4F, settings.group(ItemGroup.COMBAT)); // 攻撃速度 = 4 - 2.4 = 1.6
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack armor = target.getEquippedStack(slot);
            if (!armor.isEmpty() && armor.isDamageable()) {
                armor.damage(6, target, (e) -> e.sendEquipmentBreakStatus(slot));
            }
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, net.minecraft.entity.Entity entity, Hand hand) {
        return super.useOnEntity(stack, entity, hand);
    }

    @Override
    public ActionResult use(World world, net.minecraft.entity.player.PlayerEntity user, Hand hand) {
        if (!world.isClient && user instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) user;
            BlockPos pos = player.getBlockPos();
            player.setSpawnPoint(world.getRegistryKey(), pos, 0.0F, true, false);
            player.sendMessage(Text.literal("リスポーン地点を設定しました: " + pos.toShortString()), true);
            player.playSound(SoundEvents.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, 1.0F, 1.0F);
        }
        return ActionResult.SUCCESS;
    }
}
