package com.cypenborg.loot_gamble.rolls;

import com.cypenborg.loot_gamble.LootGamble;
import net.minecraft.network.chat.TextColor;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.Tags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public enum RollTier {

    COMMON("common", 0, 0, TextColor.WHITE, () -> Items.OAK_LOG),
    UNCOMMON("uncommon", 10, 1, TextColor.GREEN, () -> Items.COBBLESTONE),
    RARE("rare", 20, 2, TextColor.BLUE, () -> Items.IRON_INGOT),
    EPIC("epic", 30, 3, TextColor.LIGHT_PURPLE, () -> Items.DIAMOND),
    LEGENDARY("legendary", 40, 4, TextColor.GOLD, () -> Items.NETHERITE_SCRAP),
    UNIQUE("unique", 100, 5, TextColor.RED, () -> Items.NETHERITE_INGOT);

    final String name;
    final int index;
    final int multiplier;
    final TextColor col;
    final Supplier<? extends Item> upgradeMaterial;

    RollTier(String name, int index, int multiplier, TextColor col, Supplier<? extends Item> upgradeMaterial) {
        this.name = name;
        this.index = index;
        this.multiplier = multiplier;
        this.col = col;
        this.upgradeMaterial = upgradeMaterial;
    }

    public static RollTier[] getSameAndHigherTiers(RollTier originalTier) {
        List<RollTier> possibleTiers = new ArrayList<>();
        for (RollTier tier : values()) if (tier.index >= originalTier.index) possibleTiers.add(tier);
        return possibleTiers.toArray(new RollTier[0]);
    }

    public static RollTier getTierFromIndex(int index) {
        for (RollTier tier : RollTier.values()) if (index == tier.index) return tier;
        throw new IllegalArgumentException(String.format("%d is not a valid tier number", index));
    }

    public static int getLowestTierByIndex() {
        return Arrays.stream(RollTier.values()).mapToInt(RollTier::getIndex).min().orElseThrow();
    }

    public String getTranslationKey() {
        return String.format("roll_tier.%s.%s", LootGamble.MODID, name);
    }

    public int getIndex() {
        return index;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public int getRandomCoreCost() {
        return Math.powExact(2, multiplier);
    }

    public int getUpgradeMaterialCost() {
        return 1;
    }

    public Item getUpgradeMaterial() {
        return upgradeMaterial.get();
    }

    public TextColor getCol() {
        return col;
    }
}
