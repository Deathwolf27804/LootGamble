package com.cypenborg.loot_gamble.rolls.unique;

import com.cypenborg.loot_gamble.items.custom.LootType;
import com.cypenborg.loot_gamble.rolls.AbstractRoll;
import com.cypenborg.loot_gamble.rolls.RollTier;
import com.cypenborg.loot_gamble.rolls.RollType;
import net.minecraft.network.chat.Component;

import java.util.List;

public abstract class AbstractUniqueRoll extends AbstractRoll {

    public AbstractUniqueRoll(String name) {
        super(name, RollType.UNIQUE);
    }

    @Override
    public Component getDescriptionComponent(RollTier tier) {
        return applyStandardDescriptionFormatting(Component.translatable("roll.loot_gamble." + getName() + ".description"));
    }

    @Override
    public List<LootType> getApplicableLoot() {
        return List.of();
    }
}
