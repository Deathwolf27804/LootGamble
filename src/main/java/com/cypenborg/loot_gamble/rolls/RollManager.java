package com.cypenborg.loot_gamble.rolls;

import com.cypenborg.loot_gamble.items.ModDataComponents;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.*;

public class RollManager {

    static {
        new RollDictionary(RollType.RIGHT_CLICK, ModDataComponents.RIGHT_CLICK_ROLLS);
        new RollDictionary(RollType.PASSIVE, ModDataComponents.PASSIVE_ROLLS);
        new RollDictionary(RollType.SPECIALITY, ModDataComponents.SPECIALITY_ROLLS);
        new RollDictionary(RollType.UNIQUE, ModDataComponents.UNIQUE_ROLLS);
    }

    public static void registerRoll(AbstractRoll roll) {

        List<RollDictionary> dictionaries = RollDictionary.getAll();
        for (RollDictionary dictionary : dictionaries) {

            if (dictionary.rollType == roll.type) {
                dictionary.put(roll.getName(), roll);
                return;
            }
        }
        throw new IllegalRollException(roll);
    }

    public static Optional<Map<AbstractRoll, RollTier>> getRolls(Map<String, Integer> names) {
        if (names == null || names.isEmpty()) return Optional.empty();

        Map<AbstractRoll, RollTier> rollMap = new HashMap<>();
        for (String rollName : names.keySet()) rollMap.put(getRoll(rollName), RollTier.getTierFromIndex(names.get(rollName)));
        return Optional.of(rollMap);
    }

    public static AbstractRoll getRoll(int id) {
        List<RollDictionary> dictionaries = RollDictionary.getAll();
        for (RollDictionary dictionary : dictionaries) {

            Optional<AbstractRoll> optionalRoll = dictionary.get(id);
            if (optionalRoll.isPresent()) return optionalRoll.get();
        }
        throw new IllegalArgumentException(String.format("ID:%d is not a valid roll ID (of any type)", id));
    }

    public static AbstractRoll getRoll(String name) {

        List<RollDictionary> dictionaries = RollDictionary.getAll();
        for (RollDictionary dictionary : dictionaries) {

            Optional<AbstractRoll> optionalRoll = dictionary.get(name);
            if (optionalRoll.isPresent()) return optionalRoll.get();
        }
        throw new IllegalArgumentException(String.format("%s is not a valid roll (of any type)", name));
    }

    public static DeferredHolder<DataComponentType<?>, DataComponentType<Map<String, Integer>>> getRelevantDataComponent(AbstractRoll roll) {

        List<RollDictionary> dictionaries = RollDictionary.getAll();
        for (RollDictionary dictionary : dictionaries) {

            if (dictionary.rollType == roll.type) return dictionary.getRelevantDataComponent();
        }
        throw new IllegalRollException(roll);
    }

    public static List<DeferredHolder<DataComponentType<?>, DataComponentType<Map<String, Integer>>>> getAllDataComponents() {

        List<DeferredHolder<DataComponentType<?>, DataComponentType<Map<String, Integer>>>> componentList = new ArrayList<>();
        for (RollDictionary dictionary : RollDictionary.getAll()) componentList.add(dictionary.relevantDataComponent);
        return componentList;
    }

    public static class RollDictionary {

        private static final List<RollDictionary> rollDictionaries = new ArrayList<>();
        private final Map<String, AbstractRoll> map;
        private final RollType rollType;
        private final DeferredHolder<DataComponentType<?>, DataComponentType<Map<String, Integer>>> relevantDataComponent;

        public RollDictionary(RollType type, DeferredHolder<DataComponentType<?>, DataComponentType<Map<String, Integer>>> modDataComponent) {
            map = new HashMap<>();
            rollType = type;
            this.relevantDataComponent = modDataComponent;
            rollDictionaries.add(this);
        }

        void put(String name, AbstractRoll roll) {
            map.put(name, roll);
        }

        Optional<AbstractRoll> get(String name) {
            return Optional.ofNullable(map.get(name));
        }

        Optional<AbstractRoll> get(int id) {
            for (AbstractRoll roll : map.values()) if (roll.id == id) return Optional.of(roll);
            return Optional.empty();
        }

        public RollType getRollType() {
            return rollType;
        }

        public Map<String, AbstractRoll> getMap() {
            return Collections.unmodifiableMap(map);
        }

        public DeferredHolder<DataComponentType<?>, DataComponentType<Map<String, Integer>>> getRelevantDataComponent() {
            return relevantDataComponent;
        }

        public static List<RollDictionary> getAll() {
            return Collections.unmodifiableList(rollDictionaries);
        }
    }
}

