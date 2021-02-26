package elbrunanzo.cobble.fish;

import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Random;
import java.util.function.Predicate;

public class cobblefishEntity extends SchoolingFishEntity {
    public cobblefishEntity(EntityType<? extends SchoolingFishEntity> entityType, World world) {
        super(entityType, world);
    }

    protected EntityNavigation createNavigation(World world) {
        return (new MobNavigation(this, world));
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1,new swimtobottomgoal(this,0.1D,40));
        this.goalSelector.add(0, new EscapeDangerGoal(this, 0.2D));
        GoalSelector var10000 = this.goalSelector;
        Predicate var10009 = EntityPredicates.EXCEPT_SPECTATOR;
        var10009.getClass();
        var10000.add(3, new FleeEntityGoal(this, PlayerEntity.class, 2.0F, 0.15D, 0.2D, var10009::test));
        this.goalSelector.add(4, new FollowGroupLeaderGoal(this));
        this.goalSelector.add(2, new swimatbottomgoal(this, 0.1, 1));
    }

    protected ItemStack getFishBucketItem() {
        return new ItemStack(cobblefish.cobblefish_bucket);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_COD_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_COD_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_COD_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_COD_FLOP;
    }

    //changed the canSSpawn method !world.getBlockState(pos.down()).isOf(Blocks.WATER) instead of (!world.getBlockState(pos.down()).isOf(Blocks.WATER) || !world.getBlockState(pos.down(2)).isOf(Blocks.WATER))
    // This caused wrong spawning, I changed the weight too: 7 to 20; this might flood the ocean or make it very rare bcs of the hard spawnrestriction, time will tell.
    public static boolean canSSpawn(EntityType<cobblefishEntity> mob, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return !(world.getBlockState(pos.down()).getBlock() instanceof PlantBlock) && world.getBlockState(pos).isOf(Blocks.WATER) && !world.getBlockState(pos.down()).isOf(Blocks.WATER);
    }

    public static class swimtobottomgoal extends MoveToTargetPosGoal{
        public swimtobottomgoal(PathAwareEntity mob, double speed, int range) {
            super(mob, speed, range);
        }

        @Override
        protected boolean isTargetPos(WorldView world, BlockPos pos) {
            Block block = world.getBlockState(pos.down()).getBlock();
            if (block != Blocks.WATER && pos.getY() <= this.mob.getPos().getY()) {
                return true;
            }
            return false;
        }

        @Override
        public boolean canStart() {
                return super.canStart();
            }

        }


    public static class swimatbottomgoal extends MoveToTargetPosGoal {
        public swimatbottomgoal(PathAwareEntity mob, double speed, int range) {
            super(mob, speed, range);
        }

        @Override
        protected boolean isTargetPos(WorldView world, BlockPos pos) {
            Block block = world.getBlockState(pos).getBlock();
            if (block == Blocks.WATER && pos.getY() <= this.mob.getPos().getY()) {
                return true;
            }
            return false;
        }

        @Override
        public boolean canStart() {
            if (targetPos.getY() <= this.mob.getPos().getY()) {
                return super.canStart();
            }
            return false;
        }


    }
}



