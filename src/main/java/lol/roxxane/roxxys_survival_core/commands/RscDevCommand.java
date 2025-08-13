package lol.roxxane.roxxys_survival_core.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import lol.roxxane.roxxys_survival_core.Rsc;
import lol.roxxane.roxxys_survival_core.utils.DamageTypeUtil;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.tags.DamageTypeTags;

public class RscDevCommand {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher,
		CommandBuildContext build_context
	) {
		var builder = Commands.literal("rsc_dev");
		damages_types_that_bypass_cooldown(builder);
		dispatcher.register(builder);
	}
	@SuppressWarnings("DataFlowIssue")
	private static void damages_types_that_bypass_cooldown(
		LiteralArgumentBuilder<CommandSourceStack> builder
	) {
		builder.then(Commands.literal("print_damages_types_that_bypass_iframes")
			.executes(context -> {
				final var registry = DamageTypeUtil.registry(context.getSource().getLevel());
				final var string_builder = new StringBuilder();
				registry.getTag(DamageTypeTags.BYPASSES_COOLDOWN).ifPresent(damage_types_holder -> {
					for (var damage_type_holder : damage_types_holder)
						string_builder
							.append('"')
							.append(registry.getKey(damage_type_holder.get()).toString())
							.append("\", ");
				});
				if (string_builder.isEmpty())
					Rsc.log("No damage types bypass iframes");
				else {
					string_builder.delete(string_builder.length() - 2, string_builder.length() - 1);
					Rsc.log("Damage types that bypass iframes are:", string_builder);
				}
				return 0;
			}));
	}
}