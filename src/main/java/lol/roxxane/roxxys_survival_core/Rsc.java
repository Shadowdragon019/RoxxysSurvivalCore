package lol.roxxane.roxxys_survival_core;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.Registrate;
import lol.roxxane.roxxys_survival_core.blocks.RscBlocks;
import lol.roxxane.roxxys_survival_core.configs.RscClientConfig;
import lol.roxxane.roxxys_survival_core.configs.RscCommonConfig;
import lol.roxxane.roxxys_survival_core.configs.RscServerConfig;
import lol.roxxane.roxxys_survival_core.recipes.FireworkStarRecipeCustomization;
import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Rsc.ID)
public class Rsc {
	public static final String ID = "roxxys_survival_core";
	public static final Logger LOGGER = LogUtils.getLogger();
	public static final Registrate REGISTRATE = Registrate.create(ID);
	public static void log(Object... objects) {
		for (var object : objects)
			LOGGER.info(object.toString());
	}
	public Rsc(FMLJavaModLoadingContext context) {
		//var mod_bus = context.getModEventBus();
		REGISTRATE.addRawLang("jei.recipe.roxxys_survival_core.dyes", "Dyes: 1-8");
		REGISTRATE.addRawLang("jei.recipe.roxxys_survival_core.firework_stars", "Firework Stars: 1-8");
		REGISTRATE.addRawLang("jei.recipe.roxxys_survival_core.gunpowder", "Gunpowder: 1-3");
		REGISTRATE.addRawLang("jei.recipe.roxxys_survival_core.optional", "Optional: %s");
		context.registerConfig(ModConfig.Type.SERVER, RscServerConfig.SPEC);
		context.registerConfig(ModConfig.Type.COMMON, RscCommonConfig.SPEC);
		context.registerConfig(ModConfig.Type.CLIENT, RscClientConfig.SPEC);
		RscBlocks.register();
	    FireworkStarRecipeCustomization.remove_item(Items.FIRE_CHARGE);
		FireworkStarRecipeCustomization.remove_item(Items.GOLD_NUGGET);
		FireworkStarRecipeCustomization.remove_item(Items.SKELETON_SKULL);
		FireworkStarRecipeCustomization.remove_item(Items.DRAGON_HEAD);
		FireworkStarRecipeCustomization.add_item(Items.CLAY_BALL, FireworkRocketItem.Shape.LARGE_BALL);
		FireworkStarRecipeCustomization.add_item(Items.GOLD_INGOT, FireworkRocketItem.Shape.STAR);
	}
}