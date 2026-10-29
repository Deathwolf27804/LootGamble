package com.cypenborg.loot_gamble.datagen;

import com.cypenborg.loot_gamble.items.ModItems;
import com.cypenborg.loot_gamble.items.block_items.AbstractLootRollBlockItem;
import com.cypenborg.loot_gamble.items.block_items.ModBlockItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {

    protected ModRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {

        createLootRollBlockRecipe(ModBlockItems.COMMON_LOOT_ROLL_BLOCK_ITEM.get());
        createLootRollBlockRecipe(ModBlockItems.UNCOMMON_LOOT_ROLL_BLOCK_ITEM.get());
        createLootRollBlockRecipe(ModBlockItems.RARE_LOOT_ROLL_BLOCK_ITEM.get());
        createLootRollBlockRecipe(ModBlockItems.EPIC_LOOT_ROLL_BLOCK_ITEM.get());
        createLootRollBlockRecipe(ModBlockItems.LEGENDARY_LOOT_ROLL_BLOCK_ITEM.get());

        // ATTUNER BLOCK ENTITY
        ShapedRecipeBuilder.shaped(
                items, RecipeCategory.MISC, ModBlockItems.ATTUNER_ENTITY_BLOCK_ITEM
                )
                .pattern("RDR")
                .pattern("BSB")
                .pattern("RBR")
                .define('R', ModTags.Items.RANDOM_CORE_LIKE)
                .define('D', Tags.Items.GEMS_DIAMOND)
                .define('S', Items.SMITHING_TABLE)
                .define('B', Tags.Items.COBBLESTONES)
                .unlockedBy("has_random_core_like", has(ModTags.Items.RANDOM_CORE_LIKE))
                .save(output);
    }

    public void createLootRollBlockRecipe(AbstractLootRollBlockItem output) {

        ShapedRecipeBuilder.shaped(
                        items, RecipeCategory.MISC, output
                )
                .pattern("PrP")
                .pattern("PcP")
                .pattern("PPP")
                .define('P', ItemTags.PLANKS)
                .define('r', ModTags.Items.RANDOM_CORE_LIKE)
                .define('c', output.getTier().getUpgradeMaterial())
                .unlockedBy("has_random_core_like", has(ModTags.Items.RANDOM_CORE_LIKE))
                .save(this.output);
    }

    public static class Runner extends RecipeProvider.Runner {

        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new ModRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "LootGamble Recipes";
        }
    }
}
