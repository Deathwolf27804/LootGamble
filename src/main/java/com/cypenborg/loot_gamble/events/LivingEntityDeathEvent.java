package com.cypenborg.loot_gamble.events;

import com.cypenborg.loot_gamble.Config;
import com.cypenborg.loot_gamble.LootGamble;
import com.cypenborg.loot_gamble.datagen.ModTags;
import com.cypenborg.loot_gamble.items.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber(modid = LootGamble.MODID)
public final class LivingEntityDeathEvent {

    private static final RandomSource randomCoreDropSource = RandomSource.create();

    @SubscribeEvent
    public static void onLivingEntityDeath(LivingDeathEvent event) {

        LivingEntity entity = event.getEntity();
        Level level = entity.level();

        if (Config.CAN_DROP_RANDOM_CORES.get()
                && !entity.is(ModTags.Entities.CANNOT_DROP_RANDOM_CORE)
                && level instanceof ServerLevel serverLevel) {

            double randomDouble = randomCoreDropSource.nextDouble();
            if (randomDouble < Config.RANDOM_CORE_DROP_CHANCE.get()) dropRandomCore(serverLevel, entity);
        }
    }

    public static void dropRandomCore(ServerLevel serverLevel, LivingEntity atEntity) {

        ItemStack stack = new ItemStack(ModItems.RANDOM_CORE.get(), 1);
        atEntity.spawnAtLocation(serverLevel, stack);
    }
}
