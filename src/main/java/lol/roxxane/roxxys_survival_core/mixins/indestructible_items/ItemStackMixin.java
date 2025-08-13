package lol.roxxane.roxxys_survival_core.mixins.indestructible_items;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import lol.roxxane.roxxys_survival_core.RscServerConfig;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemStack.class)
abstract class ItemStackMixin {
	@ModifyReturnValue(method = "isDamageableItem", at = @At("RETURN"))
	private boolean rt$isDamageableItem(boolean original) {
		var durability_removed = RscServerConfig.REMOVE_DURABILITY.get();
		if (durability_removed)
			return false;
		return original;
	}
}
