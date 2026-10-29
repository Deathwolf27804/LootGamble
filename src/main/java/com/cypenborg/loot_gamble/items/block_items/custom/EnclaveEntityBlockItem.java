package com.cypenborg.loot_gamble.items.block_items.custom;

import com.cypenborg.loot_gamble.blocks.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class EnclaveEntityBlockItem extends BlockItem {

    public EnclaveEntityBlockItem(Properties properties) {
        super(ModBlocks.ENCLAVE_ENTITY_BLOCK.get(), properties);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);

        builder.accept(
                Component.translatable("item.loot_gamble.enclave_entity_block.description")
                        .withColor(TextColor.WHITE)
        );
    }
}
