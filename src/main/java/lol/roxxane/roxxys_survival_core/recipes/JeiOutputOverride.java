package lol.roxxane.roxxys_survival_core.recipes;

import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface JeiOutputOverride {
	List<ItemStack> jei_output();
	boolean shapeless();
}