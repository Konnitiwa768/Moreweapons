package com.sakalti.moreweapons.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;

public class FlowerRapierItem extends SwordItem {
    public FlowerRapierItem(Settings settings) {
        super(new ToolMaterial() {
            @Override public int getDurability() { return 1000; }
            @Override public float getMiningSpeedMultiplier() { return 4.0F; }
            @Override public float getAttackDamage() { return 3.5F; }
            @Override public int getMiningLevel() { return 2; }
            @Override public int getEnchantability() { return 16; }
            @Override public Ingredient getRepairIngredient() { return Ingredient.ofItems(Items.FLOWER_POT); }
        }, 0, -1.8F, settings); // 攻撃速度 = 4 - 1.8 = 2.4
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!target.getWorld().isClient()) {
            double armor = target.getAttributeValue(EntityAttributes.GENERIC_ARMOR);
            if (armor >= 10.0) {
                // 防御力が10以上の敵に追加ダメージ
                target.damage(DamageSource.player(attacker), 4.0F); // 任意の追加ダメージ値
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
