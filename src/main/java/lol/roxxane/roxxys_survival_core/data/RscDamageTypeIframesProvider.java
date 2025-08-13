package lol.roxxane.roxxys_survival_core.data;

import com.google.gson.JsonObject;
import lol.roxxane.roxxys_survival_core.utils.DamageTypeUtil;
import lol.roxxane.roxxys_survival_core.utils.Id;
import lol.roxxane.roxxys_survival_core.utils.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class RscDamageTypeIframesProvider implements DataProvider {
	private final PackOutput.PathProvider path_provider;
	private static final Map<ResourceLocation, Pair<ResourceLocation, Integer>> ID_FRAME_MAP = new HashMap<>();
	private static final Map<DamageType, Integer> DAMAGE_TYPE_IFRAMES_MAP = new HashMap<>();
	public RscDamageTypeIframesProvider(PackOutput output) {
		path_provider = output.createPathProvider(PackOutput.Target.DATA_PACK, getName());
	}
	public static Map<DamageType, Integer> damage_type_iframes_map() {
		if (DAMAGE_TYPE_IFRAMES_MAP.isEmpty())
			for (var entry : ID_FRAME_MAP.entrySet())
				DAMAGE_TYPE_IFRAMES_MAP.put(
					DamageTypeUtil.get(Minecraft.getInstance().level, entry.getValue().a), entry.getValue().b);
		return DAMAGE_TYPE_IFRAMES_MAP;
	}
	@Override
	public @NotNull CompletableFuture<?> run(@NotNull CachedOutput output) {
		ID_FRAME_MAP.clear();
		apply();
		var futures = new ArrayList<CompletableFuture<?>>();
		ID_FRAME_MAP.forEach((path, pair) -> {
			var object = new JsonObject();
			var damage_type = pair.a;
			var iframes = pair.b;
			object.addProperty("damage_type", damage_type.toString());
			object.addProperty("iframes", iframes);
			futures.add(DataProvider.saveStable(output, object, path_provider.json(path)));
		});
		return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
	}
	public void apply() {
		put_iframes(Id.rsc("out_of_world"), Id.mc("out_of_world"), 0);
	}
	public void put_iframes(ResourceLocation path, ResourceLocation damage_type, int iframes) {
		ID_FRAME_MAP.put(path, Pair.of(damage_type, iframes));
	}
	@Override
	public @NotNull String getName() {
		return "damage_type_iframes";
	}
}
