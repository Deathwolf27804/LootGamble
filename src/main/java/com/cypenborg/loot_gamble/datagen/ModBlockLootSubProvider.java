package com.cypenborg.loot_gamble.datagen;

import com.cypenborg.loot_gamble.blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Set;

public class ModBlockLootSubProvider extends BlockLootSubProvider {

    protected ModBlockLootSubProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {

        dropSelf(ModBlocks.ATTUNER_ENTITY_BLOCK.get());
        dropSelf(ModBlocks.ENCLAVE_ENTITY_BLOCK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return List.of(
                ModBlocks.ATTUNER_ENTITY_BLOCK.get(),
                ModBlocks.ENCLAVE_ENTITY_BLOCK.get()
        );
    }
}
