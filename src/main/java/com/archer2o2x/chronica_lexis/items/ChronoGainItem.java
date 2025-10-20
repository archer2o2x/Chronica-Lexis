package com.archer2o2x.chronica_lexis.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ChronoGainItem extends Item {

    private boolean should_tick;
    private int tick_counter;
    protected final int BASE_CHRONO_GAIN = 2; // Each stage yields x less chrono.
    protected final int BASE_GAIN_STEP = 480; // Each stage starts after x chrono gained.

    public int getBaseChronoGain() { return 10; }
    public int getMaxChronoStorage() { return 2400; }
    public int getNumChronoStages() { return 5; }

    public ChronoGainItem(Properties p_41383_) {
        super(p_41383_);
        tick_counter = 0;
        should_tick = true;
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {

        if (p_41406_ instanceof ServerPlayer player && should_tick) {
            tick_counter++;
            if (tick_counter > 20) {
                tick_counter = 0;
                CompoundTag tag = p_41404_.getOrCreateTag();
                if (tag.contains("chrono")) {
                    int chrono = tag.getInt("chrono");
                    tag.putInt("chrono", chrono + calculateChronoGain(chrono));
                } else {
                    tag.putInt("chrono", calculateChronoGain(0));
                }
            }
        }

    }

    public void setTicking(boolean value) {
        should_tick = value;
    }

    public static int getChrono(ItemStack pStack) {
        CompoundTag tag = pStack.getOrCreateTag();
        if (tag.contains("chrono")) { return tag.getInt("chrono"); }
        return 0;
    }

    public static boolean consumeChrono(ItemStack pStack, int amount) {
        CompoundTag tag = pStack.getOrCreateTag();
        if (!tag.contains("chrono")) return false;
        tag.putInt("chrono", tag.getInt("chrono") - amount);
        return true;
    }

    private int calculateChronoGain(int chrono) {

        int stages = getNumChronoStages();
        int gain = getBaseChronoGain() / stages;
        int step = getMaxChronoStorage() / stages;

        for (int i = 1; i <= stages; i++) {
            if (chrono < step * i) return gain * ((stages + 1) - i);
        }

//        if (chrono < BASE_GAIN_STEP) return BASE_CHRONO_GAIN * 5;
//        if (chrono < BASE_GAIN_STEP * 2) return BASE_CHRONO_GAIN * 4;
//        if (chrono < BASE_GAIN_STEP * 3) return BASE_CHRONO_GAIN * 3;
//        if (chrono < BASE_GAIN_STEP * 4) return BASE_CHRONO_GAIN * 2;
//        if (chrono < BASE_GAIN_STEP * 5) return BASE_CHRONO_GAIN;
        return 0;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        if (oldStack.getItem() instanceof ChronoGainItem a && newStack.getItem() instanceof ChronoGainItem b) {
            return slotChanged;
        }
        return super.shouldCauseReequipAnimation(oldStack, newStack, slotChanged);
    }
}
