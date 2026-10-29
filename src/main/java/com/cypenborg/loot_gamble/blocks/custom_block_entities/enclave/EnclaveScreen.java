package com.cypenborg.loot_gamble.blocks.custom_block_entities.enclave;

import com.cypenborg.loot_gamble.LootGamble;
import com.cypenborg.loot_gamble.items.ModDataComponents;
import com.cypenborg.loot_gamble.items.custom.LootRollItem;
import com.cypenborg.loot_gamble.rolls.*;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import java.util.*;

public class EnclaveScreen extends AbstractContainerScreen<EnclaveMenu> {

    private static final int LABEL_START_X = 79, LABEL_START_Y = 12;
    private static final int MAX_ROLLS_RENDERING = 5;
    public static final int INDEX_BUFFER = 1000;
    private static final Identifier GUI_TEXTURE = Identifier.fromNamespaceAndPath(LootGamble.MODID,
            "textures/gui/enclave.png");

    private final List<EnclaveLabel.RollHeader> rollHeaders;
    private final Map<RollType, List<RollEntry>> rollEntryMap;
    private EnclaveButton.PageControl leftPageButton;
    private EnclaveButton.PageControl rightPageButton;
    private ItemStack lastItem = ItemStack.EMPTY;
    private boolean active;
    private Map<AbstractRoll, RollTier> currentItemRolls;
    private int scrollOffset = 0;

