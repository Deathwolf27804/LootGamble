package com.cypenborg.loot_gamble.items.custom;

import com.cypenborg.loot_gamble.rolls.AbstractRoll;
import com.cypenborg.loot_gamble.rolls.unique.AbstractUniqueRoll;
import com.cypenborg.loot_gamble.rolls.RollManager;
import com.cypenborg.loot_gamble.rolls.RollTier;
import com.cypenborg.loot_gamble.rolls.speciality.AbstractSpecialityRoll;
import com.cypenborg.loot_gamble.items.custom.tools.unique.UniqueToolItem;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

import java.util.*;

public interface LootRollItem {

    LootType getLootType();
    RollTier getTier();
    void addToolBaseAttributes(ItemAttributeModifiers.Builder builder, @NotNull ItemStack stack, RollTier tier);

    static void addNewAttribute(ItemAttributeModifiers.Builder builder, Holder<Attribute> attributeHolder, double amt) {
        Identifier identifier = attributeHolder.getKey().identifier();
        builder.add(
                attributeHolder,
                new AttributeModifier(identifier, amt, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
        );
    }

    static void addNewRoll(ServerLevel level, ItemStack stack, AbstractRoll roll, RollTier tier) {
        if (roll instanceof AbstractUniqueRoll uniqueRoll) UniqueToolItem.addNewUniqueRoll(stack, uniqueRoll);

        DeferredHolder<DataComponentType<?>, DataComponentType<Map<String, Integer>>> modDataComponent =
                RollManager.getRelevantDataComponent(roll);

        Map<String, Integer> rollMap = stack.getOrDefault(modDataComponent, new HashMap<>());
        Map<String, Integer> newRollMap = new HashMap<>(rollMap);
        newRollMap.put(roll.getName(), tier.getIndex());
        stack.set(modDataComponent, newRollMap);

        if (roll instanceof AbstractSpecialityRoll specialityRoll) specialityRoll.addSpeciality(level, stack, tier);
    }

    static void removeRoll(ServerLevel level, ItemStack stack, AbstractRoll roll, RollTier tier) {

        DeferredHolder<DataComponentType<?>, DataComponentType<Map<String, Integer>>> modDataComponent =
                RollManager.getRelevantDataComponent(roll);

        Map<String, Integer> rollMap = stack.getOrDefault(modDataComponent, new HashMap<>());
        Map<String, Integer> newRollMap = new HashMap<>(rollMap);
        newRollMap.remove(roll.getName());
        stack.set(modDataComponent, newRollMap);

        if (roll instanceof AbstractSpecialityRoll specialityRoll) specialityRoll.remove(level, stack, tier);
    }

    static void onDamageEntity(Entity victim, float damage, DamageSource source) {

        if (!victim.level().isClientSide() && source.getEntity() instanceof LivingEntity perpetrator
                && victim instanceof LivingEntity livingVictim) {

            ItemStack weapon = source.getWeaponItem();
            if (weapon == null) return;

            RollManager.getAllDataComponents().forEach((dataComponent) -> {

                Optional<Map<AbstractRoll, RollTier>> optionalRolls =
                        RollManager.getRolls(weapon.get(dataComponent));

                optionalRolls.ifPresent(rolls -> rolls.forEach(
                        (roll, tier) -> roll.onDamageEntity(perpetrator, livingVictim, damage, weapon, tier)
                ));
            });
        }

    }

    static void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {

        if (!level.isClientSide() && slot != null && owner instanceof LivingEntity entity ) {

            RollManager.getAllDataComponents().forEach((dataComponent) -> {

                Optional<Map<AbstractRoll, RollTier>> optionalRolls =
                        RollManager.getRolls(entity.getItemBySlot(slot).get(dataComponent));

                optionalRolls.ifPresent(rolls -> rolls.forEach(
                        (roll, tier) -> roll.inventoryTick(itemStack, level, entity, slot, tier)
                ));
            });
        }
    }
}
