package com.sakalti.moreweapons.items;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class ShulkerGreatSwordItem extends Item {

    public ShulkerGreatSwordItem() {
        super(new Settings().group(ItemGroup.COMBAT).maxDamage(1502)); // 耐久値1502
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true; // エンチャント可能
    }

    @Override
    public float getAttackDamage() {
        return 19.5F; // ダメージ19.5
    }

    @Override
    public double getAttackSpeed() {
        return 0.76; // 攻撃速度0.76
    }

    @Override
    public int getEnchantability() {
        return 10; // エンチャント可能度を10に設定
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, net.minecraft.util.Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            // ダメージ増加エンチャントを変更: 常に 1 + (1 * レベル)
            int sharpnessLevel = stack.getEnchantmentLevel(Enchantments.SHARPNESS);
            if (sharpnessLevel > 0) {
                float finalDamage = getAttackDamage() + (1 + (sharpnessLevel * 1)); // ダメージ増加式
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, ItemStack player) {
        tooltip.add(Text.literal("特性: ダメージ増加エンチャント効果を常に 1 + (1 * レベル) に変更"));
        super.appendTooltip(stack, world, tooltip, player);
    }
}
