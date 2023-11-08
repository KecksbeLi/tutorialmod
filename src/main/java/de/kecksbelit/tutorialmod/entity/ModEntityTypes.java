package de.kecksbelit.tutorialmod.entity;


import de.kecksbelit.tutorialmod.Tutorialmod;
import de.kecksbelit.tutorialmod.entity.custom.IceBoatEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Tutorialmod.MOD_ID);

    public static final RegistryObject<EntityType<IceBoatEntity>> ICEBOAT =
            ENTITY_TYPES.register("ice_boat",
                    () -> EntityType.Builder.of(IceBoatEntity::new, MobCategory.MISC)
                            .sized(1.5f, 0.6f)
                            .build(new ResourceLocation(Tutorialmod.MOD_ID, "ice_boat").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}