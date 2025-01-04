package net.dereckan.cursopago.item.custom;

import net.dereckan.cursopago.components.FoundBlockData;
import net.dereckan.cursopago.components.ModDataComponentTypes;
import net.dereckan.cursopago.item.ModItems;
import net.dereckan.cursopago.util.InventoryUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

/**
 * Implementación de un detector de metales como item personalizado en Minecraft.
 * Esta clase extiende de Item para crear una herramienta que puede detectar
 * bloques valiosos (como menas de hierro) bajo la superficie.
 * <p>
 * Características principales:
 * - Detecta bloques valiosos bajo el punto de click
 * - Emite sonidos al encontrar bloques
 * - Puede guardar información en una tableta de datos
 * - Muestra coordenadas de los bloques encontrados
 *
 * @author [Tu nombre]
 * @version 1.0
 */
public class MetalDetectorItem extends Item {

    /**
     * Constructor para el detector de metales.
     * Inicializa el item con las configuraciones básicas heredadas de Item.
     *
     * @param settings Configuraciones del item como durabilidad, grupo creativo, etc.
     */
    public MetalDetectorItem(Settings settings) {
        super(settings);
    }


    /**
     * Maneja la interacción cuando el jugador hace clic derecho con el detector en un bloque.
     * Busca bloques valiosos desde el punto de click hacia abajo.
     * <p>
     * El método realiza las siguientes acciones:
     * - Verifica que la acción ocurra en el servidor
     * - Busca bloques valiosos hacia abajo desde el punto clickeado
     * - Notifica al jugador si encuentra algo valioso
     * - Guarda la información en una tableta de datos si el jugador tiene una
     * - Reproduce un sonido al encontrar un bloque valioso
     *
     * @param context Contexto de uso que contiene información sobre la interacción
     * @return ActionResult.SUCCESS si la acción se completó correctamente
     */
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient()) {
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            boolean foundBlock = false;

            for (int i = 0; i <= positionClicked.getY(); i++) {
                BlockState stateBelow = context.getWorld().getBlockState(positionClicked.down(i));
                Block blockBelow = stateBelow.getBlock();

                if (isValuableBlock(stateBelow)) {
                    outputValuableCoordinates(positionClicked.add(0, -i, 0), player, blockBelow);
                    foundBlock = true;

                    if (InventoryUtil.hasPlayerStackInInventory(player, ModItems.DATA_TABLET)) {
                        addComponentToStack(player, positionClicked.add(0, -i, 0), blockBelow);
                    }

                    context.getWorld().playSound(null, positionClicked, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.BLOCKS, 0.5f, 1f);

                    break;
                }
            }

            if (!foundBlock) {
                assert player != null;
                player.sendMessage(Text.translatable("item.cursoid.metal_detector.no_valuables"), false);
            }
        }

        return ActionResult.SUCCESS;
    }

    /**
     * Agrega la información del bloque encontrado a una tableta de datos.
     *
     * @param player     Jugador que tiene la tableta de datos
     * @param pos        Posición del bloque encontrado
     * @param blockBelow Bloque valioso encontrado
     */
    private void addComponentToStack(PlayerEntity player, BlockPos pos, Block blockBelow) {
        ItemStack dataTablet =
                player.getInventory().getStack(InventoryUtil.getFirstInventoryIndex(player, ModItems.DATA_TABLET));

        FoundBlockData data = new FoundBlockData(blockBelow.getDefaultState(), pos);
        dataTablet.set(ModDataComponentTypes.FOUND_BLOCK, data);
    }

    /**
     * Envía un mensaje al jugador con las coordenadas del bloque valioso encontrado.
     *
     * @param blockPos   Posición del bloque encontrado
     * @param player     Jugador que recibirá el mensaje
     * @param blockBelow Bloque valioso encontrado
     */
    private void outputValuableCoordinates(BlockPos blockPos, PlayerEntity player, Block blockBelow) {
        player.sendMessage(Text.literal("Found " + blockBelow.asItem().getName().getString() + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + "," + blockPos.getZ() + ")"), false);
    }

    /**
     * Verifica si un bloque es considerado valioso para el detector.
     * Actualmente solo detecta menas de hierro.
     *
     * @param block Estado del bloque a verificar
     * @return true si el bloque es valioso, false en caso contrario
     */
    private boolean isValuableBlock(BlockState block) {
        return block.isOf(Blocks.IRON_ORE);
    }
}