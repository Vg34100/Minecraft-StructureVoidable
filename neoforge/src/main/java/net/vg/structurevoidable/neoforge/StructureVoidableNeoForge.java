package net.vg.structurevoidable.neoforge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.vg.structurevoidable.Constants;
import net.vg.structurevoidable.StructureVoidable;
import net.vg.structurevoidable.StructureVoidableClient;
import net.vg.structurevoidable.client.gui.screen.option.MainOptionScreen;
import org.jetbrains.annotations.NotNull;

@Mod(Constants.MOD_ID)
public final class StructureVoidableNeoForge {
    public StructureVoidableNeoForge(IEventBus modEventBus) {
        // Run our common setup
        StructureVoidable.init();

        // Register the client setup event
        if (FMLEnvironment.dist == Dist.CLIENT) {
            modEventBus.addListener(this::clientSetup);
        }

        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> new IConfigScreenFactory() {
            @Override
            public @NotNull Screen createScreen(@NotNull Minecraft arg, @NotNull Screen arg2) {
                return new MainOptionScreen(arg2);
            }

        });

    }

    private void clientSetup(final FMLClientSetupEvent event) {
        // This is called for client-side initialization
        event.enqueueWork(StructureVoidableClient::initializeClient);
    }
}