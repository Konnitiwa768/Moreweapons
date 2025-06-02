package com.sakalti.moreweapons;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.item.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MoreWeaponsMod implements ModInitializer {
    public static final String MODID = "moreweapons";
    public static final Logger LOGGER = LogManager.getLogger("MoreWeapons");

    @Override
    public void onInitialize() {
        register("precious_blade", MoreWeaponsItems.PRECIOUS_BLADE);
        register("emperor_blade", MoreWeaponsItems.EMPEROR_BLADE);
        register("legendary_greatsword", MoreWeaponsItems.LEGENDARY_GREATSWORD);
        register("corrupted_blade", MoreWeaponsItems.CORRUPTED_BLADE);
        register("armor_destroyer", MoreWeaponsItems.ARMOR_DESTROYER);
        register("laser_blade", MoreWeaponsItems.LASER_BLADE);
        register("coral_greatsword", MoreWeaponsItems.CORAL_GREATSWORD);
        register("bent_diamond_blade", MoreWeaponsItems.BENT_DIAMOND_BLADE);
        register("chorus_blade", MoreWeaponsItems.CHORUS_BLADE);
        register("deepslate_sword", MoreWeaponsItems.DEEPSLATE_SWORD);
        register("shulker_great_sword", MoreWeaponsItems.SHULKER_GREATSWORD);
        register("blood_agate", MoreWeaponsItems.BLOOD_AGATE);
        register("diamond_quarterstaff", MoreWeaponsItems.DIAMOND_QUARTERSTAFF);

        // コメントアウト中のアイテムたち
        // register("flower_blade", MoreWeaponsItems.FLOWER_BLADE);
        // register("adventurer_greatsword", MoreWeaponsItems.ADVENTURER_GREATSWORD);
        // register("iron_bow", MoreWeaponsItems.IRON_BOW);
        // register("matchlock", MoreWeaponsItems.MATCHLOCK);
        // register("normal_rifle", MoreWeaponsItems.NORMAL_RIFLE);
        // register("under_blade", MoreWeaponsItems.UNDER_BLADE);

        LOGGER.info("MoreWeaponsMod の初期化完了");
    }

    private void register(String name, Item item) {
        Registry.register(Registry.ITEM, new Identifier(MODID, name), item);
    }
}
