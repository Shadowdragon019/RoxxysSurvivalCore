package lol.roxxane.roxxys_survival_core.utils;

import com.google.gson.JsonElement;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Function;

public class JsonUtil {
	public static <T> T get(JsonElement json, String key, Function<JsonElement, T> transformer) {
		return transformer.apply(json.getAsJsonObject().get(key));
	}
	@SuppressWarnings("DataFlowIssue")
	public static <T> T get_from_registry(JsonElement json, String key, IForgeRegistry<T> registry) {
		return registry.getValue(Id.parse(json.getAsJsonObject().get(key).getAsString()));
	}
	public static Item get_item(JsonElement json, String key) {
		return get_from_registry(json, key, ForgeRegistries.ITEMS);
	}
}
