package net.dereckan.cursopago.item.custom;

import net.dereckan.cursopago.components.ModDataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

/**
 * Implementación de una tabla de datos como item personalizado en Minecraft.
 * Esta clase extiende de Item para crear un objeto que puede almacenar y mostrar
 * información sobre bloques encontrados.
 * <p>
 * Características principales:
 * - Almacena información sobre bloques encontrados
 * - Muestra un efecto de brillo cuando contiene datos
 * - Puede limpiar sus datos almacenados
 * - Muestra la información en el tooltip
 *
 * @author [Tu nombre]
 * @version 1.0
 */
public class DataTableItem extends Item {

    /**
     * Constructor para la tabla de datos.
     * Inicializa el item con las configuraciones básicas heredadas de Item.
     *
     * @param settings Configuraciones del item como durabilidad, grupo creativo, etc.
     */
    public DataTableItem(Settings settings) {
        super(settings);
    }

    /**
     * Maneja el uso del item cuando el jugador hace clic derecho.
     * Si el item contiene datos de un bloque encontrado, los limpia.
     *
     * @param world El mundo donde se usa el item
     * @param user  El jugador que usa el item
     * @param hand  La mano con la que se usa el item
     * @return TypedActionResult con el resultado de la acción
     */
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (stack.get(ModDataComponentTypes.FOUND_BLOCK) != null) {
            stack.set(ModDataComponentTypes.FOUND_BLOCK, null);
        }
        return super.use(world, user, hand);
    }

    /**
     * Determina si el item debe mostrar el efecto de brillo encantado.
     * El item brillará cuando contenga datos de un bloque encontrado.
     *
     * @param stack El ItemStack a verificar
     * @return true si el item contiene datos de un bloque, false en caso contrario
     */
    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.get(ModDataComponentTypes.FOUND_BLOCK) != null;
    }

    /**
     * Agrega información adicional al tooltip del item.
     * Si el item contiene datos de un bloque encontrado, muestra esa información
     * en el tooltip.
     *
     * @param stack   El ItemStack actual
     * @param context Contexto del tooltip
     * @param tooltip Lista de textos a mostrar en el tooltip
     * @param type    Tipo de tooltip
     */
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (stack.get(ModDataComponentTypes.FOUND_BLOCK) != null) {
            tooltip.add(Text.literal(Objects.requireNonNull(stack.get(ModDataComponentTypes.FOUND_BLOCK)).getOutputString()));
        }
    }
}