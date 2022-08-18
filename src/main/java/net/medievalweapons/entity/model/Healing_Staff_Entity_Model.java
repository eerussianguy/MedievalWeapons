package net.medievalweapons.entity.model;

import net.fabricmc.api.Environment;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;

@Environment(EnvType.CLIENT)
public class Healing_Staff_Entity_Model extends Model {
    private final ModelPart base;

    public Healing_Staff_Entity_Model(ModelPart base) {
        super(RenderType::entitySolid);
        this.base = base.getChild("base");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild("base",
                CubeListBuilder.create().texOffs(25, 16).addBox(-20.0F, 20.0F, -0.5F, 2.0F, 1.0F, 1.0F).texOffs(24, 18).addBox(-21.0F, 19.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(0, 12)
                        .addBox(-21.0F, 18.0F, -0.5F, 6.0F, 1.0F, 1.0F).texOffs(25, 13).addBox(-17.0F, 19.0F, -0.5F, 2.0F, 1.0F, 1.0F).texOffs(24, 8).addBox(-19.0F, 17.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(13, 9)
                        .addBox(-20.0F, 16.0F, -0.5F, 5.0F, 1.0F, 1.0F).texOffs(14, 25).addBox(-20.0F, 15.0F, -0.5F, 2.0F, 1.0F, 1.0F).texOffs(24, 6).addBox(-17.0F, 15.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(24, 0)
                        .addBox(-16.0F, 14.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(21, 24).addBox(-15.0F, 13.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(7, 24).addBox(-14.0F, 12.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(14, 23)
                        .addBox(-13.0F, 11.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(0, 23).addBox(-12.0F, 10.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(21, 22).addBox(-11.0F, 9.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(7, 22)
                        .addBox(-10.0F, 8.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(21, 20).addBox(-9.0F, 7.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(21, 11).addBox(-8.0F, 6.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(14, 21)
                        .addBox(-7.0F, 5.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(0, 21).addBox(-6.0F, 4.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(20, 2).addBox(-5.0F, 3.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(7, 20)
                        .addBox(-4.0F, 2.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(14, 19).addBox(-3.0F, 1.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(0, 19).addBox(-2.0F, 0.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(18, 15)
                        .addBox(-1.0F, -1.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(18, 4).addBox(0.0F, -2.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(7, 18).addBox(1.0F, -3.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(17, 17)
                        .addBox(2.0F, -4.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(13, 13).addBox(3.0F, -5.0F, -0.5F, 5.0F, 1.0F, 1.0F).texOffs(0, 27).addBox(6.0F, -4.0F, -0.5F, 1.0F, 1.0F, 1.0F).texOffs(0, 17)
                        .addBox(4.0F, -6.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(0, 4).addBox(3.0F, -7.0F, -0.5F, 8.0F, 1.0F, 1.0F).texOffs(26, 4).addBox(4.0F, -8.0F, -0.5F, 1.0F, 1.0F, 1.0F).texOffs(0, 14)
                        .addBox(6.0F, -9.0F, -0.5F, 4.0F, 2.0F, 1.0F).texOffs(23, 26).addBox(9.0F, -6.0F, -0.5F, 1.0F, 1.0F, 1.0F).texOffs(0, 0).addBox(5.0F, -10.0F, -0.5F, 11.0F, 1.0F, 1.0F).texOffs(19, 26)
                        .addBox(6.0F, -11.0F, -0.5F, 1.0F, 1.0F, 1.0F).texOffs(0, 10).addBox(9.0F, -11.0F, -0.5F, 6.0F, 1.0F, 1.0F).texOffs(0, 25).addBox(11.0F, -9.0F, -0.5F, 2.0F, 1.0F, 1.0F).texOffs(9, 26)
                        .addBox(14.0F, -9.0F, -0.5F, 1.0F, 1.0F, 1.0F).texOffs(16, 6).addBox(8.0F, -13.0F, -0.5F, 3.0F, 2.0F, 1.0F).texOffs(10, 15).addBox(13.0F, -13.0F, -0.5F, 3.0F, 2.0F, 1.0F).texOffs(0, 6)
                        .addBox(9.0F, -14.0F, -0.5F, 7.0F, 1.0F, 1.0F).texOffs(0, 2).addBox(8.0F, -15.0F, -0.5F, 9.0F, 1.0F, 1.0F).texOffs(5, 26).addBox(9.0F, -16.0F, -0.5F, 1.0F, 1.0F, 1.0F).texOffs(0, 8)
                        .addBox(11.0F, -16.0F, -0.5F, 6.0F, 1.0F, 1.0F).texOffs(13, 11).addBox(14.0F, -17.0F, -0.5F, 3.0F, 1.0F, 1.0F),
                PartPose.offset(13.0F, 3.0F, 0.0F));
        return LayerDefinition.create(modelData, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        this.base.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}