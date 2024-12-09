package net.dereckan.cursopago;

import net.dereckan.cursopago.block.ModBlocks;
import net.dereckan.cursopago.item.ModItemGroups;
import net.dereckan.cursopago.item.ModItems;
import net.dereckan.cursopago.util.HammerUsageEvent;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CursoDeMinecraftPago implements ModInitializer {
    public static final String MOD_ID = "cursoid";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItemGroups.registerItemGroups();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 600);
        PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());
    }
}