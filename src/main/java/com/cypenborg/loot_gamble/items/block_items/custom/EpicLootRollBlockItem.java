package com.cypenborg.loot_gamble.items.block_items.custom;

import com.cypenborg.loot_gamble.blocks.ModBlocks;
import com.cypenborg.loot_gamble.items.block_items.AbstractLootRollBlockItem;
import com.cypenborg.loot_gamble.rolls.RollTier;

public class EpicLootRollBlockItem extends AbstractLootRollBlockItem {

    public EpicLootRollBlockItem(Properties properties) {
        super(ModBlocks.EPIC_LOOT_ROLL_BLOCK.get(), properties);
    }

    @Override
    public RollTier getTier() {
        return RollTier.EPIC;
    }
}
