package com.archer2o2x.chronica_lexis.items;

import com.archer2o2x.chronica_lexis.ChronicaLexisMod;
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

    public static final RegistryObject<Item> SCRIPTURE = ITEMS.register("scripture",
            () -> new ScriptureItem(new Item.Properties().rarity(Rarity.RARE).fireResistant().stacksTo(1), "prayer_of_fast_recovery"));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }

    // TODO NEXT TIME
    // - Turn the scripture item into a single item that reads its state from NBT and can generate addon scriptures as well.

}
