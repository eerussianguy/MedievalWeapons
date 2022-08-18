package net.medievalweapons.init;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.medievalweapons.client.model.*;
import net.medievalweapons.compat.CompatRender;
import net.medievalweapons.client.renderer.FranciscaHTRenderer;
import net.medievalweapons.client.renderer.FranciscaLTRenderer;
import net.medievalweapons.client.renderer.HealingBallRenderer;
import net.medievalweapons.client.renderer.MedievalJavelinRenderer;
import net.medievalweapons.init.ParticleInit.HealingAuraParticle;
import net.medievalweapons.network.EntitySpawnPacket;

public class RenderInit
{

    public static final ModelLayerLocation BIG_AXE_LAYER = new ModelLayerLocation(new ResourceLocation("medievalweapons:big_axe_render_layer"), "big_axe_render_layer");
    public static final ModelLayerLocation FRANCISCA_HT_LAYER = new ModelLayerLocation(new ResourceLocation("medievalweapons:francisca_ht_render_layer"), "francisca_ht_render_layer");
    public static final ModelLayerLocation FRANCISCA_LT_LAYER = new ModelLayerLocation(new ResourceLocation("medievalweapons:francisca_lt_render_layer"), "francisca_lt_render_layer");
    public static final ModelLayerLocation HEALING_STAFF_LAYER = new ModelLayerLocation(new ResourceLocation("medievalweapons:healing_staff_render_layer"), "healing_staff_render_layer");
    public static final ModelLayerLocation JAVELIN_LAYER = new ModelLayerLocation(new ResourceLocation("medievalweapons:javelin_render_layer"), "javelin_render_layer");
    public static final ModelLayerLocation LANCE_LAYER = new ModelLayerLocation(new ResourceLocation("medievalweapons:lance_render_layer"), "lance_render_layer");
    public static final ModelLayerLocation MACE_LAYER = new ModelLayerLocation(new ResourceLocation("medievalweapons:mace_render_layer"), "mace_render_layer");
    public static final ModelLayerLocation THALLEOUS_SWORD_LAYER = new ModelLayerLocation(new ResourceLocation("medievalweapons:thalleous_sword_render_layer"), "thalleous_sword_render_layer");

    public static void init()
    {
        // Packet
        ClientPlayNetworking.registerGlobalReceiver(EntitySpawnPacket.ID, EntitySpawnPacket::onPacket);
        // Francisca

        EntityRendererRegistry.register(EntityInit.WOODEN_FRANCISCA_LT, FranciscaLTRenderer::new);
        EntityRendererRegistry.register(EntityInit.STONE_FRANCISCA_LT, FranciscaLTRenderer::new);
        EntityRendererRegistry.register(EntityInit.IRON_FRANCISCA_HT, FranciscaHTRenderer::new);
        EntityRendererRegistry.register(EntityInit.GOLDEN_FRANCISCA_HT, FranciscaHTRenderer::new);
        EntityRendererRegistry.register(EntityInit.DIAMOND_FRANCISCA_HT, FranciscaHTRenderer::new);
        EntityRendererRegistry.register(EntityInit.NETHERITE_FRANCISCA_HT, FranciscaHTRenderer::new);
        // Javelin
        EntityRendererRegistry.register(EntityInit.WOODEN_JAVELIN, MedievalJavelinRenderer::new);
        EntityRendererRegistry.register(EntityInit.STONE_JAVELIN, MedievalJavelinRenderer::new);
        EntityRendererRegistry.register(EntityInit.IRON_JAVELIN, MedievalJavelinRenderer::new);
        EntityRendererRegistry.register(EntityInit.GOLDEN_JAVELIN, MedievalJavelinRenderer::new);
        EntityRendererRegistry.register(EntityInit.DIAMOND_JAVELIN, MedievalJavelinRenderer::new);
        EntityRendererRegistry.register(EntityInit.NETHERITE_JAVELIN, MedievalJavelinRenderer::new);
        // Healing Ball
        EntityRendererRegistry.register(EntityInit.HEALING_BALL_ENTITY, HealingBallRenderer::new);
        // Particle
        ParticleFactoryRegistry.getInstance().register(ParticleInit.HEALING_AURA_PARTICLE, HealingAuraParticle.Factory::new);
        // Compat
        CompatRender.loadRenderer();

        EntityModelLayerRegistry.registerModelLayer(BIG_AXE_LAYER, BigAxeModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(FRANCISCA_HT_LAYER, FranciscaHTModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(FRANCISCA_LT_LAYER, FranciscaLtModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(HEALING_STAFF_LAYER, HealingStaffModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(JAVELIN_LAYER, MedievalJavelinModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(LANCE_LAYER, Lance_Entity_Model::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(MACE_LAYER, Mace_Entity_Model::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(THALLEOUS_SWORD_LAYER, ThalleousSwordModel::getTexturedModelData);

    }

}