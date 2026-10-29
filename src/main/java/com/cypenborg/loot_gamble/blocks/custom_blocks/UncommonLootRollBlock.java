package com.cypenborg.loot_gamble.blocks.custom_blocks;

import com.cypenborg.loot_gamble.rolls.RollTier;

public class UncommonLootRollBlock extends AbstractLootRollBlock{

    public UncommonLootRollBlock(Properties properties) {
        super(properties, RollTier.UNCOMMON);
    }
}
