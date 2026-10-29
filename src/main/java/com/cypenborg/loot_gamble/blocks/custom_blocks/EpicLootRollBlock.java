package com.cypenborg.loot_gamble.blocks.custom_blocks;

import com.cypenborg.loot_gamble.rolls.RollTier;

public class EpicLootRollBlock extends AbstractLootRollBlock{

    public EpicLootRollBlock(Properties properties) {
        super(properties, RollTier.EPIC);
    }
}
