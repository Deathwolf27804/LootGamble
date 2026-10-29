package com.cypenborg.loot_gamble.rolls.unique;

import com.cypenborg.loot_gamble.rolls.RollTier;
import com.cypenborg.loot_gamble.rolls.right_click.AbstractRightClickRoll;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;


public class SatiatingDashRoll extends AbstractUniqueRoll{

    static final int COOLDOWN = 40;

    public SatiatingDashRoll() {
        super("satiating_dash_roll");
    }

    @Override
    public void onRightClick(Level level, Player player, InteractionHand hand, RollTier tier) {

        if (!level.isClientSide()) {

            Vec3 unitViewAngle = player.getLookAngle();
            Vec3 adjDashAngle = unitViewAngle.horizontal().scale(1 + 0.25d * 5);
            player.setDeltaMovement(adjDashAngle);
            player.hurtMarked = true;
            player.resetFallDistance();
            player.getFoodData().eat(4, 0);

            AbstractRightClickRoll.playActivateSoundEffect(level, player.blockPosition());
            addNewCooldown(player, player.getItemInHand(hand), COOLDOWN);
        }
    }
}
