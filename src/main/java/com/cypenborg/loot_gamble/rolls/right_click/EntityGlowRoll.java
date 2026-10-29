package com.cypenborg.loot_gamble.rolls.right_click;

import com.cypenborg.loot_gamble.items.custom.LootType;
import com.cypenborg.loot_gamble.rolls.AddEffectRoll;
import com.cypenborg.loot_gamble.rolls.RollTier;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class EntityGlowRoll extends AbstractRightClickRoll implements AddEffectRoll {

    public static final int BASE_GLOW_DISTANCE = 3-0;

    public EntityGlowRoll() {
        super("entity_glow_roll");
    }

    public int getGlowDistance(RollTier tier) {
        return BASE_GLOW_DISTANCE + 10 * tier.getMultiplier();
    }

    @Override
    public void onRightClick(Level level, Player player, InteractionHand hand, RollTier tier) {

        Vec3 playerPos = player.position();
        Vec3 posDelta = new Vec3(getGlowDistance(tier), getGlowDistance(tier), getGlowDistance(tier));
        AABB entityAABB = new AABB(playerPos.subtract(posDelta), playerPos.add(posDelta));

        level.getEntities(player, entityAABB)
                .forEach(entity -> {
                    if (entity instanceof LivingEntity livingEntity)
                        addEffectToEntity(livingEntity, getEffectHolder(tier), getDuration(tier), getAmplifier(tier));
                });
        addNewCooldown(player, player.getItemInHand(hand), getCooldown(tier));
        playActivateSoundEffect(level, player.blockPosition());
    }

    @Override
    public int getCooldown(RollTier tier) {
        return getDuration(tier) + DEFAULT_COOLDOWN;
    }

    @Override
    public List<LootType> getApplicableLoot() {
        return List.of(LootType.SWORD, LootType.AXE, LootType.SPEAR);
    }

    @Override
    public Holder<MobEffect> getEffectHolder(RollTier tier) {
        return MobEffects.GLOWING;
    }

    @Override
    public int getDuration(RollTier tier) {
        return BASE_MEDIUM_DURATION * 20;
    }

    @Override
    public int getAmplifier(RollTier tier) {
        return 0;
    }

    @Override
    public Component getDescriptionComponent(RollTier tier) {
        return applyStandardDescriptionFormatting(Component.translatable(
                "roll_header.loot_gamble.more_info.entity_glow", getGlowDistance(tier)));
    }
}
