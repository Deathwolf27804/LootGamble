package com.cypenborg.loot_gamble.rolls;

import com.cypenborg.loot_gamble.rolls.passive.*;
import com.cypenborg.loot_gamble.rolls.right_click.*;
import com.cypenborg.loot_gamble.rolls.speciality.*;
import com.cypenborg.loot_gamble.rolls.unique.AerialRoll;
import com.cypenborg.loot_gamble.rolls.unique.SatiatingDashRoll;

public class Rolls {

    // ACTIVE
    public static final SpeedBoostRoll SPEED_BOOST_ROLL = new SpeedBoostRoll();
    public static final StrengthRoll RAGE_ROLL = new StrengthRoll();
    public static final AbsorptionRoll ABSORPTION_ROLL = new AbsorptionRoll();
    public static final InstantHealRoll INSTANT_HEAL_ROLL = new InstantHealRoll();
    public static final DashRoll DASH_ROLL = new DashRoll();
    public static final SummonGolemRoll SUMMON_GOLEM_ROLL = new SummonGolemRoll();
    public static final SummonFireballRoll SUMMON_FIREBALL_ROLL = new SummonFireballRoll();
    public static final EntityGlowRoll ENTITY_GLOW_ROLL = new EntityGlowRoll();
    public static final OreGlowRoll ORE_GLOW_ROLL = new OreGlowRoll();

    // PASSIVE
    public static final NightVisionRoll NIGHT_VISION_ROLL = new NightVisionRoll();
    public static final RegenRoll REGEN_ROLL = new RegenRoll();
    public static final HasteRoll HASTE_ROLL = new HasteRoll();
    public static final JumpBoostRoll JUMP_BOOST_ROLL = new JumpBoostRoll();
    public static final SlowVictimRoll SLOW_VICTIM_ROLL = new SlowVictimRoll();
    public static final WeakenVictimRoll WEAKEN_VICTIM_ROLL = new WeakenVictimRoll();
    public static final LifeStealRoll LIFE_STEAL_ROLL = new LifeStealRoll();

    // SPECIALITY
    public static final UnbreakingRoll UNBREAKING_ROLL = new UnbreakingRoll();
    public static final SharpnessRoll SHARPNESS_ROLL = new SharpnessRoll();
    public static final EfficiencyRoll EFFICIENCY_ROLL = new EfficiencyRoll();
    public static final LootingRoll LOOTING_ROLL = new LootingRoll();
    public static final FortuneRoll FORTUNE_ROLL = new FortuneRoll();
    public static final WindBurstRoll WIND_BURST_ROLL = new WindBurstRoll();
    public static final DensityRoll DENSITY_ROLL = new DensityRoll();
    public static final BreachRoll BREACH_ROLL = new BreachRoll();
    public static final LungeRoll LUNGE_ROLL = new LungeRoll();

    // UNIQUE
    public static final AerialRoll AERIAL_ROLL = new AerialRoll();
    public static final SatiatingDashRoll SATIATING_DASH_ROLL = new SatiatingDashRoll();

    public static void init() {

        // ACTIVE
        RollManager.registerRoll(SPEED_BOOST_ROLL);
        RollManager.registerRoll(RAGE_ROLL);
        RollManager.registerRoll(ABSORPTION_ROLL);
        RollManager.registerRoll(INSTANT_HEAL_ROLL);
        RollManager.registerRoll(DASH_ROLL);
        RollManager.registerRoll(SUMMON_GOLEM_ROLL);
        RollManager.registerRoll(SUMMON_FIREBALL_ROLL);
        RollManager.registerRoll(ENTITY_GLOW_ROLL);
        RollManager.registerRoll(ORE_GLOW_ROLL);

        // PASSIVE
        RollManager.registerRoll(NIGHT_VISION_ROLL);
        RollManager.registerRoll(REGEN_ROLL);
        RollManager.registerRoll(HASTE_ROLL);
        RollManager.registerRoll(JUMP_BOOST_ROLL);
        RollManager.registerRoll(SLOW_VICTIM_ROLL);
        RollManager.registerRoll(WEAKEN_VICTIM_ROLL);
        RollManager.registerRoll(LIFE_STEAL_ROLL);

        // SPECIALITY
        RollManager.registerRoll(UNBREAKING_ROLL);
        RollManager.registerRoll(SHARPNESS_ROLL);
        RollManager.registerRoll(EFFICIENCY_ROLL);
        RollManager.registerRoll(LOOTING_ROLL);
        RollManager.registerRoll(FORTUNE_ROLL);
        RollManager.registerRoll(WIND_BURST_ROLL);
        RollManager.registerRoll(DENSITY_ROLL);
        RollManager.registerRoll(BREACH_ROLL);
        RollManager.registerRoll(LUNGE_ROLL);

        // UNIQUE
        RollManager.registerRoll(AERIAL_ROLL);
        RollManager.registerRoll(SATIATING_DASH_ROLL);
    }
}
