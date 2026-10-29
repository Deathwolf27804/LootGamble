package com.cypenborg.loot_gamble.blocks.custom_block_entities;

import com.cypenborg.loot_gamble.LootGamble;
import com.cypenborg.loot_gamble.blocks.custom_block_entities.attuner.AttunerScreen;
import com.cypenborg.loot_gamble.blocks.custom_block_entities.enclave.EnclaveScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@EventBusSubscriber(modid = LootGamble.MODID, value = Dist.CLIENT)
public class ModScreens {

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(
                ModMenus.ATTUNER_MENU.get(),
                AttunerScreen::new
        );
        event.register(
                ModMenus.ENCLAVE_MENU.get(),
                EnclaveScreen::new
        );
    }
}