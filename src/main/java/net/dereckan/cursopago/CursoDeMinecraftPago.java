package net.dereckan.cursopago;

import net.dereckan.cursopago.block.ModBlocks;
import net.dereckan.cursopago.item.ModItemGroups;
import net.dereckan.cursopago.item.ModItems;
import net.fabricmc.api.ModInitializer;

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
	}
}