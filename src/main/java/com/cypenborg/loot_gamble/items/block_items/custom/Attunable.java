package com.cypenborg.loot_gamble.items.block_items.custom;

import com.cypenborg.loot_gamble.items.custom.LootGroupType;
import net.minecraft.world.item.ItemStack;

public interface Attunable {

    LootGroupType getAttunement(ItemStack stack);
    void setAttunement(ItemStack stack, LootGroupType lgt);
}
