package com.cypenborg.loot_gamble.rolls;

public class IllegalRollException extends RuntimeException {

    // See LootRollManager
    public IllegalRollException(AbstractRoll roll) {
        super(String.format("%s is not a properly handled roll type", roll.getName()));
    }
}
