package com.cypenborg.loot_gamble.blocks.custom_blocks;

import com.cypenborg.loot_gamble.rolls.RollTier;

public class RareLootRollBlock extends AbstractLootRollBlock{

    public RareLootRollBlock(Properties properties) {
        super(properties, RollTier.RARE);
    }
}
