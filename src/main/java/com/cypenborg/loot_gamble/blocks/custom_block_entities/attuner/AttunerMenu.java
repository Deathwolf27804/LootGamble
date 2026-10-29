package com.cypenborg.loot_gamble.blocks.custom_block_entities.attuner;

import com.cypenborg.loot_gamble.blocks.custom_block_entities.ModMenus;
import com.cypenborg.loot_gamble.blocks.custom_block_entities.ItemSlot;
import com.cypenborg.loot_gamble.blocks.custom_block_entities.TagSlot;
import com.cypenborg.loot_gamble.datagen.ModTags;
import com.cypenborg.loot_gamble.items.ModDataComponents;
import com.cypenborg.loot_gamble.items.ModItems;
import com.cypenborg.loot_gamble.items.custom.LootGroupType;
import com.cypenborg.loot_gamble.items.custom.LootType;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.Container;

public class AttunerMenu extends AbstractContainerMenu {

    private final Container inventory;

    // Client-side constructor; the server supplies the real inventory.
    public AttunerMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, new SimpleContainer(1));
    }

    public AttunerMenu(int containerId, Inventory playerInventory, Container inventory) {
        super(ModMenus.ATTUNER_MENU.get(), containerId);
        this.inventory = inventory;

        checkContainerSize(inventory, 1);
        inventory.startOpen(playerInventory.player);

        // Attuner slot
        addSlot(new TagSlot(inventory, 0, 16, 35, ModTags.Items.ATTUNABLE));

        // Player inventory
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 9; col++)
                addSlot(new Slot(playerInventory, 9 + col + row * 9, 8 + col * 18, 95 + row * 18));

        // Player hotbar
        for (int col = 0; col < 9; col++)
            addSlot(new Slot(playerInventory, col, 8 + col * 18, 153));
    }

    public ItemStack getAttuningItemStack() {
        return slots.getFirst().getItem();
   }

    @Override
    public boolean stillValid(Player player) {
        return inventory.stillValid(player);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        Slot slot = this.slots.get(slotIndex);

        if (!slot.hasItem()) {
            return ItemStack.EMPTY;
        }

        ItemStack sourceStack = slot.getItem();
        ItemStack originalStack = sourceStack.copy();

        if (slotIndex == 0) {
            if (!this.moveItemStackTo(sourceStack, 1, 37, true)) return ItemStack.EMPTY;

        } else {
            Slot attunerSlot = this.slots.getFirst();

            if (attunerSlot.mayPlace(sourceStack)) {
                if (!this.moveItemStackTo(sourceStack, 0, 1, false)) return ItemStack.EMPTY;
            } else {

                if (slotIndex < 28) {
                    if (!this.moveItemStackTo(sourceStack, 28, 37, false)) return ItemStack.EMPTY;
                } else {
                    if (!this.moveItemStackTo(sourceStack, 1, 28, false)) return ItemStack.EMPTY;
                }
            }
        }

        if (sourceStack.isEmpty()) slot.set(ItemStack.EMPTY);
        else slot.setChanged();
        if (sourceStack.getCount() == originalStack.getCount()) return ItemStack.EMPTY;
        slot.onTake(player, sourceStack);
        return originalStack;
    }

    @Override
    public boolean clickMenuButton(Player player, int buttonId) {
        if (inventory == null || !stillValid(player)) return false;

        ItemStack item = getAttuningItemStack();
        if (item.isEmpty()) return false;


        // LGT BUTTONS
        LootGroupType[] lootGroupTypes = LootGroupType.values();
        if (buttonId >= 0 && buttonId < lootGroupTypes.length) {
            item.set(ModDataComponents.LGT_ATTUNEMENT, lootGroupTypes[buttonId]);
            item.set(ModDataComponents.LT_ATTUNEMENT, LootType.NONE);
            slots.getFirst().setChanged();
            return true;
        }

        // LT BUTTONS
        LootType[] lootTypes = LootType.values();
        if (buttonId >= lootGroupTypes.length && buttonId < lootGroupTypes.length + lootTypes.length) {

            item.set(ModDataComponents.LT_ATTUNEMENT, lootTypes[buttonId - lootGroupTypes.length]);
            slots.getFirst().setChanged();
            return true;
        }

        throw new IllegalArgumentException(
                String.format("Button ID of %d is not in range [0-%d)", buttonId, lootGroupTypes.length + lootTypes.length)
        );
    }
}
