package com.archer2o2x.chronica_lexis.capabilities;

import com.archer2o2x.chronica_lexis.ChronicaLexisMod;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChronoGainAttacher {

    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(ChronicaLexisMod.MODID, "chrono_gain");

    public static class ChronoGainProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

        private final ChronoGainCapability backend = new ChronoGainCapabilityImpl();
        private final LazyOptional<ChronoGainCapability> optional = LazyOptional.of(() -> backend);

        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            return ModCapabilities.CHRONO_GAIN.orEmpty(cap, this.optional);
        }

        void invalidate() {
            this.optional.invalidate();
        }

        @Override
        public CompoundTag serializeNBT() {
            return this.backend.serializeNBT();
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            this.backend.deserializeNBT(nbt);
        }
    }

    public static void attach(final AttachCapabilitiesEvent<ItemStack> event) {
        final ChronoGainProvider provider = new ChronoGainProvider();
        event.addCapability(ID, provider);
    }

    private ChronoGainAttacher() {}

}
