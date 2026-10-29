package com.cypenborg.loot_gamble.rolls;

import com.cypenborg.loot_gamble.LootGamble;
import com.cypenborg.loot_gamble.items.custom.LootType;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public abstract class AbstractRoll {

    final String name;
    final RollType type;
    final int id;
    private static int ID_HELPER = 0;

    public AbstractRoll(String name, RollType type) {
        this.name = name;
        this.type = type;
        this.id = ++ID_HELPER;
    }

    public void onRightClick(Level level, Player player, InteractionHand hand, RollTier tier) {}

    public void inventoryTick(ItemStack itemStack, ServerLevel level, LivingEntity owner, @Nullable EquipmentSlot slot, RollTier tier) {}

    public void onDamageEntity(LivingEntity perpetrator, LivingEntity victim, float damage, ItemStack weapon, RollTier tier) {}

    public static void addEffectToEntity(LivingEntity entity, Holder<MobEffect> effect, int duration, int amplifier) {

        MobEffectInstance mobEffectInstance = new MobEffectInstance(effect, duration, amplifier, true, true);
        entity.addEffect(mobEffectInstance);
    }

    public void addNewCooldownSecs(Player player, ItemStack stack, int timeSeconds) {

        addNewCooldown(player, stack, timeSeconds * 20);
    }

    public void addNewCooldown(Player player, ItemStack stack, int time) {

        player.getCooldowns().addCooldown(stack, time);
    }

    public Component getDescriptionComponent(RollTier tier) {
        return Component.translatable("roll_header.loot_gamble.more_info.none").withColor(TextColor.GRAY).withStyle(ChatFormatting.ITALIC);
    }

    public static Component applyStandardDescriptionFormatting(MutableComponent component) {
        return component.withColor(TextColor.DARK_GRAY).withStyle(ChatFormatting.ITALIC);
    }

    public RollTier getMinimumApplicableTier() {
        return RollTier.COMMON;
    }

    public String getTranslationKey() {
        return "roll." + LootGamble.MODID + "." + name;
    }

    public String getName() {
        return name;
    }

    public RollType getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public abstract List<LootType> getApplicableLoot();
}
