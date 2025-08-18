package lol.roxxane.roxxys_survival_core.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class RscClientConfig {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec.BooleanValue ADD_FIREWORK_STAR_RECIPES_TO_JEI =
		BUILDER.define("add_firework_star_recipe_to_jei", false);
	public static final ForgeConfigSpec SPEC = BUILDER.build();
}
