package com.cypenborg.loot_gamble.blocks.custom_block_entities.enclave;

import com.cypenborg.loot_gamble.blocks.custom_block_entities.InstanceSlot;
import com.cypenborg.loot_gamble.blocks.custom_block_entities.ModMenus;
import com.cypenborg.loot_gamble.blocks.custom_block_entities.ItemSlot;
import com.cypenborg.loot_gamble.items.ModDataComponents;
import com.cypenborg.loot_gamble.items.ModItems;
import com.cypenborg.loot_gamble.items.custom.LootRollItem;
import com.cypenborg.loot_gamble.items.custom.tools.unique.UniqueToolItem;
import com.cypenborg.loot_gamble.rolls.AbstractRoll;
import com.cypenborg.loot_gamble.rolls.RollManager;
import com.cypenborg.loot_gamble.rolls.RollTier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class EnclaveMenu extends AbstractContainerMenu {

    private final Container inventory;
    private static final int LOOT_ITEM_SLOT = 0;
    private static final int RANDOM_CORE_SLOT = 1;
    private static final int MATERIAL_SLOT = 2;

    public EnclaveMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, new SimpleContainer(3));
    }

    public EnclaveMenu(int containerId, Inventory playerInventory, Container inventory) {
        super(ModMenus.ENCLAVE_MENU.get(), containerId);
        this.inventory = inventory;

        checkContainerSize(inventory, 3);
        inventory.startOpen(playerInventory.player);

        // Enclave slot
        addSlot(new InstanceSlot(inventory, LOOT_ITEM_SLOT, 26, 50, LootRollItem.class, UniqueToolItem.class));
        addSlot(new ItemSlot(inventory, RANDOM_CORE_SLOT, 16, 30, ModItems.RANDOM_CORE));
        addSlot(new Slot(inventory, MATERIAL_SLOT, 36, 30));

        // Player inventory
        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 9; col++)
                addSlot(new Slot(playerInventory, 9 + col + row * 9, 8 + col * 18, 95 + row * 18));

        // Player hotbar
        for (int col = 0; col < 9; col++)
            addSlot(new Slot(playerInventory, col, 8 + col * 18, 153));
    }


    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {
        if (slotIndex < 0 || slotIndex >= this.slots.size()) return ItemStack.EMPTY;

        Slot sourceSlot = this.slots.get(slotIndex);
        if (!sourceSlot.hasItem()) return ItemStack.EMPTY;

        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack originalStack = sourceStack.copy();

        if (slotIndex >= 0 && slotIndex <= 2) {

            if (!this.moveItemStackTo(sourceStack, 3, 39, true)) return ItemStack.EMPTY;
        } else {
            // Moving an item from player inventory into the Enclave.
            boolean inserted = false;

            // Try each input slot separately, respecting its mayPlace() rule.
            for (int enclaveSlotIndex = 0; enclaveSlotIndex < 3; enclaveSlotIndex++) {

                Slot enclaveSlot = this.slots.get(enclaveSlotIndex);
                if (enclaveSlot.mayPlace(sourceStack) &&
                        this.moveItemStackTo(sourceStack, enclaveSlotIndex, enclaveSlotIndex + 1, false)) {
                    inserted = true;
                    break;
                }
            }

            if (!inserted) {
                // Main inventory -> hotbar
                if (slotIndex >= 3 && slotIndex < 30) {
                    if (!this.moveItemStackTo(sourceStack, 30, 39, false)) return ItemStack.EMPTY;
                }
                // Hotbar -> main inventory
                else if (slotIndex >= 30 && slotIndex < 39) {
                    if (!this.moveItemStackTo(sourceStack, 3, 30, false)) return ItemStack.EMPTY;
                } else return ItemStack.EMPTY;
            }
        }

        if (sourceStack.isEmpty()) sourceSlot.set(ItemStack.EMPTY);
        else sourceSlot.setChanged();
        if (sourceStack.getCount() == originalStack.getCount()) return ItemStack.EMPTY;
        sourceSlot.onTake(player, sourceStack);
        return originalStack;
    }

    @Override
    public boolean clickMenuButton(Player player, int buttonID) {

        if (player.level() instanceof ServerLevel serverLevel) {

            if (inventory == null || !stillValid(player)) return false;

            ItemStack stack = getLootSlot().getItem();
            if (stack.isEmpty()) return false;

            boolean add = buttonID < EnclaveScreen.INDEX_BUFFER;
            int id = add ? buttonID : buttonID - EnclaveScreen.INDEX_BUFFER;

            AbstractRoll roll = RollManager.getRoll(id);
            RollTier tier = RollTier.getTierFromIndex(stack.get(ModDataComponents.TIER));

            if (add) {

                LootRollItem.addNewRoll(serverLevel, stack, roll, tier);
                getRandomCoreSlot().remove(tier.getRandomCoreCost());
                getMaterialSlot().remove(tier.getUpgradeMaterialCost());

                getLootSlot().setChanged();
                getRandomCoreSlot().setChanged();
                getMaterialSlot().setChanged();
            } else {

                LootRollItem.removeRoll(serverLevel, stack, roll, tier);
                getLootSlot().setChanged();
            }
            return true;

        } else return false;
    }

    public boolean canAdd(RollTier tier) {
        return !getLootSlot().getItem().isEmpty()
                && getRandomCoreSlot().getItem().count() >= tier.getRandomCoreCost()
                && getMaterialSlot().getItem().is(tier.getUpgradeMaterial())
                && getMaterialSlot().getItem().count() >= tier.getUpgradeMaterialCost();
    }

    public InstanceSlot getLootSlot() {
        return (InstanceSlot) slots.get(LOOT_ITEM_SLOT);
    }

    public ItemSlot getRandomCoreSlot() {
        return (ItemSlot) slots.get(RANDOM_CORE_SLOT);
    }

    public Slot getMaterialSlot() {
        return slots.get(MATERIAL_SLOT);
    }

    @Override
    public boolean stillValid(Player player) {
        return inventory.stillValid(player);
    }
}
