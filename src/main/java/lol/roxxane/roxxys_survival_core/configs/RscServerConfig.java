package lol.roxxane.roxxys_survival_core.configs;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class RscServerConfig {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final IntValue SURVIVAL_MINING_COOLDOWN =
		BUILDER.comment("Vanilla value is 5")
			.defineInRange("survival_mining_cooldown", 5, 0, Integer.MAX_VALUE);
	public static final IntValue CREATIVE_MINING_COOLDOWN =
		BUILDER.comment("Vanilla value is 5")
			.defineInRange("creative_mining_cooldown", 5, 0, Integer.MAX_VALUE);
	public static final IntValue DEFAULT_IFRAMES =
		BUILDER.defineInRange("default_iframes", 0, 0, Integer.MAX_VALUE);
	public static final BooleanValue OVERRIDE_IFRAME_FUNCTIONALITY =
		BUILDER.define("override_iframe_functionality", false); // TODO: Setting to false doesn't work?
	public static final BooleanValue CUSTOMIZE_FIREWORK_STAR_RECIPE =
		BUILDER.define("customize_firework_star_recipe", false);
	public static final ForgeConfigSpec SPEC = BUILDER.build();
}
