package com.archer2o2x.chronica_lexis.items;

import com.archer2o2x.chronica_lexis.ChronicaLexisMod;
import com.archer2o2x.chronica_lexis.items.scriptures.ModScriptures;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTab {

    public static DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ChronicaLexisMod.MODID);

    public static RegistryObject<CreativeModeTab> MAIN_TAB = TABS.register("main", () -> CreativeModeTab.builder()
            .title(Component.translatable("item_group." + ChronicaLexisMod.MODID + ".main"))
            .icon(() -> new ItemStack(ModItems.CHRONICHA_LEXIS_TOME.get()))
            .displayItems((params, output) -> {
                output.accept(ModItems.CHRONICHA_LEXIS_TOME.get());

                output.accept(ModItems.THEORICA_TEMPORALIS_TOME.get());
                output.accept(ModItems.STIRRING_THEORICA_TEMPORALIS_TOME.get());
                output.accept(ModItems.AWAKENED_THEORICA_TEMPORALIS_TOME.get());

                // Generate a full complement of ScriptureItems.
                ModScriptures.REGISTRY.get().getKeys().forEach(scripture -> {
                    ItemStack stack = new ItemStack(ModItems.SCRIPTURE.get());
                    ScriptureItem.setScripture(stack, scripture);
                    output.accept(stack);
                });

            })
            .build());

    public static void register(IEventBus bus) {
        TABS.register(bus);
    }
}
