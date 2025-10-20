package com.archer2o2x.chronica_lexis.items.scriptures;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class PrayerOfFastRecoveryScripture extends Scripture {

    @Override
    public State getState() {
        return new State("prayer_of_fast_recovery", 450, Rarity.COMMON);
    }

    @Override
    public void onUse(Player player, ItemStack stack) {
        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 300, 2, true, false));
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 300, 2, true, false));
    }

}
