package de.kecksbelit.tutorialmod.core.init;
import de.kecksbelit.tutorialmod.core.init.custom.EightBallItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import de.kecksbelit.tutorialmod.Tutorialmod;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Tutorialmod.MODID);
    public static final RegistryObject<Item> TESTITEM = ITEMS.register("testitem", () -> new Item (new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));
    public static final RegistryObject<Item> EIGHT_BALL = ITEMS.register("eight_ball", () -> new EightBallItem(new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE).stacksTo(1)));
    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }

}
