package com.archer2o2x.chronica_lexis.items;

import com.archer2o2x.chronica_lexis.items.scriptures.ModScriptures;
import com.archer2o2x.chronica_lexis.items.scriptures.Scripture;
import com.archer2o2x.chronica_lexis.items.scriptures.PrayerOfFastRecoveryScripture;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChronicaLexisItem extends ChronoGainItem {

    public Scripture test;

    public ChronicaLexisItem(Properties p_41383_) {
        super(p_41383_);
        test = new PrayerOfFastRecoveryScripture();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        if (p_41433_ instanceof ServerPlayer player) { // chronica_lexis:prayer_of_fast_recovery
            if (ModScriptures.REGISTRY.get().getValue(ResourceLocation.fromNamespaceAndPath("chronica_lexis", "prayer_of_fast_recovery")) != null) {
                player.sendSystemMessage(Component.literal("Worked"));
            }
//            for (ResourceLocation loc : ModScriptures.REGISTRY.get().getKeys()) {
//                player.sendSystemMessage(Component.literal(loc.toString()));
//            }
            ItemStack stack = p_41433_.getItemInHand(p_41434_);
            if (getChrono(stack) > test.getState().cost()) {
                consumeChrono(stack, test.getState().cost());
                test.onUse(player, stack);
            }
            //ModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new OpenTomePacket(stack));
        }
        return InteractionResultHolder.success(p_41433_.getItemInHand(p_41434_));
    }

    @Override
    public boolean isFoil(ItemStack p_41453_) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        p_41423_.add(Component.literal("Chrono [ %d / %d ]".formatted(getChrono(p_41421_), getMaxChronoStorage())).withStyle(ChatFormatting.GRAY));
        p_41423_.add(Component.translatable("desc.chronica_lexis.chronica_lexis_tome.1").withStyle(ChatFormatting.DARK_GRAY));
        p_41423_.add(Component.translatable("desc.chronica_lexis.chronica_lexis_tome.2").withStyle(ChatFormatting.DARK_GRAY));
    }

    public static ItemStack getChronicaLexis(Player player) {
        if (player == null) return null;
        InventoryMenu inv = player.inventoryMenu;
        for (ItemStack stack : inv.getItems()) {
            if (stack.getItem() instanceof ChronicaLexisItem) return stack;
        }
        return null;
    }
}
