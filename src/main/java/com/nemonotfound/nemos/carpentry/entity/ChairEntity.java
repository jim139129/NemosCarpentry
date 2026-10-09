package com.nemonotfound.nemos.carpentry.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class ChairEntity extends Entity {

    public ChairEntity(EntityType<?> type, Level level) {
        super(type, level);
        this.noPhysics = true;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {

    }

    @Override
    public void tick() {
        if (!this.level().isClientSide() && !this.isVehicle()) {
            this.discard();
        }
    }

    @Override
    public boolean hurtServer(@NotNull ServerLevel level, @NotNull DamageSource source, float amount) {
        return false;
    }

    @Override
    public boolean shouldRender(double x, double y, double z) {
        return false;
    }

    @Override
    protected void readAdditionalSaveData(@NotNull ValueInput valueInput) {

    }

    @Override
    protected void addAdditionalSaveData(@NotNull ValueOutput valueOutput) {

    }

    //TODO: Refactor
    @Override
    public @NotNull Vec3 getDismountLocationForPassenger(@NotNull LivingEntity passenger) {
        var direction = this.getDirection();

        if (direction.getAxis() != Direction.Axis.Y) {
            var offsetsForDirection = DismountHelper.offsetsForDirection(direction);
            var blockPos = this.blockPosition();
            var mutableBlockPos = new BlockPos.MutableBlockPos();

            for (Pose entityPose : passenger.getDismountPoses()) {
                var localBounds = passenger.getLocalBoundsForPose(entityPose);

                for (int[] offsets : offsetsForDirection) {
                    mutableBlockPos.set(blockPos.getX() + offsets[0], blockPos.getY() + 0.3, blockPos.getZ() + offsets[1]);
                    var blockFloorHeight = this.level().getBlockFloorHeight(mutableBlockPos);

                    if (DismountHelper.isBlockFloorValid(blockFloorHeight)) {
                        var vec3d = Vec3.upFromBottomCenterOf(mutableBlockPos, blockFloorHeight);

                        if (DismountHelper.canDismountTo(this.level(), passenger, localBounds.move(vec3d))) {
                            passenger.setPose(entityPose);

                            return vec3d;
                        }
                    }
                }
            }
        }

        return super.getDismountLocationForPassenger(passenger);
    }

    @Override
    public boolean dismountsUnderwater() {
        return false;
    }
}
