package de.kecksbelit.tutorialmod.event;

import de.kecksbelit.tutorialmod.Tutorialmod;
import de.kecksbelit.tutorialmod.item.ModItems;
import de.kecksbelit.tutorialmod.villager.ModVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = Tutorialmod.MOD_ID)
public class ModEvents {

    private static boolean putArmorOn = false;
    private static boolean playerStartedTheTickCounter = false;
    private static boolean playerStoppedJumping = false;
    private static int tickCounter = 0;
    private static boolean playerJumped = false;
    private static boolean isAllowedToUseOtherFlyingMethodes = false;

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event){
        if(event.getType() == VillagerProfession.TOOLSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.EIGHT_BALL.get(), 1);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 2),
                    stack,10,8,0.02F));
        }

        if(event.getType() == ModVillagers.JUMP_MASTER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ModItems.BLUEBERRY.get(), 15);
            int villagerLevel = 1;

            trades.get(villagerLevel).add((trader, rand) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 5),
                    stack,10,8,0.02F));
        }
    }

   @SubscribeEvent
   public static void onLivingFall(LivingFallEvent event) {
       if (event.getEntity() instanceof Player)
       {
           Player player = (Player) event.getEntity();
           if(player != null && player.getArmorSlots() != null)
           {
               event.setCanceled(player.getInventory().getArmor(0).getItem().equals(ModItems.BOOTS_OF_Volare.get()));
           }
       }
   }

   //@SubscribeEvent
   //public static void onKeyInputEvent(TickEvent.ClientTickEvent event) {
   //    Player player = Minecraft.getInstance().player;
   //    if(checkIfPlayerIsAllowedToFlyWithVolareBoots(player))
   //    {
   //        if(player.getInventory().getArmor(0).getItem() == ModItems.BOOTS_OF_Volare.get())
   //        {
   //            double x = player.getPosition(1).x;
   //            double y = player.getPosition(1).y;
   //            double z = player.getPosition(1).z;
   //            BlockState blockStateBelowPlayer = player.getCommandSenderWorld().getBlockState(new BlockPos(x,y -.05,z));
   //            if(blockStateBelowPlayer.getBlock() != Blocks.AIR && blockStateBelowPlayer.getBlock() != Blocks.WATER && blockStateBelowPlayer.getBlock() != Blocks.LAVA)
   //            {
   //                resetCheckIfPlayerJumpedTwiceAttributes();
   //                player.getAbilities().mayfly = false;
   //                player.getAbilities().flying = false;
   //                return;
   //            }
   //            if(playerStartedTheTickCounter)
   //            {
   //                allowFlyingTickCounter();
   //            }
   //            if(checkIfPlayerDoubleJumps() && !player.getAbilities().flying && !player.getAbilities().mayfly)
   //            {
   //                player.getAbilities().mayfly = true;
   //                player.getAbilities().flying = true;
   //            }
   //            putArmorOn = true;
   //        }
   //        else
   //        {
   //            if(putArmorOn)
   //            {
   //                putArmorOn = false;
   //                player.getAbilities().mayfly = false;
   //                player.getAbilities().flying = false;
   //            }
   //        }
   //    }
   //}

   //private static void resetCheckIfPlayerJumpedTwiceAttributes()
   //{
   //    playerJumped = false;
   //    playerStartedTheTickCounter = false;
   //    playerStoppedJumping = false;
   //    tickCounter = 0;
   //}

   //private static void allowFlyingTickCounter()
   //{
   //    if(tickCounter == 10)
   //    {
   //        resetCheckIfPlayerJumpedTwiceAttributes();
   //    }
   //    else
   //    {
   //        tickCounter += 1;
   //    }
   //}

   //private static boolean checkIfPlayerIsAllowedToFlyWithVolareBoots(Player player)
   //{
   //    if(player == null || player.getArmorSlots() == null)
   //    {
   //        return false;
   //    }
   //    if(player.isCreative() || player.isSpectator())
   //    {
   //        return false;
   //    }
   //    return true;
   //}
   //private static boolean checkIfPlayerDoubleJumps()
   //{
   //    if (Minecraft.getInstance().options.keyJump.isDown())
   //    {
   //        playerJumped = true;
   //        if(playerStoppedJumping && tickCounter <= 10)
   //        {
   //            resetCheckIfPlayerJumpedTwiceAttributes();
   //            return true;
   //        }
   //        playerStartedTheTickCounter = true;
   //    }
   //    else if(playerJumped)
   //    {
   //        playerStoppedJumping = true;
   //    }
   //    return false;
   //}
}
