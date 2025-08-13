package lol.roxxane.roxxys_survival_core;

import net.minecraftforge.common.ForgeConfigSpec;
public class RscClientConfig {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec.BooleanValue HIDE_ITEMS_IN_JEI =
		BUILDER.define("hide_items_in_jeo", true);
	public static final ForgeConfigSpec SPEC = BUILDER.build();
}
