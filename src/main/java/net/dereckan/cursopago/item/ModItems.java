package net.dereckan.cursopago.item;

import net.dereckan.cursopago.CursoDeMinecraftPago;
import net.dereckan.cursopago.item.custom.ChainSawItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    // Aqui estamos creando nuevos items.
    public static final Item FLUORITE = registerItem("fluorite", new Item(new Item.Settings()));
    public static final Item RAW_FLUORITE = registerItem("raw_fluorite", new Item(new Item.Settings()));
    public static final Item CHAINSAW = registerItem("chainsaw", new ChainSawItem(new Item.Settings().maxDamage(32)));
    public static final Item STRAWBERRY = registerItem("strawberry", new Item(new Item.Settings().food(ModFoodComponent.STRAWBERRY)));

    /**
     * Este metodo lo reusaremos para cuando queramos crear nuevos items.
     * Este metodo tomara dos parametros name e item, y los registrara en el registro de Minecraft
     * Registries.ITEM: El registro de items de Minecraft
     * Identifier.of(): Crea un identificador único para el item usando el ID del mod y el nombre
     * Retorna el Item registrado.
     */
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(CursoDeMinecraftPago.MOD_ID, name), item);
    }

    /**
     * customIngredients es un método que organiza y agrupa los items FLUORITE y RAW_FLUORITE para que aparezcan juntos en un grupo específico del inventario creativo, mejorando la experiencia del usuario al interactuar con los nuevos items del mod.
     */
    private static void customIngredients(FabricItemGroupEntries entries) {
//        entries.add(FLUORITE);
//        entries.add(RAW_FLUORITE);
    }

    /**
     * Se usa para inicializar todos los items del mod
     * Actualmente solo está registrando un mensaje en el log indicando que se están registrando los items
     * Es el punto de entrada para registrar todos los items del mod
     */
    public static void registerModItems() {
        CursoDeMinecraftPago.LOGGER.info("Registering Mod Items for " + CursoDeMinecraftPago.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems:: customIngredients);
    }
}
