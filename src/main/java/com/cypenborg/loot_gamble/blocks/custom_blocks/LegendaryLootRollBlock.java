package com.cypenborg.loot_gamble.blocks.custom_blocks;

import com.cypenborg.loot_gamble.rolls.RollTier;

public class LegendaryLootRollBlock extends AbstractLootRollBlock{

    public LegendaryLootRollBlock(Properties properties) {
        super(properties, RollTier.LEGENDARY);
    }
}
