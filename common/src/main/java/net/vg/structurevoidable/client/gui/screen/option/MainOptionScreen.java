package net.vg.structurevoidable.client.gui.screen.option;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.platform.Window;
import com.mojang.serialization.Codec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.OptionsSubScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.vg.structurevoidable.Constants;
import net.vg.structurevoidable.config.ModConfigs;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MainOptionScreen extends OptionsSubScreen {
    private static final Component TITLE_TEXT = Component.translatable("config.general.title");
    private static final Component SERVER_SETTINGS_TEXT = Component.translatable("config.server.title");
    private static final Component CLIENT_SETTINGS_TEXT = Component.translatable("config.client.title");

    private boolean changeServer = false;
    private boolean changeClient = false;

    private  OptionsList list;

    public MainOptionScreen(Screen parent) {
        super(parent, Minecraft.getInstance().options, TITLE_TEXT);
        Constants.LOGGER.debug("Initializing MainConfigScreen with title: {}", TITLE_TEXT.getString());
    }

    @Override
    protected void init() {
        assert this.minecraft != null;
        this.list = new OptionsList(this.minecraft, this.width, this.height, 32, this.height - 32, 25);
        Window window = this.minecraft.getWindow();


        Constants.LOGGER.debug("Adding options to MainConfigScreen");


        OptionInstance<Boolean> serverButton = OptionInstance.createBoolean(
                "config.server.title",
                OptionInstance.noTooltip(),
                changeServer,
                value -> changeServer = value
        );

        OptionInstance<Boolean> clientButton = OptionInstance.createBoolean(
                "config.client.title",
                OptionInstance.noTooltip(),
                changeClient,
                value -> changeClient = value
        );


        OptionInstance<?>[] options = new OptionInstance[]{
                serverButton,
                clientButton
                // Add more options here as needed
        };

        list.addSmall(options);

        this.addWidget(list);
        this.addRenderableWidget(Button.builder(CommonComponents.GUI_DONE, (button) -> {
            this.minecraft.options.save();
            window.changeFullscreenVideoMode();
            this.minecraft.setScreen(this.lastScreen);
        }).bounds(this.width / 2 - 100, this.height - 27, 200, 20).build());

//        Constants.LOGGER.debug("Added {} clickable widgets", clickableWidgets.size());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        assert this.minecraft != null;
        this.renderBackground(guiGraphics);
        this.list.render(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.drawCenteredString(this.font, this.title, this.width / 2, 20, 16777215);
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        if (changeServer) {
            this.minecraft.setScreen(new ServerOptionScreen(this));
            changeServer = false;
        } else if (changeClient) {
            this.minecraft.setScreen(new ClientOptionScreen(this));
            changeClient = false;

        }
    }
}
