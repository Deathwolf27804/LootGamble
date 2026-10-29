package com.cypenborg.loot_gamble.items.custom;

import com.cypenborg.loot_gamble.items.ModItems;
import com.cypenborg.loot_gamble.rolls.RollType;
import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.*;
import java.util.function.Supplier;

public enum LootGroupType implements StringRepresentable{

    NONE(() -> Items.BARRIER),
    TOOLS(ModItems.COMMON_SWORD_ITEM);
//    ARMOR(() -> Items.DIAMOND_CHESTPLATE);

    public static final Codec<LootGroupType> CODEC = StringRepresentable.fromEnum(LootGroupType::values);
    private final Map<RollType, Integer> rollNumberMap;
    private final Supplier<? extends Item> displayItem;

    LootGroupType(Supplier<? extends Item> displayItem) {

        this.rollNumberMap = new HashMap<>();
        this.displayItem = displayItem;
    }

    public static void init() {

        TOOLS.setEntry(RollType.RIGHT_CLICK, 1);
        TOOLS.setEntry(RollType.PASSIVE, 2);
        TOOLS.setEntry(RollType.SPECIALITY, 1);
        TOOLS.setEntry(RollType.UNIQUE, 0);

//        ARMOR.setEntry(RollType.RIGHT_CLICK, 0);
//        ARMOR.setEntry(RollType.PASSIVE, 2);
//        ARMOR.setEntry(RollType.SPECIALITY, 1);
//        ARMOR.setEntry(RollType.UNIQUE, 0);
    }

    public void setEntry(RollType rollType, int num) {
        rollNumberMap.put(rollType, num);
    }

    public int getNumberOfRolls(RollType rollType) {
        Integer num = rollNumberMap.get(rollType);
        if (num == null) throw new ExceptionInInitializerError(
                String.format("No value for LootGroupType:%s was initialised for RollType:%s", name(), rollType.name()));
        return num;
    }

    public List<LootType> getLootTypes() {
        List<LootType> lootTypesToGive = new ArrayList<>();
        LootType[] lootTypes = LootType.values();
        Arrays.stream(lootTypes).forEach(lt -> {
            if (lt.getGroupType() == this) lootTypesToGive.add(lt);
        });
        return lootTypesToGive;

    }

    public String getTranslationKey() {
        return "loot_group_type.loot_gamble." + name().toLowerCase();
    }

    public Item getDisplayItem() {
        return displayItem.get();
    }

    public static int size() {
        return values().length;
    }

    @Override
    public String getSerializedName() {
        return name().toLowerCase();
    }
}
