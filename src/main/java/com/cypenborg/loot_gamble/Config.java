package com.cypenborg.loot_gamble;

import com.cypenborg.loot_gamble.datagen.ModTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue CAN_DROP_RANDOM_CORES = BUILDER
            .comment(
                    String.format("Mobs (except for those defined in the %s tag) will be able to drop random cores",
                            ModTags.Entities.CANNOT_DROP_RANDOM_CORE.toString())
            )
            .define("can_drop_random_cores", true);

    public static final ModConfigSpec.DoubleValue RANDOM_CORE_DROP_CHANCE = BUILDER
            .comment(
                    String.format("%s must be enabled", CAN_DROP_RANDOM_CORES.getPath())
            )
            .defineInRange("random_core_drop_chance", 0.1d, 0d, 1d);


//    public static final ModConfigSpec.ConfigValue<String> MAGIC_NUMBER_INTRODUCTION = BUILDER
//            .comment("What you want the introduction message to be for the magic number")
//            .define("magicNumberIntroduction", "The magic number is... ");
//
//    // a list of strings that are treated as resource locations for items
//    public static final ModConfigSpec.ConfigValue<List<? extends String>> ITEM_STRINGS = BUILDER
//            .comment("A list of items to log on common setup.")
//            .defineListAllowEmpty("items", List.of("minecraft:iron_ingot"), () -> "", Config::validateItemName);

    static final ModConfigSpec SPEC = BUILDER.build();

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(Identifier.parse(itemName));
    }
}
