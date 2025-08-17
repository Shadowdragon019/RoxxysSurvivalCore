package lol.roxxane.roxxys_survival_core.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RscCommonConfig {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec.BooleanValue OVERRIDE_WORLD_STARTING_TIME =
		BUILDER.define("override_world_starting_time", false);
	public static final ForgeConfigSpec.IntValue WORLD_STARTING_TIME =
		BUILDER.defineInRange("world_starting_time", 6000, 0, Integer.MAX_VALUE);
	public static final ForgeConfigSpec SPEC = BUILDER.build();
}
