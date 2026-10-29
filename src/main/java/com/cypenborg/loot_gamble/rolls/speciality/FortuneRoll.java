package com.cypenborg.loot_gamble.rolls.speciality;

import com.cypenborg.loot_gamble.items.custom.LootType;
import com.cypenborg.loot_gamble.rolls.RollTier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.List;

public class FortuneRoll extends AbstractSpecialityRoll{

    public FortuneRoll() {
        super("fortune_roll");
    }

    @Override
    public ResourceKey<Enchantment> getEnchantment(RollTier tier) {
        return Enchantments.FORTUNE;
    }

    @Override
    public int getBaseEnchantmentLevel() {
        return 2;
    }

    @Override
    public List<LootType> getApplicableLoot() {
        return List.of(LootType.AXE, LootType.PICKAXE, LootType.SHOVEL, LootType.HOE);
    }

    @Override
    public RollTier getMinimumApplicableTier() {
        return RollTier.EPIC;
    }
}
