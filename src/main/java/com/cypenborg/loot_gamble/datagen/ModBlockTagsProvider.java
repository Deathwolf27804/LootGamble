package com.cypenborg.loot_gamble.datagen;

import com.cypenborg.loot_gamble.LootGamble;
import com.cypenborg.loot_gamble.blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {

    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, LootGamble.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(ModTags.Blocks.LOOT_ROLL_BLOCKS)
                .add(ModBlocks.COMMON_LOOT_ROLL_BLOCK.getKey())
                .add(ModBlocks.UNCOMMON_LOOT_ROLL_BLOCK.getKey())
                .add(ModBlocks.RARE_LOOT_ROLL_BLOCK.getKey())
                .add(ModBlocks.EPIC_LOOT_ROLL_BLOCK.getKey())
                .add(ModBlocks.LEGENDARY_LOOT_ROLL_BLOCK.getKey())
                .add(ModBlocks.UNIQUE_LOOT_ROLL_BLOCK.getKey());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.ATTUNER_ENTITY_BLOCK.getKey())
                .add(ModBlocks.ENCLAVE_ENTITY_BLOCK.getKey());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.ATTUNER_ENTITY_BLOCK.getKey())
                .add(ModBlocks.ENCLAVE_ENTITY_BLOCK.getKey());
    }
}
