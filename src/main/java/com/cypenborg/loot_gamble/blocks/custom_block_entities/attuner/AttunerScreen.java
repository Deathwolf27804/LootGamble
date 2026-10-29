package com.cypenborg.loot_gamble.blocks.custom_block_entities.attuner;

import com.cypenborg.loot_gamble.LootGamble;
import com.cypenborg.loot_gamble.items.ModDataComponents;
import com.cypenborg.loot_gamble.items.custom.LootGroupType;
import com.cypenborg.loot_gamble.items.custom.LootType;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

import java.util.*;

public class AttunerScreen extends AbstractContainerScreen<AttunerMenu> {

    private static final int LGT_START_X = 60, LGT_START_Y = 15;
    private static final int LT_START_X = 60, LT_START_Y = 41;
    private static final Identifier GUI_TEXTURE = Identifier.fromNamespaceAndPath(LootGamble.MODID,
                    "textures/gui/attuner.png");
    private boolean lgtButtonsActive;
    private final List<AttunerButton.LGT> lgtButtons;
    private final Map<LootGroupType, List<AttunerButton.LT>> ltButtonsMap;

    public AttunerScreen(AttunerMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 176, 177);
        lgtButtonsActive = false;
        lgtButtons = new ArrayList<>();
        ltButtonsMap = new HashMap<>();
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        boolean stackIsEmpty = menu.getAttuningItemStack().isEmpty();
        if (stackIsEmpty && lgtButtonsActive || !stackIsEmpty && !lgtButtonsActive) setLGTButtonsActive(!stackIsEmpty);
    }

    @Override
    protected void init() {
        super.init();

        // LootGroupType Buttons
        LootGroupType[] lootGroupTypes = LootGroupType.values();
        for (int i = 0, x = LGT_START_X; i < lootGroupTypes.length; i++, x += AttunerButton.SIZE) {

            addLGTButton(x, LGT_START_Y, lootGroupTypes[i], this);

            List<LootType> lootTypes = lootGroupTypes[i].getLootTypes();
            List<AttunerButton.LT> ltButtons = new ArrayList<>();
            for (int i2 = 0, x2 = LT_START_X, y2 = LT_START_Y; i2 < lootTypes.size(); i2++, x2 += AttunerButton.SIZE) {
                if (lootGroupTypes[i] == LootGroupType.NONE) continue;

                ltButtons.add(addLTButton(x2, y2, lootTypes.get(i2), this));
                if (i2 % 5 == 4) {

                    x2 = LT_START_X - AttunerButton.SIZE;
                    y2 += AttunerButton.SIZE;
                }
            }
            ltButtonsMap.put(lootGroupTypes[i], ltButtons);
        }

    }

    private void addLGTButton(int x, int y, LootGroupType lgt, AttunerScreen screen) {

        AttunerButton.LGT button = new AttunerButton.LGT(this.getLeftPos() + x, this.getTopPos() + y, lgt, screen);
        addRenderableWidget(button);
        lgtButtons.add(button);
    }

    private AttunerButton.LT addLTButton(int x, int y, LootType lt, AttunerScreen screen) {

        AttunerButton.LT button = new AttunerButton.LT(this.getLeftPos() + x, this.getTopPos() + y, lt, screen);
        addRenderableWidget(button);
        return button;
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

    private LootGroupType getAttunedLGT() {
        return menu.getAttuningItemStack().getOrDefault(ModDataComponents.LGT_ATTUNEMENT, LootGroupType.NONE);
    }

    private Optional<LootType> getAttunedLT() {
        return Optional.ofNullable(menu.getAttuningItemStack().get(ModDataComponents.LT_ATTUNEMENT));
    }

    private void setLGTButtonsActive(boolean active) {
        lgtButtons.forEach(btn -> btn.setActive(active));
        lgtButtonsActive = active;
        if (active) {

            LootGroupType attunedLGT = getAttunedLGT();
            lgtButtons.forEach(btn -> {if (btn.getLGT() == attunedLGT) btn.setSelected(true);});
            setLTButtonsActive(attunedLGT);

            Optional<LootType> attunedLT = getAttunedLT();
            attunedLT.ifPresent(
                    (lt) -> ltButtonsMap.forEach((_, ltButtons) ->
                            ltButtons.forEach((btn) -> {
                                if (btn.getLT() == lt) btn.setSelected(true);
                            })
                    )
            );
        }
        else deactivateAllLTButtons();
    }

    private void setLTButtonsActive(LootGroupType activeLGT) {
        ltButtonsMap.forEach((lgt, ltButtons) ->
                ltButtons.forEach(btn -> btn.setActive(activeLGT == lgt)));
    }

    private void deactivateAllLTButtons() {
        ltButtonsMap.forEach((_, ltButtons) -> ltButtons.forEach((btn) -> btn.setActive(false)));
    }

    private void deselectAllLTButtons() {
        ltButtonsMap.forEach((_, ltButtons) -> ltButtons.forEach((btn) -> btn.setSelected(false)));
    }

    public void LGTButtonClicked(AttunerButton.LGT button) {
        LootGroupType selectedLGT = button.getLGT();
        lgtButtons.forEach(btn -> btn.setSelected(selectedLGT == btn.getLGT()));
        setLTButtonsActive(selectedLGT);
        deselectAllLTButtons();

        minecraft.gameMode.handleInventoryButtonClick(menu.containerId, selectedLGT.ordinal());
    }

    public void LTButtonClicked(AttunerButton.LT button) {
        LootGroupType activeLGT = getAttunedLGT();
        LootType selectedLT = button.getLT();
        ltButtonsMap.forEach((lgt, ltButtons) -> {
            if (activeLGT == lgt) ltButtons.forEach(btn -> btn.setSelected(selectedLT == btn.getLT()));
        });

        minecraft.gameMode.handleInventoryButtonClick(menu.containerId, LootGroupType.size() + selectedLT.ordinal());
    }
}