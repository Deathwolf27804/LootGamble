package com.cypenborg.loot_gamble.rolls.right_click;

import com.cypenborg.loot_gamble.items.custom.LootType;
import com.cypenborg.loot_gamble.rolls.RollTier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class DashRoll extends AbstractRightClickRoll{

    public DashRoll() {
        super("dash_roll");
    }

    @Override
    public List<LootType> getApplicableLoot() {
        return List.of(LootType.SWORD, LootType.AXE, LootType.SPEAR, LootType.SHOVEL, LootType.PICKAXE, LootType.HOE);
    }

    @Override
    public void onRightClick(Level level, Player player, InteractionHand hand, RollTier tier) {

        if (!level.isClientSide()) {

            Vec3 unitViewAngle = player.getLookAngle();
            Vec3 adjDashAngle = unitViewAngle.horizontal().scale(1 + 0.25d * tier.getMultiplier());
            player.setDeltaMovement(adjDashAngle);
            player.hurtMarked = true;
            player.resetFallDistance();
            addNewCooldown(player, player.getItemInHand(hand), getCooldown(tier));
            playActivateSoundEffect(level, player.blockPosition());
        }
    }

    @Override
    public int getCooldown(RollTier tier) {
        return DEFAULT_COOLDOWN;
    }

    @Override
    public Component getDescriptionComponent(RollTier tier) {
        return applyStandardDescriptionFormatting(
               Component.translatable("roll_header.loot_gamble.more_info.dash")
        );
    }
}
