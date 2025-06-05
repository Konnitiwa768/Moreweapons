package com.sakalti.sakaplus.ore;

import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SakaPlusOreBlockItems {
    public static void init() {
        registerBlockItem("orange_crystal_ore", SakaPlusOreBlocks.ORANGE_CRYSTAL_ORE);
        registerBlockItem("beige_crystal_ore", SakaPlusOreBlocks.BEIGE_CRYSTAL_ORE);
        registerBlockItem("cyan_crystal_ore", SakaPlusOreBlocks.CYAN_CRYSTAL_ORE);
        registerBlockItem("purple_crystal_ore", SakaPlusOreBlocks.PURPLE_CRYSTAL_ORE);
        registerBlockItem("black_crystal_ore", SakaPlusOreBlocks.BLACK_CRYSTAL_ORE);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registry.ITEM, new Identifier("sakaplus", name),
            new BlockItem(block, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    }
}