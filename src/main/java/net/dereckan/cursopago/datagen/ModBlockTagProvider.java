/**
 * Paquete que contiene las clases relacionadas con la generación de datos del mod
 */
package net.dereckan.cursopago.datagen;

// Importaciones necesarias
import net.dereckan.cursopago.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

/**
 * Clase que proporciona y gestiona los tags de bloques personalizados del mod.
 * Extiende de FabricTagProvider.BlockTagProvider para utilizar la funcionalidad de generación de tags de Fabric.
 */
public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    /**
     * Constructor de la clase ModBlockTagProvider.
     *
     * @param output Objeto FabricDataOutput que maneja la salida de datos generados
     * @param registriesFuture CompletableFuture que contiene el registro de lookups necesario para la generación de tags
     */
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    /**
     * Método que configura los tags de bloques del mod.
     * Este método es llamado durante la generación de datos para establecer las propiedades de minado de los bloques.
     *
     * @param wrapperLookup Objeto WrapperLookup que proporciona acceso al registro de bloques
     */
    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // Define los bloques que pueden ser minados con pico
        // Agrega todos los bloques de fluorita al tag PICKAXE_MINEABLE
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(
                ModBlocks.FLUORITE_BLOCK,
                ModBlocks.FLUORITE_ORE,
                ModBlocks.FLUORITE_DEEPSLATE_ORE,
                ModBlocks.FLUORITE_END_ORE,
                ModBlocks.FLUORITE_NETHER_ORE);

        // Define los bloques que requieren un pico de diamante para ser minados
        // Los minerales del End y Nether requieren herramientas de diamante
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL).add(
                ModBlocks.FLUORITE_END_ORE,      // Mineral de fluorita del End
                ModBlocks.FLUORITE_NETHER_ORE);  // Mineral de fluorita del Nether

        // Define los bloques que requieren un pico de hierro para ser minados
        // El mineral en piedra profunda requiere herramientas de hierro
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL).add(
                ModBlocks.FLUORITE_DEEPSLATE_ORE); // Mineral de fluorita en piedra profunda
    }
}