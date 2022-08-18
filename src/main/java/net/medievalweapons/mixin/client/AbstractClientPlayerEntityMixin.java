package net.medievalweapons.mixin.client;

import com.mojang.authlib.GameProfile;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.At;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.medievalweapons.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

@Environment(EnvType.CLIENT)
@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerEntityMixin extends Player {

    public AbstractClientPlayerEntityMixin(Level world, BlockPos pos, float yaw, GameProfile profile) {
        super(world, pos, yaw, profile);
    }

    @Inject(method = "getFovMultiplier", at = @At(value = "TAIL"), locals = LocalCapture.CAPTURE_FAILSOFT, cancellable = true)
    private void getFovMultiplierMixin(CallbackInfoReturnable<Float> info, float f) {
        Item item = this.getUseItem().getItem();
        if (this.isUsingItem() && (item == ItemInit.LONG_BOW_ITEM || item == ItemInit.RECURVE_BOW_ITEM)) {
            int i = this.getTicksUsingItem();
            float g;
            if (item == ItemInit.LONG_BOW_ITEM) {
                g = (float) i / 50.0F;
            } else {
                g = (float) i / 16.0F;
            }

            if (g > 1.0F) {
                g = 1.0F;
            } else {
                g *= g;
            }

            f *= 1.0F - g * 0.15F;
            info.setReturnValue(Mth.lerp(Minecraft.getInstance().options.fovEffectScale, 1.0F, f));
        }

    }

}
