package net.vg.structurevoidable.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.minecraftforge.fml.loading.FMLEnvironment;
import net.vg.structurevoidable.Constants;
import net.vg.structurevoidable.StructureVoidable;
import net.vg.structurevoidable.StructureVoidableClient;
import net.vg.structurevoidable.client.gui.screen.option.ClientOptionScreen;
import net.vg.structurevoidable.client.gui.screen.option.MainOptionScreen;

@Mod(Constants.MOD_ID)
public final class StructureVoidableForge {
    public StructureVoidableForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(Constants.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        StructureVoidable.init();

        // Register the client setup event
        if (FMLEnvironment.dist == Dist.CLIENT) {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        }

        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((minecraft, screen) -> new MainOptionScreen(screen)));
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        // This is called for client-side initialization
        event.enqueueWork(StructureVoidableClient::initializeClient);
    }
}
