package lol.roxxane.roxxys_survival_core.recipes;

import com.google.gson.JsonObject;
import lol.roxxane.roxxys_survival_core.utils.JsonUtil;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class RscSmithingRecipe implements Recipe<Container> {
	public final ResourceLocation id;
	public final Item base;
	public final Item material; //Can't have multiple counts for this as assemble is ran multiple times
	public final Item result;
	public RscSmithingRecipe(ResourceLocation id, Item base, Item material, Item result) {
		this.id = id;
		this.base = base;
		this.material = material;
		this.result = result;
	}
	public void save(Consumer<FinishedRecipe> writer) {
		writer.accept(new Finished(getId(), this));
	}
	@Override
	public boolean matches(Container container, Level level) {
		var input_item = container.getItem(0).getItem();
		var material_item = container.getItem(1).getItem();
		return base == input_item && material == material_item;
	}
	@Override
	public ItemStack assemble(Container container, RegistryAccess registry_access) {
		var input_tag = container.getItem(0).getTag();
		var result_stack = result.getDefaultInstance();
		if (input_tag != null)
			result_stack.setTag(input_tag);
		return result_stack;
	}
	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= 3;
	}
	@Override
	public ItemStack getResultItem(RegistryAccess registryAccess) {
		return result.getDefaultInstance();
	}
	@Override
	public ResourceLocation getId() {
		return id;
	}
	@Override
	public RecipeSerializer<?> getSerializer() {
		return RscRecipeSerializers.SMITHING;
	}
	@Override
	public RecipeType<?> getType() {
		return RscRecipeTypes.SMITHING;
	}
	public static class Serializer implements RecipeSerializer<RscSmithingRecipe> {
		@Override
		public RscSmithingRecipe fromJson(ResourceLocation id, JsonObject json) {
			return new RscSmithingRecipe(id,
				JsonUtil.get_item(json, "base"),
				JsonUtil.get_item(json, "material"),
				JsonUtil.get_item(json, "result"));
		}
		@Override
		public @Nullable RscSmithingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buffer) {
			return new RscSmithingRecipe(id,
				buffer.readRegistryId(),
				buffer.readRegistryId(),
				buffer.readRegistryId());
		}
		@Override
		public void toNetwork(FriendlyByteBuf buffer, RscSmithingRecipe recipe) {
			buffer.writeRegistryId(ForgeRegistries.ITEMS, recipe.base);
			buffer.writeRegistryId(ForgeRegistries.ITEMS, recipe.material);
			buffer.writeRegistryId(ForgeRegistries.ITEMS, recipe.result);
		}
	}
	private record Finished(ResourceLocation id, RscSmithingRecipe recipe) implements FinishedRecipe {
		@SuppressWarnings("DataFlowIssue")
		@Override
		public void serializeRecipeData(JsonObject json) {
			json.addProperty("base", ForgeRegistries.ITEMS.getKey(recipe.base).toString());
			json.addProperty("material", ForgeRegistries.ITEMS.getKey(recipe.material).toString());
			json.addProperty("result", ForgeRegistries.ITEMS.getKey(recipe.result).toString());
		}
		@Override
		public ResourceLocation getId() {
			return id;
		}
		@Override
		public RecipeSerializer<?> getType() {
			return RscRecipeSerializers.SMITHING;
		}
		@Override
		public @Nullable JsonObject serializeAdvancement() {
			return null;
		}
		@Override
		public @Nullable ResourceLocation getAdvancementId() {
			return null;
		}
	}
}
