package lol.roxxane.roxxys_survival_core.data;

import com.google.gson.JsonElement;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public abstract class SimpleProvider<K, V> implements DataProvider {
	public final String name;
	private final PackOutput.PathProvider path_provider;
	protected final HashMap<K, V> MAP = new HashMap<>();
	public SimpleProvider(String name, PackOutput output) {
		this.name = name;
		path_provider = output.createPathProvider(PackOutput.Target.DATA_PACK, getName());
	}
	@Override
	public @NotNull CompletableFuture<?> run(@NotNull CachedOutput output) {
		MAP.clear();
		fill();
		var futures = new ArrayList<CompletableFuture<?>>();
		MAP.forEach((key, value) ->
			futures.add(DataProvider.saveStable(output, entry_to_data(key, value),
				path_provider.json(entry_to_path(key, value)))));
		return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
	}
	abstract void fill();
	abstract public @NotNull JsonElement entry_to_data(K key, V value);
	abstract public @NotNull ResourceLocation entry_to_path(K key, V value);
	public void put(K key, V value) {
		MAP.put(key, value);
	}
	@Override
	public @NotNull String getName() {
		return name;
	}
}
