package com.cypenborg.loot_gamble.blocks.custom_block_entities;

import com.cypenborg.loot_gamble.items.custom.LootRollItem;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

public class InstanceSlot extends Slot {

    Class<? extends LootRollItem> allowedClass;
    Class<? extends LootRollItem> disallowedClass;

    public InstanceSlot(Container container, int slot, int x, int y,
                        Class<? extends LootRollItem> allowedClass, @Nullable Class<? extends LootRollItem> disallowedClass) {
        super(container, slot, x, y);
        this.allowedClass = allowedClass;
        this.disallowedClass = disallowedClass;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return allowedClass.isInstance(stack.getItem()) && !disallowedClass.isInstance(stack.getItem());
    }
}
