package com.cypenborg.loot_gamble;

import com.cypenborg.loot_gamble.blocks.ModBlockEntities;
import com.cypenborg.loot_gamble.blocks.ModBlocks;
import com.cypenborg.loot_gamble.blocks.custom_block_entities.ModMenus;
import com.cypenborg.loot_gamble.entities.ModEntityTypes;
import com.cypenborg.loot_gamble.items.ModItems;
import com.cypenborg.loot_gamble.items.block_items.ModBlockItems;
import com.cypenborg.loot_gamble.items.custom.LootGroupType;
import com.cypenborg.loot_gamble.rolls.Rolls;
import com.cypenborg.loot_gamble.util.ModCreativeModeTabs;
import com.cypenborg.loot_gamble.items.ModDataComponents;
import com.cypenborg.loot_gamble.util.ModKeyMappings;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(LootGamble.MODID)
public class LootGamble {
    public static final String MODID = "loot_gamble";
    public static final Logger LOGGER = LogUtils.getLogger();

    public LootGamble(IEventBus modEventBus, ModContainer modContainer) {

        modEventBus.addListener(this::commonSetup);

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockItems.BLOCK_ITEMS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModMenus.MENUS.register(modEventBus);
        ModEntityTypes.ENTITY_TYPES.register(modEventBus);
        ModCreativeModeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        ModDataComponents.register(modEventBus);
        ModKeyMappings.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (LevelUp) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        LootGroupType.init();
        Rolls.init();
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
