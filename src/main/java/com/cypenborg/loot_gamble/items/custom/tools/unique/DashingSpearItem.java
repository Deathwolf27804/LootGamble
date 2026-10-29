package com.cypenborg.loot_gamble.items.custom.tools.unique;

import com.cypenborg.loot_gamble.items.custom.tools.SpearLootRollItem;
import com.cypenborg.loot_gamble.rolls.AbstractRoll;
import com.cypenborg.loot_gamble.rolls.RollTier;
import com.cypenborg.loot_gamble.rolls.Rolls;
import net.minecraft.world.item.ToolMaterial;

import java.util.Map;

public class DashingSpearItem extends SpearLootRollItem implements UniqueToolItem{

    public DashingSpearItem(Properties properties) {
        super(properties, ToolMaterial.NETHERITE, RollTier.UNIQUE);
    }

    @Override
    public Map<AbstractRoll, RollTier> getRolls() {
        return Map.of(Rolls.SATIATING_DASH_ROLL, RollTier.UNIQUE,
                Rolls.LUNGE_ROLL, RollTier.LEGENDARY,
                Rolls.JUMP_BOOST_ROLL, RollTier.LEGENDARY);
    }
}
