package com.cypenborg.loot_gamble.items.custom;

import com.cypenborg.loot_gamble.items.ModItems;
import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.Supplier;

public enum LootType implements StringRepresentable{

    NONE(LootGroupType.NONE, () -> Items.BARRIER),
    SWORD(LootGroupType.TOOLS, ModItems.COMMON_SWORD_ITEM),
    AXE(LootGroupType.TOOLS, ModItems.COMMON_AXE_ITEM),
    PICKAXE(LootGroupType.TOOLS, ModItems.COMMON_PICKAXE_ITEM),
    SPEAR(LootGroupType.TOOLS, ModItems.COMMON_SPEAR_ITEM),
    SHOVEL(LootGroupType.TOOLS, ModItems.COMMON_SHOVEL_ITEM),
    HOE(LootGroupType.TOOLS, ModItems.COMMON_HOE_ITEM);
//    HEAD(LootGroupType.ARMOR, () -> Items.DIAMOND_HELMET),
//    CHEST(LootGroupType.ARMOR, () -> Items.DIAMOND_CHESTPLATE),
//    LEGS(LootGroupType.ARMOR, () -> Items.DIAMOND_LEGGINGS),
//    FEET(LootGroupType.ARMOR, () -> Items.DIAMOND_BOOTS);

    public static final Codec<LootType> CODEC = StringRepresentable.fromEnum(LootType::values);
    final LootGroupType groupType;
    final Supplier<? extends Item> displayItem;

    LootType(LootGroupType groupType, Supplier<? extends Item> displayItem) {
        this.groupType = groupType;
        this.displayItem = displayItem;
    }

    public LootGroupType getGroupType() {
        return groupType;
    }

    public Item getDisplayItem() {
        return displayItem.get();
    }

    public String getTranslationKey() {
        return "loot_type.loot_gamble." + name().toLowerCase();
    }

    @Override
    public String getSerializedName() {
        return name().toLowerCase();
    }
}
