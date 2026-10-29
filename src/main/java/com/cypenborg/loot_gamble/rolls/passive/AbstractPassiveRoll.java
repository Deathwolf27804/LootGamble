package com.cypenborg.loot_gamble.rolls.passive;

import com.cypenborg.loot_gamble.rolls.AbstractRoll;
import com.cypenborg.loot_gamble.rolls.RollType;

public abstract class AbstractPassiveRoll extends AbstractRoll {

    public AbstractPassiveRoll(String name) {
        super(name, RollType.PASSIVE);
    }
}
