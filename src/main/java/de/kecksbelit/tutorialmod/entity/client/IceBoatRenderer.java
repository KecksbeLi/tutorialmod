package de.kecksbelit.tutorialmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import de.kecksbelit.tutorialmod.Tutorialmod;
import de.kecksbelit.tutorialmod.entity.custom.IceBoatEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import static java.lang.String.valueOf;


public class IceBoatRenderer extends GeoEntityRenderer<IceBoatEntity> {


    public IceBoatRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new IceBoatModel());
        this.shadowRadius = 0.3f;
        try {
            FileWriter fileWriter = new FileWriter("E:\\ModTexture\\test\\boat.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(valueOf( " " +  LocalDateTime.now().getSecond() + " "));
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void applyRotations(IceBoatEntity animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick) {
        float lerpRot = Mth.rotLerp(partialTick, animatable.yRotO, animatable.getYRot());
        super.applyRotations(animatable, poseStack, ageInTicks, lerpRot, partialTick);
    }
    @Override
    public ResourceLocation getTextureLocation(IceBoatEntity instance) {
        return new ResourceLocation(Tutorialmod.MOD_ID, "textures/entity/iceboat_texture.png");
    }
}

