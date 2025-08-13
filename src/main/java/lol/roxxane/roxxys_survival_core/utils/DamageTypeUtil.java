package lol.roxxane.roxxys_survival_core.utils;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

public class DamageTypeUtil {
	public static Registry<DamageType> registry(Level level) {
		return level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE);
	}
	public static DamageType get(Level level, ResourceLocation id) {
		return registry(level).get(id);
	}
	public static ResourceLocation get_key(Level level, DamageType type) {
		return registry(level).getKey(type);
	}
}
