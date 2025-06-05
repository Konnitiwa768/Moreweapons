package com.sakalti.sakaplus.ore;

import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class SakaPlusOreBlocks {
    public static final Block ORANGE_CRYSTAL_ORE = register("orange_crystal_ore");
    public static final Block BEIGE_CRYSTAL_ORE = register("beige_crystal_ore");
    public static final Block CYAN_CRYSTAL_ORE = register("cyan_crystal_ore");
    public static final Block PURPLE_CRYSTAL_ORE = register("purple_crystal_ore");
    public static final Block BLACK_CRYSTAL_ORE = register("black_crystal_ore");

    private static Block register(String name) {
        return Registry.register(Registry.BLOCK, new Identifier("sakaplus", name),
            new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.DIAMOND_ORE), UniformIntProvider.create(2, 5)));
    }

    public static void init() {
        // 呼び出し用メソッド（空でもOK）
    }
}