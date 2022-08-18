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
public class Thalleous_Sword_Entity_Model extends Model {
    private final ModelPart base;

    public Thalleous_Sword_Entity_Model(ModelPart base) {
        super(RenderType::entitySolid);
        this.base = base.getChild("base");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild("base",
                CubeListBuilder.create().texOffs(13, 27).addBox(-6.0F, 7.0F, 0.0F, 2.0F, 1.0F, 1.0F).texOffs(0, 26).addBox(-7.0F, 6.0F, 0.0F, 3.0F, 1.0F, 1.0F).texOffs(19, 23)
                        .addBox(-8.0F, 5.0F, 0.0F, 4.0F, 1.0F, 1.0F).texOffs(21, 5).addBox(-8.0F, 4.0F, 0.0F, 5.0F, 1.0F, 1.0F).texOffs(25, 21).addBox(-5.0F, 3.0F, 0.0F, 3.0F, 1.0F, 1.0F).texOffs(23, 25)
                        .addBox(-4.0F, 2.0F, 0.0F, 3.0F, 1.0F, 1.0F).texOffs(15, 25).addBox(-3.0F, 1.0F, 0.0F, 3.0F, 1.0F, 1.0F).texOffs(0, 20).addBox(-2.0F, 0.0F, 0.0F, 6.0F, 1.0F, 1.0F).texOffs(13, 19)
                        .addBox(-1.0F, -1.0F, 0.0F, 6.0F, 1.0F, 1.0F).texOffs(7, 25).addBox(2.0F, 1.0F, 0.0F, 3.0F, 1.0F, 1.0F).texOffs(9, 23).addBox(2.0F, 2.0F, 0.0F, 4.0F, 1.0F, 1.0F).texOffs(0, 24)
                        .addBox(2.0F, 3.0F, 0.0F, 3.0F, 1.0F, 1.0F).texOffs(27, 27).addBox(3.0F, 4.0F, 0.0F, 1.0F, 1.0F, 1.0F).texOffs(0, 14).addBox(-1.0F, -2.0F, 0.0F, 7.0F, 1.0F, 1.0F).texOffs(0, 2)
                        .addBox(-4.0F, -3.0F, 0.0F, 11.0F, 1.0F, 1.0F).texOffs(0, 0).addBox(-5.0F, -4.0F, 0.0F, 15.0F, 1.0F, 1.0F).texOffs(0, 6).addBox(0.0F, -5.0F, 0.0F, 10.0F, 1.0F, 1.0F).texOffs(23, 27)
                        .addBox(-3.0F, -6.0F, 0.0F, 1.0F, 1.0F, 1.0F).texOffs(23, 3).addBox(-4.0F, -5.0F, 0.0F, 3.0F, 1.0F, 1.0F).texOffs(19, 27).addBox(8.0F, -3.0F, 0.0F, 1.0F, 1.0F, 1.0F).texOffs(0, 12)
                        .addBox(1.0F, -6.0F, 0.0F, 8.0F, 1.0F, 1.0F).texOffs(0, 10).addBox(2.0F, -7.0F, 0.0F, 8.0F, 1.0F, 1.0F).texOffs(0, 8).addBox(3.0F, -8.0F, 0.0F, 8.0F, 1.0F, 1.0F).texOffs(0, 4)
                        .addBox(2.0F, -9.0F, 0.0F, 10.0F, 1.0F, 1.0F).texOffs(7, 27).addBox(3.0F, -10.0F, 0.0F, 2.0F, 1.0F, 1.0F).texOffs(0, 18).addBox(6.0F, -10.0F, 0.0F, 6.0F, 1.0F, 1.0F).texOffs(17, 13)
                        .addBox(7.0F, -11.0F, 0.0F, 6.0F, 1.0F, 1.0F).texOffs(17, 11).addBox(8.0F, -12.0F, 0.0F, 6.0F, 1.0F, 1.0F).texOffs(17, 9).addBox(10.0F, -13.0F, 0.0F, 6.0F, 1.0F, 1.0F).texOffs(13, 17)
                        .addBox(11.0F, -14.0F, 0.0F, 6.0F, 1.0F, 1.0F).texOffs(13, 21).addBox(12.0F, -15.0F, 0.0F, 5.0F, 1.0F, 1.0F).texOffs(0, 16).addBox(12.0F, -16.0F, 0.0F, 6.0F, 1.0F, 1.0F).texOffs(15, 15)
                        .addBox(13.0F, -17.0F, 0.0F, 6.0F, 1.0F, 1.0F).texOffs(0, 22).addBox(15.0F, -18.0F, 0.0F, 4.0F, 1.0F, 1.0F).texOffs(21, 7).addBox(16.0F, -19.0F, 0.0F, 4.0F, 1.0F, 1.0F).texOffs(26, 18)
                        .addBox(18.0F, -20.0F, 0.0F, 2.0F, 1.0F, 1.0F),
                PartPose.offset(0.0F, 16.0F, 0.0F));
        return LayerDefinition.create(modelData, 64, 64);
    }

    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        this.base.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}