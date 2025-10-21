package com.archer2o2x.chronica_lexis.items;

import com.archer2o2x.chronica_lexis.items.scriptures.ModScriptures;
import com.archer2o2x.chronica_lexis.items.scriptures.Scripture;
import com.archer2o2x.chronica_lexis.items.scriptures.PrayerOfFastRecoveryScripture;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
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

import java.util.ArrayList;
import java.util.List;

public class ChronicaLexisItem extends ChronoGainItem {

    public ChronicaLexisItem(Properties p_41383_) {
        super(p_41383_);
    }

//    public void gatherScriptures(ItemStack stack) {
//        List<Scripture> result = new ArrayList<>();
//        CompoundTag base = stack.getOrCreateTag();
//        if (!base.contains("scriptures")) {
//            scriptures = new Scripture[0];
//            return;
//        }
//        for (String key : base.getCompound("scriptures").getAllKeys()) {
//            if (ModScriptures.get(key) != null) {
//                result.add(ModScriptures.get(key));
//            }
//        }
//        scriptures = result.toArray(new Scripture[0]);
//    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        if (p_41433_ instanceof ServerPlayer player) { // chronica_lexis:prayer_of_fast_recovery
            if (ModScriptures.get("prayer_of_fast_recovery") != null) {
                player.sendSystemMessage(Component.literal("Worked"));
            }
//            for (ResourceLocation loc : ModScriptures.REGISTRY.get().getKeys()) {
//                player.sendSystemMessage(Component.literal(loc.toString()));
//            }
            ItemStack stack = p_41433_.getItemInHand(p_41434_);
            if (!hasScripture(stack, "prayer_of_fast_recovery")) {
                addScripture(stack, "prayer_of_fast_recovery");
            }
            Scripture test = getSelectedScripture(stack);
            if (test == null) return InteractionResultHolder.fail(p_41433_.getItemInHand(p_41434_));
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

    public static void activateScripture(Player player, ItemStack stack) {
        CompoundTag base = stack.getOrCreateTag();
        if (!base.contains("scriptures") || !base.contains("scriptureIndex")) return;
        int index = base.getInt("scriptureIndex");
        //base.getCompound("scriptures").
    }

    public static void addScripture(ItemStack stack, String name) {
        CompoundTag tome = stack.getOrCreateTag();
        ListTag scriptures = tome.getList("scriptures", ListTag.TAG_STRING);
        scriptures.add(StringTag.valueOf(name));
        tome.put("scriptures", scriptures);
        if (!tome.contains("selected") || tome.getString("selected").isEmpty()) tome.putString("selected", name);
    }

    public static Scripture getSelectedScripture(ItemStack stack) {
        CompoundTag tome = stack.getOrCreateTag();
        if (!tome.contains("selected")) return null;
        return ModScriptures.get(tome.getString("selected"));
    }

    public static void removeScripture(ItemStack stack, String name) {
        CompoundTag tome = stack.getOrCreateTag();
        ListTag scriptures = tome.getList("scriptures", ListTag.TAG_STRING);
        scriptures.remove(StringTag.valueOf(name));
        tome.put("scriptures", scriptures);
        if (tome.getString("selected").equals(name)) tome.putString("selected", scriptures.getString(0));
    }

    public static boolean hasScripture(ItemStack stack, String name) {
        CompoundTag tome = stack.getOrCreateTag();
        return tome.getList("scriptures", ListTag.TAG_STRING).contains(StringTag.valueOf(name));
    }

    public static List<String> getScriptures(ItemStack stack) {
        CompoundTag tome = stack.getOrCreateTag();
        List<String> result = new ArrayList<>();
        ListTag scriptures = tome.getList("scriptures", ListTag.TAG_STRING);
        for (Tag tag : scriptures) {
            if (tag instanceof StringTag stringTag) {
                result.add(stringTag.getAsString());
            }
        }
        return result;
    }
}
