package com.sakalti.moreweapons;

import com.sakalti.moreweapons.items.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class MoreWeaponsMod implements ModInitializer {

    public static final String MOD_ID = "moreweapons";

    // アイテムの静的フィールド宣言
    public static final Item FLOWER_BLADE = new FlowerBladeItem(new Item.Settings());
    public static final Item ADVENTURER_GREATSWORD = new AdventurerGreatswordItem(new Item.Settings());
    public static final Item PRECIOUS_BLADE = new PreciousBladeItem(new Item.Settings());
    public static final Item EMPEROR_BLADE = new EmperorBladeItem(new Item.Settings());
    public static final Item LEGENDARY_GREATSWORD = new LegendaryGreatSwordItem(new Item.Settings());
    public static final Item CORRUPTED_BLADE = new CorruptedBladeItem(new Item.Settings());
    public static final Item IRON_BOW = new IronBowItem(new Item.Settings());
    public static final Item MATCHLOCK = new MatchlockItem(new Item.Settings());
    public static final Item ARMOR_DESTROYER = new ArmorDestroyerItem(new Item.Settings());
    public static final Item LASER_BLADE = new LaserBladeItem(new Item.Settings());
    public static final Item UNDER_BLADE = new UnderBladeItem(new Item.Settings());
    public static final Item CORAL_GREATSWORD = new CoralGreatswordItem(new Item.Settings());
    public static final Item BENT_DIAMOND_BLADE = new BentDiamondBladeItem(new Item.Settings());
    public static final Item CHORUS_BLADE = new ChorusBladeItem(new Item.Settings());
    public static final Item DEEPSLATE_SWORD = new DeepslateSwordItem(new Item.Settings());
    public static final Item NORMAL_RIFLE = new NormalRifleItem(new Item.Settings());
    public static final Item SHULKER_GREAT_SWORD = new ShulkerGreatSwordItem(new Item.Settings());
    public static final Item BLOOD_AGATE = new BloodAgateItem(ToolMaterial.DIAMOND, 3, 6.0F, new Item.Settings());
    public static final Item DIAMOND_QUARTERSTAFF = new DiamondQuarterStaffItem(ToolMaterial.DIAMOND, 3, 2.0F, new Item.Settings());

    // カスタムアイテムグループ登録
    public static final ItemGroup COMBAT_GROUP = Registry.register(
        Registries.ITEM_GROUP,
        new Identifier(MOD_ID, "combat"),
        FabricItemGroup.builder()
            .displayName(Text.translatable("itemGroup.moreweapons.combat"))
            .icon(() -> new ItemStack(Items.DIAMOND_SWORD))
            .entries((context, entries) -> {
                entries.add(FLOWER_BLADE);
                entries.add(ADVENTURER_GREATSWORD);
                entries.add(PRECIOUS_BLADE);
                entries.add(EMPEROR_BLADE);
                entries.add(LEGENDARY_GREATSWORD);
                entries.add(CORRUPTED_BLADE);
                entries.add(IRON_BOW);
                entries.add(MATCHLOCK);
                entries.add(ARMOR_DESTROYER);
                entries.add(LASER_BLADE);
                entries.add(UNDER_BLADE);
                entries.add(CORAL_GREATSWORD);
                entries.add(BENT_DIAMOND_BLADE);
                entries.add(CHORUS_BLADE);
                entries.add(DEEPSLATE_SWORD);
                entries.add(NORMAL_RIFLE);
                entries.add(SHULKER_GREAT_SWORD);
                entries.add(BLOOD_AGATE);
                entries.add(DIAMOND_QUARTERSTAFF);
            })
            .build()
    );

    @Override
    public void onInitialize() {
        register("flower_blade", FLOWER_BLADE);
        register("adventurer_greatsword", ADVENTURER_GREATSWORD);
        register("precious_blade", PRECIOUS_BLADE);
        register("emperor_blade", EMPEROR_BLADE);
        register("legendary_greatsword", LEGENDARY_GREATSWORD);
        register("corrupted_blade", CORRUPTED_BLADE);
        register("iron_bow", IRON_BOW);
        register("matchlock", MATCHLOCK);
        register("armor_destroyer", ARMOR_DESTROYER);
        register("laser_blade", LASER_BLADE);
        register("under_blade", UNDER_BLADE);
        register("coral_greatsword", CORAL_GREATSWORD);
        register("bent_diamond_blade", BENT_DIAMOND_BLADE);
        register("chorus_blade", CHORUS_BLADE);
        register("deepslate_sword", DEEPSLATE_SWORD);
        register("normal_rifle", NORMAL_RIFLE);
        register("shulker_great_sword", SHULKER_GREAT_SWORD);
        register("blood_agate", BLOOD_AGATE);
        register("diamond_quarterstaff", DIAMOND_QUARTERSTAFF);

        System.out.println("MoreWeapons Mod initialized.");
    }

    private static void register(String name, Item item) {
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, name), item);
    }
}
