package net.medievalweapons.mixin.client;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.At;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.medievalweapons.access.PlayerAccess;
import net.medievalweapons.init.TagInit;
import net.medievalweapons.item.Big_Axe_Item;
import net.medievalweapons.item.Long_Sword_Item;
import net.medievalweapons.item.Ninjato_Item;
import net.medievalweapons.network.PlayerPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

@Environment(EnvType.CLIENT)
@Mixin(Minecraft.class)
public class MinecraftClientMixin {
    @Shadow
    @Nullable
    public LocalPlayer player;
    @Shadow
    protected int attackCooldown;
    @Shadow
    @Nullable
    public HitResult crosshairTarget;
    @Shadow
    @Nullable
    public MultiPlayerGameMode interactionManager;
    @Unique
    private int offhandAttackCooldown;
    @Unique
    private boolean attackedOffhand;

    @Inject(method = "Lnet/minecraft/client/MinecraftClient;tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;handleInputEvents()V"))
    public void tickMixin(CallbackInfo info) {
        if (this.offhandAttackCooldown > 0)
            --this.offhandAttackCooldown;
    }

    @Inject(method = "doAttack", at = @At(value = "HEAD"), cancellable = true)
    private void doAttackMixin(CallbackInfoReturnable<Boolean> info) {
        if (player != null) {
            ItemStack itemStack = player.getMainHandItem();
            if ((itemStack.is(TagInit.DOUBLE_HANDED_ITEMS) || itemStack.is(TagInit.ACCROSS_DOUBLE_HANDED_ITEMS) || itemStack.getItem() instanceof Long_Sword_Item
                    || itemStack.getItem() instanceof Big_Axe_Item) && (!player.getOffhandItem().isEmpty() || player.isSwimming() || player.isPassenger()))
                info.setReturnValue(false);

            if (this.offhandAttackCooldown == 0 && this.attackCooldown < 5 && !this.player.isHandsBusy() && itemStack.getItem() instanceof Ninjato_Item
                    && player.getOffhandItem().getItem() instanceof Ninjato_Item) {

                if (this.attackedOffhand) {
                    this.attackedOffhand = false;
                    // this.player.getAttackCooldownProgress(1.0F) < 0.8F &&

                    switch (this.crosshairTarget.getType()) {
                    case ENTITY: {
                        Minecraft.getInstance().getConnection().send(PlayerPacket.attackPacket(((EntityHitResult) this.crosshairTarget).getEntity()));
                        if (!this.player.isSpectator()) {
                            ((PlayerAccess) this.player).doOffhandAttack(((EntityHitResult) this.crosshairTarget).getEntity());
                            ((PlayerAccess) this.player).resetLastAttackedOffhandTicks();
                        }
                        this.offhandAttackCooldown = 5;
                        break;
                    }
                    case BLOCK: {
                        BlockHitResult blockHitResult = (BlockHitResult) this.crosshairTarget;
                        BlockPos blockPos = blockHitResult.getBlockPos();
                        if (!this.player.level.getBlockState(blockPos).isAir()) {
                            this.interactionManager.startDestroyBlock(blockPos, blockHitResult.getDirection());
                            if (!this.player.level.getBlockState(blockPos).isAir())
                                break;
                            break;
                        }
                    }
                    case MISS: {
                        if (this.interactionManager.hasMissTime()) {
                            this.offhandAttackCooldown = 10;
                        }
                        ((PlayerAccess) this.player).resetLastAttackedOffhandTicks();
                    }
                    }
                    this.player.swing(InteractionHand.OFF_HAND);
                    info.setReturnValue(false);
                } else
                    this.attackedOffhand = true;
            }
        }
    }

    @Inject(method = "doItemUse", at = @At(value = "HEAD"), cancellable = true)
    private void doItemUseMixin(CallbackInfo info) {
        if (player != null) {
            ItemStack itemStack = player.getMainHandItem();
            if ((itemStack.is(TagInit.DOUBLE_HANDED_ITEMS) || itemStack.is(TagInit.ACCROSS_DOUBLE_HANDED_ITEMS) || itemStack.getItem() instanceof Long_Sword_Item
                    || itemStack.getItem() instanceof Big_Axe_Item) && (!player.getOffhandItem().isEmpty() || player.isSwimming() || player.isPassenger()))
                info.cancel();
        }
    }

    @Inject(method = "handleBlockBreaking", at = @At(value = "HEAD"), cancellable = true)
    private void handleBlockBreakingMixin(boolean bl, CallbackInfo info) {
        if (player != null) {
            ItemStack itemStack = player.getMainHandItem();
            if ((itemStack.is(TagInit.DOUBLE_HANDED_ITEMS) || itemStack.is(TagInit.ACCROSS_DOUBLE_HANDED_ITEMS) || itemStack.getItem() instanceof Long_Sword_Item
                    || itemStack.getItem() instanceof Big_Axe_Item) && (!player.getOffhandItem().isEmpty() || player.isSwimming() || player.isPassenger()))
                info.cancel();
        }
    }

}
