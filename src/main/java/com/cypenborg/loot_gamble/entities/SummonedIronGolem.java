package com.cypenborg.loot_gamble.entities;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SummonedIronGolem extends IronGolem {

    private int lifeTime, maxLifeTime;
    Player summoner;

    public SummonedIronGolem(EntityType<? extends IronGolem> entityType, Level level) {
        super(entityType, level);
        setPlayerCreated(true);
        lifeTime = -1;
        maxLifeTime = 0;
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

    private void playDisappearSound(ServerLevel level) {
        level.playSound(null, blockPosition(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.NEUTRAL, 0.5f, 1f);
    }

    public void setSummoner(Player summoner) {
        this.summoner = summoner;
    }

    public void setMaxLifeTime(int maxLifeTime) {
        this.maxLifeTime = maxLifeTime;
    }
}
