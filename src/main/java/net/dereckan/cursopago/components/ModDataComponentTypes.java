package net.dereckan.cursopago.components;

import net.dereckan.cursopago.CursoDeMinecraftPago;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.function.UnaryOperator;


public class ModDataComponentTypes {
    public static final ComponentType<BlockPos> COORDINATES = register("coordinates",
            builder -> builder.codec(BlockPos.CODEC));

    public static final ComponentType<FoundBlockData> FOUND_BLOCK = register("found_block",
            builder -> builder.codec(FoundBlockData.CODEC));


    private static <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(CursoDeMinecraftPago.MOD_ID, name),
                (builderOperator.apply(ComponentType.builder())).build());
    }

    public static void registerDataComponentTypes() {
        CursoDeMinecraftPago.LOGGER.info("Registering data component types for " + CursoDeMinecraftPago.MOD_ID);
    }
}
