package com.sakalti.tconx;

import net.minecraftforge.fml.common.Mod;
import com.sakalti.tconx.modifier.TconxModifiers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import com.sakalti.tconx.material.hachilite.ModMaterials;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.Modifier;
import com.sakalti.tconx.modifier.GlacialBindModifier;

@Mod("tconx")
public class ModMain {
    
    public ModMain() {
        TconxModifiers.init();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModMaterials.registerMaterials();
        ModMetals.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
