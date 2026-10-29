package com.cypenborg.loot_gamble.rolls.right_click;

import com.cypenborg.loot_gamble.entities.ModEntityTypes;
import com.cypenborg.loot_gamble.entities.SummonedIronGolem;
import com.cypenborg.loot_gamble.entities.SummonedSnowGolem;
import com.cypenborg.loot_gamble.items.custom.LootType;
import com.cypenborg.loot_gamble.rolls.RollTier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SummonGolemRoll extends AbstractRightClickRoll{

    public static final int BASE_LIFETIME = 15 * 20;
    public static final int EXTENDED_COOLDOWN = 10 * 20;
    public static final RollTier IRON_GOLEM_TIER = RollTier.EPIC;

    public SummonGolemRoll() {
        super("summon_golem_roll");
    }

    @Override
    public List<LootType> getApplicableLoot() {
        return List.of(LootType.SWORD, LootType.AXE);
    }

    private EntityType<? extends Entity> getSummonedEntityType(RollTier tier) {
        return tier.getMultiplier() < IRON_GOLEM_TIER.getMultiplier() ?
                ModEntityTypes.SUMMONED_SNOW_GOLEM.get() : ModEntityTypes.SUMMONED_IRON_GOLEM.get();
    }

    private void setSummonedEntityTraits(LivingEntity livingEntity, RollTier tier) {
        if (livingEntity instanceof SummonedIronGolem ironGolem) ironGolem.setMaxLifeTime(BASE_LIFETIME);
        else if (livingEntity instanceof SummonedSnowGolem snowGolem) snowGolem.setMaxLifeTime(BASE_LIFETIME);

        MobEffectInstance summonSpeed = new MobEffectInstance(MobEffects.SPEED, -1, tier.getMultiplier());
        livingEntity.addEffect(summonSpeed);
    }

    @Override
    public void onRightClick(Level level, Player player, InteractionHand hand, RollTier tier) {

        LivingEntity summonedEntity = getSummonedEntityType(tier).create(level, EntitySpawnReason.COMMAND).asLivingEntity();
        if (summonedEntity != null) {

            setSummonedEntityTraits(summonedEntity, tier);
            Vec3 summonPosition = player.position().add(player.getLookAngle()).add(0, 0.25d, 0);
            summonedEntity.setPos(summonPosition);

            level.addFreshEntity(summonedEntity);
            addNewCooldown(player, player.getItemInHand(hand), getCooldown(tier));
            playActivateSoundEffect(level, player.blockPosition());
        }
    }

    @Override
    public int getCooldown(RollTier tier) {
        return BASE_LIFETIME + DEFAULT_COOLDOWN + EXTENDED_COOLDOWN;
    }

    @Override
    public Component getDescriptionComponent(RollTier tier) {
        String summonedMobName = getSummonedEntityType(tier).getDescription().getString();
        return applyStandardDescriptionFormatting(
                Component.translatable("roll_header.loot_gamble.more_info.summon_golem", summonedMobName, BASE_LIFETIME / 20)
        );
    }
}
