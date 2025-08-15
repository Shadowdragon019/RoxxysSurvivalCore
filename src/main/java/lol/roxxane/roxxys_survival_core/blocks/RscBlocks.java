package lol.roxxane.roxxys_survival_core.blocks;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.ConfiguredModel;

import static lol.roxxane.roxxys_survival_core.Rsc.REGISTRATE;

public class RscBlocks {
	public static final RegistryEntry<FlintBlock> FLINT =
		REGISTRATE.block("flint", p -> new FlintBlock(p))
			.blockstate((ctx, provider) ->
				provider.getVariantBuilder(ctx.getEntry()).forAllStatesExcept(
					state -> ConfiguredModel.builder()
						.modelFile(provider.models().getExistingFile(provider.modLoc("block/flint")))
						.rotationY(((int) state.getValue(
							BlockStateProperties.HORIZONTAL_FACING).toYRot() + 180) % 360
						)
						.build(),
					BlockStateProperties.WATERLOGGED
				)
			)
			.item()
			.tab(CreativeModeTabs.INGREDIENTS)
			.build()
			.register();
	public static void register(){}
}
