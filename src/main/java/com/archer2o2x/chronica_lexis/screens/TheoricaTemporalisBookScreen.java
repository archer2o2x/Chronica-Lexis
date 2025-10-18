package com.archer2o2x.chronica_lexis.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TheoricaTemporalisBookScreen extends Screen {

    private static final Component TITLE = Component.literal("Cool Title Test");

    private static final ResourceLocation TEXTURE_LEFT =
            ResourceLocation.fromNamespaceAndPath("chronica_lexis", "textures/gui/tome_left.png");
    private static final ResourceLocation TEXTURE_RIGHT =
            ResourceLocation.fromNamespaceAndPath("chronica_lexis", "textures/gui/tome_right.png");

    private static final String[] PAGES = {
            "This is a test of the",
            "multiline book display.",
            "As you can see it looks",
            "alright, but has to be",
            "separated line by line."

    };

    private final int imageWidth, imageHeight;
    private int leftPos, topPos;

    private Button prev, next;
    private ItemStack item;

    public TheoricaTemporalisBookScreen(ItemStack item) {
        super(TITLE);
        this.item = item;
        this.imageWidth = 160;
        this.imageHeight = 196;
    }

    @Override
    public void render(@NotNull GuiGraphics gui, int mouse_x, int mouse_y, float partialTicks) {
        renderBackground(gui);
        //gui.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
//        gui.blit(TEXTURE_LEFT, this.leftPos - 81, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
//        gui.blit(TEXTURE_RIGHT, this.leftPos + 81, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
//        int titleLeftHalf = this.font.width("Theorica Temporalis") / 2;
//        int titleRightHalf = this.font.width("Page 1 / 1") / 2;
//        gui.drawString(this.font, "Theorica Temporalis", this.leftPos - 1 - titleLeftHalf, this.topPos + 10, 0, false);
//        gui.drawString(this.font, "Page 1 / 1", this.leftPos + 161 - titleRightHalf, this.topPos + 10, 0, false);
//        //gui.drawString(this.font, "Theorica Temporalis", this.leftPos + 81 - titleHalf, this.topPos + 10, 0, false);
//        gui.hLine(this.leftPos - 66, this.leftPos + 64, this.topPos + 20, 0xFF000000);
//        gui.hLine(this.leftPos + 94, this.leftPos + 224, this.topPos + 20, 0xFF000000);
//
//        // Render Text for book
//        //String[] pages = wrapText(PAGE, 24);//24
//        for (int i = 0; i < PAGES.length; i++) {
//            gui.drawString(this.font, PAGES[i], this.leftPos - 66, this.topPos + 25 + (10 * i), 0xFF000000, false);
//        }
        renderPage(gui, TEXTURE_LEFT, -81, "Theorica Temporalis", PAGES, 1);
        renderPage(gui, TEXTURE_RIGHT, 81, "Stirring", new String[] {}, 2);

        gui.renderItem(this.item, this.leftPos, this.topPos + 98);

        super.render(gui, mouse_x, mouse_y, partialTicks);
    }

    private void renderPage(GuiGraphics gui, ResourceLocation texture, int offset_x, String title, String[] lines, int pageIndex) {
        gui.blit(texture, this.leftPos + offset_x, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        int titleHalf = this.font.width(title) / 2;
        gui.drawString(this.font, title, this.leftPos + 80 - titleHalf + offset_x, this.topPos + 10, 0, false);
        gui.hLine(this.leftPos + 15 + offset_x, this.leftPos + 145 + offset_x, this.topPos + 20, 0xFF000000);
        for (int i = 0; i < lines.length; i++) {
            gui.drawString(this.font, lines[i], this.leftPos + 20 + offset_x, this.topPos + 25 + (10 * i), 0xFF000000, false);
        }
        int pageNumHalf = this.font.width(String.valueOf(pageIndex)) / 2;
        gui.drawString(this.font, String.valueOf(pageIndex), this.leftPos + 80 - pageNumHalf + offset_x, this.topPos + 196 - 21, 0, false);
    }

    private String[] wrapText(String input, int width) {
        int amount = (int) Math.ceil((double) input.length() / width);
        String[] result = new String[amount];
        for (int i = 0; i < amount; i++) {
            result[i] = input.substring(i * width, Math.min((i + 1) * width, input.length()));
        }
        return result;
        // Must write a better method
//        List<String> result = new ArrayList<>();
//        String[] words = input.split("\\s");
//        String current = "";
//        int index = 0;
//        do {
//            current = current + words[index] + (current.isEmpty() ? "" : " ");
//            index++;
//        } while (this.font.width(current) < width);
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
                        .bounds(this.leftPos - 75, this.topPos + 196 - 25, 60, 15)
                        .build());

        this.next = addRenderableWidget(
                Button.builder(Component.literal("Next"), btn -> btn.active = false)
                        .bounds(this.leftPos + 175, this.topPos + 196 - 25, 60, 15)
                        .build());
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

}
