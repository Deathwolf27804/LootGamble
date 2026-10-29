package com.cypenborg.loot_gamble.rolls.speciality;

import com.cypenborg.loot_gamble.rolls.AbstractRoll;
import com.cypenborg.loot_gamble.rolls.RollTier;
import com.cypenborg.loot_gamble.rolls.RollType;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;

public abstract class AbstractSpecialityRoll extends AbstractRoll {

    public AbstractSpecialityRoll(String name) {
        super(name, RollType.SPECIALITY);
    }

    public abstract ResourceKey<Enchantment> getEnchantment(RollTier tier);

    public abstract int getBaseEnchantmentLevel();

    public int getEnchantmentLevel(RollTier tier) {
        return getBaseEnchantmentLevel() + tier.getMultiplier() - getMinimumApplicableTier().getMultiplier();
    }

    public void addSpeciality(ServerLevel level, ItemStack stack, RollTier tier) {

        ResourceKey<Enchantment> enchantmentResourceKey = getEnchantment(tier);
        Holder<Enchantment> enchantmentHolder = level.registryAccess().getOrThrow(enchantmentResourceKey);
        stack.enchant(enchantmentHolder, getEnchantmentLevel(tier));
    }

    public void remove(ServerLevel level, ItemStack stack, RollTier tier) {

        ResourceKey<Enchantment> enchantmentResourceKey = getEnchantment(tier);
        Holder<Enchantment> enchantmentHolder = level.registryAccess().getOrThrow(enchantmentResourceKey);

        ItemEnchantments enchantments = stack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
        ItemEnchantments.Mutable mutable = new ItemEnchantments.Mutable(enchantments);
        mutable.removeIf(holder -> holder.equals(enchantmentHolder));
        stack.set(DataComponents.ENCHANTMENTS, mutable.toImmutable());
    }

    @Override
    public RollTier getMinimumApplicableTier() {
        return RollTier.RARE;
    }

    @Override
    public Component getDescriptionComponent(RollTier tier) {

        Level level = Minecraft.getInstance().level;
        String enchantmentName = level.registryAccess().getOrThrow(getEnchantment(tier)).value().description().getString();

        return applyStandardDescriptionFormatting(
                Component.translatable("roll_header.loot_gamble.more_info.enchantment",
                        enchantmentName, getEnchantmentLevel(tier))
        );
    }
}
