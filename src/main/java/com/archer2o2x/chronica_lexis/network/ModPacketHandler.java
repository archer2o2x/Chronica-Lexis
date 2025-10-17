package com.archer2o2x.chronica_lexis.network;

import com.archer2o2x.chronica_lexis.ChronicaLexisMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModPacketHandler {

    private static int PACKET_ID = 1;
    private static final String PROTOCOL_VER = "1.0";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            ResourceLocation.fromNamespaceAndPath(ChronicaLexisMod.MODID, "main"),
            () -> PROTOCOL_VER,
            PROTOCOL_VER::equals,
            PROTOCOL_VER::equals
    );

    public static void register() {
        INSTANCE.registerMessage(PACKET_ID++,
                OpenTomePacket.class,
                OpenTomePacket::encode,
                OpenTomePacket::decode,
                OpenTomePacket::handle);
    }

}
