package lol.roxxane.roxxys_survival_core;

import com.mojang.logging.LogUtils;
import lol.roxxane.roxxys_survival_core.configs.RscServerConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Rsc.ID)
public class Rsc {
	public static final String ID = "roxxys_survival_core";
	public static final Logger LOGGER = LogUtils.getLogger();
	public static void log(Object... objects) {
		for (var object : objects)
			LOGGER.info(object.toString());
	}
	public Rsc(FMLJavaModLoadingContext context) {
		//var mod_bus = context.getModEventBus();
		context.registerConfig(ModConfig.Type.SERVER, RscServerConfig.SPEC);
	}
}