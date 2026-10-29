package com.cypenborg.loot_gamble.items.custom.tools;

import com.cypenborg.loot_gamble.LootGamble;
import com.cypenborg.loot_gamble.items.ModItems;
import com.cypenborg.loot_gamble.items.custom.LootRollItem;
import com.cypenborg.loot_gamble.items.custom.LootType;
import com.cypenborg.loot_gamble.rolls.AbstractRoll;
import com.cypenborg.loot_gamble.rolls.RollManager;
import com.cypenborg.loot_gamble.rolls.RollTier;
import com.cypenborg.loot_gamble.rolls.right_click.AbstractRightClickRoll;
import com.cypenborg.loot_gamble.items.ModDataComponents;
import com.cypenborg.loot_gamble.util.ModKeyMappings;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public interface ToolLootRollItem extends LootRollItem {
    
    static @Nullable ItemStack createToolItemWithTier(ItemAttributeModifiers.Builder builder, LootType lootType, RollTier tier) {

        @Nullable LootRollItem correctItem = getCorrectLootRollItem(lootType, tier);
        if (correctItem == null) return null;

        if (correctItem instanceof Item item) item.getDefaultInstance();

        ItemStack stack = new ItemStack((ItemLike) correctItem, 1);
        stack.set(ModDataComponents.TIER, tier.getIndex());

        correctItem.addToolBaseAttributes(builder, stack, tier);
        stack.set(DataComponents.ATTRIBUTE_MODIFIERS, builder.build());
        stack.set(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT.withHidden(DataComponents.ATTRIBUTE_MODIFIERS, true));
        
        return stack;
    }

    private static @Nullable LootRollItem getCorrectLootRollItem(LootType lootType, RollTier tier) {
        for (DeferredHolder<Item, ? extends Item> item : ModItems.ITEMS.getEntries())
            if (item.get() instanceof LootRollItem lootRollItem && lootRollItem.getLootType() == lootType && lootRollItem.getTier() == tier)
                return lootRollItem;
        System.out.printf("LootType:%s with RollTier:%s was not registered in ModItems%n", lootType.name(), tier.name());
        return null;
    }

    static void use(Level level, Player player, InteractionHand hand) {

        if (!level.isClientSide()) {

            RollManager.getAllDataComponents().forEach((dataComponent) -> {

                Optional<Map<AbstractRoll, RollTier>> optionalRolls =
                        RollManager.getRolls(player.getItemInHand(hand).get(dataComponent));

                optionalRolls.ifPresent(rolls -> rolls.forEach(
                        (roll, tier) -> roll.onRightClick(level, player, hand, tier)
                ));
            });
        }
    }

    static void appendHoverText(ItemStack itemStack, Item.TooltipContext context, TooltipDisplay display,
                                Consumer<Component> builder, TooltipFlag tooltipFlag) {
        boolean moreInfo = tooltipFlag.hasShiftDown();

        // TIER
        int tierInt = itemStack.getOrDefault(ModDataComponents.TIER, RollTier.getLowestTierByIndex());
        RollTier tier = RollTier.getTierFromIndex(tierInt);
        builder.accept(Component.translatable("roll_tier.loot_gamble.header").append(": ").withColor(TextColor.GRAY)
                .append(Component.translatable(tier.getTranslationKey()).withColor(tier.getCol()))
        );

        // ATTRIBUTES
        builder.accept(CommonComponents.EMPTY);
        ItemAttributeModifiers modifiers = itemStack.getAttributeModifiers();
        for (ItemAttributeModifiers.Entry modifier : modifiers.modifiers()) {

            String name = modifier.attribute().value().getDescriptionId();
            double amt = modifier.modifier().amount() + getBaselineAttributeAmount(modifier);
            builder.accept(
                    Component.translatable(name).withColor(TextColor.BLUE)
                            .append(Component.literal(String.format(": %.2f", amt)))
            );
        }

        // ROLLS
        builder.accept(CommonComponents.EMPTY);
        for (RollManager.RollDictionary dictionary : RollManager.RollDictionary.getAll()) {

            Map<String, Integer> rollMap = itemStack.get(dictionary.getRelevantDataComponent());
            if (rollMap == null || rollMap.isEmpty()) continue;

            MutableComponent rollTypeHeader =
                    Component.translatable(dictionary.getRollType().getHeaderTranslationKey())
                            .append(Component.literal(": ")).withColor(TextColor.BLUE);

            List<String> rollNames = rollMap.keySet().stream().toList();

            MutableComponent fullRollTypeDescription = Component.empty();
            for (int i = 0; i < rollMap.size(); i++) {

                String rollName = rollNames.get(i);
                AbstractRoll roll = RollManager.getRoll(rollName);
                TextColor col = RollTier.getTierFromIndex(rollMap.get(rollName)).getCol();

                MutableComponent rollNameText = Component.translatable(roll.getTranslationKey()).withColor(col);
                if (roll instanceof AbstractRightClickRoll rightClickRoll) {
                    rollNameText.append(CommonComponents.SPACE)
                            .append(Component.translatable("roll_header.loot_gamble.cooldown",
                                    rightClickRoll.getCooldown(tier) / 20f)
                                    .withColor(TextColor.DARK_GRAY));
                }

                if (!moreInfo) {

                    fullRollTypeDescription.append(rollNameText);
                    if (i < rollMap.size() - 1) fullRollTypeDescription.append(Component.literal(" | "));

                } else {

                    Component rollNameDescription = roll.getDescriptionComponent(tier);
                    fullRollTypeDescription
                            .append(CommonComponents.NEW_LINE)
                            .append(CommonComponents.SPACE)
                            .append(rollNameText)
                            .append(CommonComponents.NEW_LINE)
                            .append(CommonComponents.SPACE).append(CommonComponents.SPACE)
                            .append(rollNameDescription);
                }

            }

            builder.accept(rollTypeHeader.append(fullRollTypeDescription));
        }

        // MORE DETAILED DESCRIPTION
        builder.accept(CommonComponents.EMPTY);
        if (!moreInfo) {

            builder.accept(
                    Component.translatable(String.format("roll_header.%s.more_info", LootGamble.MODID),
                                    ModKeyMappings.MORE_INFO_KEY.getTranslatedKeyMessage()
                            )
                            .withColor(TextColor.DARK_GRAY)
                            .withStyle(ChatFormatting.ITALIC)
            );
        }
    }

    static double getBaselineAttributeAmount(ItemAttributeModifiers.Entry attribute) {

        if (attribute.attribute().is(Attributes.ATTACK_DAMAGE)) return 1;
        else if (attribute.attribute().is(Attributes.ATTACK_SPEED)) return 4;
        else return 0;
    }
}
