package com.cypenborg.loot_gamble.blocks;

import com.cypenborg.loot_gamble.LootGamble;
import com.cypenborg.loot_gamble.blocks.custom_block_entities.attuner.AttunerEntityBlock;
import com.cypenborg.loot_gamble.blocks.custom_block_entities.enclave.EnclaveEntityBlock;
import com.cypenborg.loot_gamble.blocks.custom_blocks.*;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(LootGamble.MODID);

    public static final DeferredBlock<Block> COMMON_LOOT_ROLL_BLOCK = BLOCKS.registerBlock(
            "common_loot_roll_block", CommonLootRollBlock::new);
    public static final DeferredBlock<Block> UNCOMMON_LOOT_ROLL_BLOCK = BLOCKS.registerBlock(
            "uncommon_loot_roll_block", UncommonLootRollBlock::new);
    public static final DeferredBlock<Block> RARE_LOOT_ROLL_BLOCK = BLOCKS.registerBlock(
            "rare_loot_roll_block", RareLootRollBlock::new);
    public static final DeferredBlock<Block> EPIC_LOOT_ROLL_BLOCK = BLOCKS.registerBlock(
            "epic_loot_roll_block", EpicLootRollBlock::new);
    public static final DeferredBlock<Block> LEGENDARY_LOOT_ROLL_BLOCK = BLOCKS.registerBlock(
            "legendary_loot_roll_block", LegendaryLootRollBlock::new);
    public static final DeferredBlock<Block> UNIQUE_LOOT_ROLL_BLOCK = BLOCKS.registerBlock(
            "unique_loot_roll_block", UniqueLootRollBlock::new);

    public static final DeferredBlock<AttunerEntityBlock> ATTUNER_ENTITY_BLOCK = BLOCKS.registerBlock(
            "attuner_entity_block", AttunerEntityBlock::new);
    public static final DeferredBlock<EnclaveEntityBlock> ENCLAVE_ENTITY_BLOCK = BLOCKS.registerBlock(
            "enclave_entity_block", EnclaveEntityBlock::new);

}
