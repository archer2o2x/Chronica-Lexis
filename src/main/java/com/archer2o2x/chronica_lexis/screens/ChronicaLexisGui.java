package com.archer2o2x.chronica_lexis.screens;

import com.archer2o2x.chronica_lexis.items.ChronicaLexisItem;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.fml.earlydisplay.ColourScheme;

public class ChronicaLexisGui {

    public static final IGuiOverlay OVERLAY = (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {

        Player player = Minecraft.getInstance().player;
        if (player == null) return;
        ItemStack stack = ChronicaLexisItem.getChronicaLexis(player);
        if (stack == null) return;

        int x = 50;
        int y = screenHeight / 2;

        guiGraphics.drawString(gui.getFont(), Component.literal("Success!"), x, y, color(255, 255, 255, 255), false);

    };

    public static int color(int red, int green, int blue, int alpha) {
        return new ColourScheme.Colour(red, green, blue).packedint(alpha);
    }

}
