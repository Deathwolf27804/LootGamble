package com.cypenborg.loot_gamble.items;

import com.cypenborg.loot_gamble.LootGamble;
import com.cypenborg.loot_gamble.items.custom.LootGroupType;
import com.cypenborg.loot_gamble.items.custom.LootType;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Map;
import java.util.function.UnaryOperator;

public class ModDataComponents {

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, LootGamble.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> TIER = register(
            LootGamble.MODID + ".tier",
            builder -> builder.persistent(Codec.INT));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Map<String, Integer>>> RIGHT_CLICK_ROLLS = register(
            LootGamble.MODID + ".right_click_rolls",
            builder -> builder.persistent(Codec.unboundedMap(Codec.STRING, Codec.INT)));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Map<String, Integer>>> PASSIVE_ROLLS = register(
            LootGamble.MODID + ".passive_rolls",
            builder -> builder.persistent(Codec.unboundedMap(Codec.STRING, Codec.INT)));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Map<String, Integer>>> SPECIALITY_ROLLS = register(
            LootGamble.MODID + ".speciality_rolls",
            builder -> builder.persistent(Codec.unboundedMap(Codec.STRING, Codec.INT)));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Map<String, Integer>>> UNIQUE_ROLLS = register(
            LootGamble.MODID + ".unique_rolls",
            builder -> builder.persistent(Codec.unboundedMap(Codec.STRING, Codec.INT)));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<LootGroupType>> LGT_ATTUNEMENT = register(
            LootGamble.MODID + ".lgt_attunement",
            builder -> builder.persistent(LootGroupType.CODEC));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<LootType>> LT_ATTUNEMENT = register(
            LootGamble.MODID + ".lt_attunement",
            builder -> builder.persistent(LootType.CODEC));

    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
