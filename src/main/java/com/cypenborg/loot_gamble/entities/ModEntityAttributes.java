package com.cypenborg.loot_gamble.entities;

import com.cypenborg.loot_gamble.LootGamble;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = LootGamble.MODID)
public class ModEntityAttributes {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(
                ModEntityTypes.SUMMONED_IRON_GOLEM.get(),
                SummonedIronGolem.createAttributes().build()
        );
        event.put(
                ModEntityTypes.SUMMONED_SNOW_GOLEM.get(),
                SummonedSnowGolem.createAttributes().build()
        );
    }
}