package net.vg.structurevoidable.config;

import com.mojang.datafixers.util.Pair;
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




    public static String[] necessaryConfigs = {
        "config.barrier.behavior", // If the structure void should have the same behavior as the barrier for rendering
        "config.outline.always.visible", // If the outline version of the structure void should always be visible
        "config.full.block.outline", // If the breakable and placeable outline should be a full block
        "config.full.block.render", // If the visible outline render should be a full block
        "config.outline.color" // The color of the outline render
    };

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(StructureVoidable.MOD_ID + "config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("config.barrier.behavior", true), Arrays.asList(
                "If the structure void should have the same behavior as the barrier for rendering",
                "Default: True"
        ));
        configs.addKeyValuePair(new Pair<>("config.outline.always.visible", false), Arrays.asList(
                "If the outline version of the structure void should always be visible",
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

    }

    private static void assignConfigs() {
        // Client Settings
        // Boolean
        BARRIER_BEHAVIOR = CONFIG.getOrDefault("config.barrier.behavior", true);
        OUTLINE_VISIBLE = CONFIG.getOrDefault("config.outline.always.visible", false);
        FULL_BLOCK_OUTLINE = CONFIG.getOrDefault("config.full.block.outline", true);
        FULL_BLOCK_RENDER = CONFIG.getOrDefault("config.full.block.render", false);

        // Integer or Double

        // Double

        // String
        OUTLINE_COLOR = CONFIG.getOrDefault("config.outline.color", "default");

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }

    public static void saveConfigs() {
        CONFIG.set("config.barrier.behavior", BARRIER_BEHAVIOR);
        CONFIG.set("config.outline.always.visible", OUTLINE_VISIBLE);
        CONFIG.set("config.full.block.outline", FULL_BLOCK_OUTLINE);
        CONFIG.set("config.full.block.render", FULL_BLOCK_RENDER);
        CONFIG.set("config.outline.color", OUTLINE_COLOR);



        CONFIG.save();
    }
}