package de.kecksbelit.tutorialmod.block;

import de.kecksbelit.tutorialmod.Tutorialmod;
import de.kecksbelit.tutorialmod.item.ModCreativeModeTab;
import de.kecksbelit.tutorialmod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Tutorialmod.MOD_ID);

    public static final RegistryObject<Block> ZIRCON_BLOCK = registerBlock("zircon_block", () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(6F).requiresCorrectToolForDrops()), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> ZIRCON_ORE = registerBlock("zircon_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(6F).requiresCorrectToolForDrops(), UniformInt.of(3, 7)), ModCreativeModeTab.TUTORIAL_TAB);


    public static final RegistryObject<Block> DEEPSLATE_ZIRCON_ORE = registerBlock("deepslate_zircon_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(6F).requiresCorrectToolForDrops(), UniformInt.of(3, 7)), ModCreativeModeTab.TUTORIAL_TAB);

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab)
    {
        RegistryObject<T> registryBlock = BLOCKS.register(name, block);
        registerBlockItem(name, registryBlock, tab);
        return registryBlock;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab)
    {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }
    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
