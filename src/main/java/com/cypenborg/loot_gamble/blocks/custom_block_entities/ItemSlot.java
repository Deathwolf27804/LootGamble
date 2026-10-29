package com.cypenborg.loot_gamble.blocks.custom_block_entities;

import net.minecraft.tags.TagKey;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class ItemSlot extends Slot {

    protected Supplier<? extends Item> allowedItem;

    public ItemSlot(Container container, int slot, int x, int y, Supplier<? extends Item> allowedItems) {
        super(container, slot, x, y);
        this.allowedItem = allowedItems;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.is(allowedItem.get());
    }

    public static class Dynamic extends ItemSlot {

        public Dynamic(Container container, int slot, int x, int y) {
            super(container, slot, x, y, null);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return allowedItem != null && super.mayPlace(stack);
        }

        public void setAllowedItem(Supplier<? extends Item> item) {
            this.allowedItem = item;
        }

        public void removeTag() {
            allowedItem = null;
        }
    }
}
