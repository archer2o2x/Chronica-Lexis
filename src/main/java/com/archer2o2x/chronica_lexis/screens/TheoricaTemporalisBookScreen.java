package com.archer2o2x.chronica_lexis.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class TheoricaTemporalisBookScreen extends Screen {

    private static final Component TITLE = Component.literal("Cool Title Test");

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath("chronica_lexis", "textures/gui/tome_back.png");

    private final int imageWidth, imageHeight;
    private int leftPos, topPos;

    private Button prev, next;

    public TheoricaTemporalisBookScreen() {
        super(TITLE);
        this.imageWidth = 160;
        this.imageHeight = 196;
    }

    @Override
    public void render(@NotNull GuiGraphics gui, int mouse_x, int mouse_y, float partialTicks) {
        renderBackground(gui);
        gui.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        int titleHalf = this.font.width("Theorica Temporalis") / 2;
        gui.drawString(this.font, "Theorica Temporalis", this.leftPos + 80 - titleHalf, this.topPos + 10, 0, false);
        gui.hLine(this.leftPos + 15, this.leftPos + 145, this.topPos + 20, 0xFF000000);
        super.render(gui, mouse_x, mouse_y, partialTicks);
    }

    @Override
    protected void init() {
        super.init();

        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;

        if (this.minecraft == null) return;

        Level level = this.minecraft.level;

        if (level == null) return;

        this.prev = addRenderableWidget(
                Button.builder(Component.literal("Prev"), btn -> btn.active = false)
                        .bounds(this.leftPos + 15, this.topPos + 196 - 25, 60, 15)
                        .build());

        this.next = addRenderableWidget(
                Button.builder(Component.literal("Next"), btn -> btn.active = false)
                        .bounds(this.leftPos + 85, this.topPos + 196 - 25, 60, 15)
                        .build());
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

}
