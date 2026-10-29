package com.cypenborg.loot_gamble.rolls.unique;

import com.cypenborg.loot_gamble.rolls.RollTier;
import com.cypenborg.loot_gamble.rolls.right_click.AbstractRightClickRoll;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class AerialRoll extends AbstractUniqueRoll{

    static final int COOLDOWN = 20;

    public AerialRoll() {
        super("aerial_roll");
    }

    @Override
    public void onRightClick(Level level, Player player, InteractionHand hand, RollTier tier) {

        if (!level.isClientSide()) {

            player.setDeltaMovement(0, 1, 0);
            player.hurtMarked = true;
            player.resetFallDistance();

            AbstractRightClickRoll.playActivateSoundEffect(level, player.blockPosition());
            addNewCooldown(player, player.getItemInHand(hand), COOLDOWN);
        }
    }
}
