package de.kecksbelit.tutorialmod.item.custom;

import de.kecksbelit.tutorialmod.entity.ModEntityTypes;
import de.kecksbelit.tutorialmod.entity.custom.IceBoatEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class IceBoatItem extends Item {
    public IceBoatItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        IceBoatEntity iceBoatEntity = ModEntityTypes.ICEBOAT.get().create(pLevel);
        iceBoatEntity.setPos(pPlayer.getX(), pPlayer.getY(), pPlayer.getZ());

        pPlayer.getViewVector(1);
        pLevel.addFreshEntity(iceBoatEntity);

        if(!pPlayer.getAbilities().instabuild)
        {
            pPlayer.setItemInHand(pUsedHand, ItemStack.EMPTY);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
