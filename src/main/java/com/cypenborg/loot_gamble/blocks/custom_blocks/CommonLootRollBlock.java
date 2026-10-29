package com.cypenborg.loot_gamble.blocks.custom_blocks;

import com.cypenborg.loot_gamble.rolls.RollTier;

public class CommonLootRollBlock extends AbstractLootRollBlock {

    public CommonLootRollBlock(Properties properties) {
        super(properties, RollTier.COMMON);
    }
}
