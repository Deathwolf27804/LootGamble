package com.cypenborg.loot_gamble.blocks.custom_block_entities.enclave;

import com.cypenborg.loot_gamble.LootGamble;
import com.cypenborg.loot_gamble.rolls.AbstractRoll;
import com.cypenborg.loot_gamble.rolls.RollTier;
import com.cypenborg.loot_gamble.rolls.RollType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ActiveTextCollector;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.ComponentRenderUtils;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.Nullable;

public abstract class EnclaveLabel extends StringWidget {

    public static final int HEIGHT = 12;
    public static final int WIDTH = 68;

    private static final Identifier SPRITE =
            Identifier.fromNamespaceAndPath(LootGamble.MODID, "enclave/label");

    private static final Identifier HOVERED_SPRITE =
            Identifier.fromNamespaceAndPath(LootGamble.MODID, "enclave/label_highlighted");

    public EnclaveLabel(int x, int y, Component message, @Nullable Component tooltip) {
        super(x, y, WIDTH, HEIGHT, message, Minecraft.getInstance().font);
        if (tooltip != null) setTooltip(Tooltip.create(tooltip));
        setActive(false);
    }

    @Override
    public void extractWidgetRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        Identifier sprite = isHovered ? HOVERED_SPRITE : SPRITE;
        graphics.blitSprite(
                RenderPipelines.GUI_TEXTURED, sprite,
                getX(), getY(), width, height
        );
        super.extractWidgetRenderState(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public void visitLines(ActiveTextCollector output) {

        Component message = this.getMessage();
        Font font = this.getFont();
        int maxWidth = 68;
        int height = 12;
        int textWidth = font.width(message);
        int x = this.getX() + 3;
        int y = this.getY() + 1 + (height - 9) / 2;

        if (textWidth > maxWidth) output.accept(x, y, ComponentRenderUtils.clipText(message, font, maxWidth));
        else output.accept(x, y, message.getVisualOrderText());
    }

    @Override
    public void onClick(MouseButtonEvent event, boolean doubleClick) {}

    public void setActive(boolean active) {
        this.active = active;
        this.visible = active;
    }

    public static class Roll extends EnclaveLabel {

        private final AbstractRoll roll;
        private RollTier tier;

        public Roll(int x, int y, AbstractRoll roll) {
            super(x, y, Component.translatable(roll.getTranslationKey()), roll.getDescriptionComponent(RollTier.COMMON));
            this.roll = roll;
            tier = RollTier.COMMON;
        }

        public void setTier(RollTier tier) {
            this.tier = tier;

            setTooltip(Tooltip.create(roll.getDescriptionComponent(tier)));
            setMessage(
                    Component.translatable(roll.getTranslationKey())
                            .withStyle(style -> style.withColor(tier.getCol().getValue()))
            );
            setWidth(WIDTH);
        }

        public AbstractRoll getRoll() {
            return roll;
        }

        public RollTier getTier() {
            return tier;
        }
    }

    public static class RollHeader extends EnclaveLabel {

        private final RollType type;

        public RollHeader(int x, int y, RollType type) {
            super(x, y, Component.translatable(type.getHeaderTranslationKey()), null);
            this.type = type;
        }

        public RollType getType() {
            return type;
        }
    }
}
