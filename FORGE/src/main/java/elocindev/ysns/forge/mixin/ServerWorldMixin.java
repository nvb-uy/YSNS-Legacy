package elocindev.ysns.forge.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity.RemovalReason;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import elocindev.ysns.forge.YSNS;

@Mixin(ServerLevel.class)
public class ServerWorldMixin { 

    @Inject(method = "addEntity", at = @At("HEAD"), remap = false, cancellable = true)
	private void blacklist(Entity p_8873_, CallbackInfoReturnable<Boolean> info) {
        String id = EntityType.getKey(p_8873_.getType()).toString();
        if (YSNS.Config.blacklisted_entities.contains(id)) {
            p_8873_.remove(RemovalReason.DISCARDED); 
            info.setReturnValue(Boolean.valueOf(false));
        }
	}
}
