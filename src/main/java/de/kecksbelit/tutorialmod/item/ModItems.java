package de.kecksbelit.tutorialmod.item;

import de.kecksbelit.tutorialmod.Tutorialmod;
import de.kecksbelit.tutorialmod.armor.VolareArmor;
import de.kecksbelit.tutorialmod.base.ModArmorMaterial;
import de.kecksbelit.tutorialmod.block.ModBlocks;
import de.kecksbelit.tutorialmod.item.custom.EightBallItem;
import de.kecksbelit.tutorialmod.item.custom.IceBoatItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Tutorialmod.MOD_ID);

    public static final RegistryObject<Item> ICE_BOAT_ITEM = ITEMS.register("iceboat_item",
            () -> new IceBoatItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> ZIRCON = ITEMS.register("zircon",() -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EIGHT_BALL = ITEMS.register("eight_ball",() -> new EightBallItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<ArmorItem> BOOTS_OF_Volare = ITEMS.register("boots_of_volare",() -> new VolareArmor(ArmorTiers.VOLARE, EquipmentSlot.FEET, new Item.Properties()));
    public static final RegistryObject<Item> BLUEBERRY_SEEDS = ITEMS.register("blueberry_seeds",() -> new ItemNameBlockItem(ModBlocks.Blueberry_Crop.get(),new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> BLUEBERRY = ITEMS.register("blueberry",() -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(4).build())));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }

    public static class ArmorTiers {
        public static final ArmorMaterial VOLARE = new ModArmorMaterial(
                "volare",
                500,
                new int[] { 2, 40, 50, 20 },
                300,
                SoundEvents.ARMOR_EQUIP_LEATHER,
                0.0f,
                0.0f,
                () -> Ingredient.of(ModItems.BOOTS_OF_Volare.get()));


    }
}
