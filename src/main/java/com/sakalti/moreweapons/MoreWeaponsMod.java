package com.sakalti.moreweapons;

import com.sakalti.moreweapons.items.*; // 自作アイテムクラス群をimport
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial; // ツール素材の定義（DIAMONDなど）
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MoreWeaponsMod implements ModInitializer {

    // アイテムグループ(COMBAT)の定義。インベントリのタブのようなもの
    public static final ItemGroup COMBAT = FabricItemGroupBuilder.create(
            new Identifier("moreweapons", "combat")) // modid = moreweapons, タブID = combat
        .icon(() -> new ItemStack(Items.DIAMOND_SWORD)) // タブのアイコンはダイヤモンドソード
        .build();

    // アイテム登録用に名前とItemオブジェクトをまとめるクラス
    private static class ItemRegisterInfo {
        String name; // アイテムID名
        Item item;   // 登録するItemオブジェクト

        ItemRegisterInfo(String name, Item item) {
            this.name = name;
            this.item = item;
        }
    }

    @Override
    public void onInitialize() {
        // 登録するアイテムの配列。コメントアウトで不要なアイテムは除外可能。
        ItemRegisterInfo[] items = new ItemRegisterInfo[] {
            new ItemRegisterInfo("flower_blade", new FlowerBladeItem(new Item.Settings().group(COMBAT))),
            // new ItemRegisterInfo("adventurer_greatsword", new AdventurerGreatswordItem()), // 引数なしに変更した場合はクラス修正必要
            new ItemRegisterInfo("precious_blade", new PreciousBladeItem()), // 引数なしコンストラクタ
            new ItemRegisterInfo("emperor_blade", new EmperorBladeItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("legendary_greatsword", new LegendaryGreatSwordItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("corrupted_blade", new CorruptedBladeItem(new Item.Settings().group(COMBAT))),
            // new ItemRegisterInfo("iron_bow", new IronBowItem(new Item.Settings().group(COMBAT))),
            // new ItemRegisterInfo("matchlock", new MatchlockItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("armor_destroyer", new ArmorDestroyerItem(new Item.Settings().group(COMBAT))),
            // new ItemRegisterInfo("laser_blade", new LaserBladeItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("under_blade", new UnderBladeItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("coral_greatsword", new CoralGreatswordItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("bent_diamond_blade", new BentDiamondBladeItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("chorus_blade", new ChorusBladeItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("deepslate_sword", new DeepslateSwordItem(new Item.Settings().group(COMBAT))),
            // new ItemRegisterInfo("normal_rifle", new NormalRifleItem(new Item.Settings().group(COMBAT))),
            new ItemRegisterInfo("shulker_great_sword", new ShulkerGreatSwordItem(new Item.Settings().group(COMBAT))),
            // ToolMaterials.DIAMONDは標準ツール素材。攻撃力3、攻撃速度6.0Fなどのカスタム値を渡す例
            // new ItemRegisterInfo("blood_agate", new BloodAgateItem(ToolMaterials.DIAMOND, 3, 6.0F, new Item.Settings().group(COMBAT))),
            // new ItemRegisterInfo("diamond_quarterstaff", new DiamondQuarterStaffItem(new Item.Settings().group(COMBAT))),
        };

        // アイテム登録ループ
        for (ItemRegisterInfo info : items) {
            try {
                Registry.register(
                    Registry.ITEM,
                    new Identifier("moreweapons", info.name), // modid: アイテム名
                    info.item
                );
                System.out.println("アイテム " + info.name + " の登録に成功しました。");
            } catch (Exception e) {
                System.err.println("アイテム " + info.name + " の登録に失敗しました: " + e.getMessage());
            }
        }
    }
}
