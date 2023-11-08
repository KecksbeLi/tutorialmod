package de.kecksbelit.tutorialmod.entity.custom;

import de.kecksbelit.tutorialmod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class IceBoatEntity extends Boat implements GeoEntity {
    List<Integer> myList = new ArrayList<>();
    List<BlockPos> myBlockPos = new ArrayList<>();
    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private boolean lastTickInWater = false;
    private float lastYrot = this.getYRot();
    private double lastY = this.getY();
    private double lastX = this.getX();
    private double lastZ = this.getZ();
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    public IceBoatEntity(EntityType<? extends Boat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public void move(MoverType pType, Vec3 pPos) {
        super.move(pType, pPos);
    }

    @Override
    public void tick() {
        super.tick();
        float f = 0.0F;
        if(!myList.isEmpty())
        {
            int currentPos = 0;
            Iterator<Integer> iterator = myList.iterator();
            while (iterator.hasNext()) {
                int i = iterator.next();
                if (i == 100) {
                    level.setBlock(myBlockPos.get(currentPos), Blocks.WATER.defaultBlockState(), 2);
                    iterator.remove();
                    myBlockPos.remove(currentPos);
                } else {
                    i += 1;
                    myList.set(currentPos, i);
                    currentPos++;
                }
            }
        }
        if (this.isVehicle())
        {
            this.setDeltaMovement(this.getDeltaMovement().add((double)(Mth.sin(-this.getYRot() * ((float)Math.PI / 180F)) * f), 0.0D, (double)(Mth.cos(this.getYRot() * ((float)Math.PI / 180F)) * f)));
        }
        if (isInWater()) {
            // Calculate boat-like movement
            setDeltaMovement(getLookAngle().scale(0.2).add(0, 0.05, 0));
            lastTickInWater = true;
        }
        if(lastTickInWater == true && !isInWater())
        {
            tryToPlaceNineIceBlocksWhenWater(this.blockPosition().getY());
            tryToPlaceNineIceBlocksWhenWater(this.blockPosition().getY()-1);
        }
        if(!isInWater() && !lastTickInWater)
        {
            tryToPlaceNineIceBlocksWhenWater(this.blockPosition().getY());
            tryToPlaceNineIceBlocksWhenWater(this.blockPosition().getY()-1);
        }
    }

    @Override
    protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
        this.resetFallDistance();
        if (!this.canBoatInFluid(this.level.getFluidState(this.blockPosition().below())) && pY < 0.0D) {
        this.fallDistance -= (float)pY;
        }
        super.checkFallDamage(pY, pOnGround, pState, pPos);
    }

    @Override
    public Item getDropItem() {
        return ModItems.ICE_BOAT_ITEM.get();
    }
    private void tryToPlaceNineIceBlocksWhenWater(double yPosition)
    {
        if(this.level.getBlockState(this.getOnPos()) == Blocks.WATER.defaultBlockState() || this.level.getBlockState(this.getOnPos()).getMaterial() == Material.WATER_PLANT || this.level.getBlockState(this.getOnPos()) == Blocks.SEAGRASS.defaultBlockState()){
            int i = 3;
            int z = this.getOnPos().getZ() + 1;;
            while(i>0)
            {
                int x = this.getOnPos().getX() + 1;
                int o = 3;

                while(o>0)
                {
                    o--;
                    Vec3 vector = new Vec3(x, yPosition, z);
                    BlockPos blockPos = new BlockPos(vector);
                    BlockPos blockPosPlusOne = new BlockPos(vector.x, vector.y +1, vector.z);
                    if(this.level.getBlockState(blockPos) == Blocks.WATER.defaultBlockState() || this.level.getBlockState(blockPos).getMaterial() == Material.WATER_PLANT || this.level.getBlockState(blockPos) == Blocks.SEAGRASS.defaultBlockState())
                    {
                        if(lastTickInWater || (this.level.getBlockState(this.getOnPos()) == Blocks.PACKED_ICE.defaultBlockState() && this.level.getBlockState(blockPosPlusOne) == Blocks.PACKED_ICE.defaultBlockState()))
                        {
                            lastTickInWater = false;
                            Vec3 vec3 = new Vec3(this.getOnPos().getX(),this.getOnPos().getY() + 2.8,this.getOnPos().getZ());
                            this.setPos(vec3);
                        }
                        this.level.setBlock(blockPos, Blocks.PACKED_ICE.defaultBlockState(), 2);
                        myList.add(0);
                        myBlockPos.add(blockPos);
                    }
                    x --;
                }
                z--;
                i--;
            }
        }
    }
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::paddle));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    private <T extends GeoAnimatable> PlayState paddle(AnimationState<T> tAnimationState) {
        if((this.getYRot() != lastYrot || this.getY() != lastY || this.getX() != lastX || this.getZ() != lastZ)){
            lastYrot = this.getYRot();
            lastY = this.getY();
            lastX = this.getX();
            lastZ = this.getZ();
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.iceboat.paddle", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;

    }
}
