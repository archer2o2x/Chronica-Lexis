package com.archer2o2x.chronica_lexis.items.scriptures;

import com.archer2o2x.chronica_lexis.ChronicaLexisMod;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.phys.Vec3;

public class UtteranceOfInstantVelocity extends Scripture {
    @Override
    public State getState() {
        return new State(ResourceLocation.fromNamespaceAndPath(ChronicaLexisMod.MODID, "utterance_of_instant_velocity"), 60, Rarity.COMMON);
    }

//    @Override
//    public void onTick(Player player, ItemStack stack) {
//        player.displayClientMessage(Component.literal(String.valueOf(player.getDeltaMovement())), true);
//    }

    @Override
    public void onUse(Player player, ItemStack stack) {
        player.setDeltaMovement(player.getViewVector(0).multiply(1, 0, 1).scale(2).add(0, 1, 0));
        player.hurtMarked = true;
        player.resetFallDistance();
        //player.move(MoverType.SELF, new Vec3(0, 10, 0));
    }
}
