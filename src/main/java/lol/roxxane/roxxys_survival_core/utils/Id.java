package lol.roxxane.roxxys_survival_core.utils;

import lol.roxxane.roxxys_survival_core.Rsc;
import net.minecraft.resources.ResourceLocation;

public class Id {
	public static ResourceLocation of(String namespace, String path) {
		return ResourceLocation.fromNamespaceAndPath(namespace, path);
	}
	public static ResourceLocation rsc(String path) {
		return ResourceLocation.fromNamespaceAndPath(Rsc.ID, path);
	}
	public static ResourceLocation mc(String path) {
		return ResourceLocation.fromNamespaceAndPath("minecraft", path);
	}
	public static ResourceLocation forge(String path) {
		return ResourceLocation.fromNamespaceAndPath("forge", path);
	}
	public static ResourceLocation parse(String id) {
		return ResourceLocation.parse(id);
	}
	public static String stringify(ResourceLocation id) {
		if (id.getNamespace().equals("minecraft")) {
			return id.getPath();
		} else return id.toString();
	}
}
