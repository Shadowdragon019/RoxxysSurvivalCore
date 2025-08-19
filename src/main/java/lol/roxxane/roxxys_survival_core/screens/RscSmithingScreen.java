package lol.roxxane.roxxys_survival_core.screens;

import lol.roxxane.roxxys_survival_core.menus.RscSmithingMenu;
import lol.roxxane.roxxys_survival_core.utils.Id;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class RscSmithingScreen extends ItemCombinerScreen<RscSmithingMenu> {
	public static final ResourceLocation id = Id.rsc("textures/gui/container/smithing.png");
	public RscSmithingScreen(RscSmithingMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title, id);
		titleLabelX = 44;
		titleLabelY = 15;
	}
	@Override
	protected void renderErrorIcon(GuiGraphics graphic, int x, int y) {}
}
