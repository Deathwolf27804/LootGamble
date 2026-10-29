package com.cypenborg.loot_gamble.datagen;

import com.cypenborg.loot_gamble.LootGamble;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class ModTags {

    public static final class Items {

        public static final TagKey<Item> RANDOM_CORE_LIKE;
        public static final TagKey<Item> ATTUNABLE;

        static {

            RANDOM_CORE_LIKE = createTag("random_core_like");
            ATTUNABLE = createTag("attunable");
        }

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(Identifier.fromNamespaceAndPath(LootGamble.MODID, name));
        }

    }

    public static final class Blocks {

        public static final TagKey<Block> LOOT_ROLL_BLOCKS;

        static {

            LOOT_ROLL_BLOCKS = createTag("loot_roll_blocks");
        }

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(Identifier.fromNamespaceAndPath(LootGamble.MODID, name));
        }
    }

    public static final class Entities {

        public static final TagKey<EntityType<?>> CANNOT_DROP_RANDOM_CORE;
        public static final TagKey<EntityType<?>> VANILLA_PASSIVE_MOBS;

        static {

            CANNOT_DROP_RANDOM_CORE = createTag("cannot_drop_random_core");
            VANILLA_PASSIVE_MOBS = createTag("passive_mobs");
        }

        private static TagKey<EntityType<?>> createTag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(LootGamble.MODID, name));
        }
    }
}
