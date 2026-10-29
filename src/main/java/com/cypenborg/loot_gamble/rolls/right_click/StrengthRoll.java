package com.cypenborg.loot_gamble.rolls.right_click;

import com.cypenborg.loot_gamble.items.custom.LootType;
import com.cypenborg.loot_gamble.rolls.AddEffectRoll;
import com.cypenborg.loot_gamble.rolls.RollTier;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

public class StrengthRoll extends AbstractRightClickRoll implements AddEffectRoll {

    public StrengthRoll() {
        super("strength_roll");
    }

    @Override
    public Holder<MobEffect> getEffectHolder(RollTier tier) {
        return MobEffects.STRENGTH;
    }

    @Override
    public int getDuration(RollTier tier) {
        return (BASE_MEDIUM_DURATION + 5 * tier.getMultiplier()) * 20;
    }

    @Override
    public int getAmplifier(RollTier tier) {
        return Math.max(BASE_AMPLIFIER + tier.getMultiplier(), 2);
    }

    @Override
    public void onRightClick(Level level, Player player, InteractionHand hand, RollTier tier) {
        AddEffectRoll.super.onRightClick(player, tier);
        addNewCooldown(player, player.getItemInHand(hand), getCooldown(tier));
        playActivateSoundEffect(level, player.blockPosition());
    }

    @Override
    public Component getDescriptionComponent(RollTier tier) {
        return AddEffectRoll.super.getDescriptionComponent(tier);
    }

    @Override
    public List<LootType> getApplicableLoot() {
        return List.of(LootType.SWORD, LootType.AXE, LootType.SPEAR);
    }

    @Override
    public int getCooldown(RollTier tier) {
        return getDuration(tier) + DEFAULT_COOLDOWN;
    }
}
