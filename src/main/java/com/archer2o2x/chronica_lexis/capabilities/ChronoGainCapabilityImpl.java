package com.archer2o2x.chronica_lexis.capabilities;

import net.minecraft.nbt.CompoundTag;

public class ChronoGainCapabilityImpl implements ChronoGainCapability {

    private static final String NBT_CHRONO = "chrono";
    private int chrono = 0;

    @Override
    public int getChrono() {
        return this.chrono;
    }

    @Override
    public void incrementChrono() {
        this.chrono++;
    }

    @Override
    public void setChrono(int value) {
        this.chrono = value;
    }

    @Override
    public CompoundTag serializeNBT() {
        final CompoundTag tag = new CompoundTag();
        tag.putInt(NBT_CHRONO, chrono);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.chrono = nbt.getInt(NBT_CHRONO);
    }
}
