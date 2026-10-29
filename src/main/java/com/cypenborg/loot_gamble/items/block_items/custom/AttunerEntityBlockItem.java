package com.cypenborg.loot_gamble.items.block_items.custom;

import com.cypenborg.loot_gamble.blocks.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class AttunerEntityBlockItem extends BlockItem {

    public AttunerEntityBlockItem(Properties properties) {
        super(ModBlocks.ATTUNER_ENTITY_BLOCK.get(), properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);

        builder.accept(
                Component.translatable("item.loot_gamble.attuner_entity_block.description")
                        .withColor(TextColor.WHITE)
        );
    }
}
