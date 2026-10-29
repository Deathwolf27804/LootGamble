package com.cypenborg.loot_gamble.datagen;

import com.cypenborg.loot_gamble.LootGamble;
import com.cypenborg.loot_gamble.items.ModItems;
import com.cypenborg.loot_gamble.items.block_items.ModBlockItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, LootGamble.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(ModTags.Items.RANDOM_CORE_LIKE)
                .add(ModItems.RANDOM_CORE.getKey());

        tag(ModTags.Items.ATTUNABLE)
                .add(ModBlockItems.COMMON_LOOT_ROLL_BLOCK_ITEM.getKey())
                .add(ModBlockItems.UNCOMMON_LOOT_ROLL_BLOCK_ITEM.getKey())
                .add(ModBlockItems.RARE_LOOT_ROLL_BLOCK_ITEM.getKey())
                .add(ModBlockItems.EPIC_LOOT_ROLL_BLOCK_ITEM.getKey())
                .add(ModBlockItems.LEGENDARY_LOOT_ROLL_BLOCK_ITEM.getKey());

        tag(ItemTags.SWORDS)
                .add(ModItems.COMMON_SWORD_ITEM.getKey())
                .add(ModItems.UNCOMMON_SWORD_ITEM.getKey())
                .add(ModItems.RARE_SWORD_ITEM.getKey())
                .add(ModItems.EPIC_SWORD_ITEM.getKey())
                .add(ModItems.LEGENDARY_SWORD_ITEM.getKey())
                .add(ModItems.AERIAL_SWORD_UNIQUE_ITEM.getKey());

        tag(ItemTags.AXES)
                .add(ModItems.COMMON_AXE_ITEM.getKey())
                .add(ModItems.UNCOMMON_AXE_ITEM.getKey())
                .add(ModItems.RARE_AXE_ITEM.getKey())
                .add(ModItems.EPIC_AXE_ITEM.getKey())
                .add(ModItems.LEGENDARY_AXE_ITEM.getKey());

        tag(ItemTags.PICKAXES)
                .add(ModItems.COMMON_PICKAXE_ITEM.getKey())
                .add(ModItems.UNCOMMON_PICKAXE_ITEM.getKey())
                .add(ModItems.RARE_PICKAXE_ITEM.getKey())
                .add(ModItems.EPIC_PICKAXE_ITEM.getKey())
                .add(ModItems.LEGENDARY_PICKAXE_ITEM.getKey());

        tag(ItemTags.SPEARS)
                .add(ModItems.COMMON_SPEAR_ITEM.getKey())
                .add(ModItems.UNCOMMON_SPEAR_ITEM.getKey())
                .add(ModItems.RARE_SPEAR_ITEM.getKey())
                .add(ModItems.EPIC_SPEAR_ITEM.getKey())
                .add(ModItems.LEGENDARY_SPEAR_ITEM.getKey())
                .add(ModItems.DASHING_SPEAR_UNIQUE_ITEM.getKey());

        tag(ItemTags.SHOVELS)
                .add(ModItems.COMMON_SHOVEL_ITEM.getKey())
                .add(ModItems.UNCOMMON_SHOVEL_ITEM.getKey())
                .add(ModItems.RARE_SHOVEL_ITEM.getKey())
                .add(ModItems.EPIC_SHOVEL_ITEM.getKey())
                .add(ModItems.LEGENDARY_SHOVEL_ITEM.getKey());

        tag(ItemTags.HOES)
                .add(ModItems.COMMON_HOE_ITEM.getKey())
                .add(ModItems.UNCOMMON_HOE_ITEM.getKey())
                .add(ModItems.RARE_HOE_ITEM.getKey())
                .add(ModItems.EPIC_HOE_ITEM.getKey())
                .add(ModItems.LEGENDARY_HOE_ITEM.getKey());
    }
}
