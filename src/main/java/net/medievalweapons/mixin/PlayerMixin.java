package net.medievalweapons.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;

import com.mojang.authlib.GameProfile;
import net.medievalweapons.access.PlayerAccess;
import net.medievalweapons.config.MedievalConfig;
import net.medievalweapons.item.BigAxeItem;
import net.medievalweapons.item.SmallAxeItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity implements PlayerAccess
{
    public PlayerMixin(Level world, BlockPos pos, float yaw, GameProfile profile)
    {
        super(EntityType.PLAYER, world);
    }

    @Inject(method = "blockUsingShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;blockUsingShield(Lnet/minecraft/world/entity/LivingEntity;)V", shift = Shift.AFTER))
    public void takeShieldHitMixin(LivingEntity attacker, CallbackInfo info)
    {
        Item item = attacker.getMainHandItem().getItem();
        if (attacker instanceof Player && !(item instanceof AxeItem) && item instanceof TieredItem)
        {
            int extraCool = MedievalConfig.SERVER.extraWeaponShieldBlockingCooldown.get();
            if (extraCool != 0 && (item instanceof SmallAxeItem || item instanceof BigAxeItem))
            {
                Player playerEntity = (Player) (Object) this;
                playerEntity.getCooldowns().addCooldown(playerEntity.getUseItem().getItem(), extraCool);
                playerEntity.stopUsingItem();
            }
            else
            {
                extraCool = MedievalConfig.SERVER.shieldBlockingCooldown.get();
                if (extraCool != 0)
                {
                    Player playerEntity = (Player) (Object) this;
                    playerEntity.getCooldowns().addCooldown(playerEntity.getUseItem().getItem(), extraCool);
                    playerEntity.stopUsingItem();
                }
            }
        }

    }

}