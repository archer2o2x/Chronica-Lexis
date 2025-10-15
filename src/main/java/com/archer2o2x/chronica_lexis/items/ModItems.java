package com.archer2o2x.chronica_lexis.items;

import com.archer2o2x.chronica_lexis.ChronicaLexisMod;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ChronicaLexisMod.MODID);

    public static final RegistryObject<Item> CHRONICHA_LEXIS_TOME = ITEMS.register("chronica_lexis_tome",
            () -> new ChronicaLexisItem(new Item.Properties().rarity(Rarity.EPIC).fireResistant().stacksTo(1)));

    public static final RegistryObject<Item> THEORICA_TEMPORALIS_TOME = ITEMS.register("theorica_temporalis_tome",
            () -> new TheoricaTemporalisItem(new Item.Properties().rarity(Rarity.EPIC).fireResistant().stacksTo(1), TheoricaTemporalisItem.State.DORMANT));
    public static final RegistryObject<Item> STIRRING_THEORICA_TEMPORALIS_TOME = ITEMS.register("stirring_theorica_temporalis_tome",
            () -> new TheoricaTemporalisItem(new Item.Properties().rarity(Rarity.EPIC).fireResistant().stacksTo(1), TheoricaTemporalisItem.State.STIRRING));
    public static final RegistryObject<Item> AWAKENED_THEORICA_TEMPORALIS_TOME = ITEMS.register("awakened_theorica_temporalis_tome",
            () -> new TheoricaTemporalisItem(new Item.Properties().rarity(Rarity.EPIC).fireResistant().stacksTo(1), TheoricaTemporalisItem.State.AWAKENED));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }

    // TODO NEXT TIME
    // - Started on the screen functionality of the Chronica Lexis, but I think that the Item.use method is only called server-side.
    // - Gonna need to look into codecs (or the other networking stuff) to make it load the screen properly.
    // - Also need to have a think about how I'm gonna store the book data, ideally not hard code anything nor spam NBT data.
    // - Implement the basic functionality of the Chronica Lexis, E.G. Holding scriptures, scrolling and usage.

}
