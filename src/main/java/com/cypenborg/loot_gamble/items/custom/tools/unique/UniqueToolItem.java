package com.cypenborg.loot_gamble.items.custom.tools.unique;

import com.cypenborg.loot_gamble.rolls.AbstractRoll;
import com.cypenborg.loot_gamble.rolls.unique.AbstractUniqueRoll;
import com.cypenborg.loot_gamble.rolls.RollManager;
import com.cypenborg.loot_gamble.rolls.RollTier;
import com.cypenborg.loot_gamble.items.custom.tools.ToolLootRollItem;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.HashMap;
import java.util.Map;

public interface UniqueToolItem extends ToolLootRollItem {

    Map<AbstractRoll, RollTier> getRolls();

    static void addNewUniqueRoll(ItemStack stack, AbstractUniqueRoll roll) {
        DeferredHolder<DataComponentType<?>, DataComponentType<Map<String, Integer>>> modDataComponent
                = RollManager.getRelevantDataComponent(roll);

        Map<String, Integer> rollMap = stack.getOrDefault(modDataComponent, new HashMap<>());
        rollMap.put(roll.getName(), RollTier.UNIQUE.getIndex());
        stack.set(modDataComponent, rollMap);
    }
}
