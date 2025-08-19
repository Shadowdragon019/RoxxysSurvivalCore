package lol.roxxane.roxxys_survival_core.blocks;

import lol.roxxane.roxxys_survival_core.menus.RscSmithingMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class RscSmithingTableBlock extends CraftingTableBlock {
	private static final Component TITLE = Component.translatable("container.roxxys_survival_core.smithing_table");
	public RscSmithingTableBlock(Properties properties) {
		super(properties);
	}
	@Override
	public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
		return new SimpleMenuProvider((container_id, inventory, $) ->
			new RscSmithingMenu(container_id, inventory, ContainerLevelAccess.create(level, pos)),
			TITLE);
	}
	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
		InteractionHand $, BlockHitResult $1
	) {
		if (level.isClientSide)
			return InteractionResult.SUCCESS;
		else {
			player.openMenu(state.getMenuProvider(level, pos));
			return InteractionResult.CONSUME;
		}
	}
}
