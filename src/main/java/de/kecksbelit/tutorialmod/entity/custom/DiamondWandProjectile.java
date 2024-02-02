package de.kecksbelit.tutorialmod.entity.custom;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;

public class DiamondWandProjectile extends Projectile {
    private int removeTick = 0;
    public DiamondWandProjectile(EntityType<? extends Projectile> type, Level world) {
        super(type, world);
        // Set size, AI, goals, etc.
    }
    // Additional methods and overrides
   // private static final float ANIMATION_DURATION_TICKS = 2.25f * 20.0f; // 2.25 seconds * 20 ticks per second
   // private static final float ROTATION_AMOUNT = -360.0f;
   // private float currentRotation = 0.0f; // Current rotation
   // private float rotationPerTick = ROTATION_AMOUNT / ANIMATION_DURATION_TICKS; // Rotation per tick


    @Override
    protected void defineSynchedData() {

    }

    public void shoot(Entity shooter, float pitch, float yaw, float roll, float velocity, float inaccuracy) {
        float xDirection = (float) (-Math.sin(yaw * 0.017453292F) * Math.cos(pitch * 0.017453292F));
        float yDirection = (float) -Math.sin((pitch + roll) * 0.017453292F);
        float zDirection = (float) (Math.cos(yaw * 0.017453292F) * Math.cos(pitch * 0.017453292F));

        Vector3f direction = new Vector3f(xDirection, yDirection, zDirection);
        direction.normalize();
        direction.mul(velocity);
        direction.add(0.0000005F, yDirection,0.0000005F);

        this.setDeltaMovement(new Vec3(direction.x(), direction.y(), direction.z()));
        this.setPos(shooter.getX(), shooter.getY() + shooter.getEyeHeight(), shooter.getZ());
    }


   //public float getCurrentRotation() {
   //    return currentRotation;
   //}
    @Override
    public void tick() {
        removeTick ++;
        super.tick();
        Vec3 motion = this.getDeltaMovement();
        if(removeTick == 200)
        {
            this.discard();
            this.remove(RemovalReason.DISCARDED);
        }
        // Gravity
        motion = motion.add(0, 0, 0); // Adjust gravity factor as needed


        this.setPos(this.getX() + motion.x, this.getY(), this.getZ() + motion.z);
        this.setDeltaMovement(motion);

    }

    @Override
    public void stopSeenByPlayer(ServerPlayer pServerPlayer) {
        super.stopSeenByPlayer(pServerPlayer);
        this.discard();
        this.remove(RemovalReason.DISCARDED);
    }
}