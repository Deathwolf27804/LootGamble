package com.cypenborg.loot_gamble.blocks.custom_block_entities.enclave;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.Nullable;

public class EnclaveEntityBlock extends Block implements EntityBlock {

    public EnclaveEntityBlock(Properties properties) {
        super(properties.requiresCorrectToolForDrops().strength(3f));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new EnclaveBlockEntity(blockPos, blockState);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return EnclaveBlockEntity.useWithoutItem(state, level, pos, player ,hitResult);
    }
}
