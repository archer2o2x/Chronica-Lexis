package com.archer2o2x.chronica_lexis.events;

import com.archer2o2x.chronica_lexis.ChronicaLexisMod;
import com.archer2o2x.chronica_lexis.screens.ChronicaLexisGui;
import com.archer2o2x.chronica_lexis.utils.ModKeybinds;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ChronicaLexisMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModClientEvents {

    @SubscribeEvent
    public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll("chronica_lexis", ChronicaLexisGui.OVERLAY);
    }

    @SubscribeEvent
    public static void registerBinding(RegisterKeyMappingsEvent event) {
        event.register(ModKeybinds.ACTIVATE.get());
        event.register(ModKeybinds.QUICK_SELECT.get());
        event.register(ModKeybinds.NEXT.get());
        event.register(ModKeybinds.PREVIOUS.get());
    }

}
