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
public class Francisca_HT_Entity_Model extends Model {
    private final ModelPart base;

    public Francisca_HT_Entity_Model(ModelPart base) {
        super(RenderType::entitySolid);
        this.base = base.getChild("base");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild("base",
                CubeListBuilder.create().texOffs(16, 0).addBox(0.0F, -1.0F, 4.0F, 1.0F, 1.0F, 3.0F).texOffs(0, 9).addBox(0.0F, 7.0F, -8.0F, 1.0F, 1.0F, 2.0F).texOffs(0, 21)
                        .addBox(0.0F, 6.0F, -8.0F, 1.0F, 1.0F, 3.0F).texOffs(10, 20).addBox(0.0F, 5.0F, -7.0F, 1.0F, 1.0F, 3.0F).texOffs(19, 19).addBox(0.0F, 4.0F, -6.0F, 1.0F, 1.0F, 3.0F).texOffs(18, 14)
                        .addBox(0.0F, 3.0F, -5.0F, 1.0F, 1.0F, 3.0F).texOffs(5, 18).addBox(0.0F, 2.0F, -4.0F, 1.0F, 1.0F, 3.0F).texOffs(0, 17).addBox(0.0F, 1.0F, -3.0F, 1.0F, 1.0F, 3.0F).texOffs(16, 10)
                        .addBox(0.0F, 0.0F, -2.0F, 1.0F, 1.0F, 3.0F).texOffs(16, 6).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 3.0F).texOffs(0, 9).addBox(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 7.0F).texOffs(9, 0)
                        .addBox(0.0F, -3.0F, 1.0F, 1.0F, 1.0F, 5.0F).texOffs(0, 0).addBox(0.0F, -5.0F, -2.0F, 1.0F, 2.0F, 7.0F).texOffs(9, 9).addBox(0.0F, -6.0F, -1.0F, 1.0F, 1.0F, 5.0F).texOffs(12, 15)
                        .addBox(0.0F, -7.0F, -1.0F, 1.0F, 1.0F, 4.0F).texOffs(0, 3).addBox(0.0F, -8.0F, 1.0F, 1.0F, 1.0F, 2.0F).texOffs(0, 0).addBox(0.0F, 0.0F, 5.0F, 1.0F, 1.0F, 2.0F),
                PartPose.offset(0.5F, 16.0F, 1.0F));
        return LayerDefinition.create(modelData, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        this.base.yRot = 1.5708F;
        this.base.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}