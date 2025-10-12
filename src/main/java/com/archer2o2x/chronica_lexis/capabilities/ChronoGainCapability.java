package com.archer2o2x.chronica_lexis.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;
import net.minecraftforge.common.util.INBTSerializable;

@AutoRegisterCapability
public interface ChronoGainCapability extends INBTSerializable<CompoundTag> {

    int getChrono();
    void incrementChrono();
    void setChrono(int value);

}
