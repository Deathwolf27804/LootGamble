package com.cypenborg.loot_gamble.util;

import com.cypenborg.loot_gamble.LootGamble;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(
        modid = LootGamble.MODID,
        value = Dist.CLIENT
)
public class ModKeyMappings {

    public static final KeyMapping.Category LOOT_GAMBLE_CATEGORY =
            new KeyMapping.Category(
                    Identifier.fromNamespaceAndPath(LootGamble.MODID, "keybindings")
            );

    public static final KeyMapping MORE_INFO_KEY = new KeyMapping(
            String.format("key.%s.more_info", LootGamble.MODID),
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_LEFT_SHIFT,
            LOOT_GAMBLE_CATEGORY
    );


    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(ModKeyMappings::registerKeyMappings);
    }

    private static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.registerCategory(LOOT_GAMBLE_CATEGORY);
        event.register(MORE_INFO_KEY);
    }

    public static boolean isMoreInfoKeyHeldDown() {
        return MORE_INFO_KEY.isDown();
    }
}