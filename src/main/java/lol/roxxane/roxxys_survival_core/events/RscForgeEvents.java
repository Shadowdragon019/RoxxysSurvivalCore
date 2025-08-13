package lol.roxxane.roxxys_survival_core.events;

import lol.roxxane.roxxys_survival_core.Rsc;
import lol.roxxane.roxxys_survival_core.commands.RscDevCommand;
import lol.roxxane.roxxys_survival_core.configs.RscServerConfig;
import lol.roxxane.roxxys_survival_core.data.RscDamageTypeIframesProvider;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
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
		Rsc.log(RscServerConfig.DEFAULT_IFRAMES.get());
		var damage_type = event.getSource().type();
		event.getEntity().invulnerableTime =
			RscDamageTypeIframesProvider.damage_type_iframes_map()
				.getOrDefault(damage_type, RscServerConfig.DEFAULT_IFRAMES.get());
	}
}
