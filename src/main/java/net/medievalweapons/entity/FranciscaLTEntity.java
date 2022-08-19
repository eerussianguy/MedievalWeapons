package net.medievalweapons.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

import net.minecraftforge.network.NetworkHooks;

import net.medievalweapons.item.FranciscaLTItem;

public class FranciscaLTEntity extends AbstractArrow
{
    private static final EntityDataAccessor<Boolean> ENCHANTMENT_GLINT = SynchedEntityData.defineId(FranciscaLTEntity.class, EntityDataSerializers.BOOLEAN);;
    private ItemStack francisca_LT;
    private final Set<UUID> piercedEntities = new HashSet<>();

    public FranciscaLTEntity(EntityType<? extends FranciscaLTEntity> entityType, Level world, Supplier<? extends Item> item)
    {
        super(entityType, world);
        this.francisca_LT = new ItemStack(item.get());
    }

    public FranciscaLTEntity(Level world, LivingEntity owner, FranciscaLTItem item, ItemStack stack)
    {
        super(item.getType(), owner, world);
        this.francisca_LT = new ItemStack(item);
        this.francisca_LT = stack.copy();
        this.entityData.set(ENCHANTMENT_GLINT, stack.hasFoil());
    }

    public FranciscaLTEntity(Level world, double x, double y, double z, FranciscaLTItem item)
    {
        super(item.getType(), x, y, z, world);
        this.francisca_LT = new ItemStack(item);
    }

    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(ENCHANTMENT_GLINT, false);
    }

    @Override
    public Packet<?> getAddEntityPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected ItemStack getPickupItem()
    {
        return this.francisca_LT.copy();
    }

    public boolean enchantingGlint()
    {
        return this.entityData.get(ENCHANTMENT_GLINT);
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult)
    {
        int level = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PIERCING, this.francisca_LT);
        Entity hitEntity = entityHitResult.getEntity();
        if (this.piercedEntities.contains(hitEntity.getUUID()) || this.piercedEntities.size() > level)
        {
            return;
        }
        this.piercedEntities.add(hitEntity.getUUID());
        float damage = ((FranciscaLTItem) this.francisca_LT.getItem()).getDamage() * 2.3F;
        if (hitEntity instanceof Animal)
        {
            int impalingLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.IMPALING, this.francisca_LT);
            if (impalingLevel > 0)
            {
                damage += impalingLevel * 1.5F;
            }
        }

        Entity owner = this.getOwner();
        DamageSource damageSource = createDamageSource(this, owner == null ? this : owner);
        SoundEvent soundEvent = SoundEvents.TRIDENT_HIT;
        if (hitEntity.hurt(damageSource, damage))
        {
            if (hitEntity.getType() == EntityType.ENDERMAN)
            {
                return;
            }

            if (hitEntity instanceof LivingEntity)
            {
                LivingEntity hitLivingEntity = (LivingEntity) hitEntity;
                if (owner instanceof LivingEntity)
                {
                    EnchantmentHelper.doPostHurtEffects(hitLivingEntity, owner);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity) owner, hitLivingEntity);
                }
                this.playSound(soundEvent, 1.0F, 1.0F);
                this.doPostHurtEffects(hitLivingEntity);
            }
        }

        if (this.piercedEntities.size() > level)
        {
            this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01D, -0.1D, -0.01D));
        }
        else
        {
            this.setDeltaMovement(this.getDeltaMovement().scale(0.75));
        }

    }

    @Override
    public void playerTouch(Player player)
    {
        Entity entity = this.getOwner();
        if (entity == null || entity.getUUID() == player.getUUID())
        {
            super.playerTouch(player);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt)
    {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("francisca_lt", 10))
        {
            this.francisca_LT = ItemStack.of(nbt.getCompound("francisca_lt"));
            this.entityData.set(ENCHANTMENT_GLINT, this.francisca_LT.hasFoil());
        }

        this.piercedEntities.clear();
        if (nbt.contains("francisca_lt_hit", 9))
        {
            for (Tag hitEntity : nbt.getList("francisca_lt_hit", 10))
            {
                this.piercedEntities.add(((CompoundTag) hitEntity).getUUID("UUID"));
            }
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt)
    {
        super.addAdditionalSaveData(nbt);
        nbt.put("francisca_lt", this.francisca_LT.save(new CompoundTag()));

        ListTag tags = new ListTag();
        for (UUID uuid : this.piercedEntities)
        {
            CompoundTag c = new CompoundTag();
            c.putUUID("UUID", uuid);
            tags.add(c);
        }
        nbt.put("francisca_lt_hit", tags);
    }

    @Override
    public void tickDespawn()
    {
        if (this.pickup != AbstractArrow.Pickup.ALLOWED)
        {
            super.tickDespawn();
        }
    }

    @Override
    public boolean shouldRender(double cameraX, double cameraY, double cameraZ)
    {
        return true;
    }

    public static DamageSource createDamageSource(Entity entity, Entity owner)
    {
        return new IndirectEntityDamageSource("francisca", entity, owner).setProjectile();
    }

}