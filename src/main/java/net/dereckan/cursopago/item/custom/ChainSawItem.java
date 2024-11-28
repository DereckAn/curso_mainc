package net.dereckan.cursopago.item.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.Objects;

/**
 * Implementación de una motosierra como item personalizado en Minecraft.
 * Esta clase extiende de Item para crear una herramienta que puede romper troncos
 * instantáneamente al hacer clic derecho.
 *
 * @author El deret
 * @version 1.0
 */
public class ChainSawItem extends Item {

    /**
     * Constructor para la motosierra.
     * Inicializa el item con las configuraciones básicas heredadas de Item.
     *
     * @param settings Configuraciones del item como durabilidad, grupo creativo, etc.
     */
    public ChainSawItem(Settings settings) {
        super(settings);
    }

    /**
     * Maneja la interacción cuando el jugador hace clic derecho con la motosierra en un bloque.
     * Este método se ejecuta cada vez que el jugador usa la motosierra en un bloque.
     *
     * Funcionalidad:
     * - Verifica que la acción ocurra en el servidor
     * - Comprueba si el bloque objetivo es un tronco
     * - Rompe el bloque si es un tronco
     * - Aplica daño al item
     *
     * @param context Contiene toda la información sobre el uso del item:
     *                - El mundo donde se usa
     *                - La posición del bloque
     *                - El jugador que lo usa
     *                - El item siendo usado
     * @return ActionResult.CONSUME si la acción fue exitosa
     */
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        // Obtiene el mundo donde se está usando el item
        World world = context.getWorld();

        // Verifica que estemos en el lado del servidor para evitar desincronización
        if (!world.isClient) {
            // Comprueba si el bloque es un tronco usando BlockTags
            if (world.getBlockState(context.getBlockPos()).isIn(BlockTags.LOGS)) {
                // Rompe el bloque y dropea sus items (true indica que debe soltar items)
                world.breakBlock(context.getBlockPos(), true, context.getPlayer());

                // Aplica daño al item después de usarlo
                context.getStack().damage(1, // Cantidad de daño
                        ((ServerWorld) world), // Conversión a ServerWorld
                        ((ServerPlayerEntity) context.getPlayer()), // Conversión a ServerPlayerEntity
                        item -> Objects.requireNonNull(context.getPlayer())
                                .sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND) // Muestra animación de rotura
                );
            }
        }

        // Indica que la acción fue exitosa y consumió un uso
        return ActionResult.CONSUME;
    }
}