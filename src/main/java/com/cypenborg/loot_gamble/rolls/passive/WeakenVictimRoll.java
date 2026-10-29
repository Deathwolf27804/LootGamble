package com.cypenborg.loot_gamble.rolls.passive;

import com.cypenborg.loot_gamble.items.custom.LootType;
import com.cypenborg.loot_gamble.rolls.AddEffectRoll;
import com.cypenborg.loot_gamble.rolls.RollTier;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class WeakenVictimRoll extends AbstractPassiveRoll implements AddEffectRoll {

    public WeakenVictimRoll() {
        super("weaken_victim_roll");
    }

    @Override
    public Holder<MobEffect> getEffectHolder(RollTier tier) {
        return MobEffects.WEAKNESS;
    }

    @Override
    public int getDuration(RollTier tier) {
        return (BASE_SHORT_DURATION + tier.getMultiplier()) * 20;
    }

    @Override
    public int getAmplifier(RollTier tier) {
        return Math.max(BASE_AMPLIFIER + tier.getMultiplier(), 2);
    }

    @Override
    public void onDamageEntity(LivingEntity perpetrator, LivingEntity victim, float damage, ItemStack weapon, RollTier tier) {
        AddEffectRoll.super.onDamageEntity(victim, tier);
    }

    @Override
    public Component getDescriptionComponent(RollTier tier) {
        return AddEffectRoll.super.getDescriptionComponent(tier);
    }

    @Override
    public List<LootType> getApplicableLoot() {
        return List.of(LootType.SWORD, LootType.AXE, LootType.SPEAR);
    }
}
