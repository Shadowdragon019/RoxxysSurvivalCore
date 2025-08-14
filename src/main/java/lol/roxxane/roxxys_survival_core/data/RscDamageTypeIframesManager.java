package lol.roxxane.roxxys_survival_core.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import lol.roxxane.roxxys_survival_core.utils.DamageTypeUtil;
import lol.roxxane.roxxys_survival_core.utils.Id;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.damagesource.DamageType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class RscDamageTypeIframesManager extends SimpleJsonResourceReloadListener {
	private static final Gson GSON = new GsonBuilder().create();
	private static final Map<ResourceLocation, Integer> DAMAGE_TYPE_ID_MAP = new HashMap<>();
	private static final Map<DamageType, Integer> DAMAGE_TYPE_IFRAMES_MAP = new HashMap<>();
	public RscDamageTypeIframesManager() {
		super(GSON, "damage_type_iframes");
	}
	@Override
	protected void apply(@NotNull Map<ResourceLocation, JsonElement> map, @NotNull ResourceManager $,
		@NotNull ProfilerFiller $1
	) {
		DAMAGE_TYPE_ID_MAP.clear();
		map.forEach((location, json) -> {
			var object = json.getAsJsonObject();
			DAMAGE_TYPE_ID_MAP.put(
				Id.parse(object.get("damage_type").getAsString()),
				object.get("iframes").getAsInt()
			);
		});
	}
	public static Map<DamageType, Integer> registry() {
		if (DAMAGE_TYPE_IFRAMES_MAP.isEmpty())
			DAMAGE_TYPE_ID_MAP.forEach((type, iframes) ->
				DAMAGE_TYPE_IFRAMES_MAP.put(DamageTypeUtil.get(Minecraft.getInstance().level, type), iframes));
		return DAMAGE_TYPE_IFRAMES_MAP;
	}
}
