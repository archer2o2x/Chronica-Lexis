package com.archer2o2x.chronica_lexis.datagen.items;

import com.archer2o2x.chronica_lexis.ChronicaLexisMod;
import com.archer2o2x.chronica_lexis.items.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {


    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ChronicaLexisMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        simpleItem(ModItems.CHRONICHA_LEXIS_TOME);
        simpleItem(ModItems.THEORICA_TEMPORALIS_TOME);
        simpleItem(ModItems.STIRRING_THEORICA_TEMPORALIS_TOME);
        simpleItem(ModItems.AWAKENED_THEORICA_TEMPORALIS_TOME);

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(ChronicaLexisMod.MODID, "item/" + item.getId().getPath()));
    }
}
