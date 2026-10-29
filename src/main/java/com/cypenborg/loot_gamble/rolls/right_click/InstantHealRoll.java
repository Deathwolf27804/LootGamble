package com.cypenborg.loot_gamble.rolls.right_click;

import com.cypenborg.loot_gamble.items.custom.LootType;
import com.cypenborg.loot_gamble.rolls.RollTier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

public class InstantHealRoll extends AbstractRightClickRoll {

    public InstantHealRoll() {
        super("instant_heal_roll");
    }

    @Override
    public List<LootType> getApplicableLoot() {
        return List.of(LootType.SWORD, LootType.AXE, LootType.SPEAR);
    }

    @Override
    public RollTier getMinimumApplicableTier() {
        return RollTier.RARE;
    }

    public float getHealAmount(RollTier tier) {
        return 2 + tier.getMultiplier();
    }

    @Override
    public void onRightClick(Level level, Player player, InteractionHand hand, RollTier tier) {

        player.heal(getHealAmount(tier));
        addNewCooldown(player, player.getItemInHand(hand), DEFAULT_COOLDOWN);
        playActivateSoundEffect(level, player.blockPosition());
    }

    @Override
    public int getCooldown(RollTier tier) {
        return DEFAULT_COOLDOWN;
    }

    @Override
    public Component getDescriptionComponent(RollTier tier) {
        return applyStandardDescriptionFormatting(
                Component.translatable("roll_header.loot_gamble.more_info.instant_heal", getHealAmount(tier))
        );
    }
}
