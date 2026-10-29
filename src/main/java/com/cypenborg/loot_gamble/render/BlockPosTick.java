package com.cypenborg.loot_gamble.render;

import net.minecraft.core.BlockPos;

public class BlockPosTick {

    private final BlockPos pos;
    private int tick;
    private final int maxTicks;

    public BlockPosTick(BlockPos pos, int maxTicks) {
        assert maxTicks > 0;
        this.pos = pos;
        this.maxTicks = maxTicks;
        this.tick = 0;
    }

    public boolean tickAndCheck() {
        return ++tick == maxTicks;
    }

    public BlockPos getPos() {
        return pos;
    }
}
