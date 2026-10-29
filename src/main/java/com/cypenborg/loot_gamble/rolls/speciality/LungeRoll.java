package com.cypenborg.loot_gamble.rolls.speciality;

import com.cypenborg.loot_gamble.items.custom.LootType;
import com.cypenborg.loot_gamble.rolls.RollTier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.List;

public class LungeRoll extends AbstractSpecialityRoll{

    public LungeRoll() {
        super("lunge_roll");
    }

    @Override
    public ResourceKey<Enchantment> getEnchantment(RollTier tier) {
        return Enchantments.LUNGE;
    }

    @Override
    public int getBaseEnchantmentLevel() {
        return 1;
    }

    @Override
    public RollTier getMinimumApplicableTier() {
        return RollTier.UNCOMMON;
    }

    @Override
    public List<LootType> getApplicableLoot() {
        return List.of(LootType.SPEAR);
    }
}
