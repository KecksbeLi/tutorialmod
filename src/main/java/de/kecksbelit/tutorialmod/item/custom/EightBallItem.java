package de.kecksbelit.tutorialmod.item.custom;

import de.kecksbelit.tutorialmod.entity.ModEntityTypes;
import de.kecksbelit.tutorialmod.entity.custom.DiamondWandProjectile;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
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

public class EightBallItem extends Item {
    public EightBallItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide() && pUsedHand == InteractionHand.MAIN_HAND)
        {
            Vec3 playerEyePosition = pPlayer.getEyePosition(1.0F);
            Vec3 playerLookVec = pPlayer.getViewVector(1.0F);
            Vec3 playerRayTraceEnd = playerEyePosition.add(playerLookVec.x * 4, playerLookVec.y * 4, playerLookVec.z * 4);
            HitResult rayTraceResult = pPlayer.level.clip(new ClipContext(playerEyePosition, playerRayTraceEnd, ClipContext.Block.OUTLINE, ClipContext.Fluid.ANY, pPlayer));

            if (rayTraceResult.getType() != HitResult.Type.BLOCK)
            {
            DiamondWandProjectile diamondWandProjectile = ModEntityTypes.DIAMOND_WAND_ENTITY.get().create(pLevel);
            BlockPos blockPos = ((BlockHitResult) rayTraceResult).getBlockPos();
            diamondWandProjectile.setPos(blockPos.getX(), blockPos.getY() +1.2, pPlayer.getZ());
            diamondWandProjectile.shoot(pPlayer, 0.0F, 1.0F, 0.0F, 1.5F, 1.0F);
            pLevel.addFreshEntity(diamondWandProjectile);}
            randomNumberOutput(pPlayer);
            pPlayer.getCooldowns().addCooldown(this, 20);
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }



    private void randomNumberOutput(Player player)
    {
        player.sendSystemMessage(Component.literal("Your Number is" + getRandomNumber()));
    }

    private int getRandomNumber()
    {
        return RandomSource.createNewThreadLocalInstance().nextInt(10);
    }
}
