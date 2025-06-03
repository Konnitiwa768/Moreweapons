package com.sakalti.moreweapons.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Items;

public class LapisTridentItem extends SwordItem {
    public LapisTridentItem(Settings settings) {
        super(new ToolMaterial() {
            @Override public int getDurability() { return 500; }
            @Override public float getMiningSpeedMultiplier() { return 1.0F; }
            @Override public float getAttackDamage() { return 8.0F; }
            @Override public int getMiningLevel() { return 2; }
            @Override public int getEnchantability() { return 15; }
            @Override public Ingredient getRepairIngredient() {
                return Ingredient.ofItems(Items.LAPIS_LAZULI);
            }
        }, 3, -1.8F + 1.2F, settings); // base 4 + attack damage 8 - 3 = 攻撃速度調整 (この辺り調整可)
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.getWorld().isClient() && attacker instanceof PlayerEntity player) {
            // 経験値を与える
            player.giveExperiencePoints(3); // 3XP付与（調整可）
        }
        return super.postHit(stack, target, attacker);
    }
}
