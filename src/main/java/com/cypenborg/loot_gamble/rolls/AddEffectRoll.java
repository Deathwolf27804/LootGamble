package com.cypenborg.loot_gamble.rolls;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import static com.cypenborg.loot_gamble.rolls.AbstractRoll.addEffectToEntity;
import static com.cypenborg.loot_gamble.rolls.AbstractRoll.applyStandardDescriptionFormatting;

public interface AddEffectRoll {

    int BASE_AMPLIFIER = 0;
    int BASE_SHORT_DURATION = 1;
    int BASE_MEDIUM_DURATION = 5;
    int BASE_LONG_DURATION = 15;

    Holder<MobEffect> getEffectHolder(RollTier tier);
    int getDuration(RollTier tier);
    int getAmplifier(RollTier tier);

    default void onRightClick(Player player, RollTier tier) {
        addEffectToEntity(player, getEffectHolder(tier), getDuration(tier), getAmplifier(tier));
    }

    default void inventoryTick(LivingEntity owner, RollTier tier) {
        addEffectToEntity(owner, getEffectHolder(tier), getDuration(tier), getAmplifier(tier));
    }

    default void onDamageEntity(LivingEntity victim, RollTier tier) {
        addEffectToEntity(victim, getEffectHolder(tier), getDuration(tier), getAmplifier(tier));
    }

    default Component getDescriptionComponent(RollTier tier) {
        String effectName = getEffectHolder(tier).value().getDisplayName().getString();
        return applyStandardDescriptionFormatting(Component.translatable("roll_header.loot_gamble.more_info.effects",
                effectName, getAmplifier(tier), getDuration(tier) / 20));
    }

    default Component getVictimDescriptionComponent(RollTier tier) {
        String effectName = getEffectHolder(tier).value().getDisplayName().getString();
        return applyStandardDescriptionFormatting(Component.translatable("roll_header.loot_gamble.more_info.effects_victim",
                        effectName, getAmplifier(tier), getDuration(tier) / 20));
    }
}
