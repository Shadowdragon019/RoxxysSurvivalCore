package lol.roxxane.roxxys_survival_core.menus;

import lol.roxxane.roxxys_survival_core.utils.Id;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.MenuType.MenuSupplier;
import net.minecraftforge.registries.ForgeRegistries;

public class RscMenuTypes {
	public static final MenuType<RscSmithingMenu> SMITHING =
		register("smithing", RscSmithingMenu::new);
	private static <T extends AbstractContainerMenu> MenuType<T> register(String path, MenuSupplier<T> factory) {
		var menu = new MenuType<>(factory, FeatureFlags.DEFAULT_FLAGS);
		ForgeRegistries.MENU_TYPES.register(Id.rsc(path), menu);
		return menu;
	}
	public static void register(){}
}
