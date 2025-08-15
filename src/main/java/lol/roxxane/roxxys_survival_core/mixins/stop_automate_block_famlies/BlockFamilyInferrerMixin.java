package lol.roxxane.roxxys_survival_core.mixins.stop_automate_block_famlies;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import snownee.kiwi.customization.block.family.BlockFamily;
import snownee.kiwi.customization.block.family.BlockFamilyInferrer;
import snownee.kiwi.util.KHolder;

import java.util.Collection;
import java.util.List;

@Mixin(BlockFamilyInferrer.class)
abstract class BlockFamilyInferrerMixin {
	@Inject(method = "generate", cancellable = true, remap = false, at = @At("HEAD"))
	private void stop_generation(CallbackInfoReturnable<Collection<KHolder<BlockFamily>>> cir) {
		cir.setReturnValue(List.of());
	}
}
