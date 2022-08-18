package net.medievalweapons.client.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class LanceModel extends Model
{
    private final ModelPart base;

    public LanceModel(ModelPart base)
    {
        super(RenderType::entitySolid);
        this.base = base.getChild("base");
    }

    public static LayerDefinition getTexturedModelData()
    {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();
        modelPartData.addOrReplaceChild("base",
            CubeListBuilder.create().texOffs(21, 18).addBox(-20.0F, 19.0F, -0.5F, 2.0F, 1.0F, 1.0F).texOffs(21, 14).addBox(-20.0F, 18.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(21, 1)
                .addBox(-19.0F, 17.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(14, 21).addBox(-18.0F, 16.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(0, 21).addBox(-17.0F, 15.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(7, 20)
                .addBox(-16.0F, 14.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(19, 3).addBox(-15.0F, 13.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(14, 19).addBox(-14.0F, 12.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(0, 19)
                .addBox(-13.0F, 11.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(18, 9).addBox(-12.0F, 10.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(18, 7).addBox(-11.0F, 9.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(18, 5)
                .addBox(-10.0F, 8.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(7, 18).addBox(-9.0F, 7.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(14, 17).addBox(-8.0F, 6.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(0, 17)
                .addBox(-7.0F, 5.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(16, 11).addBox(-6.0F, 4.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(7, 16).addBox(-5.0F, 3.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(14, 15)
                .addBox(-4.0F, 2.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(0, 15).addBox(-3.0F, 1.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(14, 13).addBox(-2.0F, 0.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(14, 0)
                .addBox(-1.0F, -1.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(7, 14).addBox(0.0F, -2.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(0, 13).addBox(1.0F, -3.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(12, 2)
                .addBox(2.0F, -4.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(7, 12).addBox(3.0F, -5.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(11, 6).addBox(4.0F, -6.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(11, 4)
                .addBox(5.0F, -7.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(9, 8).addBox(6.0F, -8.0F, -0.5F, 4.0F, 1.0F, 1.0F).texOffs(0, 2).addBox(7.0F, -10.0F, -0.5F, 5.0F, 2.0F, 1.0F).texOffs(0, 5)
                .addBox(8.0F, -11.0F, -0.5F, 5.0F, 1.0F, 1.0F).texOffs(0, 0).addBox(8.0F, -12.0F, -0.5F, 6.0F, 1.0F, 1.0F).texOffs(0, 9).addBox(10.0F, -13.0F, -0.5F, 4.0F, 1.0F, 1.0F).texOffs(0, 7)
                .addBox(11.0F, -14.0F, -0.5F, 4.0F, 1.0F, 1.0F).texOffs(0, 11).addBox(13.0F, -15.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(9, 10).addBox(14.0F, -16.0F, -0.5F, 3.0F, 1.0F, 1.0F).texOffs(21, 16)
                .addBox(15.0F, -17.0F, -0.5F, 2.0F, 1.0F, 1.0F),
            PartPose.offset(12.0F, 4.0F, 0.5F));
        return LayerDefinition.create(modelData, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha)
    {
        this.base.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}