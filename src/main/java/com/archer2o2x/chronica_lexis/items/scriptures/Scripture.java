package com.archer2o2x.chronica_lexis.items.scriptures;

import net.minecraft.nbt.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class Scripture {

    public State getState() { return null; }

    public void onTick(Player player, ItemStack stack) {}
    public void onUse(Player player, ItemStack stack) {}
    public void onHurt(Player player, ItemStack stack, int damage) {}
    public void onAttack(Player player, ItemStack stack, Entity target) {}

    public record State(String id, int cost, Rarity rarity) {};

    // tome = {
    //   scriptures = List<String>
    //   state = List<Compound>,
    //   selected = Int
    // }

}
