package com.sakalti.moreweapons;

import com.sakalti.moreweapons.items.*;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MoreWeaponsMod implements ModInitializer {

    // アイテム情報をまとめるための内部クラス
    private static class ItemRegisterInfo {
        String name;
        Item item;

        ItemRegisterInfo(String name, Item item) {
            this.name = name;
            this.item = item;
        }
    }

    @Override
    public void onInitialize() {
        // 配列で一括管理
        ItemRegisterInfo[] items = new ItemRegisterInfo[] {
            new ItemRegisterInfo("flower_blade", new FlowerBladeItem()),
            new ItemRegisterInfo("adventurer_greatsword", new AdventurerGreatswordItem()),
            new ItemRegisterInfo("precious_blade", new PreciousBladeItem()),
            new ItemRegisterInfo("emperor_blade", new EmperorBladeItem()),
            new ItemRegisterInfo("legendary_greatsword", new LegendaryGreatSwordItem()),
            new ItemRegisterInfo("corrupted_blade", new CorruptedBladeItem()),
            new ItemRegisterInfo("iron_bow", new IronBowItem()),
            new ItemRegisterInfo("matchlock", new MatchlockItem()),
            new ItemRegisterInfo("armor_destroyer", new ArmorDestroyerItem()),
            new ItemRegisterInfo("laser_blade", new LaserBladeItem()),
            new ItemRegisterInfo("under_blade", new UnderBladeItem()),
            new ItemRegisterInfo("coral_greatsword", new CoralGreatswordItem()),
            new ItemRegisterInfo("bent_diamond_blade", new BentDiamondBladeItem()),
            new ItemRegisterInfo("chorus_blade", new ChorusBladeItem()),
            new ItemRegisterInfo("deepslate_sword", new DeepslateSwordItem()),
            new ItemRegisterInfo("normal_rifle", new NormalRifleItem()),
            new ItemRegisterInfo("shulker_great_sword", new ShulkerGreatSwordItem()),
            new ItemRegisterInfo("blood_agate", new BloodAgateItem(ToolMaterials.DIAMOND, 3, 6.0F, new Item.Settings())),
            new ItemRegisterInfo("diamond_quarterstaff", new DiamondQuarterStaffItem(ToolMaterials.DIAMOND, 3, 2.0F, new Item.Settings()))
        };

        // 配列の要素をループで登録
        for (ItemRegisterInfo info : items) {
            Registry.register(
                Registries.ITEM,
                new Identifier("moreweapons", info.name),
                info.item
            );
        }
    }
}
