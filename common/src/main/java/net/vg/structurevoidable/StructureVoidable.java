package net.vg.structurevoidable;

import net.vg.structurevoidable.block.entity.ModBlockEntities;
import net.vg.structurevoidable.config.ModConfigs;
import net.vg.structurevoidable.util.ModKeyMaps;

public final class StructureVoidable {

    public static void init() {
        // Log the initialization message with mod name and version
        Constants.LOGGER.info("Initialized Mod: {} v{}", Constants.MOD_NAME, Constants.MOD_VERSION);

        // Write common init code here.
        Constants.LOGGER.info("Registering configurations...");
        ModConfigs.registerConfigs();

        // Register block entities
        Constants.LOGGER.info("Registering block entities...");
        ModBlockEntities.register();

        // Key Maps
        Constants.LOGGER.info("Registering keybindings...");
        ModKeyMaps.register();
    }
}