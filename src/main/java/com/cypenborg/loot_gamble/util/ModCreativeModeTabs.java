package com.cypenborg.loot_gamble.util;

import com.cypenborg.loot_gamble.LootGamble;
import com.cypenborg.loot_gamble.items.ModItems;
import com.cypenborg.loot_gamble.items.block_items.ModBlockItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LootGamble.MODID);

    static {

        CREATIVE_MODE_TABS.register(
                "loot_gamble_tab",
                () -> CreativeModeTab.builder()
                        .title(Component.translatable("itemGroup.loot_gamble"))
                        .withTabsBefore(CreativeModeTabs.COMBAT)
                        .icon(() -> ModItems.RANDOM_CORE.get().getDefaultInstance())
                        .displayItems((_, output) -> {

                            output.accept(ModItems.RANDOM_CORE);
                            output.accept(ModBlockItems.COMMON_LOOT_ROLL_BLOCK_ITEM);
                            output.accept(ModBlockItems.UNCOMMON_LOOT_ROLL_BLOCK_ITEM);
                            output.accept(ModBlockItems.RARE_LOOT_ROLL_BLOCK_ITEM);
                            output.accept(ModBlockItems.EPIC_LOOT_ROLL_BLOCK_ITEM);
                            output.accept(ModBlockItems.LEGENDARY_LOOT_ROLL_BLOCK_ITEM);
                            output.accept(ModBlockItems.UNIQUE_LOOT_ROLL_BLOCK_ITEM);
                            output.accept(ModBlockItems.ATTUNER_ENTITY_BLOCK_ITEM);
                            output.accept(ModBlockItems.ENCLAVE_ENTITY_BLOCK_ITEM);

                        }).build());
    }
}
