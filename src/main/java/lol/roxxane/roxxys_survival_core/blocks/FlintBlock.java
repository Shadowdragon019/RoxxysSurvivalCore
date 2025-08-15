package lol.roxxane.roxxys_survival_core.blocks;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED;

@SuppressWarnings("deprecation")
public class FlintBlock extends Block implements SimpleWaterloggedBlock {
	public FlintBlock(Properties properties) {
		super(properties);
		registerDefaultState(getStateDefinition().any()
			.setValue(WATERLOGGED, false)
			.setValue(HORIZONTAL_FACING, Direction.EAST)
		);
	}
	@Override
	public @NotNull FluidState getFluidState(@NotNull BlockState state) {
		if (state.getValue(WATERLOGGED))
			return Fluids.WATER.getSource(false);
		else return Fluids.EMPTY.defaultFluidState();
	}
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		var level = ctx.getLevel();
		var pos = ctx.getClickedPos();
		var state = defaultBlockState();
		var direction = ctx.getClickedFace();
		var fluidState = level.getFluidState(pos);
		if (!ctx.replacingClickedOnBlock() && direction.getAxis().isHorizontal())
			state = state.setValue(HORIZONTAL_FACING, direction);
		else
			state = state.setValue(HORIZONTAL_FACING, ctx.getHorizontalDirection().getOpposite());
		state.setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
		return state;
	}
	@Override
	public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(
			WATERLOGGED,
			HORIZONTAL_FACING
		);
	}
	/*@Override
	public @NotNull VoxelShape getShape(
		BlockState state,
		@NotNull BlockGetter level,
		@NotNull BlockPos blockPos,
		@NotNull CollisionContext ctx
	) {
		return switch (state.getValue(HORIZONTAL_FACING)) {
			case EAST -> east;
			case WEST -> west;
			case SOUTH -> south;
			case NORTH -> north;
			default -> throw new IllegalStateException("Not a possible direction!");
		};
	}*/
}
