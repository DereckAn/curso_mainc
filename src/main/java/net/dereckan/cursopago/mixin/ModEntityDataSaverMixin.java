package net.dereckan.cursopago.mixin;

import net.dereckan.cursopago.util.IEntityDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class ModEntityDataSaverMixin implements IEntityDataSaver {

    private NbtCompound persistantData;

    @Override
    public NbtCompound getPersistantData() {
        if (persistantData == null)
            persistantData = new NbtCompound();

        return persistantData;
    }

    @Inject(method = "writeNbt", at = @At("HEAD"))
    protected void injectWriteMethod(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> info) {
        if (this.persistantData != null) {
            nbt.put("cursopago.custom_data", persistantData);
        }

    }

    @Inject(method = "readNbt", at = @At("HEAD"))
    protected void  injectReadMethod(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains("cursopago.custom_data", 10)) {
            this.persistantData = nbt.getCompound("cursopago.custom_data");
        }

    }


}
