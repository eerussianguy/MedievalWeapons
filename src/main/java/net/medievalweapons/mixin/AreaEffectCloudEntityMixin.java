package net.medievalweapons.mixin;

import java.util.Iterator;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.At;

import net.medievalweapons.init.ParticleInit;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.LivingEntity;

@Mixin(AreaEffectCloud.class)
public abstract class AreaEffectCloudEntityMixin {
    @Shadow
    private LivingEntity owner;

    @Inject(method = "Lnet/minecraft/entity/AreaEffectCloudEntity;tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffect;applyInstantEffect(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/LivingEntity;ID)V"), cancellable = true, locals = LocalCapture.CAPTURE_FAILSOFT)
    private void tickMixin(CallbackInfo info, boolean bl, float f, List<MobEffectInstance> list, List<LivingEntity> list2, Iterator<LivingEntity> var6, LivingEntity livingEntity) {
        if (livingEntity.equals(owner) && getParticleType().equals(ParticleInit.HEALING_AURA_PARTICLE)) {
            info.cancel();
        }
    }

    @Shadow
    public ParticleOptions getParticleType() {
        return null;
    }

}
