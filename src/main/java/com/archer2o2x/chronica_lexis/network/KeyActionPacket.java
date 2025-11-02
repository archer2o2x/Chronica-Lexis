package com.archer2o2x.chronica_lexis.network;

import com.archer2o2x.chronica_lexis.client.ModClientHooks;
import com.archer2o2x.chronica_lexis.items.ChronicaLexisItem;
import com.archer2o2x.chronica_lexis.items.scriptures.Scripture;
import com.archer2o2x.chronica_lexis.utils.ModKeybinds;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class KeyActionPacket {

    private final ModKeybinds.Actions value;

    public KeyActionPacket(ModKeybinds.Actions value) {
        this.value = value;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeEnum(value);
    }

    public static KeyActionPacket decode(FriendlyByteBuf buffer) {
        return new KeyActionPacket(buffer.readEnum(ModKeybinds.Actions.class));
    }

    public static void handle(KeyActionPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {

            if (packet.value == ModKeybinds.Actions.ACTIVATE) {
                Player player = ctx.get().getSender();
                ItemStack stack = ChronicaLexisItem.getChronicaLexis(player);
                if (stack == null) return;
                Scripture scripture = ChronicaLexisItem.getSelectedScripture(stack);
                if (scripture == null) return;
                ChronicaLexisItem.activateCurrentScripture(player, stack);
//                if (ChronicaLexisItem.getChrono(stack) > scripture.getState().cost()) {
//                    ChronicaLexisItem.consumeChrono(stack, scripture.getState().cost());
//                    scripture.onUse(player, stack);
//                }
            }

        });
        ctx.get().setPacketHandled(true);
    }

}
