package com.cypenborg.loot_gamble.rolls.speciality;

import com.cypenborg.loot_gamble.items.custom.LootType;
import com.cypenborg.loot_gamble.rolls.RollTier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.List;

public class EfficiencyRoll extends AbstractSpecialityRoll{

    public EfficiencyRoll() {
        super("efficiency_roll");
    }

    @Override
    public ResourceKey<Enchantment> getEnchantment(RollTier tier) {
        return Enchantments.EFFICIENCY;
    }

    @Override
    public int getBaseEnchantmentLevel() {
        return 4;
    }

    @Override
    public List<LootType> getApplicableLoot() {
        return List.of(LootType.AXE, LootType.PICKAXE, LootType.SHOVEL, LootType.HOE);
    }
}
