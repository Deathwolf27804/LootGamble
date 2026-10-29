package com.cypenborg.loot_gamble.rolls.right_click;

import com.cypenborg.loot_gamble.items.custom.LootType;
import com.cypenborg.loot_gamble.rolls.RollTier;
import com.cypenborg.loot_gamble.render.OreGlowRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;

import java.util.ArrayList;
import java.util.List;

public class OreGlowRoll extends AbstractRightClickRoll {

    public static final int BASE_DURATION = 5 * 20;
    public static final int BASE_RANGE = 15;

    public OreGlowRoll() {
        super("ore_glow_roll");
    }

    @Override
    public void onRightClick(Level level, Player player, InteractionHand hand, RollTier tier) {

        List<BlockPos> orePositions = findOrePositions(level, player, tier);
        OreGlowRenderer.submitOrePositions(orePositions, getDuration(tier));
        addNewCooldown(player, player.getItemInHand(hand), getCooldown(tier));
        playActivateSoundEffect(level, player.blockPosition());
    }

    private List<BlockPos> findOrePositions(Level level, Player player, RollTier tier) {

        BlockPos center = player.blockPosition();
        List<BlockPos> found = new ArrayList<>();
        int range = getRange(tier);

        for (int x = center.getX() - range; x <= center.getX() + range; x++) {
            for (int y = center.getY() - range; y <= center.getY() + range; y++) {
                for (int z = center.getZ() - range; z <= center.getZ() + range; z++) {

                    int dx = x - center.getX();
                    int dy = y - center.getY();
                    int dz = z - center.getZ();

                    if (dx * dx + dy * dy + dz * dz > range * range) continue;

                    BlockPos pos = new BlockPos(x, y, z);
                    if (level.getBlockState(pos).is(Tags.Blocks.ORES)) found.add(pos.immutable());
                }
            }
        }

        return found;
    }

    public int getRange(RollTier tier) {
        return BASE_RANGE + 5 * tier.getMultiplier();
    }

    public int getDuration(RollTier tier) {
        return BASE_DURATION;
    }

    @Override
    public int getCooldown(RollTier tier) {
        return getDuration(tier) + DEFAULT_COOLDOWN - tier.getMultiplier() * 20;
    }

    @Override
    public List<LootType> getApplicableLoot() {
        return List.of(LootType.PICKAXE);
    }

    @Override
    public Component getDescriptionComponent(RollTier tier) {
        return applyStandardDescriptionFormatting(Component.translatable(
                "roll_header.loot_gamble.more_info.ore_glow", getRange(tier)));
    }
}
