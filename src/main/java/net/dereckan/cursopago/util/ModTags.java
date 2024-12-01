package net.dereckan.cursopago.util;

import net.dereckan.cursopago.CursoDeMinecraftPago;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        private static TagKey<Block> createTag(String id) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(CursoDeMinecraftPago.MOD_ID, id));
        }
    }

    public static class Items {
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(CursoDeMinecraftPago.MOD_ID, name));
        }
    }
}
