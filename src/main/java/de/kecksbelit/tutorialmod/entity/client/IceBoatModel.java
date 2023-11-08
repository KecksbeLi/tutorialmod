package de.kecksbelit.tutorialmod.entity.client;

import de.kecksbelit.tutorialmod.Tutorialmod;
import de.kecksbelit.tutorialmod.entity.custom.IceBoatEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class IceBoatModel extends GeoModel<IceBoatEntity> {
    @Override
    public ResourceLocation getModelResource(IceBoatEntity object) {
        return new ResourceLocation(Tutorialmod.MOD_ID, "geo/ice_boat.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(IceBoatEntity object) {
        return new ResourceLocation(Tutorialmod.MOD_ID, "textures/entity/ice_boat_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(IceBoatEntity animatable) {
        return new ResourceLocation(Tutorialmod.MOD_ID, "animations/iceboatmodel.animation.json");
    }
}
