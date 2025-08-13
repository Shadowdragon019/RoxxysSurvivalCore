package lol.roxxane.roxxys_survival_core.configs;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class RscServerConfig {
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final BooleanValue REMOVE_DURABILITY =
		BUILDER.define("remove_durability", false);
	public static final IntValue SURVIVAL_MINING_COOLDOWN =
		BUILDER.comment("Vanilla value is 5")
			.defineInRange("survival_mining_cooldown", 5, 0, Integer.MAX_VALUE);
	public static final IntValue CREATIVE_MINING_COOLDOWN =
		BUILDER.comment("Vanilla value is 5")
			.defineInRange("creative_mining_cooldown", 5, 0, Integer.MAX_VALUE);
	public static final IntValue DEFAULT_IFRAMES =
		BUILDER.defineInRange("default_iframes", 5, 0, Integer.MAX_VALUE);
	public static final BooleanValue OVERRIDE_IFRAME_FUNCTIONALITY =
		BUILDER.define("override_iframe_functionality", false);
	public static final ForgeConfigSpec SPEC = BUILDER.build();
}
