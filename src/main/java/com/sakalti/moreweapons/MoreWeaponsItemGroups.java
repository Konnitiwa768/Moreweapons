package com.sakalti.moreweapons;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupEntries;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class MoreWeaponsItemGroups implements ClientModInitializer {

    public static final ItemGroup COMBAT_GROUP = FabricItemGroupBuilder.create(
        new Identifier(MoreWeaponsMod.MODID, "combat"))
        .icon(() -> new ItemStack(Items.DIAMOND_SWORD)) // タブのアイコン
        .build();

    @Override
    public void onInitializeClient() {
        ItemGroupEvents.modifyEntriesEvent(COMBAT_GROUP).register(this::addItems);
    }

    private void addItems(FabricItemGroupEntries entries) {
        // entries.add(MoreWeaponsItems.PRECIOUS_BLADE);
        entries.add(MoreWeaponsItems.EMPEROR_BLADE);
        entries.add(MoreWeaponsItems.LEGENDARY_GREATSWORD);
        entries.add(MoreWeaponsItems.CORRUPTED_BLADE);
        entries.add(MoreWeaponsItems.ARMOR_DESTROYER);
        entries.add(MoreWeaponsItems.LASER_BLADE);
        entries.add(MoreWeaponsItems.CORAL_GREATSWORD);
        entries.add(MoreWeaponsItems.BENT_DIAMOND_BLADE);
        entries.add(MoreWeaponsItems.CHORUS_BLADE);
        entries.add(MoreWeaponsItems.DEEPSLATE_SWORD);
        entries.add(MoreWeaponsItems.SHULKER_GREATSWORD);
        entries.add(MoreWeaponsItems.BLOOD_AGATE);
        entries.add(MoreWeaponsItems.DIAMOND_QUARTERSTAFF);

        // 一部コメントアウト中のアイテムを表示リストに追加
        // entries.add(MoreWeaponsItems.FLOWER_BLADE);
        // entries.add(MoreWeaponsItems.ADVENTURER_GREATSWORD);
        // entries.add(MoreWeaponsItems.IRON_BOW);
        // entries.add(MoreWeaponsItems.MATCHLOCK);
        // entries.add(MoreWeaponsItems.NORMAL_RIFLE);
        // entries.add(MoreWeaponsItems.UNDER_BLADE);
    }
}
