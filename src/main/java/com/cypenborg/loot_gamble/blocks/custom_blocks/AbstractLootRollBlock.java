package com.cypenborg.loot_gamble.blocks.custom_blocks;

import com.cypenborg.loot_gamble.items.ModDataComponents;
import com.cypenborg.loot_gamble.items.custom.LootGroupType;
import com.cypenborg.loot_gamble.items.custom.LootRollManager;
import com.cypenborg.loot_gamble.items.custom.LootType;
import com.cypenborg.loot_gamble.rolls.RollTier;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import org.jspecify.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class AbstractLootRollBlock extends Block {

    protected final Map<RollTier, Integer> tierProbMap;
    public static final EnumProperty<LootGroupType> LGT_ATTUNEMENT =
            EnumProperty.create("lgt_attunement", LootGroupType.class);
    public static final EnumProperty<LootType> LT_ATTUNEMNT =
            EnumProperty.create("lt_attunement", LootType.class);

    public AbstractLootRollBlock(Properties properties, RollTier tier) {
        super(properties.destroyTime(0));

        registerDefaultState(getStateDefinition().any()
                .setValue(LGT_ATTUNEMENT, LootGroupType.NONE)
                .setValue(LT_ATTUNEMNT, LootType.NONE)
        );

        RollTier[] tiers = getTiers(tier);
        int[] weights = getWeights(tiers);
        if (tiers.length != weights.length) throw new ExceptionInInitializerError(
                String.format("Length of possible tiers (%d) is not the same as length of weights (%d)", tiers.length, weights.length)
        );
        tierProbMap = setMap(tiers, weights);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack destroyedWith) {

        if (level instanceof ServerLevel serverLevel && player instanceof ServerPlayer serverPlayer) {

            boolean playSound = true;

            @Nullable ItemStack itemStack = LootRollManager.createNewToolLootRoll(serverLevel, serverPlayer, this, state);
            if (itemStack == null) {
                itemStack = getItemStackFromBlockState(state);
                playSound = false;
            }

            ItemEntity itemEntity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), itemStack);
            level.addFreshEntity(itemEntity);
            if (playSound) level.playSound(null, pos, SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS, 0.5f, 0.1f);
        }
        super.playerDestroy(level, player, pos, state, blockEntity, destroyedWith);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LGT_ATTUNEMENT).add(LT_ATTUNEMNT);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity by, ItemStack itemStack) {
        super.setPlacedBy(level, pos, state, by, itemStack);

        LootGroupType lgt = itemStack.getOrDefault(ModDataComponents.LGT_ATTUNEMENT, LootGroupType.NONE);
        LootType lt = itemStack.getOrDefault(ModDataComponents.LT_ATTUNEMENT, LootType.NONE);

        BlockState newState = state.setValue(LGT_ATTUNEMENT, lgt).setValue(LT_ATTUNEMNT, lt);
        level.setBlock(pos, newState, 3);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean includeData, Player player) {
        if (!includeData) return super.getCloneItemStack(level, pos, state, false, player);
        else return getItemStackFromBlockState(state);
    }

    public static ItemStack getItemStackFromBlockState(BlockState state) {
        ItemStack stack = new ItemStack(state.getBlock());
        stack.set(ModDataComponents.LGT_ATTUNEMENT, state.getValueOrElse(LGT_ATTUNEMENT, LootGroupType.NONE));
        stack.set(ModDataComponents.LT_ATTUNEMENT, state.getValueOrElse(LT_ATTUNEMNT, LootType.NONE));
        return stack;
    }

    protected Map<RollTier, Integer> setMap(RollTier[] tiers, int[] weights) {
        Map<RollTier, Integer> map = new HashMap<>();
        for (int i = 0; i < tiers.length; i++) map.put(tiers[i], weights[i]);
        return map;
    }

    public RollTier[] getTiers(RollTier baseTier) {
        return RollTier.getSameAndHigherTiers(baseTier);
    }

    public int[] getWeights(RollTier[] tiers) {

        int[] weights = new int[tiers.length];
        for (int i = 0; i < tiers.length - 1; i++) weights[i] = 9 * Math.powExact(10, weights.length - 2 - i);
        weights[weights.length - 1] = 1;
        return weights;
    }

    public int getTotalWeights() {
        return tierProbMap.values().stream().mapToInt(Integer::intValue).sum();
    }

    public Map<RollTier, Integer> getTierProbMap() {

        LinkedHashMap<RollTier, Integer> sortedMap = tierProbMap.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(
                        (Map.Entry<RollTier, Integer> entry) -> entry.getValue()
                        ).reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, _) -> a,
                        LinkedHashMap::new
                ));
        return Collections.unmodifiableMap(sortedMap);
    }
}
