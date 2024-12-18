package net.dereckan.cursopago.item;

import net.dereckan.cursopago.CursoDeMinecraftPago;
import net.dereckan.cursopago.item.custom.ChainSawItem;
import net.dereckan.cursopago.item.custom.HammerItem;
import net.dereckan.cursopago.item.custom.ModEffectSwordItem;
import net.dereckan.cursopago.item.custom.PaxelItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModItems {

    // Aqui estamos creando nuevos items.
    public static final Item FLUORITE = registerItem("fluorite", new Item(new Item.Settings()));
    public static final Item RAW_FLUORITE = registerItem("raw_fluorite", new Item(new Item.Settings()));
    public static final Item CHAINSAW = registerItem("chainsaw", new ChainSawItem(new Item.Settings().maxDamage(32)));
    public static final Item STRAWBERRY = registerItem("strawberry", new Item(new Item.Settings().food(ModFoodComponent.STRAWBERRY)) {
        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("tooltip.cursoid.strawberry.tooltip.1"));
            super.appendTooltip(stack, context, tooltip, type);
        }
    });
    public static final Item STARLIGHT_ASHES = registerItem("starlight_ashes", new Item(new Item.Settings()));

    public static final Item FLUORITE_SWORD = registerItem("fluorite_sword", new ModEffectSwordItem(ModToolMaterials.FLUORITE,
            new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.FLUORITE, 3, -2.4F)), StatusEffects.LEVITATION));
    public static final Item FLUORITE_PICKAXE = registerItem("fluorite_pickaxe", new PickaxeItem(ModToolMaterials.FLUORITE,
            new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.FLUORITE, 1, -2.8F))));
    public static final Item FLUORITE_SHOVEL = registerItem("fluorite_shovel", new ShovelItem(ModToolMaterials.FLUORITE,
            new Item.Settings().attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.FLUORITE, 1.5f, -3F))));
    public static final Item FLUORITE_AXE = registerItem("fluorite_axe", new AxeItem(ModToolMaterials.FLUORITE,
            new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.FLUORITE, 6, -3.2F))));
    public static final Item FLUORITE_HOE = registerItem("fluorite_hoe", new HoeItem(ModToolMaterials.FLUORITE,
            new Item.Settings().attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.FLUORITE, 0, -3F))));
    public static final Item FLUORITE_PAXEL = registerItem("fluorite_paxel", new PaxelItem(ModToolMaterials.FLUORITE,
            new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.FLUORITE, 4, -2.3F))));
    public static final Item FLUORITE_HAMMER = registerItem("fluorite_hammer", new HammerItem(ModToolMaterials.FLUORITE,
            new Item.Settings().attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.FLUORITE, 8, -3.3F))));

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
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::customIngredients);
    }
}
