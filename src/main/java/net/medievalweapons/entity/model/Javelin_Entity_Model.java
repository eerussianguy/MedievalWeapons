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
public class Javelin_Entity_Model extends Model {
    private final ModelPart base;

    public Javelin_Entity_Model(ModelPart base) {
        super(RenderType::entitySolid);
        this.base = base.getChild("base");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild("base",
                CubeListBuilder.create().texOffs(18, 0).addBox(-0.5F, 14.0F, -15.0F, 1.0F, 1.0F, 2.0F).texOffs(15, 23).addBox(-0.5F, 13.0F, -15.0F, 1.0F, 1.0F, 3.0F).texOffs(0, 23)
                        .addBox(-0.5F, 12.0F, -14.0F, 1.0F, 1.0F, 3.0F).texOffs(21, 0).addBox(-0.5F, 11.0F, -13.0F, 1.0F, 1.0F, 3.0F).texOffs(10, 21).addBox(-0.5F, 10.0F, -12.0F, 1.0F, 1.0F, 3.0F).texOffs(20, 16)
                        .addBox(-0.5F, 9.0F, -11.0F, 1.0F, 1.0F, 3.0F).texOffs(20, 12).addBox(-0.5F, 8.0F, -10.0F, 1.0F, 1.0F, 3.0F).texOffs(20, 20).addBox(-0.5F, 7.0F, -9.0F, 1.0F, 1.0F, 3.0F).texOffs(5, 20)
                        .addBox(-0.5F, 6.0F, -8.0F, 1.0F, 1.0F, 3.0F).texOffs(15, 19).addBox(-0.5F, 5.0F, -7.0F, 1.0F, 1.0F, 3.0F).texOffs(0, 19).addBox(-0.5F, 4.0F, -6.0F, 1.0F, 1.0F, 3.0F).texOffs(18, 8)
                        .addBox(-0.5F, 3.0F, -5.0F, 1.0F, 1.0F, 3.0F).texOffs(17, 4).addBox(-0.5F, 2.0F, -4.0F, 1.0F, 1.0F, 3.0F).texOffs(10, 17).addBox(-0.5F, 1.0F, -3.0F, 1.0F, 1.0F, 3.0F).texOffs(5, 16)
                        .addBox(-0.5F, 0.0F, -2.0F, 1.0F, 1.0F, 3.0F).texOffs(15, 15).addBox(-0.5F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F).texOffs(0, 15).addBox(-0.5F, -2.0F, 0.0F, 1.0F, 1.0F, 3.0F).texOffs(13, 9)
                        .addBox(-0.5F, -3.0F, 1.0F, 1.0F, 1.0F, 3.0F).texOffs(13, 0).addBox(-0.5F, -4.0F, 2.0F, 1.0F, 1.0F, 3.0F).texOffs(10, 13).addBox(-0.5F, -5.0F, 3.0F, 1.0F, 1.0F, 3.0F).texOffs(12, 5)
                        .addBox(-0.5F, -6.0F, 4.0F, 1.0F, 1.0F, 3.0F).texOffs(5, 12).addBox(-0.5F, -7.0F, 5.0F, 1.0F, 1.0F, 3.0F).texOffs(7, 0).addBox(-0.5F, -8.0F, 6.0F, 1.0F, 1.0F, 4.0F).texOffs(6, 7)
                        .addBox(-0.5F, -9.0F, 7.0F, 1.0F, 1.0F, 4.0F).texOffs(0, 0).addBox(-0.5F, -10.0F, 7.0F, 1.0F, 1.0F, 5.0F).texOffs(0, 6).addBox(-0.5F, -11.0F, 8.0F, 1.0F, 1.0F, 4.0F).texOffs(0, 11)
                        .addBox(-0.5F, -12.0F, 9.0F, 1.0F, 1.0F, 3.0F),
                PartPose.offset(0.0F, 9.0F, 0.0F));
        return LayerDefinition.create(modelData, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        this.base.xRot = 0.7854F;
        this.base.yRot = -1.5708F;
        this.base.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}