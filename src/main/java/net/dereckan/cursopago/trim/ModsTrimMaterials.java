package net.dereckan.cursopago.trim;

import net.dereckan.cursopago.CursoDeMinecraftPago;
import net.dereckan.cursopago.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.trim.ArmorTrimMaterial;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class ModsTrimMaterials {
    public static final RegistryKey<ArmorTrimMaterial> FLUORITE = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(CursoDeMinecraftPago.MOD_ID, "fluorite"));

    public static void bootstrap(Registerable<ArmorTrimMaterial> registerable) {
        register(registerable, FLUORITE, Registries.ITEM.getEntry(ModItems.FLUORITE),
                Style.EMPTY.withColor(TextColor.parse("#b03fe0").getOrThrow()), 1.0F);

    }

    private static void register(Registerable<ArmorTrimMaterial> registerable,
                                 RegistryKey<ArmorTrimMaterial> armorTrimKey, RegistryEntry<Item> item,
                                 Style style, float itemModelIndex) {

        ArmorTrimMaterial trimmaterial = new ArmorTrimMaterial(armorTrimKey.getValue().getPath(), item,
                itemModelIndex, Map.of(), Text.translatable(Util.createTranslationKey("trim_material",
                armorTrimKey.getValue())).fillStyle(style));

        registerable.register(armorTrimKey, trimmaterial);
    }
}