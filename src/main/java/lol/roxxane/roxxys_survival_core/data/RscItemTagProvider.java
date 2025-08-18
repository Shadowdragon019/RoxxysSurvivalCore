package lol.roxxane.roxxys_survival_core.data;

import lol.roxxane.roxxys_survival_core.Rsc;
import lol.roxxane.roxxys_survival_core.items.RscItems;
import lol.roxxane.roxxys_survival_core.tags.RscItemTags;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class RscItemTagProvider extends ItemTagsProvider {
	public RscItemTagProvider(PackOutput output, CompletableFuture<Provider> provider,
		CompletableFuture<TagLookup<Block>> block_tags, @Nullable ExistingFileHelper existing_file_helper
	) {
		super(output, provider, block_tags, Rsc.ID, existing_file_helper);
	}
	@SuppressWarnings("unchecked")
	@Override
	protected void addTags(@NotNull Provider provider) {
		tag(RscItemTags.IRON_PLATES).add(RscItems.IRON_PLATE.get());
		tag(RscItemTags.PLATES).addTags(RscItemTags.IRON_PLATES);
	}
}
