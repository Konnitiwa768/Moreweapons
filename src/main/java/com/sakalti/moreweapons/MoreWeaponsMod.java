package com.sakalti.moreweapons;

import com.sakalti.moreweapons.items.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;

public class MoreWeaponsMod implements ModInitializer {

    public static final ItemGroup COMBAT = FabricItemGroupBuilder.create(
            new Identifier("moreweapons", "combat"))
        .icon(() -> new ItemStack(Items.DIAMOND_SWORD))
        .build();

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
        ItemRegisterInfo[] items = new ItemRegisterInfo[] {
            // new ItemRegisterInfo("flower_blade", new FlowerBladeItem(new Item.Settings().group(COMBAT))),
            // new ItemRegisterInfo("adventurer_greatsword", new AdventurerGreatswordItem()), // 引数なしに変更（要クラス修正）
            new ItemRegisterInfo("precious_blade", new PreciousBladeItem()), // 引数なしに変更（要クラス修正）
            new ItemRegisterInfo("emperor_blade", new EmperorBladeItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("legendary_greatsword", new LegendaryGreatSwordItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("corrupted_blade", new CorruptedBladeItem(new Item.Settings().group(COMBAT))),
            // new ItemRegisterInfo("iron_bow", new IronBowItem(new Item.Settings().group(COMBAT))),
            // new ItemRegisterInfo("matchlock", new MatchlockItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("armor_destroyer", new ArmorDestroyerItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("laser_blade", new LaserBladeItem(new Item.Settings().group(COMBAT))),
            // new ItemRegisterInfo("under_blade", new UnderBladeItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("coral_greatsword", new CoralGreatswordItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("bent_diamond_blade", new BentDiamondBladeItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("chorus_blade", new ChorusBladeItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("deepslate_sword", new DeepslateSwordItem(new Item.Settings().group(COMBAT))),
            // new ItemRegisterInfo("normal_rifle", new NormalRifleItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("shulker_great_sword", new ShulkerGreatSwordItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("blood_agate", new BloodAgateItem(ToolMaterials.DIAMOND, 3, 6.0F, new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("diamond_quarterstaff", new DiamondQuarterStaffItem(ToolMaterials.DIAMOND, 3, 2.0F, new Item.Settings().group(COMBAT)))
        };

        for (ItemRegisterInfo info : items) {
            try {
                Registry.register(
                    Registry.ITEM,
                    new Identifier("moreweapons", info.name),
                    info.item
                );
                System.out.println("アイテム " + info.name + " の登録に成功しました。");
            } catch (Exception e) {
                System.err.println("アイテム " + info.name + " の登録に失敗しました: " + e.getMessage());
            }
        }
    }
}
