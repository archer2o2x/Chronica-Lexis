package com.archer2o2x.chronica_lexis.utils;

import com.archer2o2x.chronica_lexis.ChronicaLexisMod;
import com.ibm.icu.impl.StaticUnicodeSets;
import com.mojang.blaze3d.platform.InputConstants;
import cpw.mods.modlauncher.api.IEnvironment;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;

public class ModKeybinds {

    // Tries to activate the current scripture in the tome.
    public static final Lazy<KeyMapping> ACTIVATE = register("activate", GLFW.GLFW_KEY_X);
    // Brings up a quick select menu for choosing a specific scripture in the tome.
    public static final Lazy<KeyMapping> QUICK_SELECT = register("quick_select", GLFW.GLFW_KEY_Z);

    // Select the next scripture in the tome. Unbound by default.
    public static final Lazy<KeyMapping> NEXT = register("next");
    // Select the previous scripture in the tome. Unbound by default.
    public static final Lazy<KeyMapping> PREVIOUS = register("previous");

    public enum Actions {
        ACTIVATE(1),
        QUICK_SELECT(2),
        NEXT(3),
        PREVIOUS(4);

        public final int value;
        Actions(int value) { this.value = value; }
    }

    public static Lazy<KeyMapping> register(String name) {
        return Lazy.of(() -> new KeyMapping(
                "key." + ChronicaLexisMod.MODID + "." + name,
                KeyConflictContext.IN_GAME,
                InputConstants.UNKNOWN,
                "key.categories." + ChronicaLexisMod.MODID + ".main"
        ));
    }

    public static Lazy<KeyMapping> register(String name, int key) {
        return Lazy.of(() -> new KeyMapping(
                "key." + ChronicaLexisMod.MODID + "." + name,
                KeyConflictContext.IN_GAME,
                InputConstants.Type.KEYSYM,
                key,
                "key.categories." + ChronicaLexisMod.MODID + ".main"
        ));
    }

}
