package com.cypenborg.loot_gamble.items;

import com.cypenborg.loot_gamble.LootGamble;
import com.cypenborg.loot_gamble.items.custom.RandomCoreItem;
import com.cypenborg.loot_gamble.items.custom.tools.unique.DashingSpearItem;
import com.cypenborg.loot_gamble.rolls.RollTier;
import com.cypenborg.loot_gamble.items.custom.tools.*;
import com.cypenborg.loot_gamble.items.custom.tools.SwordLootRollItem;
import com.cypenborg.loot_gamble.items.custom.tools.unique.AerialSwordItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(LootGamble.MODID);

    // STUFF
    public static final DeferredItem<RandomCoreItem> RANDOM_CORE = ITEMS.registerItem("random_core", RandomCoreItem::new);

    // UNIQUE
    public static final DeferredItem<AerialSwordItem> AERIAL_SWORD_UNIQUE_ITEM = ITEMS.registerItem("aerial_sword",
            AerialSwordItem::new);
    public static final DeferredItem<DashingSpearItem> DASHING_SPEAR_UNIQUE_ITEM = ITEMS.registerItem("dashing_spear",
            DashingSpearItem::new);

    // SWORDS
    public static final DeferredItem<SwordLootRollItem> COMMON_SWORD_ITEM = ITEMS.registerItem("common_sword_item",
            props -> new SwordLootRollItem(props, ToolMaterial.WOOD, RollTier.COMMON));
    public static final DeferredItem<SwordLootRollItem> UNCOMMON_SWORD_ITEM = ITEMS.registerItem("uncommon_sword_item",
        props -> new SwordLootRollItem(props, ToolMaterial.STONE, RollTier.UNCOMMON));
    public static final DeferredItem<SwordLootRollItem> RARE_SWORD_ITEM = ITEMS.registerItem("rare_sword_item",
        props -> new SwordLootRollItem(props, ToolMaterial.IRON, RollTier.RARE));
    public static final DeferredItem<SwordLootRollItem> EPIC_SWORD_ITEM = ITEMS.registerItem("epic_sword_item",
        props -> new SwordLootRollItem(props, ToolMaterial.DIAMOND, RollTier.EPIC));
    public static final DeferredItem<SwordLootRollItem> LEGENDARY_SWORD_ITEM = ITEMS.registerItem("legendary_sword_item",
        props -> new SwordLootRollItem(props, ToolMaterial.NETHERITE, RollTier.LEGENDARY));

    // AXES
    public static final DeferredItem<AxeLootRollItem> COMMON_AXE_ITEM = ITEMS.registerItem("common_axe_item",
            props -> new AxeLootRollItem(props, ToolMaterial.WOOD, RollTier.COMMON));
    public static final DeferredItem<AxeLootRollItem> UNCOMMON_AXE_ITEM = ITEMS.registerItem("uncommon_axe_item",
            props -> new AxeLootRollItem(props, ToolMaterial.STONE, RollTier.UNCOMMON));
    public static final DeferredItem<AxeLootRollItem> RARE_AXE_ITEM = ITEMS.registerItem("rare_axe_item",
            props -> new AxeLootRollItem(props, ToolMaterial.IRON, RollTier.RARE));
    public static final DeferredItem<AxeLootRollItem> EPIC_AXE_ITEM = ITEMS.registerItem("epic_axe_item",
            props -> new AxeLootRollItem(props, ToolMaterial.DIAMOND, RollTier.EPIC));
    public static final DeferredItem<AxeLootRollItem> LEGENDARY_AXE_ITEM = ITEMS.registerItem("legendary_axe_item",
            props -> new AxeLootRollItem(props, ToolMaterial.NETHERITE, RollTier.LEGENDARY));


    // PICKAXES
    public static final DeferredItem<PickaxeLootRollItem> COMMON_PICKAXE_ITEM = ITEMS.registerItem("common_pickaxe_item",
            props -> new PickaxeLootRollItem(props, ToolMaterial.WOOD, RollTier.COMMON));
    public static final DeferredItem<PickaxeLootRollItem> UNCOMMON_PICKAXE_ITEM = ITEMS.registerItem("uncommon_pickaxe_item",
            props -> new PickaxeLootRollItem(props, ToolMaterial.STONE, RollTier.UNCOMMON));
    public static final DeferredItem<PickaxeLootRollItem> RARE_PICKAXE_ITEM = ITEMS.registerItem("rare_pickaxe_item",
            props -> new PickaxeLootRollItem(props, ToolMaterial.IRON, RollTier.RARE));
    public static final DeferredItem<PickaxeLootRollItem> EPIC_PICKAXE_ITEM = ITEMS.registerItem("epic_pickaxe_item",
            props -> new PickaxeLootRollItem(props, ToolMaterial.DIAMOND, RollTier.EPIC));
    public static final DeferredItem<PickaxeLootRollItem> LEGENDARY_PICKAXE_ITEM = ITEMS.registerItem("legendary_pickaxe_item",
            props -> new PickaxeLootRollItem(props, ToolMaterial.NETHERITE, RollTier.LEGENDARY));


    // SPEARS
    public static final DeferredItem<SpearLootRollItem> COMMON_SPEAR_ITEM = ITEMS.registerItem("common_spear_item",
            props -> new SpearLootRollItem(props, ToolMaterial.WOOD, RollTier.COMMON));
    public static final DeferredItem<SpearLootRollItem> UNCOMMON_SPEAR_ITEM = ITEMS.registerItem("uncommon_spear_item",
            props -> new SpearLootRollItem(props, ToolMaterial.STONE, RollTier.UNCOMMON));
    public static final DeferredItem<SpearLootRollItem> RARE_SPEAR_ITEM = ITEMS.registerItem("rare_spear_item",
            props -> new SpearLootRollItem(props, ToolMaterial.IRON, RollTier.RARE));
    public static final DeferredItem<SpearLootRollItem> EPIC_SPEAR_ITEM = ITEMS.registerItem("epic_spear_item",
            props -> new SpearLootRollItem(props, ToolMaterial.DIAMOND, RollTier.EPIC));
    public static final DeferredItem<SpearLootRollItem> LEGENDARY_SPEAR_ITEM = ITEMS.registerItem("legendary_spear_item",
            props -> new SpearLootRollItem(props, ToolMaterial.NETHERITE, RollTier.LEGENDARY));


    // SHOVELS
    public static final DeferredItem<ShovelLootRollItem> COMMON_SHOVEL_ITEM = ITEMS.registerItem("common_shovel_item",
            props -> new ShovelLootRollItem(props, ToolMaterial.WOOD, RollTier.COMMON));
    public static final DeferredItem<ShovelLootRollItem> UNCOMMON_SHOVEL_ITEM = ITEMS.registerItem("uncommon_shovel_item",
            props -> new ShovelLootRollItem(props, ToolMaterial.STONE, RollTier.UNCOMMON));
    public static final DeferredItem<ShovelLootRollItem> RARE_SHOVEL_ITEM = ITEMS.registerItem("rare_shovel_item",
            props -> new ShovelLootRollItem(props, ToolMaterial.IRON, RollTier.RARE));
    public static final DeferredItem<ShovelLootRollItem> EPIC_SHOVEL_ITEM = ITEMS.registerItem("epic_shovel_item",
            props -> new ShovelLootRollItem(props, ToolMaterial.DIAMOND, RollTier.EPIC));
    public static final DeferredItem<ShovelLootRollItem> LEGENDARY_SHOVEL_ITEM = ITEMS.registerItem("legendary_shovel_item",
            props -> new ShovelLootRollItem(props, ToolMaterial.NETHERITE, RollTier.LEGENDARY));


    // HOES
    public static final DeferredItem<HoeLootRollItem> COMMON_HOE_ITEM = ITEMS.registerItem("common_hoe_item",
            props -> new HoeLootRollItem(props, ToolMaterial.WOOD, RollTier.COMMON));
    public static final DeferredItem<HoeLootRollItem> UNCOMMON_HOE_ITEM = ITEMS.registerItem("uncommon_hoe_item",
            props -> new HoeLootRollItem(props, ToolMaterial.STONE, RollTier.UNCOMMON));
    public static final DeferredItem<HoeLootRollItem> RARE_HOE_ITEM = ITEMS.registerItem("rare_hoe_item",
            props -> new HoeLootRollItem(props, ToolMaterial.IRON, RollTier.RARE));
    public static final DeferredItem<HoeLootRollItem> EPIC_HOE_ITEM = ITEMS.registerItem("epic_hoe_item",
            props -> new HoeLootRollItem(props, ToolMaterial.DIAMOND, RollTier.EPIC));
    public static final DeferredItem<HoeLootRollItem> LEGENDARY_HOE_ITEM = ITEMS.registerItem("legendary_hoe_item",
            props -> new HoeLootRollItem(props, ToolMaterial.NETHERITE, RollTier.LEGENDARY));
}
