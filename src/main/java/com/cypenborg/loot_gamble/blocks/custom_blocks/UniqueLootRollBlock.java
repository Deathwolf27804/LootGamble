package com.cypenborg.loot_gamble.blocks.custom_blocks;

import com.cypenborg.loot_gamble.rolls.RollTier;

public class UniqueLootRollBlock extends AbstractLootRollBlock {

    public UniqueLootRollBlock(Properties properties) {
        super(properties, RollTier.UNIQUE);
    }
}
