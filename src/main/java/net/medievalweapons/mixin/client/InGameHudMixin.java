package net.medievalweapons.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.fabricmc.api.Environment;
import net.medievalweapons.access.PlayerAccess;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.world.entity.HumanoidArm;
import net.fabricmc.api.EnvType;

@Environment(EnvType.CLIENT)
@Mixin(Gui.class)
public abstract class InGameHudMixin extends GuiComponent {

    @Shadow
    @Final
    @Mutable
    private final Minecraft client;
    @Shadow
    private int scaledWidth;
    @Shadow
    private int scaledHeight;

    public InGameHudMixin(Minecraft client) {
        this.client = client;
    }

    @Inject(method = "renderCrosshair", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getAttackCooldownProgress(F)F", shift = Shift.AFTER))
    private void renderCrosshairMixin(PoseStack matrices, CallbackInfo info) {
        float o = ((PlayerAccess) this.client.player).getAttackCooldownProgressOffhand(1.0F);
        if (o < 1.0F) {
            int u = (int) (o * 17.0F);
            this.blit(matrices, this.scaledWidth / 2 - 8, this.scaledHeight / 2 - 7 + 16 + 8, 36, 94, 16, 4);
            this.blit(matrices, this.scaledWidth / 2 - 8, this.scaledHeight / 2 - 7 + 16 + 8, 52, 94, u, 4);
        }
    }
    // todo: overlay

    @Inject(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getAttackCooldownProgress(F)F", shift = Shift.AFTER))
    private void renderHotbar(float tickDelta, PoseStack matrices, CallbackInfo info) {
        float o = ((PlayerAccess) this.client.player).getAttackCooldownProgressOffhand(1.0F);
        if (o < 1.0F) {
            HumanoidArm arm = this.client.player.getMainArm().getOpposite();
            int r = (this.scaledWidth / 2) + 91 + 6;
            if (arm == HumanoidArm.RIGHT) {
                r = (this.scaledWidth / 2) - 91 - 22;
            }
            int s = (int) (o * 19.0F);
            RenderSystem.setShaderTexture(0, GuiComponent.GUI_ICONS_LOCATION);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            blit(matrices, r + 18, this.scaledHeight - 20, 0, 94, 18, 18);
            blit(matrices, r + 18, this.scaledHeight - 20 + 18 - s, 18, 112 - s, 18, s);
        }
    }

}
