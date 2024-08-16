package net.vg.structurevoidable.client.gui.screen.option;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.client.OptionInstance;
import net.minecraft.network.chat.Component;
import net.vg.structurevoidable.config.ModConfigs;

public class ServerOptionScreen extends OptionsSubScreen {

    public ServerOptionScreen(Screen parent) {
        super(parent, Minecraft.getInstance().options, Component.translatable("config.server.title"));
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void addOptions() {
        OptionInstance<?>[] options = new OptionInstance[]{
                // Add more options here as needed
        };

        for (OptionInstance<?> option : options) {
            this.list.addBig(option);
        }
    }

    @Override
    public void onClose() {
        ModConfigs.saveConfigs();
        this.minecraft.setScreen(this.lastScreen);
    }

    private static Component getGenericValueText(Component prefix, Component value) {
        return Component.translatable("options.generic_value", prefix, value);
    }
}