package lol.roxxane.roxxys_survival_core;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.Registrate;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Rsc.ID)
public class Rsc {
	public static final String ID = "roxxys_survival_core";
	public static final Logger LOGGER = LogUtils.getLogger();
	public static final Registrate REGISTRATE = Registrate.create(ID).skipErrors(false);
	public static void log(Object... objects) {
		for (var object : objects)
			LOGGER.info(object.toString());
	}
	public Rsc(FMLJavaModLoadingContext context) {
		//var mod_bus = context.getModEventBus();
		context.registerConfig(ModConfig.Type.SERVER, RscServerConfig.SPEC);
		context.registerConfig(ModConfig.Type.CLIENT, RscClientConfig.SPEC);
		RscItems.register();
		RscBlocks.register();
		RscRecipeTypes.register();
		RscRecipeSerializers.register();
	}
}
/*
Make sure tiers take time to get. Going from stone -> iron felt pretty good in the test
Food heals/gives effects
Remove/reduce mob i-frames so you can hit them more
Zombie hp - 40
Spider hp - 30
Remove shields
Merge the wood/stone tool tiers
Want: Some mod to show how much hearts something heals
*/