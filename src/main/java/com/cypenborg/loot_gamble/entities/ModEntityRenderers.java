package com.cypenborg.loot_gamble.entities;

import com.cypenborg.loot_gamble.LootGamble;
import net.minecraft.client.renderer.entity.IronGolemRenderer;
import net.minecraft.client.renderer.entity.SnowGolemRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = LootGamble.MODID, value = Dist.CLIENT)
public class ModEntityRenderers {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(
                ModEntityTypes.SUMMONED_IRON_GOLEM.get(),
                IronGolemRenderer::new
        );
        event.registerEntityRenderer(
                ModEntityTypes.SUMMONED_SNOW_GOLEM.get(),
                SnowGolemRenderer::new
        );
        event.registerEntityRenderer(ModEntityTypes.SUMMONED_FIREBALL.get(),
                ThrownItemRenderer::new);
    }
}