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
	// Logger used to write text to the console and log file.
	// Best practice is to use your mod ID as the logger's name.
	public static final Logger LOGGER = LoggerFactory.getLogger("structurevoidable");

	// Constants for mod information.
	public static final String MOD_ID = "structurevoidable";
	public static final String MOD_NAME = "Structure Voidable";
	public static final String MOD_VERSION = fetchModVersion();

	/**
	 * This method is called when Minecraft is ready to load mods.
	 * It initializes the mod by registering configurations and block entities.
	 */
	@Override
	public void onInitialize() {
		// Register configurations
		LOGGER.info("Registering configurations...");
		ModConfigs.registerConfigs();

		// Register block entities
		LOGGER.info("Registering block entities...");
		ModBlockEntities.registerBlockEntities();

		// Log the initialization message with mod name and version
		LOGGER.info("Initialized Mod: {} v{}", MOD_NAME, MOD_VERSION);
	}

	/**
	 * Fetches the mod version from the mod metadata.
	 *
	 * @return The version of the mod as a String.
	 */
	private static String fetchModVersion() {
		// Attempt to get the mod container from the FabricLoader
		Optional<ModContainer> modContainer = FabricLoader.getInstance().getModContainer(MOD_ID);

		// If the mod container is found, return the version from the metadata.
		// Otherwise, return a default version "1.0.0".
		return modContainer.map(container -> container.getMetadata().getVersion().getFriendlyString()).orElse("1.0.0");
	}
}
