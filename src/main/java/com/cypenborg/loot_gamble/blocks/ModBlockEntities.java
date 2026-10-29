package com.cypenborg.loot_gamble.blocks;

import com.cypenborg.loot_gamble.LootGamble;
import com.cypenborg.loot_gamble.blocks.custom_block_entities.attuner.AttunerBlockEntity;
import com.cypenborg.loot_gamble.blocks.custom_block_entities.enclave.EnclaveBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, LootGamble.MODID);

    public static final Supplier<BlockEntityType<AttunerBlockEntity>> ATTUNER_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            "attuner_block_entity", () -> new BlockEntityType<>(AttunerBlockEntity::new, ModBlocks.ATTUNER_ENTITY_BLOCK.get()));
    public static final Supplier<BlockEntityType<EnclaveBlockEntity>> ENCLAVE_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            "enclave_block_entity", () -> new BlockEntityType<>(EnclaveBlockEntity::new, ModBlocks.ENCLAVE_ENTITY_BLOCK.get()));
}
