package com.cypenborg.loot_gamble.items.block_items;

import com.cypenborg.loot_gamble.blocks.custom_blocks.AbstractLootRollBlock;
import com.cypenborg.loot_gamble.items.ModDataComponents;
import com.cypenborg.loot_gamble.items.custom.LootGroupType;
import com.cypenborg.loot_gamble.items.custom.LootType;
import com.cypenborg.loot_gamble.rolls.RollTier;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.function.Consumer;

public abstract class AbstractLootRollBlockItem extends BlockItem {

    public AbstractLootRollBlockItem(Block block, Properties properties) {
        super(block, properties);
    }

    public abstract RollTier getTier();

    @Override
    public Component getName(ItemStack itemStack) {
        return ((MutableComponent) super.getName(itemStack)).withColor(getTier().getCol());
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display,
                                Consumer<Component> builder, TooltipFlag tooltipFlag) {

        if (itemStack.getItem() instanceof AbstractLootRollBlockItem blockItem
                && blockItem.getBlock() instanceof AbstractLootRollBlock block) {
            Map<RollTier, Integer> tierProbMap = block.getTierProbMap();
            int weightTotal = block.getTotalWeights();


            LootType lootType = itemStack.getOrDefault(ModDataComponents.LT_ATTUNEMENT, LootType.NONE);
            Component lootTypeComponent = lootType != LootType.NONE ?
                            Component.literal(" ⟶ ")
                            .append(Component.translatable(lootType.getTranslationKey())) :
                    CommonComponents.EMPTY;

            builder.accept(
                    Component.translatable("roll_tier.loot_gamble.attunement_header")
                            .append(CommonComponents.SPACE)
                            .append(Component.translatable(itemStack.getOrDefault(ModDataComponents.LGT_ATTUNEMENT, LootGroupType.NONE)
                                    .getTranslationKey()))
                            .append(lootTypeComponent)
                            .withColor(TextColor.GRAY)
            );

            builder.accept(CommonComponents.EMPTY);
            builder.accept(Component.translatable("roll_tier.loot_gamble.rate_header").withColor(TextColor.WHITE)
                    .withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.UNDERLINE));
            for (RollTier tier : tierProbMap.keySet()) {

                Component tierText = Component.translatable(tier.getTranslationKey()).append(": ").withColor(tier.getCol())
                        .append(Component.literal(formatPercentage(tierProbMap.get(tier), weightTotal)).withColor(TextColor.GRAY));
                builder.accept(tierText);
            }
        }
    }

    private static String formatPercentage(int value, int total) {
        return BigDecimal.valueOf(value)
                .multiply(BigDecimal.valueOf(100))
                .divide(BigDecimal.valueOf(total), 10, RoundingMode.HALF_UP)
                .stripTrailingZeros()
                .toPlainString() + "%";
    }
}
