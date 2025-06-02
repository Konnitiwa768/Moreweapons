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
            armorValue = target.getArmor(); // もしgetArmor()が使えなければ、0固定でもOK
        }

        if (armorValue >= 3) {
            target.damage(DamageSource.player(attacker), 7.0F); // 防具が3以上の敵にはダイヤの剣のダメージ
        } else {
            target.damage(DamageSource.player(attacker), 8.5F); // 通常時のダメージ
        }
        // 剣としての耐久値を消費
        stack.damage(1, attacker, (e) -> e.sendToolBreakStatus(attacker.getActiveHand()));
        return super.postHit(stack, target, attacker);
    }
}
