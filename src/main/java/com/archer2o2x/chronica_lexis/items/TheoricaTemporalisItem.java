package com.archer2o2x.chronica_lexis.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TheoricaTemporalisItem extends ChronoGainItem {

    private static final int DORMANCY_THRESHOLD = 10;
    private final State state;

    @Override
    public int getBaseChronoGain() { return 1; }
    @Override
    public int getMaxChronoStorage() { return DORMANCY_THRESHOLD; }
    @Override
    public int getNumChronoStages() { return 1; }

    public TheoricaTemporalisItem(Properties p_41383_, State state) {
        super(p_41383_);
        this.state = state;
    }

    @Override
    public void inventoryTick(ItemStack pItemStack, Level pLevel, Entity pEntity, int p_41407_, boolean p_41408_) {
        super.inventoryTick(pItemStack, pLevel, pEntity, p_41407_, p_41408_);
        if (state == State.DORMANT && pEntity instanceof  Player player) {
            if (getChrono(pItemStack) >= DORMANCY_THRESHOLD) {
                sendStatusMessage(player, Component.literal("The Theorica Temporalis slowly stirs from its slumber."));
                ItemStack newItem = new ItemStack(ModItems.STIRRING_THEORICA_TEMPORALIS_TOME.get());
                if (player.getItemInHand(InteractionHand.OFF_HAND) == pItemStack) {
                    player.setItemSlot(EquipmentSlot.OFFHAND, newItem);
                } else {
                    player.getSlot(p_41407_).set(newItem);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        switch (state) {
            case DORMANT -> {
                p_41423_.add(Component.literal("A grand tome of old magic,").withStyle(ChatFormatting.DARK_GRAY));
                p_41423_.add(Component.literal("still slumbering deeply.").withStyle(ChatFormatting.DARK_GRAY));
            }
            case STIRRING -> {
                p_41423_.add(Component.literal("A grand tome of old magic,").withStyle(ChatFormatting.DARK_GRAY));
                p_41423_.add(Component.literal("stirring slowly from sleep.").withStyle(ChatFormatting.DARK_GRAY));
            }
            case AWAKENED -> {
                p_41423_.add(Component.literal("A grand tome of old magic,").withStyle(ChatFormatting.DARK_GRAY));
                p_41423_.add(Component.literal("watching and waiting.").withStyle(ChatFormatting.DARK_GRAY));
            }
        }
    }

    public void sendStatusMessage(Player player, MutableComponent component) {
        player.displayClientMessage(component.withStyle(ChatFormatting.LIGHT_PURPLE), true);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack p_41453_) {
        return true;
    }

    public enum State {
        DORMANT,
        STIRRING,
        AWAKENED
    }

}
