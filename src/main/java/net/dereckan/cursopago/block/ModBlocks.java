package net.dereckan.cursopago.block;

import net.dereckan.cursopago.CursoDeMinecraftPago;
import net.dereckan.cursopago.block.custom.FluoriteLampBlock;
import net.dereckan.cursopago.block.custom.MagicBlock;
import net.dereckan.cursopago.block.custom.StrawberryCropBlock;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

/**
 * Clase que maneja el registro de nuevos bloques para el mod.
 * Esta clase es fundamental para añadir y gestionar nuevos bloques en el juego.
 */
public class ModBlocks {

    /**
     * Bloque de Fluorita con propiedades personalizadas.
     * <p>
     * Propiedades:
     * - Sonido: Similar al bloque de amatista
     * - Dureza: 4.0
     * - Requiere herramienta para ser minado
     */
    public static final Block FLUORITE_BLOCK = registerBlock("fluorite_block",
            new Block(AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .strength(4f)
                    .requiresTool()));

    public static final Block FLUORITE_ORE = registerBlock("fluorite_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 4), AbstractBlock.Settings.create()
                    .strength(4f)
                    .requiresTool()));

    public static final Block FLUORITE_DEEPSLATE_ORE = registerBlock("fluorite_deepslate_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(3, 6), AbstractBlock.Settings.create()
                    .strength(6f)
                    .requiresTool()));

    public static final Block FLUORITE_NETHER_ORE = registerBlock("fluorite_nether_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(3, 6), AbstractBlock.Settings.create()
                    .strength(6f)
                    .requiresTool()));

    public static final Block FLUORITE_END_ORE = registerBlock("fluorite_end_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(3, 6), AbstractBlock.Settings.create()
                    .strength(6f)
                    .requiresTool()));

    public static final Block MAGIN_BLOCK = registerBlock("magic_block",
            new MagicBlock(AbstractBlock.Settings.create().strength(1f).requiresTool()));

    public static final Block FLUORITE_STAIRS = registerBlock("fluorite_stairs",
            new StairsBlock(ModBlocks.FLUORITE_BLOCK.getDefaultState(), AbstractBlock.Settings.create().strength(4f)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .requiresTool()));

    public static final Block FLUORITE_SLAB = registerBlock("fluorite_slab",
            new SlabBlock(AbstractBlock.Settings.create().strength(4f)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .requiresTool()));

    public static final Block FLUORITE_BUTTON = registerBlock("fluorite_button",
            new ButtonBlock(BlockSetType.IRON, 10, AbstractBlock.Settings.create().strength(4f)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .requiresTool()));

    public static final Block FLUORITE_PRESSURE_PLATE = registerBlock("fluorite_pressure_plate",
            new PressurePlateBlock(BlockSetType.IRON, AbstractBlock.Settings.create().strength(4f)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .requiresTool()));

    public static final Block FLUORITE_FENCE = registerBlock("fluorite_fence",
            new FenceBlock(AbstractBlock.Settings.create().strength(4f)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .requiresTool()));

    public static final Block FLUORITE_FENCE_GATE = registerBlock("fluorite_fence_gate",
            new FenceGateBlock(WoodType.SPRUCE, AbstractBlock.Settings.create().strength(4f)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .requiresTool()));

    public static final Block FLUORITE_WALL = registerBlock("fluorite_wall",
            new WallBlock(AbstractBlock.Settings.create().strength(4f)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .requiresTool()));

    public static final Block FLUORITE_DOOR = registerBlock("fluorite_door",
            new DoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create().strength(4f)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .nonOpaque()
                    .requiresTool()));

    public static final Block FLUORITE_TRAPDOOR = registerBlock("fluorite_trapdoor",
            new TrapdoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create().strength(4f)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .nonOpaque()
                    .requiresTool()));

    public static final Block FLUORITE_LAMP = registerBlock("fluorite_lamp",
            new FluoriteLampBlock(AbstractBlock.Settings.create().strength(4f)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .requiresTool()
                    .luminance(state -> state.get(FluoriteLampBlock.CLICKDED) ? 15 : 0)));


    public static final Block STRAWBERRY_CROP = registerBlockWithoutBlockItem("strawberry_crop",
        new StrawberryCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT)));


    private static Block registerBlockWithoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(CursoDeMinecraftPago.MOD_ID, name), block);
    }


    /**
     * Registra un nuevo bloque en el juego y su item correspondiente.
     *
     * @param name  El nombre identificador del bloque
     * @param block El bloque a registrar
     * @return El bloque registrado
     */
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(CursoDeMinecraftPago.MOD_ID, name), block);
    }

    /**
     * Crea y registra el item correspondiente a un bloque.
     * Esto permite que el bloque aparezca en el inventario del jugador.
     *
     * @param name  El nombre identificador del item del bloque
     * @param block El bloque para el cual crear el item
     */
    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(CursoDeMinecraftPago.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    /**
     * Método principal para inicializar todos los bloques del mod.
     * Este método debe ser llamado durante la inicialización del mod.
     * <p>
     * Registra un mensaje en el log cuando se inicializan los bloques,
     * sirviendo como punto de entrada para el registro de bloques.
     */
    public static void registerModBlocks() {
        CursoDeMinecraftPago.LOGGER.info("Registering Mod Blocks for " + CursoDeMinecraftPago.MOD_ID);
    }
}