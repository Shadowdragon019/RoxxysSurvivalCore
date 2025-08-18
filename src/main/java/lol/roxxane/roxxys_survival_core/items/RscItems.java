package lol.roxxane.roxxys_survival_core.items;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import static lol.roxxane.roxxys_survival_core.Rsc.REGISTRATE;

public class RscItems {
	public static final RegistryEntry<Item> IRON_PLATE =
		REGISTRATE.item("iron_plate", Item::new)
			.tab(CreativeModeTabs.INGREDIENTS)
			.defaultModel()
			.defaultLang()
			.register();
	public static void register(){}
}
