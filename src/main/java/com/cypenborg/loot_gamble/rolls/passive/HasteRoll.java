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

public class HasteRoll extends AbstractPassiveRoll implements AddEffectRoll {

    public HasteRoll() {
        super("haste_roll");
    }

    @Override
    public Holder<MobEffect> getEffectHolder(RollTier tier) {
        return MobEffects.HASTE;
    }

    @Override
    public int getDuration(RollTier tier) {
        return BASE_SHORT_DURATION * 20;
    }

    @Override
    public int getAmplifier(RollTier tier) {
        return Math.max(BASE_AMPLIFIER + tier.getMultiplier(), 2);
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
        return List.of(LootType.PICKAXE, LootType.AXE, LootType.SHOVEL, LootType.HOE);
    }
}
