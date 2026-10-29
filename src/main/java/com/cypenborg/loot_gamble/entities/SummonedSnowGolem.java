package com.cypenborg.loot_gamble.entities;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.golem.SnowGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SummonedSnowGolem extends SnowGolem {

    private int lifeTime, maxLifeTime;
    Player summoner;

    public SummonedSnowGolem(EntityType<? extends SnowGolem> type, Level level) {
        super(type, level);
        lifeTime = -1;
        maxLifeTime = 0;

        MobEffectInstance fireRes = new MobEffectInstance(MobEffects.FIRE_RESISTANCE,
                -1, 0, true, false, false);
        addEffect(fireRes);
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
