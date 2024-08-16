package net.vg.structurevoidable.config;

import com.mojang.datafixers.util.Pair;
import net.vg.structurevoidable.Constants;
import net.vg.structurevoidable.StructureVoidable;

import java.util.Arrays;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;
    public static boolean BARRIER_BEHAVIOR;
    public static boolean OUTLINE_VISIBLE;
    public static boolean FULL_BLOCK_OUTLINE;
    public static boolean FULL_BLOCK_RENDER;
    public static String OUTLINE_COLOR;

    public static boolean DISPLAY_BLOCK;
    public static String BLOCK_TYPE;

    public static String[] necessaryConfigs = {
        "config.barrier.behavior", // If the structure void should have the same behavior as the barrier for rendering
        "config.outline.always.visible", // If the outline version of the structure void should always be visible
        "config.full.block.outline", // If the breakable and placeable outline should be a full block
        "config.full.block.render", // If the visible outline render should be a full block
        "config.outline.color", // The color of the outline render

        "config.display.block", // If the structure void should display as a block
        "config.block.type", // The block it should display as
    };

    public static void registerConfigs() {
        Constants.LOGGER.info("Registering mod configurations.");
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(Constants.MOD_ID + "config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        Constants.LOGGER.debug("Creating default configuration values.");
        configs.addKeyValuePair(new Pair<>("config.barrier.behavior", true), Arrays.asList(
                "If the structure void should have the same behavior as the barrier for rendering",
                "Default: True"
        ));
        configs.addKeyValuePair(new Pair<>("config.outline.always.visible", false), Arrays.asList(
                "If the outline version of the structure void should always be visible",
                "Caution: May cause lag",
                "Default: False"
        ));
        configs.addKeyValuePair(new Pair<>("config.full.block.outline", true), Arrays.asList(
                "If the breakable and placeable outline should be a full block",
                "Default: True"
        ));
        configs.addKeyValuePair(new Pair<>("config.full.block.render", false), Arrays.asList(
                "If the visible outline render should be a full block",
                "Default: False"
        ));
        configs.addKeyValuePair(new Pair<>("config.outline.color", "default"), Arrays.asList(
                "The color of the outline render",
                "Options include: default, void, barrier",
                "Default: default"
        ));

        configs.addKeyValuePair(new Pair<>("config.display.block", false), Arrays.asList(
                "If the structure void block should display as a Minecraft block",
                "Options include: stone, deepslate, dirt, netherrack, and endstone",
                "Default: stone"
        ));

    }

    private static void assignConfigs() {
        Constants.LOGGER.info("Assigning configuration values.");
        // Assign configuration values to fields
        BARRIER_BEHAVIOR = CONFIG.getOrDefault("config.barrier.behavior", true);
        OUTLINE_VISIBLE = CONFIG.getOrDefault("config.outline.always.visible", false);
        FULL_BLOCK_OUTLINE = CONFIG.getOrDefault("config.full.block.outline", true);
        FULL_BLOCK_RENDER = CONFIG.getOrDefault("config.full.block.render", false);
        OUTLINE_COLOR = CONFIG.getOrDefault("config.outline.color", "default");

        DISPLAY_BLOCK = CONFIG.getOrDefault("config.display.block", false);
        BLOCK_TYPE = CONFIG.getOrDefault("config.block.type", "stone");

        Constants.LOGGER.info("All {} configurations have been set properly.", configs.getConfigsList().size());
    }

    public static void saveConfigs() {
        Constants.LOGGER.info("Saving current configuration values.");
        CONFIG.set("config.barrier.behavior", BARRIER_BEHAVIOR);
        CONFIG.set("config.outline.always.visible", OUTLINE_VISIBLE);
        CONFIG.set("config.full.block.outline", FULL_BLOCK_OUTLINE);
        CONFIG.set("config.full.block.render", FULL_BLOCK_RENDER);
        CONFIG.set("config.outline.color", OUTLINE_COLOR);

        CONFIG.set("config.display.block", DISPLAY_BLOCK);
        CONFIG.set("config.block.type", BLOCK_TYPE);

        CONFIG.save();
        Constants.LOGGER.info("Configuration values saved successfully.");
    }
}