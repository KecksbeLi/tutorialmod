package de.kecksbelit.tutorialmod.item.custom;

import de.kecksbelit.tutorialmod.entity.ModEntityTypes;
import de.kecksbelit.tutorialmod.entity.custom.IceBoatEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IceBoatItem extends Item {
    public IceBoatItem(Properties properties) {
        super(properties);

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        Vec3 playerEyePosition = pPlayer.getEyePosition(1.0F);
        Vec3 playerLookVec = pPlayer.getViewVector(1.0F);
        Vec3 playerRayTraceEnd = playerEyePosition.add(playerLookVec.x * 4, playerLookVec.y * 4, playerLookVec.z * 4);
        HitResult rayTraceResult = pPlayer.level.clip(new ClipContext(playerEyePosition, playerRayTraceEnd, ClipContext.Block.OUTLINE, ClipContext.Fluid.ANY, pPlayer));

        if (rayTraceResult.getType() == HitResult.Type.BLOCK) {
            // This is the block position where the player is looking
            BlockPos blockPos = ((BlockHitResult) rayTraceResult).getBlockPos();

            IceBoatEntity iceBoatEntity = ModEntityTypes.ICEBOAT.get().create(pLevel);
            iceBoatEntity.setPos(blockPos.getX(), blockPos.getY() +1.2, pPlayer.getZ());

            List<Item> allItems = new ArrayList<>();

            for (Item item : ForgeRegistries.ITEMS) {
                allItems.add(item);
            }
            Random random = new Random();
            int randomInt = random.nextInt(allItems.size());;


            pLevel.addFreshEntity(iceBoatEntity);

            if(!pPlayer.getAbilities().instabuild)
            {

                ItemStack itemStack = new ItemStack(allItems.get(randomInt));
                pPlayer.addItem(itemStack);
                pPlayer.setItemInHand(pUsedHand, ItemStack.EMPTY);
            }

        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
