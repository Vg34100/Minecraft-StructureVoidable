package net.vg.structurevoidable.client.gui.screen.option;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.OptionsSubScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.vg.structurevoidable.config.ModConfigs;

public class ServerOptionScreen extends OptionsSubScreen {

    private OptionsList list;

    public ServerOptionScreen(Screen parent) {
        super(parent, Minecraft.getInstance().options, Component.translatable("config.server.title"));
    }

    @Override
    protected void init() {
        assert this.minecraft != null;

        list = new OptionsList(this.minecraft, this.width, this.height, 32, this.height - 32, 25);
        Window window = this.minecraft.getWindow();

        OptionInstance<?>[] options = new OptionInstance[]{
                // Add more options here as needed
        };

        list.addSmall(options);

        this.addWidget(list);
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, (button) -> {
            this.minecraft.options.save();
            window.changeFullscreenVideoMode();
            this.minecraft.setScreen(this.lastScreen);
        }).bounds(this.width / 2 - 100, this.height - 27, 200, 20).build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics);
        this.list.render(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 16777215);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    @Override
    public void onClose() {
        ModConfigs.saveConfigs();
        super.onClose();
    }
}