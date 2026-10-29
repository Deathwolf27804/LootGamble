package com.cypenborg.loot_gamble.items.custom;

import com.cypenborg.loot_gamble.blocks.custom_blocks.AbstractLootRollBlock;
import com.cypenborg.loot_gamble.items.ModItems;
import com.cypenborg.loot_gamble.rolls.AbstractRoll;
import com.cypenborg.loot_gamble.rolls.RollManager;
import com.cypenborg.loot_gamble.rolls.RollTier;
import com.cypenborg.loot_gamble.items.custom.tools.ToolLootRollItem;
import com.cypenborg.loot_gamble.items.custom.tools.unique.UniqueToolItem;
import com.cypenborg.loot_gamble.items.ModDataComponents;
import com.cypenborg.loot_gamble.rolls.RollType;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.*;

public final class LootRollManager {

    static final RandomSource randomSource = RandomSource.create();

    public static @Nullable ItemStack createNewToolLootRoll(ServerLevel level, ServerPlayer player,
                                                  AbstractLootRollBlock block, BlockState state) {

        // ATTUNEMENT
        LootGroupType lgt = state.getValueOrElse(AbstractLootRollBlock.LGT_ATTUNEMENT, LootGroupType.NONE);
        LootType lt = state.getValueOrElse(AbstractLootRollBlock.LT_ATTUNEMNT, LootType.NONE);

        // CHOOSE ITEM TIER
        Map<RollTier, Integer> tierWeightMap = block.getTierProbMap();
        int weightTotal = block.getTotalWeights();
        int rInt = randomSource.nextInt(weightTotal);
        RollTier selectedTier = findKeyFromRandomNum(tierWeightMap, rInt);

        // IF ITEM TIER IS UNIQUE
        if (selectedTier == RollTier.UNIQUE) return createNewUniqueToolRollItem(level, player);

        // CHOOSE TOOL TYPE
        LootType selectedLootType = lt == LootType.NONE ? chooseRandomLootType(lgt) : lt;

        // CREATE ITEM WITH TIER
        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
        @Nullable ItemStack stack = ToolLootRollItem.createToolItemWithTier(builder, selectedLootType, selectedTier);
        if (stack == null) return null;

        // ROLLS
        List<RollManager.RollDictionary> rollDictionaries = RollManager.RollDictionary.getAll();
        for (RollManager.RollDictionary rollDictionary : rollDictionaries) {

            RollType rollType = rollDictionary.getRollType();
            int numberOfRolls = LootGroupType.TOOLS.getNumberOfRolls(rollType);
            if (numberOfRolls == 0) continue;

            Map<String, AbstractRoll> rollMap = rollDictionary.getMap();
            List<AbstractRoll> possibleRolls = new ArrayList<>();
            rollMap.values().forEach((roll) -> {

                if (stack.getItem() instanceof LootRollItem lootItem &&
                        roll.getApplicableLoot().contains(lootItem.getLootType()) &&
                            selectedTier.getIndex() >= roll.getMinimumApplicableTier().getIndex())
                    possibleRolls.add(roll);
            });

            // Roll and tier random selection
            for (int rollCount = 0; rollCount < numberOfRolls && !possibleRolls.isEmpty(); rollCount++) {
                int maxTierIndex = RollTier.LEGENDARY.getIndex();

                int randomRollInt = randomSource.nextInt(possibleRolls.size());
                AbstractRoll selectedRoll = possibleRolls.remove(randomRollInt);

                int randomRollTierInt = randomSource.nextInt(block.getTotalWeights());
                RollTier selectedRollTier = findKeyFromRandomNum(block.getTierProbMap(), Math.min(randomRollTierInt, maxTierIndex));

                LootRollItem.addNewRoll(level, stack, selectedRoll, selectedRollTier);
            }
        }

        // CUSTOM ITEM NAME
        MutableComponent defaultName = stack.getHoverName().copy();
        String stars = "☆".repeat(selectedTier.getMultiplier() + 1);
        Component name = defaultName.append(CommonComponents.SPACE).append(stars).withColor(selectedTier.getCol());
        stack.set(DataComponents.ITEM_NAME, name);

        return stack;
    }

    public static ItemStack createNewUniqueToolRollItem(ServerLevel level, ServerPlayer player) {

        final RollTier uniqueTier = RollTier.UNIQUE;
        List<UniqueToolItem> uniqueToolItems = new ArrayList<>();
        ModItems.ITEMS.getEntries().forEach((item) -> {
            if (item.get() instanceof UniqueToolItem uniqueToolItem) uniqueToolItems.add(uniqueToolItem);
        });

        // CHOOSE UNIQUE ITEM
        int randomUniqueToolInt = randomSource.nextInt(uniqueToolItems.size());
        UniqueToolItem selectedItem = uniqueToolItems.get(randomUniqueToolInt);
        ItemStack stack = new ItemStack((ItemLike) selectedItem, 1);

        ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
        selectedItem.addToolBaseAttributes(builder, stack, uniqueTier);
        stack.set(ModDataComponents.TIER, uniqueTier.getIndex());
        stack.set(DataComponents.ATTRIBUTE_MODIFIERS, builder.build());
        stack.set(DataComponents.TOOLTIP_DISPLAY, TooltipDisplay.DEFAULT.withHidden(DataComponents.ATTRIBUTE_MODIFIERS, true));

        // ADD ROLLS
        Map<AbstractRoll, RollTier> rollsMap = selectedItem.getRolls();
        rollsMap.forEach((roll, tier) -> LootRollItem.addNewRoll(level, stack, roll, tier));

        // CUSTOM ITEM NAME
        MutableComponent defaultName = stack.getHoverName().copy();
        String accent = "※";
        Component name = Component.literal(accent).append(CommonComponents.SPACE)
                .append(defaultName).append(CommonComponents.SPACE).append(accent)
                .withColor(uniqueTier.getCol()).withStyle(ChatFormatting.BOLD);
        stack.set(DataComponents.ITEM_NAME, name);

        // SEND TITLE TEXT
        player.connection.send(new ClientboundSetTitlesAnimationPacket(10, 40, 10));
        player.connection.send(new ClientboundSetTitleTextPacket(
                Component.literal("UNIQUE!").withColor(uniqueTier.getCol()).withStyle(ChatFormatting.BOLD)
        ));

        return stack;
    }

    private static LootType chooseRandomLootType(LootGroupType lgt) {

        List<LootType> possibleLTs = new ArrayList<>();
        Arrays.stream(LootType.values()).forEach(lt -> {
            if (lt.groupType == lgt || lgt == LootGroupType.NONE) possibleLTs.add(lt);
        });
        possibleLTs.remove(LootType.NONE);
        int selectedLT = randomSource.nextInt(possibleLTs.size());
        return possibleLTs.get(selectedLT);
    }

    private static <T> T findKeyFromRandomNum(Map<T, Integer> tWeightMap, int randomNum) {
        int dynamicBound = 0;
        for (T t : tWeightMap.keySet()) {
            dynamicBound += tWeightMap.get(t);
            if (randomNum < dynamicBound) return t;
        }
        throw new IllegalStateException("Something went wrong in the random choosing process");
    }
}
