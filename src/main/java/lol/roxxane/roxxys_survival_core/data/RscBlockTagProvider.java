package lol.roxxane.roxxys_survival_core.data;

import lol.roxxane.roxxys_survival_core.Rsc;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class RscBlockTagProvider extends BlockTagsProvider {
	public RscBlockTagProvider(PackOutput output, CompletableFuture<Provider> lookup_provider,
		@Nullable ExistingFileHelper existing_file_helper
	) {
		super(output, lookup_provider, Rsc.ID, existing_file_helper);
	}
	@Override
	protected void addTags(@NotNull Provider provider) {}
}
