package com.sakalti.moreweapons;

import com.sakalti.moreweapons.items.*;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;

public class MoreWeaponsItems {
    // public static final Item PRECIOUS_BLADE = new PreciousBladeItem();
    public static final Item EMPEROR_BLADE = new EmperorBladeItem();
    public static final Item LEGENDARY_GREATSWORD = new LegendaryGreatSwordItem();
    public static final Item CORRUPTED_BLADE = new CorruptedBladeItem();
    public static final Item ARMOR_DESTROYER = new ArmorDestroyerItem();
    public static final Item LASER_BLADE = new LaserBladeItem();
    public static final Item CORAL_GREATSWORD = new CoralGreatswordItem();
    public static final Item BENT_DIAMOND_BLADE = new BentDiamondBladeItem();
    public static final Item CHORUS_BLADE = new ChorusBladeItem();
    public static final Item DEEPSLATE_SWORD = new DeepslateSwordItem();
    public static final Item SHULKER_GREATSWORD = new ShulkerGreatSwordItem();
    public static final Item BLOOD_AGATE = new BloodAgateItem(ToolMaterials.DIAMOND, 3, 6.0F, new Item.Settings());
    public static final Item DIAMOND_QUARTERSTAFF = new DiamondQuarterStaffItem(ToolMaterials.DIAMOND, 3, 2.0F, new Item.Settings());

    // コメントアウト中のアイテム（必要に応じて復活可）
    // public static final Item FLOWER_BLADE = new FlowerBladeItem(new Item.Settings());
    // public static final Item ADVENTURER_GREATSWORD = new AdventurerGreatswordItem();

    // public static final Item IRON_BOW = new IronBowItem(new Item.Settings());
    // public static final Item MATCHLOCK = new MatchlockItem(new Item.Settings());
    // public static final Item NORMAL_RIFLE = new NormalRifleItem(new Item.Settings());
    // public static final Item UNDER_BLADE = new UnderBladeItem(new Item.Settings());
}
