package com.cypenborg.loot_gamble.blocks.custom_block_entities.attuner;

import com.cypenborg.loot_gamble.LootGamble;
import com.cypenborg.loot_gamble.items.custom.LootGroupType;
import com.cypenborg.loot_gamble.items.custom.LootType;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public abstract class AttunerButton extends AbstractButton {

    private static final Identifier BUTTON_SPRITE =
            Identifier.fromNamespaceAndPath(LootGamble.MODID, "attuner/button");

    private static final Identifier HOVERED_SPRITE =
            Identifier.fromNamespaceAndPath(LootGamble.MODID, "attuner/button_highlighted");

    private static final Identifier SELECTED_SPRITE =
            Identifier.fromNamespaceAndPath(LootGamble.MODID, "attuner/button_selected");

    public static final int SIZE = 20;
    protected final AttunerScreen screen;
    private boolean selected;

    public AttunerButton(int x, int y, Component message, AttunerScreen screen) {
        super(x, y, SIZE, SIZE, message);
        this.screen = screen;
        setActive(false);
    }

    @Override
    protected void extractContents(GuiGraphicsExtractor graphics, int i, int i1, float v) {
        if (!this.active) return;

        Identifier sprite;

        if (this.selected) sprite = SELECTED_SPRITE;
        else if (this.isHoveredOrFocused()) sprite = HOVERED_SPRITE;
        else sprite = BUTTON_SPRITE;

        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                sprite,
                this.getX(),
                this.getY(),
                this.width,
                this.height
        );

        this.extractIcon(graphics);
    }

    protected abstract void extractIcon(GuiGraphicsExtractor graphics);

    @Override
    protected void updateWidgetNarration(NarrationElementOutput output) {
        this.defaultButtonNarrationText(output);
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setActive(boolean active) {
        this.active = active;
        this.visible = active;
    }

    public static class LGT extends AttunerButton {

        private final LootGroupType lgt;

        public LGT(int x, int y, LootGroupType lootGroupType, AttunerScreen screen) {
            super(x, y, Component.translatable(lootGroupType.getTranslationKey()), screen);
            this.lgt = lootGroupType;
            setTooltip(Tooltip.create(message));
        }

        @Override
        protected void extractIcon(GuiGraphicsExtractor graphics) {
            graphics.item(lgt.getDisplayItem().getDefaultInstance(), getX() + 2, getY() + 2);
        }

        @Override
        public void onPress(InputWithModifiers input) {
            screen.LGTButtonClicked(this);
        }

        public LootGroupType getLGT() {
            return lgt;
        }
    }

    public static class LT extends AttunerButton {

        private final LootType lt;

        public LT(int x, int y, LootType lt, AttunerScreen screen) {
            super(x, y, Component.translatable(lt.getTranslationKey()), screen);
            this.lt = lt;
            setTooltip(Tooltip.create(message));
        }

        @Override
        protected void extractIcon(GuiGraphicsExtractor graphics) {
            graphics.item(lt.getDisplayItem().getDefaultInstance(), getX() + 2, getY() + 2);
        }

        @Override
        public void onPress(InputWithModifiers input) {
            screen.LTButtonClicked(this);
        }

        public LootType getLT() {
            return lt;
        }
    }
}