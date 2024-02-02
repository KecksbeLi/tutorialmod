package de.kecksbelit.tutorialmod.event;

import de.kecksbelit.tutorialmod.Tutorialmod;
import de.kecksbelit.tutorialmod.entity.client.DiamondWandProjectileModel;
import de.kecksbelit.tutorialmod.entity.client.ModModelLayers;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Tutorialmod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents
{
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(ModModelLayers.DIAMOND_WAND_PROJECTILE_LAYER, DiamondWandProjectileModel::createBodyLayer);
    }
}
