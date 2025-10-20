package com.archer2o2x.chronica_lexis.items.scriptures;

import com.archer2o2x.chronica_lexis.ChronicaLexisMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.*;

import java.util.function.Supplier;

public class ModScriptures {

    public static final ResourceKey<Registry<Scripture>> SCRIPTURE_REGISTRY_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(ChronicaLexisMod.MODID, "scriptures"));
    public static final DeferredRegister<Scripture> SCRIPTURES = DeferredRegister.create(SCRIPTURE_REGISTRY_KEY, ChronicaLexisMod.MODID);
    public static final Supplier<IForgeRegistry<Scripture>> REGISTRY = SCRIPTURES.makeRegistry(() -> new RegistryBuilder<Scripture>());

    public static final RegistryObject<Scripture> PRAYER_OF_FAST_RECOVERY = SCRIPTURES.register("prayer_of_fast_recovery", PrayerOfFastRecoveryScripture::new);

    public static void register(IEventBus bus) {
        SCRIPTURES.register(bus);
    }

    public static Scripture get(ResourceLocation location) {
        return REGISTRY.get().getValue(location);
    }
    public static Scripture get(String name) {
        return get(ResourceLocation.fromNamespaceAndPath(ChronicaLexisMod.MODID, name));
    }

}
