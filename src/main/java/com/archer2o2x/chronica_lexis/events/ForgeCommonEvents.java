package com.archer2o2x.chronica_lexis.events;

import com.archer2o2x.chronica_lexis.ChronicaLexisMod;
import com.archer2o2x.chronica_lexis.items.ChronicaLexisItem;
import com.archer2o2x.chronica_lexis.network.KeyActionPacket;
import com.archer2o2x.chronica_lexis.network.ModPacketHandler;
import com.archer2o2x.chronica_lexis.utils.ModKeybinds;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = ChronicaLexisMod.MODID)
public class ForgeCommonEvents {

    private static boolean activate, quick_select, next, previous = false;

    @SubscribeEvent
    public static void onClientTickEvent(TickEvent.ClientTickEvent event) {

        if (Minecraft.getInstance().player == null) return;
        ItemStack stack = ChronicaLexisItem.getChronicaLexis(Minecraft.getInstance().player);
        if (stack == null) return;
        ChronicaLexisItem.tickCurrentScripture(Minecraft.getInstance().player, stack);
        if (ModKeybinds.ACTIVATE.get().isDown() && !activate) {
            ModPacketHandler.INSTANCE.sendToServer(new KeyActionPacket(ModKeybinds.Actions.ACTIVATE));
        }
        if (ModKeybinds.QUICK_SELECT.get().isDown() && !quick_select) {
            ModPacketHandler.INSTANCE.sendToServer(new KeyActionPacket(ModKeybinds.Actions.QUICK_SELECT));
        }
        if (ModKeybinds.NEXT.get().isDown() && !next) {
            ModPacketHandler.INSTANCE.sendToServer(new KeyActionPacket(ModKeybinds.Actions.NEXT));
        }
        if (ModKeybinds.PREVIOUS.get().isDown() && !previous) {
            ModPacketHandler.INSTANCE.sendToServer(new KeyActionPacket(ModKeybinds.Actions.PREVIOUS));
        }
        activate = ModKeybinds.ACTIVATE.get().isDown();
        quick_select = ModKeybinds.QUICK_SELECT.get().isDown();
        next = ModKeybinds.NEXT.get().isDown();
        previous = ModKeybinds.PREVIOUS.get().isDown();

    }

}
