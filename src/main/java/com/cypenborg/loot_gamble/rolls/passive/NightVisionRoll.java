package com.cypenborg.loot_gamble.rolls.passive;

import com.cypenborg.loot_gamble.items.custom.LootType;
import com.cypenborg.loot_gamble.rolls.AddEffectRoll;
import com.cypenborg.loot_gamble.rolls.RollTier;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class NightVisionRoll extends AbstractPassiveRoll implements AddEffectRoll {

    public NightVisionRoll() {
        super("night_vision_roll");
    }

    @Override
    public Holder<MobEffect> getEffectHolder(RollTier tier) {
        return MobEffects.NIGHT_VISION;
    }

    @Override
    public int getDuration(RollTier tier) {
        return (BASE_LONG_DURATION + 10 * tier.getMultiplier()) * 20;
    }

    @Override
    public int getAmplifier(RollTier tier) {
        return BASE_AMPLIFIER;
    }

    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel level, LivingEntity owner, @Nullable EquipmentSlot slot, RollTier tier) {
        AddEffectRoll.super.inventoryTick(owner, tier);
    }

    @Override
    public Component getDescriptionComponent(RollTier tier) {
        return AddEffectRoll.super.getDescriptionComponent(tier);
    }

    @Override
    public List<LootType> getApplicableLoot() {
        return List.of(LootType.PICKAXE);
    }
}
