package com.archer2o2x.chronica_lexis.capabilities;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;

public class ModCapabilities {

    public static final Capability<ChronoGainCapability> CHRONO_GAIN = CapabilityManager.get(new CapabilityToken<>() {});

    public static void register(RegisterCapabilitiesEvent event) {
        event.register(ChronoGainCapability.class);
    }

    private ModCapabilities() {}

}
