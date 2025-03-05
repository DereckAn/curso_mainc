package net.dereckan.cursopago.event;

import net.dereckan.cursopago.util.IEntityDataSaver;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerCopyHandler implements ServerPlayerEvents.CopyFrom{
    @Override
    public void copyFromPlayer(ServerPlayerEntity serverPlayerEntity, ServerPlayerEntity serverPlayerEntity1, boolean b) {
        ((IEntityDataSaver) serverPlayerEntity1).getPersistantData().putIntArray("cursopago.homepos",
                ((IEntityDataSaver) serverPlayerEntity).getPersistantData().getIntArray("cursopago.homepos"));
    }
}
