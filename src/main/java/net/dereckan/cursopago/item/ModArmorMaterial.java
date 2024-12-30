package net.dereckan.cursopago.item;

import net.dereckan.cursopago.CursoDeMinecraftPago;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterial {

    public static final RegistryEntry<ArmorMaterial> FLUORITE_ARMOR_MATERIAL = registerArmorMaterial(
            "fluorite",
            () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.BOOTS, 2);      //the number means the defend values for the armor
                        map.put(ArmorItem.Type.LEGGINGS, 4);
                        map.put(ArmorItem.Type.CHESTPLATE, 6);
                        map.put(ArmorItem.Type.HELMET, 2);
                        map.put(ArmorItem.Type.BODY, 4);       //this is for the entities, not the player
                    }),
                    20,
                    SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
                    () -> Ingredient.ofItems(ModItems.FLUORITE),
                    List.of(new ArmorMaterial.Layer(
                            Identifier.of(CursoDeMinecraftPago.MOD_ID, "fluorite")
                    )),
                    0,
                    0
            ));

    // This method is to register de material into the game.
    public static RegistryEntry<ArmorMaterial> registerArmorMaterial(
            String name,
            Supplier<ArmorMaterial> material
    ) {
        return Registry.registerReference(
                Registries.ARMOR_MATERIAL,
                Identifier.of(CursoDeMinecraftPago.MOD_ID, name),
                material.get()
        );
    }
}