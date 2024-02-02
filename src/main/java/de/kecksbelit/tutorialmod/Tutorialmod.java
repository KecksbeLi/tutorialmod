package de.kecksbelit.tutorialmod;

import com.mojang.logging.LogUtils;
import de.kecksbelit.tutorialmod.block.ModBlocks;
import de.kecksbelit.tutorialmod.entity.ModEntityTypes;
import de.kecksbelit.tutorialmod.entity.client.DiamondWandProjectileRenderer;
import de.kecksbelit.tutorialmod.entity.client.IceBoatRenderer;
import de.kecksbelit.tutorialmod.item.ModCreativeModeTab;
import de.kecksbelit.tutorialmod.item.ModItems;
import de.kecksbelit.tutorialmod.villager.ModVillagers;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Tutorialmod.MOD_ID)
public class Tutorialmod {
    public static final String MOD_ID = "tutorialmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Tutorialmod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModVillagers.register(modEventBus);
        ModEntityTypes.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        GeckoLib.initialize();
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }
    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModVillagers.registerPOIs();
        });
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == ModCreativeModeTab.TUTORIAL_TAB) {
            event.accept(ModItems.ZIRCON);
            event.accept(ModItems.EIGHT_BALL);
            event.accept(ModItems.ICE_BOAT_ITEM);
            event.accept(ModItems.BLUEBERRY);
            event.accept(ModItems.BLUEBERRY_SEEDS);
            event.accept(ModBlocks.ZIRCON_BLOCK);
            event.accept(ModBlocks.DEEPSLATE_ZIRCON_ORE);
            event.accept(ModBlocks.ZIRCON_ORE);
            event.accept(ModBlocks.ZIRCON_LAMP);
            event.accept(ModBlocks.JUMPY_BLOCK);
        }

        if(event.getTab() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.ZIRCON);
        }

        if(event.getTab() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.ZIRCON_BLOCK);
            event.accept(ModBlocks.DEEPSLATE_ZIRCON_ORE);
            event.accept(ModBlocks.ZIRCON_ORE);
            event.accept(ModBlocks.ZIRCON_LAMP);
            event.accept(ModBlocks.JUMPY_BLOCK);
        }
    }
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntityTypes.ICEBOAT.get(), IceBoatRenderer::new);
            EntityRenderers.register(ModEntityTypes.DIAMOND_WAND_ENTITY.get(), DiamondWandProjectileRenderer::new);
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.Blueberry_Crop.get(), RenderType.cutout());

        }
    }
}