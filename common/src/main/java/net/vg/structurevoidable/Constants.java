package net.vg.structurevoidable;

import dev.architectury.platform.Platform;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class Constants {
    public static final String MOD_ID = "structurevoidable";
    public static final String MOD_NAME = "Structure Voidable";
    public static final String MOD_VERSION = fetchModVersion();


    public static final Logger LOGGER = LoggerFactory.getLogger("structurevoidable");


    public static String fetchModVersion() {
        String DEFAULT_VERSION = "1.0.0";

        if (Platform.isModLoaded(MOD_ID)) {
            return Platform.getMod(MOD_ID).getVersion();
        } else {
            return DEFAULT_VERSION;
        }
    }
}
