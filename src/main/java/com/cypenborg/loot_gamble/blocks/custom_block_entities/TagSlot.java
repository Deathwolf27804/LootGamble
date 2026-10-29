package com.cypenborg.loot_gamble.blocks.custom_block_entities;

import net.minecraft.tags.TagKey;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TagSlot extends Slot {

    TagKey<Item> itemTag;

    public TagSlot(Container container, int slot, int x, int y, TagKey<Item> allowedItemsTag) {
        super(container, slot, x, y);
        this.itemTag = allowedItemsTag;
    }

    @Override
    public boolean mayPlace(ItemStack itemStack) {
        return super.mayPlace(itemStack) && itemStack.is(itemTag);
    }
}
