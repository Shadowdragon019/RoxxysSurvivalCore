package lol.roxxane.roxxys_survival_core.utils;

import lol.roxxane.roxxys_survival_core.Rsc;
import net.minecraft.resources.ResourceLocation;

public class Id {
	public static ResourceLocation of(String namespace, String path) {
		return ResourceLocation.fromNamespaceAndPath(namespace, path);
	}
	public static ResourceLocation mc(String path) {
		return ResourceLocation.fromNamespaceAndPath("minecraft", path);
	}
	public static ResourceLocation rsc(String path) {
		return ResourceLocation.fromNamespaceAndPath(Rsc.ID, path);
	}
	public static ResourceLocation parse(String id) {
		return ResourceLocation.parse(id);
	}
}
