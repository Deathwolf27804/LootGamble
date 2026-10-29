package com.cypenborg.loot_gamble.blocks.custom_block_entities.attuner;

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

public class AttunerEntityBlock extends Block implements EntityBlock {

    public AttunerEntityBlock(Properties properties) {
        super(properties.requiresCorrectToolForDrops().strength(3f));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new AttunerBlockEntity(blockPos, blockState);
    }

//    private static <E extends BlockEntity, A extends BlockEntity> @Nullable BlockEntityTicker<A> createTickerHelper(
//            BlockEntityType<A> type, BlockEntityType<E> checkedType, BlockEntityTicker<? super E> ticker) {
//        return checkedType == type ? (BlockEntityTicker<A>) ticker : null;
//    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return AttunerBlockEntity.useWithoutItem(state, level, pos, player, hitResult);
    }

//    @Override
//    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
//        return createTickerHelper(type, ModBlockEntities.ATTUNER_BLOCK_ENTITY.get(), AttunerBlockEntity::tick);
//    }
}
