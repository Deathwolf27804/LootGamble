package com.cypenborg.loot_gamble.rolls.passive;

import com.cypenborg.loot_gamble.items.custom.LootType;
import com.cypenborg.loot_gamble.rolls.RollTier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class LifeStealRoll extends AbstractPassiveRoll {

    public static final float BASE_LIFE_STEAL_PERCENTAGE = 0.2f;

    public LifeStealRoll() {
        super("life_steal_roll");
    }

    @Override
    public Component getDescriptionComponent(RollTier tier) {
        return applyStandardDescriptionFormatting(Component.translatable("roll_header.loot_gamble.more_info.life_steal",
                (int) (getLifeStealPercentage(tier) * 100))
        );
    }

    @Override
    public RollTier getMinimumApplicableTier() {
        return RollTier.RARE;
    }

    @Override
    public List<LootType> getApplicableLoot() {
        return List.of(LootType.SWORD, LootType.AXE, LootType.SPEAR);
    }

    @Override
    public void onDamageEntity(LivingEntity perpetrator, LivingEntity victim, float damage, ItemStack weapon, RollTier tier) {
        if (victim.isAlive()) {

            float life_steal_amt = damage * getLifeStealPercentage(tier);
            perpetrator.heal(life_steal_amt);
        }
    }

    public static float getLifeStealPercentage(RollTier rollTier) {
        return BASE_LIFE_STEAL_PERCENTAGE * (1 + 0.1f * rollTier.getMultiplier());
    }
}
