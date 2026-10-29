package com.cypenborg.loot_gamble.render;

import com.cypenborg.loot_gamble.LootGamble;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.gizmos.DrawableGizmoPrimitives;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.SubmitCustomGeometryEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.api.distmarker.Dist;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(
        modid = LootGamble.MODID,
        value = Dist.CLIENT
)
public final class OreGlowRenderer {

    private static final int GLOW_COLOR = 0xFF00FFFF;
    private static final float LINE_WIDTH = 2.0F;

    private static final List<BlockPosTick> orePositionsTicked = new ArrayList<>();


    public static void submitOrePositions(List<BlockPos> orePositions, int maxTicks) {
        orePositions.forEach(pos -> orePositionsTicked.add(new BlockPosTick(pos, maxTicks)));
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {

        Minecraft minecraft = Minecraft.getInstance();
        Level level = minecraft.level;

        if (level != null && level.isClientSide()) {

            orePositionsTicked.removeIf(posTick -> {

                boolean ticked = posTick.tickAndCheck();
                boolean destroyed = level.getBlockState(posTick.getPos()).is(BlockTags.AIR);
                return ticked || destroyed;
            });
        }

    }

    @SubscribeEvent
    public static void onSubmitGeometry(SubmitCustomGeometryEvent event) {

        Minecraft minecraft = Minecraft.getInstance();
        Level level = minecraft.level;

        if (level != null) {

            List<BlockPos> orePositions = getOrePositions();
            if (orePositions.isEmpty()) return;

            DrawableGizmoPrimitives gizmos = new DrawableGizmoPrimitives();
            for (BlockPos pos : orePositions) addBlockOutline(gizmos, pos);

            gizmos.submit(
                    event.getSubmitNodeCollector(),
                    event.getLevelRenderState().cameraRenderState,
                    true
            );
        }
    }

    private static void addBlockOutline(DrawableGizmoPrimitives gizmos, BlockPos pos) {

        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();

        //          E -------- F
        //         /|         /|
        //        H -------- G |
        //        | A -------|-B      EAST
        //        |/         |/
        //        D -------- C

        List<BlockPos> orePositions = getOrePositions();

        int above = orePositions.contains(pos.above()) ? 1 : 0;
        int below = orePositions.contains(pos.below()) ? 1 : 0;
        int north = orePositions.contains(pos.north()) ? 1 : 0;
        int east = orePositions.contains(pos.east()) ? 1 : 0;
        int south = orePositions.contains(pos.south()) ? 1 : 0;
        int west = orePositions.contains(pos.west()) ? 1 : 0;

        boolean aboveNorth = orePositions.contains(pos.above().north());
        boolean aboveEast = orePositions.contains(pos.above().east());
        boolean aboveSouth = orePositions.contains(pos.above().south());
        boolean aboveWest = orePositions.contains(pos.above().west());
        boolean belowNorth = orePositions.contains(pos.below().north());
        boolean belowEast = orePositions.contains(pos.below().east());
        boolean belowSouth = orePositions.contains(pos.below().south());
        boolean belowWest = orePositions.contains(pos.below().west());
        boolean northEast = orePositions.contains(pos.north().east());
        boolean northWest = orePositions.contains(pos.north().west());
        boolean southEast = orePositions.contains(pos.south().east());
        boolean southWest = orePositions.contains(pos.south().west());

        Vec3 a = new Vec3(x, y, z);
        Vec3 b = new Vec3(x + 1, y, z);
        Vec3 c = new Vec3(x + 1, y, z + 1);
        Vec3 d = new Vec3(x, y, z + 1);
        Vec3 e = new Vec3(x, y + 1, z);
        Vec3 f = new Vec3(x + 1, y + 1, z);
        Vec3 g = new Vec3(x + 1, y + 1, z + 1);
        Vec3 h = new Vec3(x, y + 1, z + 1);

        // Bottom face
        line(gizmos, a, b, below + north, belowNorth);
        line(gizmos, b, c, below + east, belowEast);
        line(gizmos, c, d, below + south, belowSouth);
        line(gizmos, d, a, below + west, belowWest);

        // Top face
        line(gizmos, e, f, above + north, aboveNorth);
        line(gizmos, f, g, above + east, aboveEast);
        line(gizmos, g, h, above + south, aboveSouth);
        line(gizmos, h, e, above + west, aboveWest);

        // Vertical edges
        line(gizmos, a, e, west + north, northWest);
        line(gizmos, b, f, east + north, northEast);
        line(gizmos, c, g, east + south, southEast);
        line(gizmos, d, h, west + south, southWest);
    }

    private static void line(DrawableGizmoPrimitives gizmos, Vec3 start, Vec3 end, int adjCount, boolean corner) {
        if ((adjCount == 1 && !corner) || (adjCount == 2 && corner)) return;

        gizmos.addLine(start, end, GLOW_COLOR, LINE_WIDTH);
    }

    private static List<BlockPos> getOrePositions() {
        List<BlockPos> orePos = new ArrayList<>();
        orePositionsTicked.forEach(posTick -> orePos.add(posTick.getPos()));
        return orePos;
    }
}
