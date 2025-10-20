package com.archer2o2x.chronica_lexis.items;

import com.archer2o2x.chronica_lexis.client.ModClientHooks;
import com.archer2o2x.chronica_lexis.network.ModPacketHandler;
import com.archer2o2x.chronica_lexis.network.OpenTomePacket;
import com.archer2o2x.chronica_lexis.screens.TheoricaTemporalisBookScreen;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.chrono.MinguoEra;
import java.util.List;
import java.util.Objects;

public class TheoricaTemporalisItem extends ChronoGainItem {

    private static final int DORMANCY_THRESHOLD = 60;// * 60;
    private final State state;

    @Override
    public int getBaseChronoGain() { return 1; }
    @Override
    public int getMaxChronoStorage() { return state == State.DORMANT ? DORMANCY_THRESHOLD : 0; }
    @Override
    public int getNumChronoStages() { return 1; }

    public TheoricaTemporalisItem(Properties p_41383_, State state) {
        super(p_41383_);
        this.state = state;
    }

    @Override
    public InteractionResult useOn(UseOnContext p_41427_) {
        BlockPos pos = p_41427_.getClickedPos();
        if (p_41427_.getLevel().getBlockState(pos).getBlock() == Blocks.ENCHANTING_TABLE) {
            Player player = p_41427_.getPlayer();
            assert player != null;
            boolean heldInMainHand = player.getItemInHand(InteractionHand.MAIN_HAND) == p_41427_.getItemInHand();
            // Next form of Theorica Temporalis
            sendStatusMessage(player, Component.translatable("step.chronica_lexis.theorica_temporalis.reborn"));
            ItemStack newItem = new ItemStack(ModItems.CHRONICHA_LEXIS_TOME.get());
            if (heldInMainHand) {
                player.setItemSlot(EquipmentSlot.MAINHAND, newItem);
            } else {
                player.setItemSlot(EquipmentSlot.OFFHAND, newItem);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack stack = pPlayer.getItemInHand(pHand);
        if (!(pPlayer instanceof ServerPlayer)) return InteractionResultHolder.pass(stack);
        if (state == State.STIRRING) {
            if (getUseDuration(stack) > 0) {
                pPlayer.startUsingItem(pHand);
                return InteractionResultHolder.success(stack);
            }
        }
//        if (pLevel.isClientSide()) {
//            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ModClientHooks.openTomeScreen(null));
//        }
        ModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) pPlayer), new OpenTomePacket(stack));
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack p_41409_, @NotNull Level p_41410_, @NotNull LivingEntity p_41411_) {

        if (p_41411_ instanceof ServerPlayer player) {
            boolean heldInMainHand = player.getItemInHand(InteractionHand.MAIN_HAND) == p_41409_;
            if (player.getItemInHand(heldInMainHand ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND).getItem() != Items.CLOCK || p_41410_.getDayTime() < 22500) {
                sendStatusMessage(player, Component.translatable("step.chronica_lexis.theorica_temporalis.failure"));
                player.getCooldowns().addCooldown(this, 20);
                return p_41409_;
            }
            player.setItemInHand(heldInMainHand ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND, ItemStack.EMPTY);
//            for (ServerLevel level : Objects.requireNonNull(player.getServer()).getAllLevels()) {
//                level.setDayTime(0);
//            }
            CompoundTag tag = p_41409_.getOrCreateTag();
            int sunrises = tag.contains("sunrises") ? tag.getInt("sunrises") + 1 : 1;
            if (sunrises >= 3) {
                // Next form of Theorica Temporalis
                sendStatusMessage(player, Component.translatable("step.chronica_lexis.theorica_temporalis.awakened"));
                ItemStack newItem = new ItemStack(ModItems.AWAKENED_THEORICA_TEMPORALIS_TOME.get());
                if (heldInMainHand) {
                    player.setItemSlot(EquipmentSlot.MAINHAND, newItem);
                } else {
                    player.setItemSlot(EquipmentSlot.OFFHAND, newItem);
                }
                return p_41409_;
            } else {
                // Successful, but not enough yet
                tag.putInt("sunrises", sunrises);
                sendStatusMessage(player, Component.translatable("step.chronica_lexis.theorica_temporalis.success"));
            }
            player.getCooldowns().addCooldown(this, 1800);
        }
        return p_41409_;
    }

    @Override
    public void inventoryTick(ItemStack pItemStack, Level pLevel, Entity pEntity, int p_41407_, boolean p_41408_) {
        super.inventoryTick(pItemStack, pLevel, pEntity, p_41407_, p_41408_);
        if (state == State.DORMANT && pEntity instanceof  Player player) {
            if (getChrono(pItemStack) >= DORMANCY_THRESHOLD) {
                sendStatusMessage(player, Component.translatable("step.chronica_lexis.theorica_temporalis.stirring"));
                ItemStack newItem = new ItemStack(ModItems.STIRRING_THEORICA_TEMPORALIS_TOME.get());
                if (player.getItemInHand(InteractionHand.OFF_HAND) == pItemStack) {
                    player.setItemSlot(EquipmentSlot.OFFHAND, newItem);
                } else {
                    player.getSlot(p_41407_).set(newItem);
                }
            }
        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        boolean isSunrise = Minecraft.getInstance().level != null && Minecraft.getInstance().level.getDayTime() >= 22500;
        if (getUseDuration(p_41452_) <= 0 || state != State.STIRRING || !isSunrise) return UseAnim.NONE;
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack p_41454_) {
        boolean isSunrise = Minecraft.getInstance().level != null && Minecraft.getInstance().level.getDayTime() >= 22500;
        return (state == State.STIRRING && isSunrise) ? 60 : 0;
    }

    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        switch (state) {
            case DORMANT -> {
                p_41423_.add(Component.translatable("desc.chronica_lexis.theorica_temporalis_tome.1").withStyle(ChatFormatting.DARK_GRAY));
                p_41423_.add(Component.translatable("desc.chronica_lexis.theorica_temporalis_tome.2").withStyle(ChatFormatting.DARK_GRAY));
            }
            case STIRRING -> {
                p_41423_.add(Component.translatable("desc.chronica_lexis.stirring_theorica_temporalis_tome.1").withStyle(ChatFormatting.DARK_GRAY));
                p_41423_.add(Component.translatable("desc.chronica_lexis.stirring_theorica_temporalis_tome.2").withStyle(ChatFormatting.DARK_GRAY));
            }
            case AWAKENED -> {
                p_41423_.add(Component.translatable("desc.chronica_lexis.awakened_theorica_temporalis_tome.1").withStyle(ChatFormatting.DARK_GRAY));
                p_41423_.add(Component.translatable("desc.chronica_lexis.awakened_theorica_temporalis_tome.2").withStyle(ChatFormatting.DARK_GRAY));
            }
        }
    }

    public void sendStatusMessage(Player player, MutableComponent component) {
        player.displayClientMessage(component.withStyle(ChatFormatting.LIGHT_PURPLE), true);
    }

    @Override
    public boolean isFoil(@NotNull ItemStack p_41453_) {
        return true;
    }

    public enum State {
        DORMANT,
        STIRRING,
        AWAKENED
    }

}
