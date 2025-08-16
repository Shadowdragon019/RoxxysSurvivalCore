package lol.roxxane.roxxys_survival_core.mixins.block_family_ratio_override;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import snownee.kiwi.customization.block.family.BlockFamilies;

@Mixin(BlockFamilies.class)
abstract class BlockFamiliesMixin {
	@ModifyReturnValue(method = "getConvertRatio", remap = false, at = @At("RETURN"))
	private static float flatten_return_ratio(float original) {
		return 1;
	}
}
