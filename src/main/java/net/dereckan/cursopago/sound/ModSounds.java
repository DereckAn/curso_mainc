package net.dereckan.cursopago.sound;

import net.dereckan.cursopago.CursoDeMinecraftPago;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {

	public static final SoundEvent CHAINSAW_CUT = registerSoundEvent("chainsaw_cut");
	public static final SoundEvent CHAINSAW_PULL = registerSoundEvent(
				"chainsaw_pull");

	private static SoundEvent registerSoundEvent(String name){
		return Registry.register(Registries.SOUND_EVENT, Identifier.of(CursoDeMinecraftPago.MOD_ID, name),
														 SoundEvent.of(Identifier.of(CursoDeMinecraftPago.MOD_ID, name)));
	}


	public static void registerSounds() {
		CursoDeMinecraftPago.LOGGER.info("Registering Mod Sounds for " + CursoDeMinecraftPago.MOD_ID);
	}
}
