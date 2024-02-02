package de.kecksbelit.tutorialmod.entity.animations;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class ModAnimationsDefinitions {

    public static final AnimationDefinition ANIMATION = AnimationDefinition.Builder.withLength(2.25f).looping()
            .addAnimation("body",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.25f, KeyframeAnimations.degreeVec(0f, -365f, 0f),
                                    AnimationChannel.Interpolations.LINEAR))).build();
}
