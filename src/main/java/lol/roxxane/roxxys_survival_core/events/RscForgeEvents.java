package lol.roxxane.roxxys_survival_core.events;

import lol.roxxane.roxxys_survival_core.Rsc;
import lol.roxxane.roxxys_survival_core.commands.RscDevCommand;
import lol.roxxane.roxxys_survival_core.configs.RscCommonConfig;
import lol.roxxane.roxxys_survival_core.configs.RscServerConfig;
import lol.roxxane.roxxys_survival_core.data.RscDamageTypeIframesManager;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Rsc.ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RscForgeEvents {
	@SubscribeEvent
	public static void register_commands(RegisterCommandsEvent event) {
		var dispatcher = event.getDispatcher();
		var context = event.getBuildContext();
		RscDevCommand.register(dispatcher, context);
	}
	@SubscribeEvent
	public static void living_hurt(LivingDamageEvent event) {
		if (RscServerConfig.OVERRIDE_IFRAME_FUNCTIONALITY.get()) {
			var damage_type = event.getSource().type();
			event.getEntity().invulnerableTime =
				RscDamageTypeIframesManager.registry()
					.getOrDefault(damage_type, RscServerConfig.DEFAULT_IFRAMES.get());
		}
	}
	@SubscribeEvent
	public static void reload(AddReloadListenerEvent event) {
		event.addListener(new RscDamageTypeIframesManager());
	}
	@SubscribeEvent
	public static void tooltip(ItemTooltipEvent event) {
		var stack = event.getItemStack();
		var item = stack.getItem();
		var burn_time = ForgeHooks.getBurnTime(stack, RecipeType.SMELTING);
		var compost_chance = ComposterBlock.COMPOSTABLES.getFloat(item);
		if (burn_time > 0)
			event.getToolTip().add(Component.translatable("tooltip.burn_time_" + burn_time));
		if (compost_chance > 0)
			event.getToolTip().add(Component.translatable("tooltip.compost_chance_" + compost_chance));
	}
	@SubscribeEvent
	public static void level_loaded(LevelEvent.Load event) {
		var level = event.getLevel();
		if (level == null || level.isClientSide()) return;
		var server = level.getServer();
		if (server == null) return;
		if (!server.getWorldData().overworldData().isInitialized())
			if (level instanceof ServerLevel server_level)
				if (RscCommonConfig.OVERRIDE_WORLD_STARTING_TIME.get())
					server_level.setDayTime(RscCommonConfig.WORLD_STARTING_TIME.get());
	}
}
