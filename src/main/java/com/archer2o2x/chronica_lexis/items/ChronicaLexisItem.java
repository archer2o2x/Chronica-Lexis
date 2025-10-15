package com.archer2o2x.chronica_lexis.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChronicaLexisItem extends ChronoGainItem {
    public ChronicaLexisItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        p_41433_.setDeltaMovement(p_41433_.getDeltaMovement().add(p_41433_.getViewVector(1).scale(1)));
        return InteractionResultHolder.success(p_41433_.getItemInHand(p_41434_));
    }

    @Override
    public boolean isFoil(ItemStack p_41453_) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        p_41423_.add(Component.translatable("desc.chronica_lexis.chronica_lexis_tome.1").withStyle(ChatFormatting.DARK_GRAY));
        p_41423_.add(Component.translatable("desc.chronica_lexis.chronica_lexis_tome.2").withStyle(ChatFormatting.DARK_GRAY));
    }
}
