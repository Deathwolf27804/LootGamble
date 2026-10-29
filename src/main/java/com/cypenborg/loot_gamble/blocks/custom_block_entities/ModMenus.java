package com.cypenborg.loot_gamble.blocks.custom_block_entities;

import com.cypenborg.loot_gamble.LootGamble;
import com.cypenborg.loot_gamble.blocks.custom_block_entities.attuner.AttunerMenu;
import com.cypenborg.loot_gamble.blocks.custom_block_entities.enclave.EnclaveMenu;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModMenus {

    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(
                    BuiltInRegistries.MENU.key(),
                    LootGamble.MODID
            );

    public static final Supplier<MenuType<AttunerMenu>> ATTUNER_MENU =
            MENUS.register("attuner",
                    () -> IMenuTypeExtension.create((id, inventory, _) ->
                            new AttunerMenu(id, inventory, new SimpleContainer(1)))
            );
    public static final Supplier<MenuType<EnclaveMenu>> ENCLAVE_MENU =
            MENUS.register("enclave",
                    () -> IMenuTypeExtension.create((id, inventory, _) ->
                            new EnclaveMenu(id, inventory, new SimpleContainer(3)))
            );
}
