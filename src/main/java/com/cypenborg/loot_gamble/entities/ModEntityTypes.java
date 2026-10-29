package com.cypenborg.loot_gamble.entities;

import com.cypenborg.loot_gamble.LootGamble;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, LootGamble.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<SummonedIronGolem>> SUMMONED_IRON_GOLEM
            = ENTITY_TYPES.register("summoned_iron_golem",
            () -> EntityType.Builder.of(SummonedIronGolem::new, MobCategory.MISC)
                    .sized(1.4F, 2.7F)
                    .clientTrackingRange(10)
                    .build(
                            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(
                                    LootGamble.MODID, "summoned_iron_golem"
                            ))
                    )
    );

    public static final DeferredHolder<EntityType<?>, EntityType<SummonedSnowGolem>> SUMMONED_SNOW_GOLEM
            = ENTITY_TYPES.register("summoned_snow_golem",
            () -> EntityType.Builder.of(SummonedSnowGolem::new, MobCategory.MISC)
                    .immuneTo(BlockTags.SNOW_GOLEM_IMMUNE_TO)
                    .sized(0.7F, 1.9F)
                    .eyeHeight(1.7F)
                    .clientTrackingRange(8)
                    .build(
                            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(
                                    LootGamble.MODID, "summoned_snow_golem"
                            ))
                    )
    );

    public static final DeferredHolder<EntityType<?>, EntityType<SummonedFireball>> SUMMONED_FIREBALL
            = ENTITY_TYPES.register("summoned_fireball",
            () -> EntityType.Builder.of(SummonedFireball::new, MobCategory.MISC)
                    .noLootTable()
                    .sized(0.1F, 0.1F)
                    .clientTrackingRange(4)
                    .updateInterval(10)
                    .build(
                            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(
                                    LootGamble.MODID, "summoned_fireball"
                            ))
                    )
    );
}
