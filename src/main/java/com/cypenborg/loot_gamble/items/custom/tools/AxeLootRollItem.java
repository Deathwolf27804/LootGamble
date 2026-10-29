package com.cypenborg.loot_gamble.items.custom.tools;

import com.cypenborg.loot_gamble.items.custom.LootRollItem;
import com.cypenborg.loot_gamble.items.custom.LootType;
import com.cypenborg.loot_gamble.rolls.RollTier;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public class AxeLootRollItem extends AxeItem implements ToolLootRollItem {

    public final RollTier tier;

    public AxeLootRollItem(Properties properties, ToolMaterial toolMaterial, RollTier tier) {
        super(toolMaterial, 0, 0, properties.enchantable(30));
        this.tier = tier;
    }

    public void addToolBaseAttributes(ItemAttributeModifiers.Builder builder, @NotNull ItemStack stack, RollTier tier) {

        LootRollItem.addNewAttribute(builder, Attributes.ATTACK_DAMAGE, 5 + tier.getMultiplier());
        LootRollItem.addNewAttribute(builder, Attributes.ATTACK_SPEED, -3.2d);
    }

    @Override
    public LootType getLootType() {
        return LootType.AXE;
    }

    @Override
    public RollTier getTier() {
        return tier;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ToolLootRollItem.use(level, player, hand);
        return super.use(level, player, hand);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        LootRollItem.inventoryTick(itemStack, level, owner, slot);
        super.inventoryTick(itemStack, level, owner, slot);
    }

    @Override
    public float getAttackDamageBonus(Entity victim, float damage, DamageSource damageSource) {
        LootRollItem.onDamageEntity(victim, damage, damageSource);
        return super.getAttackDamageBonus(victim, damage, damageSource);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        ToolLootRollItem.appendHoverText(itemStack, context, display, builder, tooltipFlag);
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}
