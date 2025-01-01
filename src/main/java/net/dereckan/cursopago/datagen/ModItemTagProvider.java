package net.dereckan.cursopago.datagen;

import net.dereckan.cursopago.item.ModItems;
import net.dereckan.cursopago.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.MinecartItem;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

/**
 * Custom Item Tag Provider for the mod that handles the generation of item tags.
 * Item tags are used to group items together for recipes, loot tables, and other game mechanics.
 * Extends FabricTagProvider.ItemTagProvider to utilize Fabric's data generation system.
 */
public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {

    /**
     * Constructor for the ModItemTagProvider.
     * Sets up the data generator for item tags.
     *
     * @param output            The FabricDataOutput instance that handles data generation output
     * @param completableFuture A CompletableFuture containing the registry wrapper lookup
     *                          Used to access Minecraft's registry system asynchronously
     */
    public ModItemTagProvider(FabricDataOutput output,
                              CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    /**
     * Configures all item tags for the mod.
     * This method is called during data generation to define which items belong to which tags.
     *
     * @param wrapperLookup The registry wrapper lookup containing registry information
     */
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // Creates or gets the TRANSFORMABLE_ITEMS tag and adds items to it
        // This tag likely defines items that can be transformed in some way in the mod
        getOrCreateTagBuilder(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.FLUORITE)        // Adds custom fluorite item
                .add(ModItems.RAW_FLUORITE)    // Adds raw fluorite item
                .add(Items.COAL)               // Adds vanilla minecraft coal
                .add(Items.STICK)              // Adds vanilla minecraft stick
                .add(Items.APPLE);             // Adds vanilla minecraft apple

        // Creates or gets the vanilla TRIMMABLE_ARMOR tag and adds mod's armor items to it
        // This tag is used by Minecraft to determine which armor pieces can receive trim patterns
        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.FLUORITE_HELMET)      // Adds fluorite helmet to trimmable armor
                .add(ModItems.FLUORITE_CHESTPLATE)  // Adds fluorite chestplate to trimmable armor
                .add(ModItems.FLUORITE_LEGGINGS)    // Adds fluorite leggings to trimmable armor
                .add(ModItems.FLUORITE_BOOTS);      // Adds fluorite boots to trimmable armor
    }
}
