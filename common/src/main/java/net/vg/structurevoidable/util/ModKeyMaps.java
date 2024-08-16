package net.vg.structurevoidable.util;

import com.mojang.blaze3d.platform.InputConstants;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import net.minecraft.client.KeyMapping;
import net.vg.structurevoidable.config.ModConfigs;

public class ModKeyMaps {
    public static final KeyMapping CUSTOM_KEYMAPPING = new KeyMapping(
            "key.toggle_outline_visible", // The translation key of the name shown in the Controls screen
            InputConstants.Type.KEYSYM, // This key mapping is for Keyboards by default
            InputConstants.KEY_O, // The default keycode
            "category.structurevoidable" // The category translation key used to categorize in the Controls screen
    );



    public static void register() {
        KeyMappingRegistry.register(CUSTOM_KEYMAPPING);
        ClientTickEvent.CLIENT_POST.register(minecraft -> {
            while (CUSTOM_KEYMAPPING.consumeClick()) {
                // Do action here
                ModConfigs.OUTLINE_VISIBLE = !ModConfigs.OUTLINE_VISIBLE;
                ModConfigs.saveConfigs();
            }
        });
    }
}
