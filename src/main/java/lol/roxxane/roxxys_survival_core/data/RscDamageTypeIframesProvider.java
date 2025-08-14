package lol.roxxane.roxxys_survival_core.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lol.roxxane.roxxys_survival_core.utils.Id;
import lol.roxxane.roxxys_survival_core.utils.Pair;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class RscDamageTypeIframesProvider extends SimpleProvider<ResourceLocation, Pair<ResourceLocation, Integer>> {
	public RscDamageTypeIframesProvider(PackOutput output) {
		super("damage_type_iframes", output);
	}
	@Override
	void fill() {
		put_iframes(Id.rsc("out_of_world"), Id.mc("out_of_world"), 0);
	}
	@Override
	public @NotNull JsonElement entry_to_data(ResourceLocation $, Pair<ResourceLocation, Integer> value) {
		var object = new JsonObject();
		object.addProperty("damage_type", value.a.toString());
		object.addProperty("iframes", value.b);
		return object;
	}
	@Override
	public @NotNull ResourceLocation entry_to_path(ResourceLocation id, Pair<ResourceLocation, Integer> $) {
		return id;
	}
	public void put_iframes(ResourceLocation path, ResourceLocation damage_type, int iframes) {
		put(path, Pair.of(damage_type, iframes));
	}
}
