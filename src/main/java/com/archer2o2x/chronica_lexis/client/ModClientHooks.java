package com.archer2o2x.chronica_lexis.client;

import com.archer2o2x.chronica_lexis.screens.TheoricaTemporalisBookScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Item;

public class ModClientHooks {

    public static void openTomeScreen(Item item) {
        Minecraft.getInstance().setScreen(new TheoricaTemporalisBookScreen());
    }

}
