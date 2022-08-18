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
public class Big_Axe_Entity_Model extends Model {
    private final ModelPart base;

    public Big_Axe_Entity_Model(ModelPart base) {
        super(RenderType::entitySolid);
        this.base = base.getChild("base");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild("base",
                CubeListBuilder.create().texOffs(22, 18).addBox(-12.0F, 11.0F, -0.5F, 2.0F, 1.0F, 1.0F).texOffs(22, 11).addBox(-12.0F, 10.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(22, 9)
                        .addBox(-11.0F, 9.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(22, 7).addBox(-10.0F, 8.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(22, 22).addBox(-9.0F, 7.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(0, 22)
                        .addBox(-8.0F, 6.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(15, 21).addBox(-7.0F, 5.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(7, 21).addBox(-6.0F, 4.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(20, 2)
                        .addBox(-5.0F, 3.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(0, 20).addBox(-4.0F, 2.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(19, 4).addBox(-3.0F, 1.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(15, 19)
                        .addBox(-2.0F, 0.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(7, 19).addBox(-1.0F, -1.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(18, 14).addBox(0.0F, -2.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(0, 18)
                        .addBox(1.0F, -3.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(0, 0).addBox(2.0F, -4.0F, -0.5F, 11.0F, 1.0F, 1.0F).texOffs(13, 10).addBox(3.0F, -5.0F, -0.5F, 4.0F, 1.0F, 1.0F).texOffs(15, 17)
                        .addBox(4.0F, -6.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(7, 17).addBox(5.0F, -7.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(0, 16).addBox(6.0F, -8.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(0, 2)
                        .addBox(7.0F, -10.0F, -0.5F, 9.0F, 2.0F, 1.0F).texOffs(11, 12).addBox(6.0F, -11.0F, -0.5F, 5.0F, 1.0F, 1.0F).texOffs(0, 9).addBox(6.0F, -12.0F, -0.5F, 6.0F, 1.0F, 1.0F).texOffs(15, 6)
                        .addBox(6.0F, -13.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(22, 16).addBox(10.0F, -13.0F, -0.5F, 2.0F, 1.0F, 1.0F).texOffs(10, 14).addBox(13.0F, -12.0F, -0.5F, 3.0F, 2.0F, 1.0F).texOffs(22, 20)
                        .addBox(14.0F, -13.0F, -0.5F, 1.0F, 1.0F, 1.0F).texOffs(0, 7).addBox(10.0F, -8.0F, -0.5F, 6.0F, 1.0F, 1.0F).texOffs(0, 13).addBox(11.0F, -7.0F, -0.5F, 4.0F, 2.0F, 1.0F).texOffs(0, 11)
                        .addBox(9.0F, -5.0F, -0.5F, 5.0F, 1.0F, 1.0F).texOffs(0, 5).addBox(5.0F, -3.0F, -0.5F, 7.0F, 1.0F, 1.0F).texOffs(13, 8).addBox(6.0F, -2.0F, -0.5F, 4.0F, 1.0F, 1.0F),
                PartPose.offset(4.0F, 12.0F, 0.0F));
        return LayerDefinition.create(modelData, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        this.base.yRot = 1.5708F;
        this.base.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}