package elocindev.ysns.fabric_quilt.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity.RemovalReason;
import net.minecraft.server.world.ServerWorld;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import elocindev.ysns.fabric_quilt.YSNS;

@Mixin(ServerWorld.class)
public class ServerWorldMixin { 
	@Inject(at = @At("HEAD"), method = "addEntity", cancellable = true)
	private void blacklist(Entity entity, CallbackInfoReturnable<Boolean> info) {
        String id = EntityType.getId(entity.getType()).toString();
        if (YSNS.Config.blacklisted_entities.contains(id)) {
            entity.remove(RemovalReason.DISCARDED);
            info.setReturnValue(Boolean.valueOf(false));
        }
	}
}
