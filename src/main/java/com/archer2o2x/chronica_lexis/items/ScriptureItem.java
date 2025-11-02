package com.archer2o2x.chronica_lexis.items;

import com.archer2o2x.chronica_lexis.ChronicaLexisMod;
import com.archer2o2x.chronica_lexis.items.scriptures.ModScriptures;
import com.archer2o2x.chronica_lexis.items.scriptures.Scripture;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ScriptureItem extends Item {

    public ScriptureItem(Properties p_41383_, String scripture) {
        super(p_41383_);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level p_41432_, Player p_41433_, @NotNull InteractionHand p_41434_) {
        ItemStack scroll = p_41433_.getItemInHand(p_41434_);
        String scripture = getScripture(scroll);
        if (scripture.isEmpty()) return InteractionResultHolder.fail(scroll);
        ItemStack stack = ChronicaLexisItem.getChronicaLexis(p_41433_);
        if (stack == null) return InteractionResultHolder.fail(scroll);
        if (!ChronicaLexisItem.hasScripture(stack, scripture)) {
            ChronicaLexisItem.addScripture(stack, scripture);
            scroll.shrink(1);
        }
        return InteractionResultHolder.success(scroll);
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        ResourceLocation location = ResourceLocation.parse(getScripture(p_41421_));
        String identifier = "scripture.%s.%s.".formatted(location.getNamespace(), location.getPath());
        p_41423_.add(Component.translatable(identifier + "name").withStyle(ChatFormatting.GRAY));
        p_41423_.add(Component.translatable(identifier + "desc1").withStyle(ChatFormatting.DARK_GRAY));
        p_41423_.add(Component.translatable(identifier + "desc2").withStyle(ChatFormatting.DARK_GRAY));
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack p_41458_) {
        Scripture scripture = ModScriptures.get(getScripture(p_41458_));
        if (scripture == null) return Component.literal("");
        switch (scripture.getState().rarity()) {
            case COMMON -> { return Component.translatable("item.chronica_lexis.scripture.common"); }
            case UNCOMMON -> { return Component.translatable("item.chronica_lexis.scripture.uncommon"); }
            case RARE -> { return Component.translatable("item.chronica_lexis.scripture.rare"); }
            case EPIC -> { return Component.translatable("item.chronica_lexis.scripture.epic"); }
            default -> { return Component.literal(""); }
        }
    }

    @Override
    public boolean isFoil(@NotNull ItemStack p_41453_) {
        return true;
    }

    public static String getScripture(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains("scripture")) return "";
        return tag.getString("scripture");
    }

    public static void setScripture(ItemStack stack, ResourceLocation scripture) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putString("scripture", scripture.toString());
    }

    public static void setScripture(ItemStack stack, String scripture) {
        setScripture(stack, ResourceLocation.fromNamespaceAndPath(ChronicaLexisMod.MODID, scripture));
    }

}
