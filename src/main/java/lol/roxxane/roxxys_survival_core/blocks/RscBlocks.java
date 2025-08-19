package lol.roxxane.roxxys_survival_core.blocks;

import com.tterrag.registrate.util.entry.RegistryEntry;
import lol.roxxane.roxxys_survival_core.utils.Id;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.client.model.generators.ConfiguredModel;

import static lol.roxxane.roxxys_survival_core.Rsc.REGISTRATE;

public class RscBlocks {
	@SuppressWarnings("unused")
	public static final RegistryEntry<FlintBlock> FLINT =
		REGISTRATE.block("flint", p ->
				new FlintBlock(p.instabreak().mapColor(MapColor.STONE)))
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
	public static final RegistryEntry<RscSmithingTableBlock> SMITHING_TABLE = REGISTRATE.block("smithing_table",
			p -> new RscSmithingTableBlock(p.mapColor(MapColor.WOOD)
				.instrument(NoteBlockInstrument.BASS).strength(2.5F)
				.sound(SoundType.WOOD)))
		.blockstate((context, provider) -> {
			provider.models().cube("smithing_table",
				Id.mc("block/smithing_table_bottom"),
					Id.mc("block/smithing_table_top"),
				Id.mc("block/smithing_table_front"),
				Id.mc("block/smithing_table_front"),
				Id.mc("block/smithing_table_side"),
				Id.mc("block/smithing_table_side"))
				.texture("particle", Id.mc("block/smithing_table_front"));
			provider.getVariantBuilder(context.get()).partialState().setModels(
				ConfiguredModel.builder()
					.modelFile(provider.models().getExistingFile(Id.mc("block/smithing_table")))
					.build());
		})
		.item()
		.tab(CreativeModeTabs.FUNCTIONAL_BLOCKS)
		.build()
		.register();
	public static void register(){}
}
