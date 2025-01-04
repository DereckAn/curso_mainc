package net.dereckan.cursopago.util;

import net.dereckan.cursopago.CursoDeMinecraftPago;
import net.dereckan.cursopago.components.ModDataComponentTypes;
import net.dereckan.cursopago.item.ModItems;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class ModModelPredicates {
    public static void registerModelPredicates() {
        ModelPredicateProviderRegistry.register(ModItems.DATA_TABLET, Identifier.of(CursoDeMinecraftPago.MOD_ID, "on"),
                (stack, world, entity, seed) -> stack.get(ModDataComponentTypes.FOUND_BLOCK) != null ? 1.0F : 0.0F);
    }
}
