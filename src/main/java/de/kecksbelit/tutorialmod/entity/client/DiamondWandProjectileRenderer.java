package de.kecksbelit.tutorialmod.entity.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import de.kecksbelit.tutorialmod.Tutorialmod;
import de.kecksbelit.tutorialmod.entity.animations.ModAnimationsDefinitions;
import de.kecksbelit.tutorialmod.entity.custom.DiamondWandProjectile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Vector3f;
public class DiamondWandProjectileRenderer extends EntityRenderer<DiamondWandProjectile> {
    private static final ResourceLocation PROJECTILE_TEXTURE = new ResourceLocation(Tutorialmod.MOD_ID, "textures/entity/iceboat_texture.png");
    private final DiamondWandProjectileModel<DiamondWandProjectile> model;

    public DiamondWandProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new DiamondWandProjectileModel<>(context.bakeLayer(ModModelLayers.DIAMOND_WAND_PROJECTILE_LAYER));

    }

    @Override
    public ResourceLocation getTextureLocation(DiamondWandProjectile entity) {
        return PROJECTILE_TEXTURE;
    }

    @Override
    public void render(DiamondWandProjectile entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        // Reset lighting to default
        RenderSystem.setShaderLights(new Vector3f(1.0F, 1.0F, 1.0F), new Vector3f(0.0F, 1.0F, 0.0F));

        // Correct scaling and positioning of the model
        poseStack.translate(0.0D, 0.5D, 0.0D);
        poseStack.scale(1.0F, 1.0F, 1.0F);
        //float rotationRadians = (float) Math.toRadians(entity.getCurrentRotation());
//
        //// Manually apply rotation using basic trigonometry
        //// For demonstration, rotating around Y-axis
        //float cos = (float)Math.cos(rotationRadians);
        //float sin = (float)Math.sin(rotationRadians);
//
        //poseStack.translate(0.0D, 0.0D, 0.0D); // Adjust as needed for model centering
        //poseStack.scale(cos, 1.0F, sin);
        // Render the entity model
        // model.render(poseStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, ...);
        // Applying rotation directly using RenderSystem
        // Render the model




        model.renderToBuffer(poseStack, buffer.getBuffer(RenderType.entityCutout(getTextureLocation(entity))), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        float totalAnimationTime = ModAnimationsDefinitions.ANIMATION.lengthInSeconds();
        float animationProgress = (entity.tickCount + partialTicks) / (totalAnimationTime * 20.0f); // 20 ticks per second

        // Ensure the animation loops if it is longer than the animation duration
        animationProgress %= 1.0f;

        // Apply the animation
        model.applyAnimation(animationProgress);
        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

}
