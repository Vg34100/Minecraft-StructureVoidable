package net.vg.structurevoidable;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.vg.structurevoidable.block.entity.ModBlockEntities;
import net.vg.structurevoidable.config.ModConfigs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class StructureVoidable implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("structurevoidable");
	public static final String MOD_ID = "structurevoidable";
	public static final String MOD_NAME = "Structure Voidable";

	public static final String MOD_VERSION = fetchModVersion();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModConfigs.registerConfigs();
		ModBlockEntities.registerBlockEntities();

		LOGGER.info("Initializing Mod: " + MOD_NAME);
	}

	private static String fetchModVersion() {
		Optional<ModContainer> modContainer = FabricLoader.getInstance().getModContainer(MOD_ID);
		return modContainer.map(container -> container.getMetadata().getVersion().getFriendlyString()).orElse("1.0.0");
	}
}