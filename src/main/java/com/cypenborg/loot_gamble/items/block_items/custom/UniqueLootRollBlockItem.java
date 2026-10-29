package com.cypenborg.loot_gamble.items.block_items.custom;

import com.cypenborg.loot_gamble.blocks.ModBlocks;
import com.cypenborg.loot_gamble.items.block_items.AbstractLootRollBlockItem;
import com.cypenborg.loot_gamble.rolls.RollTier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;

public class UniqueLootRollBlockItem extends AbstractLootRollBlockItem {

    public UniqueLootRollBlockItem(Properties properties) {
        super(ModBlocks.UNIQUE_LOOT_ROLL_BLOCK.get(), properties);
    }

    @Override
    public RollTier getTier() {
        return RollTier.UNIQUE;
    }

    @Override
    public Component getName(ItemStack itemStack) {
        return ((MutableComponent) super.getName(itemStack)).withStyle(ChatFormatting.BOLD);
    }
}
