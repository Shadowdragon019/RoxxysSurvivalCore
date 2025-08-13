package lol.roxxane.roxxys_survival_core;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.world.item.Items.*;

public class RscItemTagProvider extends ItemTagsProvider {
	public RscItemTagProvider(PackOutput output, CompletableFuture<Provider> provider,
		CompletableFuture<TagLookup<Block>> block_tags, @Nullable ExistingFileHelper existing_file_helper)
	{
		super(output, provider, block_tags, Rsc.ID, existing_file_helper);
	}
	@Override
	protected void addTags(@NotNull Provider provider) {
		tag(RscItemTags.HIDE_FROM_JEI).add(
			GOLDEN_SHOVEL, GOLDEN_PICKAXE, GOLDEN_AXE, GOLDEN_HOE, GOLDEN_SWORD, GOLDEN_HELMET, GOLDEN_CHESTPLATE,
			GOLDEN_LEGGINGS, GOLDEN_BOOTS, GOLDEN_HORSE_ARMOR, NETHERITE_BLOCK, NETHERITE_SHOVEL, NETHERITE_PICKAXE,
			NETHERITE_AXE, NETHERITE_HOE, NETHERITE_SWORD, NETHERITE_HELMET, NETHERITE_CHESTPLATE, NETHERITE_LEGGINGS,
			NETHERITE_BOOTS, NETHERITE_SCRAP, NETHERITE_INGOT, NETHERITE_UPGRADE_SMITHING_TEMPLATE
		);
	}
}
