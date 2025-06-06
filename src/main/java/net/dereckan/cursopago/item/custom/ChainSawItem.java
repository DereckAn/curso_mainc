package net.dereckan.cursopago.item.custom;

import net.dereckan.cursopago.components.ModDataComponentTypes;
import net.dereckan.cursopago.sound.ModSounds;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
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
     * <p>
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

                context.getStack().set(ModDataComponentTypes.COORDINATES, context.getBlockPos());
                context.getWorld().playSound(null, context.getBlockPos(),
                                             ModSounds.CHAINSAW_CUT,
                                             SoundCategory.BLOCKS, 1f,1f );
            } else {
                context.getWorld().playSound(null, context.getBlockPos(),
                                             ModSounds.CHAINSAW_PULL,
                                             SoundCategory.BLOCKS, 1f,1f );
            }
        }

        // Indica que la acción fue exitosa y consumió un uso
        return ActionResult.CONSUME;
    }

    /**
     * Maneja el uso del item cuando se hace clic derecho en el aire.
     * Limpia las coordenadas guardadas del último bloque roto.
     *
     * @param world El mundo donde se usa el item
     * @param user El jugador que usa el item
     * @param hand La mano con la que se usa el item
     * @return TypedActionResult con el resultado de la acción
     */
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getEquippedStack(EquipmentSlot.MAINHAND);

        if (stack.get(ModDataComponentTypes.COORDINATES) != null) {
            stack.set(ModDataComponentTypes.COORDINATES, null);
        }
        return TypedActionResult.success(stack);
    }


    /**
     * Agrega información adicional al tooltip del item.
     * Muestra información diferente dependiendo si:
     * - Se mantiene presionada la tecla Shift
     * - Hay coordenadas guardadas de un bloque roto
     *
     * @param stack El ItemStack actual
     * @param context Contexto del tooltip
     * @param tooltip Lista de textos a mostrar en el tooltip
     * @param type Tipo de tooltip
     */
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (!Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("tooltip.cursoid.chainsaw.tooltip.shift", Text.of("Shift"), " for more information"));
        } else {
            tooltip.add(Text.of("tooltip.cursoid.chainsaw.tooltip.shift 1"));
            tooltip.add(Text.of("tooltip.cursoid.chainsaw.tooltip.shift 2"));
            tooltip.add(Text.of("tooltip.cursoid.chainsaw.tooltip.shift 3"));
        }

        if (stack.get(ModDataComponentTypes.COORDINATES) != null) {
            tooltip.add(Text.literal("Last block broken: " + stack.get(ModDataComponentTypes.COORDINATES)));
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}