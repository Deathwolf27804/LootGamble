package com.cypenborg.loot_gamble.datagen;

import com.cypenborg.loot_gamble.entities.ModEntityTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.TagEntry;
import net.minecraft.world.entity.EntityTypes;

import java.util.concurrent.CompletableFuture;

public class ModEntityTypeTagsProvider extends EntityTypeTagsProvider {

    public ModEntityTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        super.addTags(registries);

        tag(ModTags.Entities.CANNOT_DROP_RANDOM_CORE)
                .addTag(ModTags.Entities.VANILLA_PASSIVE_MOBS)
                .add(ModEntityTypes.SUMMONED_IRON_GOLEM.getKey())
                .add(ModEntityTypes.SUMMONED_SNOW_GOLEM.getKey());

        tag(ModTags.Entities.VANILLA_PASSIVE_MOBS)
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.ALLAY)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.ARMADILLO)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.AXOLOTL)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.BAT)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.BEE)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.CAMEL)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.CAT)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.CHICKEN)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.COD)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.COW)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.DOLPHIN)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.DONKEY)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.FOX)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.FROG)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.GLOW_SQUID)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.GOAT)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.HAPPY_GHAST)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.HORSE)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.LLAMA)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.MOOSHROOM)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.MULE)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.OCELOT)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.PANDA)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.PARROT)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.PIG)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.POLAR_BEAR)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.PUFFERFISH)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.RABBIT)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.SALMON)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.SHEEP)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.SNIFFER)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.SQUID)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.STRIDER)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.TADPOLE)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.TRADER_LLAMA)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.TROPICAL_FISH)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.TURTLE)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.VILLAGER)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.WANDERING_TRADER)))
                .add(TagEntry.element(BuiltInRegistries.ENTITY_TYPE.getKey(EntityTypes.WOLF)));
    }
}
