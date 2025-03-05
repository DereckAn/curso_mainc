package net.dereckan.cursopago;

import net.dereckan.cursopago.block.ModBlocks;
import net.dereckan.cursopago.command.ReturnHomeCommand;
import net.dereckan.cursopago.command.SetHomeCommand;
import net.dereckan.cursopago.components.ModDataComponentTypes;
import net.dereckan.cursopago.event.AttackEntityHandler;
import net.dereckan.cursopago.event.PlayerCopyHandler;
import net.dereckan.cursopago.item.ModItemGroups;
import net.dereckan.cursopago.item.ModItems;
import net.dereckan.cursopago.util.HammerUsageEvent;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.ComposterBlock;
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
        ModDataComponentTypes.registerDataComponentTypes();
        FuelRegistry.INSTANCE.add(ModItems.STARLIGHT_ASHES, 600);
        PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());
        AttackEntityCallback.EVENT.register(new AttackEntityHandler());
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.STRAWBERRY, 0.5F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ModItems.STRAWBERRY_SEEDS, 0.25F);
        CommandRegistrationCallback.EVENT.register(SetHomeCommand::register);
        CommandRegistrationCallback.EVENT.register(ReturnHomeCommand::register);
        ServerPlayerEvents.COPY_FROM.register(new PlayerCopyHandler());
    }
}