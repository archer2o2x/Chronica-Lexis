package com.archer2o2x.chronica_lexis.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TheoricaTemporalisItem extends Item {

    private State state;
    private int tick_counter = -1;

    public TheoricaTemporalisItem(Properties p_41383_, State state) {
        super(p_41383_);
        this.state = state;
    }

    @Override
    public void inventoryTick(ItemStack pItemStack, Level pLevel, Entity pEntity, int p_41407_, boolean p_41408_) {
        if (state == State.DORMANT && pEntity instanceof Player player) {
            if (tick_counter < 0) { tick_counter = 20 * 5; return; } // Set the dormancy timer.
            if (tick_counter > 0) { tick_counter--; return; }
            pEntity.sendSystemMessage(Component.literal("The Theorica Temporalis slowly stirs from its slumber.").withStyle(ChatFormatting.GREEN));
            state = State.STIRRING;
            tick_counter = -1;
        }
    }

    public enum State {
        DORMANT,
        STIRRING,
        AWAKENED
    }

}
