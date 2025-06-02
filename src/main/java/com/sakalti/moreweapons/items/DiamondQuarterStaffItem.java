package com.sakalti.moreweapons.items;

import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.damage.DamageSource;

public class DiamondQuarterStaffItem extends SwordItem {

    public DiamondQuarterStaffItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int armorValue = 0;
        if (target instanceof PlayerEntity) {
            armorValue = ((PlayerEntity) target).getArmor();
        } else {
            // プレイヤー以外の場合、armor値が必要なら独自実装か、0として扱う
            armorValue = 0; // getArmor()がない場合は0に
        }

        // attackerがPlayerEntityならDamageSource.playerを使う
        if (attacker instanceof PlayerEntity playerAttacker) {
            if (armorValue >= 3) {
                target.damage(DamageSource.player(playerAttacker), 7.0F);
            } else {
                target.damage(DamageSource.player(playerAttacker), 8.5F);
            }
        } else {
            // それ以外は通常の汎用攻撃
            if (armorValue >= 3) {
                target.damage(DamageSource.GENERIC, 7.0F);
            } else {
                target.damage(DamageSource.GENERIC, 8.5F);
            }
        }

        stack.damage(1, attacker, (e) -> e.sendToolBreakStatus(attacker.getActiveHand()));
        return super.postHit(stack, target, attacker);
    }
}
