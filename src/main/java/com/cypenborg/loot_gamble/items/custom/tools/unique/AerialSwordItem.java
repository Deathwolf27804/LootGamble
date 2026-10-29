package com.cypenborg.loot_gamble.items.custom.tools.unique;

import com.cypenborg.loot_gamble.items.custom.LootRollItem;
import com.cypenborg.loot_gamble.items.custom.LootType;
import com.cypenborg.loot_gamble.rolls.AbstractRoll;
import com.cypenborg.loot_gamble.rolls.RollTier;
import com.cypenborg.loot_gamble.rolls.Rolls;
import com.cypenborg.loot_gamble.items.custom.tools.ToolLootRollItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Consumer;

public class AerialSwordItem extends Item implements UniqueToolItem {

    public AerialSwordItem(Properties properties) {
        super(properties.sword(ToolMaterial.NETHERITE, 0, 0).enchantable(30));
    }

    @Override
    public LootType getLootType() {
        return LootType.SWORD;
    }

    @Override
    public RollTier getTier() {
        return RollTier.UNIQUE;
    }

    @Override
    public void addToolBaseAttributes(ItemAttributeModifiers.Builder builder, @NotNull ItemStack stack, RollTier tier) {

        LootRollItem.addNewAttribute(builder, Attributes.ATTACK_DAMAGE, 3 + tier.getMultiplier());
        LootRollItem.addNewAttribute(builder, Attributes.ATTACK_SPEED, -2.4);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ToolLootRollItem.use(level, player, hand);
        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
        ToolLootRollItem.appendHoverText(itemStack, context, display, builder, tooltipFlag);
        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }

    @Override
    public Map<AbstractRoll, RollTier> getRolls() {
        return Map.of(
                Rolls.AERIAL_ROLL, RollTier.UNIQUE,
                Rolls.WIND_BURST_ROLL, RollTier.EPIC,
                Rolls.DENSITY_ROLL, RollTier.LEGENDARY,
                Rolls.BREACH_ROLL, RollTier.LEGENDARY,
                Rolls.SLOW_VICTIM_ROLL, RollTier.LEGENDARY,
                Rolls.SHARPNESS_ROLL, RollTier.LEGENDARY
        );
    }
}
