package com.cypenborg.loot_gamble.items.block_items;

import com.cypenborg.loot_gamble.LootGamble;
import com.cypenborg.loot_gamble.items.block_items.custom.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockItems {


    public static final DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(LootGamble.MODID);

    public static final DeferredItem<CommonLootRollBlockItem> COMMON_LOOT_ROLL_BLOCK_ITEM = BLOCK_ITEMS.registerItem(
            "common_loot_roll_block", CommonLootRollBlockItem::new);
    public static final DeferredItem<UncommonLootRollBlockItem> UNCOMMON_LOOT_ROLL_BLOCK_ITEM = BLOCK_ITEMS.registerItem(
            "uncommon_loot_roll_block", UncommonLootRollBlockItem::new);
    public static final DeferredItem<RareLootRollBlockItem> RARE_LOOT_ROLL_BLOCK_ITEM = BLOCK_ITEMS.registerItem(
            "rare_loot_roll_block", RareLootRollBlockItem::new);
    public static final DeferredItem<EpicLootRollBlockItem> EPIC_LOOT_ROLL_BLOCK_ITEM = BLOCK_ITEMS.registerItem(
            "epic_loot_roll_block", EpicLootRollBlockItem::new);
    public static final DeferredItem<LegendaryLootRollBlockItem> LEGENDARY_LOOT_ROLL_BLOCK_ITEM = BLOCK_ITEMS.registerItem(
            "legendary_loot_roll_block", LegendaryLootRollBlockItem::new);
    public static final DeferredItem<UniqueLootRollBlockItem> UNIQUE_LOOT_ROLL_BLOCK_ITEM = BLOCK_ITEMS.registerItem(
            "unique_loot_roll_block", UniqueLootRollBlockItem::new);

    public static final DeferredItem<AttunerEntityBlockItem> ATTUNER_ENTITY_BLOCK_ITEM = BLOCK_ITEMS.registerItem(
            "attuner_entity_block", AttunerEntityBlockItem::new);

    public static final DeferredItem<EnclaveEntityBlockItem> ENCLAVE_ENTITY_BLOCK_ITEM = BLOCK_ITEMS.registerItem(
            "enclave_entity_block", EnclaveEntityBlockItem::new);
}
