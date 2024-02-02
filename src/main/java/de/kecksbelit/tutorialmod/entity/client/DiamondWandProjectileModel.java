package de.kecksbelit.tutorialmod.entity.client;// Made with Blockbench 4.9.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class DiamondWandProjectileModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart body;

	public DiamondWandProjectileModel(ModelPart root) {
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -3.1F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -2.7F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6545F, 0.0F, 0.6545F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.root().getAllParts().forEach(ModelPart::resetPose);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		int maxLight = 15728640; // 0xF000F0 - maximum light value
		body.render(poseStack, vertexConsumer, maxLight, packedOverlay, red, green, blue, alpha);
	}
	public void applyAnimation(float animationProgress) {
		// Assuming animationProgress is a value between 0 and 1, representing the progress of the animation

		// Calculate the current rotation angle based on the animation progress
		// Example: Linear interpolation between 0 and -365 degrees over the animation
		float rotationAngle = 0f + (-365f * animationProgress);

		// Convert rotation angle to radians as Minecraft uses radians for rotations
		rotationAngle = (float) Math.toRadians(rotationAngle);

		// Apply the rotation to the body part.
		// Here, rotating around the Y-axis as an example
		this.body.yRot = rotationAngle;

		// Apply similar transformations to other parts if they are also animated
	}


	@Override
	public ModelPart root() {
		return body;
	}
}