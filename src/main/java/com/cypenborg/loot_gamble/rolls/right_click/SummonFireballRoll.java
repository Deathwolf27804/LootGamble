package com.cypenborg.loot_gamble.rolls.right_click;

import com.cypenborg.loot_gamble.entities.ModEntityTypes;
import com.cypenborg.loot_gamble.entities.SummonedFireball;
import com.cypenborg.loot_gamble.items.custom.LootType;
import com.cypenborg.loot_gamble.rolls.RollTier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.List;

public class SummonFireballRoll extends AbstractRightClickRoll{

    public static final int REDUCED_COOLDOWN = 4 * 20;
    public static final int BASE_LIFETIME = 5 * 20;

    public SummonFireballRoll() {
        super("summon_fireball_roll");
    }

    private EntityType<? extends Entity> getSummonedEntity() {
        return ModEntityTypes.SUMMONED_FIREBALL.get();
    }

    private void setSummonedEntityTraits(Entity fireball, Player player, RollTier tier) {

        if (fireball instanceof SummonedFireball summonedFireball) {

            summonedFireball.setPositionRelativeTo(player);
            summonedFireball.setSummoner(player);
            summonedFireball.setMaxLifeTime(BASE_LIFETIME);
            summonedFireball.setExplosionPower(0.5f + tier.getMultiplier() * 0.5f);
        }
    }

    @Override
    public void onRightClick(Level level, Player player, InteractionHand hand, RollTier tier) {

        Entity fireball = getSummonedEntity().create(level, EntitySpawnReason.COMMAND);
        if (fireball != null) {

            setSummonedEntityTraits(fireball, player, tier);
            level.addFreshEntity(fireball);
            addNewCooldown(player, player.getItemInHand(hand), getCooldown(tier));
            playActivateSoundEffect(level, player.blockPosition());
        }
    }

    @Override
    public int getCooldown(RollTier tier) {
        return DEFAULT_COOLDOWN - REDUCED_COOLDOWN;
    }

    @Override
    public List<LootType> getApplicableLoot() {
        return List.of(LootType.SWORD, LootType.AXE);
    }

    @Override
    public Component getDescriptionComponent(RollTier tier) {
        return applyStandardDescriptionFormatting(
                Component.translatable("roll_header.loot_gamble.more_info.summon_fireball",
                        getSummonedEntity().getDescription().getString())
        );
    }
}
