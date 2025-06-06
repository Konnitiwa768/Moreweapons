package com.sakalti.moreweapons.items;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Items;

public class DeepslateSwordItem extends SwordItem {
    public DeepslateSwordItem(Settings settings) {
        super(new ToolMaterial() {
            @Override public int getDurability() { return 432; }
            @Override public float getMiningSpeedMultiplier() { return 1.5F; }
            @Override public float getAttackDamage() { return 5.0F; }
            @Override public int getMiningLevel() { return 3; }
            @Override public int getEnchantability() { return 15; }
            @Override public Ingredient getRepairIngredient() { return Ingredient.ofItems(Items.DEEPSLATE); }
        }, 0, 1.8F, settings.group(ItemGroup.COMBAT));
    }
}
