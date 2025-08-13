package lol.roxxane.roxxys_survival_core.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import lol.roxxane.roxxys_survival_core.utils.DamageTypeUtil;
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
	private static final Map<DamageType, Integer> DAMAGE_TYPE_IFRAMES_MAP = new HashMap<>();
	public RscDamageTypeIframesManager() {
		super(GSON, "damage_type_iframes");
	}
	@Override
	protected void apply(@NotNull Map<ResourceLocation, JsonElement> map, @NotNull ResourceManager resource_manager,
		@NotNull ProfilerFiller profiler
	) {
		// huh
	}
	public static Map<DamageType, Integer> damage_type_iframes_map() {
		if (DAMAGE_TYPE_IFRAMES_MAP.isEmpty())
			for (var entry : RscDamageTypeIframesProvider.ID_FRAME_MAP.entrySet())
				DAMAGE_TYPE_IFRAMES_MAP.put(
					DamageTypeUtil.get(Minecraft.getInstance().level, entry.getValue().a), entry.getValue().b);
		return DAMAGE_TYPE_IFRAMES_MAP;
	}
}
