package com.sakalti.moreweapons;

import com.sakalti.moreweapons.items.FlowerBladeItem;
import com.sakalti.moreweapons.items.UnderBladeItem;
import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MoreWeaponsMod implements ModInitializer {
    @Override
    public void onInitialize() {
        // フラワーブレードとアンダーブレードのアイテム登録
        Registry.register(Registries.ITEM, new Identifier("moreweapons", "flower_blade"), new FlowerBladeItem());
        Registry.register(Registries.ITEM, new Identifier("moreweapons", "under_blade"), new UnderBladeItem());
    }
}
