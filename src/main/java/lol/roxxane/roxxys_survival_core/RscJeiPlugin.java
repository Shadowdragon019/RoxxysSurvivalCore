package lol.roxxane.roxxys_survival_core;

import lol.roxxane.roxxys_survival_core.utils.Id;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class RscJeiPlugin implements IModPlugin {
	@Override
	public @NotNull ResourceLocation getPluginUid() {
		return Id.rsc(Rsc.ID);
	}
	@SuppressWarnings("DataFlowIssue")
	@Override
	public void registerRecipes(@NotNull IRecipeRegistration registration) {
		var stacks = ForgeRegistries.ITEMS.tags().getTag(RscItemTags.HIDE_FROM_JEI)
			.stream().map(Item::getDefaultInstance).toList();
		if (!stacks.isEmpty() && RscClientConfig.HIDE_ITEMS_IN_JEI.get())
			registration.getIngredientManager().removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK,
				stacks);
	}
}
