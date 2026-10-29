package com.cypenborg.loot_gamble.blocks.custom_block_entities.enclave;

import com.cypenborg.loot_gamble.LootGamble;
import com.cypenborg.loot_gamble.items.ModItems;
import com.cypenborg.loot_gamble.rolls.RollTier;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

public abstract class EnclaveButton extends AbstractButton {

    public static final int SIZE = 12;
    protected final EnclaveScreen screen;
    private boolean selected;

    public EnclaveButton(int x, int y, EnclaveScreen screen) {
        super(x, y, SIZE, SIZE, Component.empty());
        this.screen = screen;
        this.setActive(false);
    }

    protected abstract Identifier getSprite();
    protected abstract Identifier getHighlightedSprite();
    protected abstract Identifier getSelectedSprite();

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int i, int i1, float v) {
        if (!this.active) return;

        Identifier sprite;

        if (this.selected) sprite = getSelectedSprite();
        else if (this.isHoveredOrFocused()) sprite = getHighlightedSprite();
        else sprite = getSprite();


        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED, sprite,
                this.getX(), this.getY(), this.width, this.height
        );
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }

    public void setActive(boolean active) {
        this.active = active;
        this.visible = active;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public static class PageControl extends EnclaveButton {

        private final boolean right;

        private static final Identifier LEFT_SPRITE =
                Identifier.fromNamespaceAndPath(LootGamble.MODID, "enclave/page_button_left");

        private static final Identifier LEFT_HOVERED_SPRITE =
                Identifier.fromNamespaceAndPath(LootGamble.MODID, "enclave/page_button_left_highlighted");

        private static final Identifier LEFT_SELECTED_SPRITE =
                Identifier.fromNamespaceAndPath(LootGamble.MODID, "enclave/page_button_left_selected");

        private static final Identifier RIGHT_SPRITE =
                Identifier.fromNamespaceAndPath(LootGamble.MODID, "enclave/page_button_right");

        private static final Identifier RIGHT_HOVERED_SPRITE =
                Identifier.fromNamespaceAndPath(LootGamble.MODID, "enclave/page_button_right_highlighted");

        private static final Identifier RIGHT_SELECTED_SPRITE =
                Identifier.fromNamespaceAndPath(LootGamble.MODID, "enclave/page_button_right_selected");


        public PageControl(int x, int y, EnclaveScreen screen, boolean right) {
            super(x, y, screen);
            this.right = right;
        }

        @Override
        public void onPress(InputWithModifiers inputWithModifiers) {
            screen.pageControlButtonClicked(this);
        }

        @Override
        protected Identifier getSprite() {
            return right ? RIGHT_SPRITE : LEFT_SPRITE;
        }

        @Override
        protected Identifier getHighlightedSprite() {
            return right ? RIGHT_HOVERED_SPRITE : LEFT_HOVERED_SPRITE;
        }

        @Override
        protected Identifier getSelectedSprite() {
            return right ? RIGHT_SELECTED_SPRITE : LEFT_SELECTED_SPRITE;
        }
    }

    protected abstract static class RollEntryButton extends EnclaveButton {

        private final EnclaveScreen.RollEntry rollEntry;

        public RollEntryButton(int x, int y, EnclaveScreen.RollEntry rollEntry, EnclaveScreen screen) {
            super(x, y, screen);
            this.rollEntry = rollEntry;
        }

        public void setTooltip(String path) {
            setTooltip(Tooltip.create(Component.translatable(path)));
        }

        public EnclaveScreen.RollEntry getRollEntry() {
            return rollEntry;
        }
    }

    public static class Add extends RollEntryButton{

        private static final Identifier SPRITE =
                Identifier.fromNamespaceAndPath(LootGamble.MODID, "enclave/add_button");

        private static final Identifier HOVERED_SPRITE =
                Identifier.fromNamespaceAndPath(LootGamble.MODID, "enclave/add_button_highlighted");

        private static final Identifier SELECTED_SPRITE =
                Identifier.fromNamespaceAndPath(LootGamble.MODID, "enclave/add_button_selected");

        private static final String CONTAINED = "tooltips.loot_gamble.add.contained";
        private static final String FULL = "tooltips.loot_gamble.add.full";
        private static final String COST = "tooltips.loot_gamble.add.cost";

        public Add(int x, int y, EnclaveScreen.RollEntry entry, EnclaveScreen screen) {
            super(x, y, entry, screen);
        }

        public void isContained() {
            setSelected(true);
            setTooltip(CONTAINED);
        }

        public void isFull() {
            setSelected(true);
            setTooltip(FULL);
        }

        public void isClickable(RollTier tier) {
            setSelected(false);
            ItemStack randomCoreStack = ModItems.RANDOM_CORE.get().getDefaultInstance();
            ItemStack upgradeMaterialStack = tier.getUpgradeMaterial().getDefaultInstance();

            Component component = Component.translatable(COST)
                    .append(":")
                    .append(CommonComponents.NEW_LINE)
                    .append(randomCoreStack.getHoverName())
                    .append(": ")
                    .append(String.valueOf(tier.getRandomCoreCost()))
                    .append(CommonComponents.NEW_LINE)
                    .append(upgradeMaterialStack.getHoverName())
                    .append(": ")
                    .append(String.valueOf(tier.getUpgradeMaterialCost()));
            setTooltip(Tooltip.create(component));
        }

        @Override
        protected Identifier getSprite() {
            return SPRITE;
        }

        @Override
        protected Identifier getHighlightedSprite() {
            return HOVERED_SPRITE;
        }

        @Override
        protected Identifier getSelectedSprite() {
            return SELECTED_SPRITE;
        }

        @Override
        public void onPress(InputWithModifiers inputWithModifiers) {
            screen.addButtonClicked(getRollEntry());
        }
    }

    public static class Del extends RollEntryButton{

        private static final Identifier SPRITE =
                Identifier.fromNamespaceAndPath(LootGamble.MODID, "enclave/del_button");

        private static final Identifier HOVERED_SPRITE =
                Identifier.fromNamespaceAndPath(LootGamble.MODID, "enclave/del_button_highlighted");

        private static final Identifier SELECTED_SPRITE =
                Identifier.fromNamespaceAndPath(LootGamble.MODID, "enclave/del_button_selected");

        private static final String NOT_CONTAINED = "tooltips.loot_gamble.del.not_contained";
        private static final String DELELTABLE = "tooltips.loot_gamble.del.deletable";

        public Del(int x, int y, EnclaveScreen.RollEntry rollEntry, EnclaveScreen screen) {
            super(x, y, rollEntry, screen);
        }

        public void notContained() {
            setSelected(true);
            setTooltip(NOT_CONTAINED);
        }

        public void isClickable() {
            setSelected(false);
            setTooltip(DELELTABLE);
        }

        @Override
        protected Identifier getSprite() {
            return SPRITE;
        }

        @Override
        protected Identifier getHighlightedSprite() {
            return HOVERED_SPRITE;
        }

        @Override
        protected Identifier getSelectedSprite() {
            return SELECTED_SPRITE;
        }

        @Override
        public void onPress(InputWithModifiers inputWithModifiers) {
            screen.delButtonClicked(getRollEntry());
        }
    }
}
