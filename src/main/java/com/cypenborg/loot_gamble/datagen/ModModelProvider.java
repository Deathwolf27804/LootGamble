package com.cypenborg.loot_gamble.datagen;

import com.cypenborg.loot_gamble.LootGamble;
import com.cypenborg.loot_gamble.blocks.ModBlocks;
import com.cypenborg.loot_gamble.items.ModItems;
import com.cypenborg.loot_gamble.items.custom.LootRollItem;
import com.cypenborg.loot_gamble.items.custom.tools.ToolLootRollItem;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModModelProvider extends ModelProvider {

    public ModModelProvider(PackOutput output) {
        super(output, LootGamble.MODID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {

        blockModels.createTrivialCube(ModBlocks.COMMON_LOOT_ROLL_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.UNCOMMON_LOOT_ROLL_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.RARE_LOOT_ROLL_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.EPIC_LOOT_ROLL_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.LEGENDARY_LOOT_ROLL_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.UNIQUE_LOOT_ROLL_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.ATTUNER_ENTITY_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.ENCLAVE_ENTITY_BLOCK.get());

        itemModels.generateFlatItem(ModItems.RANDOM_CORE.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(ModItems.AERIAL_SWORD_UNIQUE_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateFlatItem(ModItems.COMMON_SWORD_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.UNCOMMON_SWORD_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.RARE_SWORD_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.EPIC_SWORD_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.LEGENDARY_SWORD_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateFlatItem(ModItems.COMMON_AXE_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.UNCOMMON_AXE_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.RARE_AXE_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.EPIC_AXE_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.LEGENDARY_AXE_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateFlatItem(ModItems.COMMON_PICKAXE_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.UNCOMMON_PICKAXE_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.RARE_PICKAXE_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.EPIC_PICKAXE_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.LEGENDARY_PICKAXE_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateSpear(ModItems.COMMON_SPEAR_ITEM.get());
        itemModels.generateSpear(ModItems.UNCOMMON_SPEAR_ITEM.get());
        itemModels.generateSpear(ModItems.RARE_SPEAR_ITEM.get());
        itemModels.generateSpear(ModItems.EPIC_SPEAR_ITEM.get());
        itemModels.generateSpear(ModItems.LEGENDARY_SPEAR_ITEM.get());
        itemModels.generateSpear(ModItems.DASHING_SPEAR_UNIQUE_ITEM.get());

        itemModels.generateFlatItem(ModItems.COMMON_SHOVEL_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.UNCOMMON_SHOVEL_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.RARE_SHOVEL_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.EPIC_SHOVEL_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.LEGENDARY_SHOVEL_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModels.generateFlatItem(ModItems.COMMON_HOE_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.UNCOMMON_HOE_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.RARE_HOE_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.EPIC_HOE_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModels.generateFlatItem(ModItems.LEGENDARY_HOE_ITEM.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
    }
}
