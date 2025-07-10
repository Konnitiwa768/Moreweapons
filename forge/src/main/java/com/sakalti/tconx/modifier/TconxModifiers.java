package com.sakalti.tconx.modifier;

import com.sakalti.tconx.modifier.GlacialBindModifier;
import com.sakalti.tconx.modifier.LongsparkModifier;
import com.sakalti.tconx.modifier.SuperMagnetModifier;
import com.sakalti.tconx.modifier.IceBindModifier;
import com.sakalti.tconx.modifier.LightTouchModifier;
import com.sakalti.tconx.modifier.FireWallModifier;
import com.sakalti.tconx.modifier.LaserPrismModifier;
import com.sakalti.tconx.modifier.PoisonousBindModifier;
import com.sakalti.tconx.modifier.LifestealModifier;
import slimeknights.tconstruct.library.modifiers.Modifier;

public class TconxModifiers {

    public static final Modifier GLACIAL_BIND = new GlacialBindModifier();
    public static final Modifier SUPERMAGNET = new SuperMagnetModifier();
    public static final Modifier ICE_BIND = new IceBindModifier();
    public static final Modifier LIGHT_TOUCH = new LightTouchModifier();
    public static final Modifier FIRE_WALL = new FireWallModifier();
    public static final Modifier LIFESTEAL = new LifestealModifier();
    public static final Modifier LONG_SPARK = new LongsparkModifier();
    public static final Modifier LASER_PRISM = new LaserPrismModifier();
    public static final Modifier POISONOUS_BIND = new PoisonousBindModifier();

    public static void init() {
        // 何も登録しない、自前インスタンス保持のみ
    }
}
