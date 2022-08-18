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
public class Mace_Entity_Model extends Model {
    private final ModelPart base;

    public Mace_Entity_Model(ModelPart base) {
        super(RenderType::entitySolid);
        this.base = base.getChild("base");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild("base",
                CubeListBuilder.create().texOffs(14, 18).addBox(-10.0F, 9.0F, -0.5F, 2.0F, 1.0F, 1.0F).texOffs(7, 17).addBox(-10.0F, 8.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(17, 3)
                        .addBox(-9.0F, 7.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(14, 16).addBox(-8.0F, 6.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(0, 16).addBox(-7.0F, 5.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(7, 15)
                        .addBox(-6.0F, 4.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(15, 5).addBox(-5.0F, 3.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(14, 14).addBox(-4.0F, 2.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(0, 14)
                        .addBox(-3.0F, 1.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(7, 13).addBox(-2.0F, 0.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(0, 10).addBox(-1.0F, -1.0F, -0.5F, 5.0F, 1.0F, 1.0F).texOffs(18, 12)
                        .addBox(2.0F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F).texOffs(0, 20).addBox(4.0F, 1.0F, -0.5F, 1.0F, 1.0F, 1.0F).texOffs(0, 12).addBox(0.0F, -2.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(0, 8)
                        .addBox(-1.0F, -3.0F, -0.5F, 5.0F, 1.0F, 1.0F).texOffs(18, 10).addBox(-1.0F, -4.0F, -0.5F, 2.0F, 1.0F, 1.0F).texOffs(19, 19).addBox(-2.0F, -5.0F, -0.5F, 1.0F, 1.0F, 1.0F).texOffs(0, 6)
                        .addBox(2.0F, -4.0F, -0.5F, 5.0F, 1.0F, 1.0F).texOffs(0, 18).addBox(5.0F, -3.0F, -0.5F, 2.0F, 1.0F, 1.0F).texOffs(9, 19).addBox(7.0F, -2.0F, -0.5F, 1.0F, 1.0F, 1.0F).texOffs(0, 4)
                        .addBox(3.0F, -5.0F, -0.5F, 7.0F, 1.0F, 1.0F).texOffs(0, 0).addBox(2.0F, -6.0F, -0.5F, 9.0F, 1.0F, 1.0F).texOffs(0, 2).addBox(2.0F, -7.0F, -0.5F, 8.0F, 1.0F, 1.0F).texOffs(5, 19)
                        .addBox(1.0F, -8.0F, -0.5F, 1.0F, 1.0F, 1.0F).texOffs(11, 7).addBox(4.0F, -8.0F, -0.5F, 4.0F, 1.0F, 1.0F).texOffs(11, 11).addBox(4.0F, -9.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(11, 9)
                        .addBox(4.0F, -10.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(19, 1).addBox(5.0F, -11.0F, -0.5F, 1.0F, 1.0F, 1.0F),
                PartPose.offset(2.0F, 14.0F, 0.0F));
        return LayerDefinition.create(modelData, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        this.base.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}