package com.archer2o2x.chronica_lexis.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class TheoricaTemporalisBookScreen extends Screen {

    private static final Component TITLE = Component.literal("Cool Title Test");

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath("chronica_lexis", "textures/item/chronica_lexis_tome.png");

    private final int imageWidth, imageHeight;
    private int leftPos, topPos;

    private Button button;

    public TheoricaTemporalisBookScreen() {
        super(TITLE);

        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    public void render(@NotNull GuiGraphics gui, int mouse_x, int mouse_y, float partialTicks) {
        renderBackground(gui);
        gui.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
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

        this.button = addRenderableWidget(
                Button.builder(Component.literal("Click Me"), btn -> btn.active = false)
                        .bounds(this.leftPos + 8, this.topPos + 8, 20, 20)
                        .build());
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

}
