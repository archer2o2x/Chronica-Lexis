package com.archer2o2x.chronica_lexis.items;

import com.archer2o2x.chronica_lexis.items.scriptures.Scripture;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ScriptureItem extends Item {

    private final String scripture;

    public ScriptureItem(Properties p_41383_, String scripture) {
        super(p_41383_);
        this.scripture = scripture;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        ItemStack scroll = p_41433_.getItemInHand(p_41434_);
        ItemStack stack = ChronicaLexisItem.getChronicaLexis(p_41433_);
        if (stack == null) return InteractionResultHolder.fail(scroll);
        if (!ChronicaLexisItem.hasScripture(stack, scripture)) {
            ChronicaLexisItem.addScripture(stack, scripture);
            scroll.shrink(1);
        }
        return InteractionResultHolder.success(scroll);
    }
}
