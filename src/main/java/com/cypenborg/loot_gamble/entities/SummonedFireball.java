package com.cypenborg.loot_gamble.entities;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.hurtingprojectile.Fireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class SummonedFireball extends Fireball {

    private static final int APPLIED_FIRE_TICKS = 5 * 20;
    private int lifeTime, maxLifeTime;
    private float explosionPower;
    Player summoner;

    public SummonedFireball(EntityType<? extends Fireball> type, Level level) {
        super(type, level);
        lifeTime = -1;
        maxLifeTime = 0;
        explosionPower = 0;
    }

    @Override
    public void tick() {
        super.tick();

        if (level() instanceof ServerLevel serverLevel) {

            lifeTime++;
            if (lifeTime == maxLifeTime) {

                serverLevel.sendParticles(ParticleTypes.POOF, getX(), getY() + 1, getZ(),
                        30, 0.5, 0.5, 0.5, 1);
                playDisappearSound(serverLevel);
                discard();
            }
        }
    }

    public void explode() {
        if (level() instanceof ServerLevel level) {

            level.explode(this, getX(), getY(), getZ(), 4.0f, Level.ExplosionInteraction.NONE);
            discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        super.onHitBlock(hitResult);
        explode();
    }

    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        super.onHitEntity(hitResult);
        hitResult.getEntity().setRemainingFireTicks(APPLIED_FIRE_TICKS);
        explode();
    }

    public void setPositionRelativeTo(Player player) {

        Vec3 facing = player.getLookAngle();
        setPos(player.position().add(facing).add(0, 1.5d, 0));
        setDeltaMovement(facing);
    }

    private void playDisappearSound(ServerLevel level) {
        level.playSound(null, blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.NEUTRAL, 0.5f, 1f);
    }

    public void setSummoner(Player summoner) {
        this.summoner = summoner;
    }

    public void setMaxLifeTime(int maxLifeTime) {
        this.maxLifeTime = maxLifeTime;
    }

    public void setExplosionPower(float explosionPower) {
        this.explosionPower = explosionPower;
    }
}
