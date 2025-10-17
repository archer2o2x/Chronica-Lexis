package com.archer2o2x.chronica_lexis.network;

import com.archer2o2x.chronica_lexis.client.ModClientHooks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

public class OpenTomePacket {

    private ResourceLocation location;

    public OpenTomePacket(ResourceLocation location) {
        this.location = location;
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeResourceLocation(location);
    }

    public static OpenTomePacket decode(FriendlyByteBuf buffer) {
        return new OpenTomePacket(buffer.readResourceLocation());
    }

    public static void handle(OpenTomePacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() ->
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ModClientHooks.openTomeScreen(ForgeRegistries.ITEMS.getValue(packet.location)))
        );
        ctx.get().setPacketHandled(true);
    }

}
