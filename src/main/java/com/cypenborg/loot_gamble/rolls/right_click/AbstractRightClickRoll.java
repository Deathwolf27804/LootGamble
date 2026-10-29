package com.cypenborg.loot_gamble.rolls.right_click;

import com.cypenborg.loot_gamble.rolls.AbstractRoll;
import com.cypenborg.loot_gamble.rolls.RollTier;
import com.cypenborg.loot_gamble.rolls.RollType;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;

public abstract class AbstractRightClickRoll extends AbstractRoll {

    public static final int DEFAULT_COOLDOWN = 5 * 20;

    public AbstractRightClickRoll(String name) {
        super(name, RollType.RIGHT_CLICK);
    }

    public abstract int getCooldown(RollTier tier);

    public static void playActivateSoundEffect(Level level, BlockPos pos) {
        level.playSound(null, pos, getActivateSoundEvent(), SoundSource.PLAYERS, 1f, 0.5f);
    }

    public static SoundEvent getActivateSoundEvent() {
        return SoundEvents.COPPER_BREAK;
    }
}
