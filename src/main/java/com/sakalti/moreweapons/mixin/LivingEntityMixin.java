package com.sakalti.moreweapons.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Shadow public int hurtTime;
    @Shadow public int maxHurtTime;

    @Shadow public int hurtResistantTime;

    @Inject(method = "tick", at = @At("HEAD"))
    private void removeInvulnerability(CallbackInfo ci) {
        this.hurtResistantTime = 0;
    }
}