    public EnclaveScreen(EnclaveMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 176, 177);
        active = false;
        rollHeaders = new ArrayList<>();
        rollEntryMap = new HashMap<>();
        currentItemRolls = Map.of();
    }

    @Override
    protected void containerTick() {
        super.containerTick();

        ItemStack currentItem = getLootItemStack();

        boolean stackIsEmpty = currentItem.isEmpty();
        if (stackIsEmpty && active || !stackIsEmpty && !active) {
            setActiveStatus(!stackIsEmpty);
            lastItem = currentItem;
        }
        else if (lastItem != currentItem) {
            deactivateAll();
            setActiveStatus(true);
            lastItem = currentItem;
        }
    }

    private void setLootItemRolls() {
        ItemStack stack = getLootItemStack();
        if (stack.isEmpty()) currentItemRolls = Map.of();
        else {
            currentItemRolls = new HashMap<>();
            RollManager.RollDictionary.getAll().forEach(dict -> {
                Map<String, Integer> rollsPrimitive = stack.get(dict.getRelevantDataComponent());
                Optional<Map<AbstractRoll, RollTier>> optionalRollMap = RollManager.getRolls(rollsPrimitive);
                optionalRollMap.ifPresent(rollMap -> currentItemRolls.putAll(rollMap));
            });
        }
    }

    private ItemStack getLootItemStack() {
        return menu.getLootSlot().getItem();
    }

    private LootRollItem getLootItem() {
        if (getLootItemStack().getItem() instanceof LootRollItem lootItem) return lootItem;
        throw new IllegalStateException("Loot item must implement LootRollItem interface");
    }

    private RollTier getTierOfRoll(AbstractRoll roll) {
        return currentItemRolls.getOrDefault(roll, RollTier.getTierFromIndex(getLootItemStack().get(ModDataComponents.TIER)));
    }

    private int getNumberOfRolls(RollType type) {
        int count = 0;
        for (AbstractRoll roll : currentItemRolls.keySet())
            if (roll.getType() == type) count++;
        return count;
    }

    private int getMaxRollsApplicable(RollType type) {
        return getLootItem().getLootType().getGroupType().getNumberOfRolls(type);
    }

    @Override
    protected void init() {
        super.init();

        // HEADER
        addPageControlButton(LABEL_START_X - EnclaveButton.SIZE, LABEL_START_Y, false);
        addPageControlButton(LABEL_START_X + EnclaveLabel.WIDTH, LABEL_START_Y, true);

        for (RollManager.RollDictionary rollDictionary : RollManager.RollDictionary.getAll()) {
            RollType rollType = rollDictionary.getRollType();
            if (!rollType.isModifiable()) continue;

            // ROLL HEADER
            addLabelHeader(LABEL_START_X, LABEL_START_Y, rollType);

            // ROLLS
            List<RollEntry> rollEntries = new ArrayList<>();
            List<AbstractRoll> rollList = rollDictionary.getMap().values().stream().toList();
            for (AbstractRoll roll : rollList) {

                RollEntry entry = RollEntry.create(0, roll, this);
                rollEntries.add(entry);
            }
            rollEntryMap.put(rollType, rollEntries);
        }
    }

    private void addPageControlButton(int x, int y, boolean right) {

        EnclaveButton.PageControl button = new EnclaveButton.PageControl(getLeftPos() + x, getTopPos() + y, this, right);
        addRenderableWidget(button);
        if (right) rightPageButton = button;
        else leftPageButton = button;
    }

    private void addLabelHeader(int x, int y, RollType type) {
        EnclaveLabel.RollHeader label = new EnclaveLabel.RollHeader(getLeftPos() + x, getTopPos() + y, type);
        addRenderableWidget(label);
        rollHeaders.add(label);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        super.extractBackground(graphics, mouseX, mouseY, partialTick);

        graphics.blit(
                RenderPipelines.GUI_TEXTURED, GUI_TEXTURE,
                leftPos, topPos,
                0, 0,
                this.imageWidth, this.imageHeight,
                BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT
        );

    }

    private int getActivePageNum() {
        for (int i = 0; i < rollHeaders.size(); i++) {
            if (rollHeaders.get(i).isActive()) return i;
        }
        throw new IllegalStateException("Active Page should not be null");
    }

    private void setPageControlButtonsStatus(boolean active) {
        leftPageButton.setActive(active);
        rightPageButton.setActive(active);
        leftPageButton.setSelected(active);
        rightPageButton.setSelected(false);
    }

    private void setPage(EnclaveLabel.RollHeader header) {
        rollHeaders.forEach(rollHeader -> rollHeader.setActive(rollHeader == header));
        setLootItemRolls();

        List<RollEntry> possibleEntries = new ArrayList<>();
        rollEntryMap.forEach((type, rollEntries) ->
            rollEntries.forEach(entry -> {
                entry.setTier(getTierOfRoll(entry.getRoll()));
                if (type == header.getType()
                        && entry.getRoll().getApplicableLoot().contains(getLootItem().getLootType())
                        && entry.getTier().getIndex() >= entry.getRoll().getMinimumApplicableTier().getIndex()) {
                    possibleEntries.add(entry);
                }
                entry.setActive(false);
            })
        );

        int labelsToRender = Math.min(MAX_ROLLS_RENDERING, possibleEntries.size());
        for (int i = 0, y = LABEL_START_Y + EnclaveLabel.HEIGHT; i < labelsToRender; i++, y += EnclaveLabel.HEIGHT) {
            RollEntry entry = possibleEntries.get(i);
            entry.setY(y);
            entry.setActive(true);
        }
    }

    private void deactivateAll() {
        rollHeaders.forEach(header -> header.setActive(false));
        rollEntryMap.forEach((_, rollEntries) ->
            rollEntries.forEach(lbl -> lbl.setActive(false))
        );
        setPageControlButtonsStatus(false);
    }

    private void setActiveStatus(boolean active) {
        this.active = active;
        if (active) {
            setPage(rollHeaders.getFirst());
            setPageControlButtonsStatus(true);
        } else deactivateAll();
    }

    public void pageControlButtonClicked(EnclaveButton.PageControl button) {
        if (button.isSelected()) return;
        leftPageButton.setSelected(false);
        rightPageButton.setSelected(false);

        boolean right = button == rightPageButton;
        int pageNum = getActivePageNum();
        pageNum += right ? 1 : -1;
        if (pageNum == 0) leftPageButton.setSelected(true);
        else if (pageNum == rollHeaders.size() - 1) rightPageButton.setSelected(true);

        EnclaveLabel.RollHeader nextPage = rollHeaders.get(pageNum);
        setPage(nextPage);
    }

    public void addButtonClicked(RollEntry entry) {
        if (entry.add.isSelected()) return;

        if (menu.canAdd(entry.getTier())) {

            minecraft.gameMode.handleInventoryButtonClick(menu.containerId, entry.getId());
            setPage(rollHeaders.get(getActivePageNum()));
        }
    }

    public void delButtonClicked(RollEntry entry) {
        if (entry.del.isSelected()) return;

        minecraft.gameMode.handleInventoryButtonClick(menu.containerId, INDEX_BUFFER + entry.getId());
        setPage(rollHeaders.get(getActivePageNum()));
    }

    public static class RollEntry {

        private final EnclaveScreen screen;
        private final EnclaveLabel.Roll label;
        private final EnclaveButton.Add add;
        private final EnclaveButton.Del del;

        private RollEntry(int y, AbstractRoll roll, EnclaveScreen screen) {

            int leftPos = screen.leftPos;
            int yPos = screen.topPos + y;

            EnclaveButton.Add add = new EnclaveButton.Add(leftPos + LABEL_START_X - EnclaveButton.SIZE, yPos, this, screen);
            EnclaveLabel.Roll label = new EnclaveLabel.Roll(leftPos + LABEL_START_X, yPos, roll);
            EnclaveButton.Del del = new EnclaveButton.Del(leftPos + LABEL_START_X + EnclaveLabel.WIDTH, yPos, this, screen);
            this.screen = screen;
            this.label = label;
            this.add = add;
            this.del = del;

            screen.addRenderableWidget(add);
            screen.addRenderableWidget(label);
            screen.addRenderableWidget(del);
        }

        public static RollEntry create(int y, AbstractRoll roll, EnclaveScreen screen) {
            return new RollEntry(y, roll, screen);
        }

        public void updateButtonStatus(RollTier tier) {
            if (screen.getLootItemStack().isEmpty()) return;

            if (screen.currentItemRolls.containsKey(getRoll())) add.isContained();
            else if (screen.getNumberOfRolls(getRoll().getType()) >= screen.getMaxRollsApplicable(getRoll().getType())) add.isFull();
            else add.isClickable(tier);

            if (!screen.currentItemRolls.containsKey(getRoll())) del.notContained();
            else del.isClickable();
        }

        public void setY(int y) {
            add.setY(screen.topPos + y);
            label.setY(screen.topPos + y);
            del.setY(screen.topPos + y);
        }

        public void setTier(RollTier tier) {
            label.setTier(tier);
            updateButtonStatus(tier);
        }

        public void setActive(boolean active) {
            add.setActive(active);
            label.setActive(active);
            del.setActive(active);
        }

        public int getId() {
            return getRoll().getId();
        }

        public AbstractRoll getRoll() {
            return label.getRoll();
        }

        public RollTier getTier() {
            return label.getTier();
        }
    }
}
