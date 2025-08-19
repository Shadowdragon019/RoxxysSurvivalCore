package lol.roxxane.roxxys_survival_core.data;

import lol.roxxane.roxxys_survival_core.Rsc;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("FieldCanBeLocal")
@Mod.EventBusSubscriber(modid = Rsc.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RscDataGen {
	private static GatherDataEvent event;
	private static DataGenerator generator;
	private static PackOutput output;
	private static ExistingFileHelper existing_file_helper;
	private static CompletableFuture<HolderLookup.Provider> provider;
	@SubscribeEvent
	public static void gather_data(GatherDataEvent event) {
		RscDataGen.event = event;
		generator = event.getGenerator();
		output = generator.getPackOutput();
		existing_file_helper = event.getExistingFileHelper();
		provider = event.getLookupProvider();
		server_provider(new RscDamageTypeIframesProvider(output));
		server_provider(new RscItemTagProvider(output, provider,
			server_provider(new RscBlockTagProvider(output, provider, existing_file_helper)).contentsGetter(),
			existing_file_helper));
		server_provider(new RscRecipeProvider(output));
	}
	private static <T extends DataProvider> T server_provider(T provider) {
		generator.addProvider(event.includeServer(), provider);
		return provider;
	}
	@SuppressWarnings("unused")
	private static <T extends DataProvider> T add_client_provider(T provider) {
		generator.addProvider(event.includeClient(), provider);
		return provider;
	}
}