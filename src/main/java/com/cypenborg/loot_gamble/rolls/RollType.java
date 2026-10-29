package com.cypenborg.loot_gamble.rolls;

import com.cypenborg.loot_gamble.LootGamble;

public enum RollType {

    RIGHT_CLICK(true),
    PASSIVE(true),
    SPECIALITY(true),
    UNIQUE(false);

    final boolean modifiable;

    RollType(boolean modifiable) {
        this.modifiable = modifiable;
    }

    public String getName() {
        return this.name().toLowerCase();
    }

    public String getHeaderTranslationKey() {
        return "roll_header." + LootGamble.MODID + "." + getName();
    }

    public boolean isModifiable() {
        return modifiable;
    }
}
