package net.dereckan.cursopago.block.custom;

import net.dereckan.cursopago.item.ModItems;
import net.dereckan.cursopago.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

/**
 * Implementación de un bloque mágico personalizado en Minecraft.
 * Este bloque tiene propiedades especiales como reproducir sonidos al usarlo
 * y transformar items específicos en diamantes cuando caen sobre él.
 */
public class MagicBlock extends Block {

    /**
     * Constructor del bloque mágico.
     * Inicializa el bloque con las configuraciones básicas heredadas de Block.
     *
     * @param settings Configuraciones del bloque como dureza, sonidos, etc.
     */
    public MagicBlock(Settings settings) {
        super(settings);
    }

    /**
     * Maneja la interacción cuando un jugador hace clic derecho en el bloque.
     * Reproduce un sonido similar al del bloque de amatista.
     *
     * @param state  Estado actual del bloque
     * @param world  Mundo donde se encuentra el bloque
     * @param pos    Posición del bloque en el mundo
     * @param player Jugador que interactúa con el bloque
     * @param hit    Información sobre el punto exacto donde se hizo clic
     * @return ActionResult.SUCCESS indicando que la interacción fue exitosa
     */
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        // Reproduce un sonido de campana de amatista
        world.playSound(
                player,             // Entidad que escuchará el sonido
                pos,               // Posición donde se reproduce el sonido
                SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME,  // Tipo de sonido
                SoundCategory.BLOCKS,  // Categoría del sonido
                1.0F,              // Volumen
                1.0F               // Tono
        );
        return ActionResult.SUCCESS;
    }

    /**
     * Maneja eventos cuando una entidad pisa o cae sobre el bloque.
     * Si la entidad es un item y cumple ciertos criterios, lo transforma en diamantes.
     *
     * @param world  Mundo donde ocurre la interacción
     * @param pos    Posición del bloque
     * @param state  Estado actual del bloque
     * @param entity Entidad que interactúa con el bloque
     */
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        // Verifica si la entidad es un item
        if (entity instanceof ItemEntity item) {
            // Verifica si el item es válido para la transformación
            if (isValidItem(item.getStack())) {
                // Transforma el item en diamantes manteniendo la cantidad
                item.setStack(new ItemStack(Items.DIAMOND, item.getStack().getCount()));
            }
        }
        // Llama al método de la clase padre para mantener comportamiento base
        super.onSteppedOn(world, pos, state, entity);
    }

    /**
     * Verifica si un item es válido para ser transformado en diamantes.
     * Los items válidos son:
     * - Fluorita
     * - Fluorita cruda
     * - Carbón
     *
     * @param stack El ItemStack a verificar
     * @return true si el item puede ser transformado, false en caso contrario
     */
    private boolean isValidItem(ItemStack stack) {
//        return stack.getItem() == ModItems.FLUORITE ||
//                stack.getItem() == ModItems.RAW_FLUORITE ||
//                stack.getItem() == Items.COAL;
        return stack.isIn(ModTags.Items.TRANSFORMABLE_ITEMS);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        tooltip.add(Text.translatable("tooltip.cursoid.magic_block.1"));
        super.appendTooltip(stack, context, tooltip, options);
    }
}