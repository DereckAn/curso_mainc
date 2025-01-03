package net.dereckan.cursopago.datagen;


import net.dereckan.cursopago.block.ModBlocks;
import net.dereckan.cursopago.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {
    public ModLootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.FLUORITE_BLOCK);
        addDrop(ModBlocks.MAGIN_BLOCK);

        addDrop(ModBlocks.FLUORITE_ORE, oreDrops(ModBlocks.FLUORITE_ORE, ModItems.RAW_FLUORITE));
        addDrop(ModBlocks.FLUORITE_DEEPSLATE_ORE, customOreDrops(ModBlocks.FLUORITE_DEEPSLATE_ORE, ModItems.RAW_FLUORITE, 2, 4));
        addDrop(ModBlocks.FLUORITE_NETHER_ORE, customOreDrops(ModBlocks.FLUORITE_NETHER_ORE, ModItems.RAW_FLUORITE, 3, 6));
        addDrop(ModBlocks.FLUORITE_END_ORE, customOreDrops(ModBlocks.FLUORITE_END_ORE, ModItems.RAW_FLUORITE, 3, 6));

        addDrop(ModBlocks.FLUORITE_STAIRS);
        addDrop(ModBlocks.FLUORITE_SLAB,slabDrops(ModBlocks.FLUORITE_SLAB));

        addDrop(ModBlocks.FLUORITE_DOOR, doorDrops(ModBlocks.FLUORITE_DOOR));
        addDrop(ModBlocks.FLUORITE_TRAPDOOR);

        addDrop(ModBlocks.FLUORITE_LAMP);
    }

    public LootTable.Builder customOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(
                drop,
                this.applyExplosionDecay(
                        drop,
                        ItemEntry.builder(item)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops)))
                                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }
}
