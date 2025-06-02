package com.sakalti.moreweapons;

import com.sakalti.moreweapons.items.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroup; // 1.19.2での正しいimport
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry; // 1.19.2ではこちら

import net.minecraft.text.Text;

public class MoreWeaponsMod implements ModInitializer {

    public static final ItemGroup COMBAT = FabricItemGroup.builder()
            .icon(() -> new ItemStack(Items.DIAMOND_SWORD))
            .displayName(Text.literal("moreweapons.combat"))
            .build();

    @Override
    public void onInitialize() {
        register("flower_blade", new FlowerBladeItem(new Item.Settings().group(COMBAT)));
        register("adventurer_greatsword", new AdventurerGreatswordItem(new Item.Settings().group(COMBAT)));
        register("precious_blade", new PreciousBladeItem(new Item.Settings().group(COMBAT)));
        register("emperor_blade", new EmperorBladeItem(new Item.Settings().group(COMBAT)));
        register("legendary_greatsword", new LegendaryGreatSwordItem(new Item.Settings().group(COMBAT)));
        register("corrupted_blade", new CorruptedBladeItem(new Item.Settings().group(COMBAT)));
        register("iron_bow", new IronBowItem(new Item.Settings().group(COMBAT)));
        register("matchlock", new MatchlockItem(new Item.Settings().group(COMBAT)));
        register("armor_destroyer", new ArmorDestroyerItem(new Item.Settings().group(COMBAT)));
        register("laser_blade", new LaserBladeItem(new Item.Settings().group(COMBAT)));
        register("under_blade", new UnderBladeItem(new Item.Settings().group(COMBAT)));
        register("coral_greatsword", new CoralGreatswordItem(new Item.Settings().group(COMBAT)));
        register("bent_diamond_blade", new BentDiamondBladeItem(new Item.Settings().group(COMBAT)));
        register("chorus_blade", new ChorusBladeItem(new Item.Settings().group(COMBAT)));
        register("deepslate_sword", new DeepslateSwordItem(new Item.Settings().group(COMBAT)));
        register("normal_rifle", new NormalRifleItem(new Item.Settings().group(COMBAT)));
        register("shulker_great_sword", new ShulkerGreatSwordItem(new Item.Settings().group(COMBAT)));
        register("blood_agate", new BloodAgateItem(ToolMaterials.DIAMOND, 3, 6.0F, new Item.Settings().group(COMBAT)));
        register("diamond_quarterstaff", new DiamondQuarterStaffItem(ToolMaterials.DIAMOND, 3, 2.0F, new Item.Settings().group(COMBAT)));
    }

    private void register(String name, Item item) {
        Registry.register(Registry.ITEM, new Identifier("moreweapons", name), item);
        System.out.println("Registered item: " + name);
    }
}
