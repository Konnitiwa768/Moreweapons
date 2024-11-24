package com.sakalti.moreweapons.items;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Items;

public class UnderBladeItem extends SwordItem {
    public UnderBladeItem() {
        super(new ToolMaterial() {
            @Override
            public int getDurability() {
                return 1667; // 耐久1667
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return 1.0F;
            }

            @Override
            public float getAttackDamage() {
                return 7.0F; // 攻撃力7
            }

            @Override
            public int getMiningLevel() {
                return 3;
            }

            @Override
            public int getEnchantability() {
                return 20;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.ofItems(Items.OBSIDIAN); // 修理アイテム
            }
        }, 0, 2.0F, new Settings().group(ItemGroup.COMBAT));
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }
}
