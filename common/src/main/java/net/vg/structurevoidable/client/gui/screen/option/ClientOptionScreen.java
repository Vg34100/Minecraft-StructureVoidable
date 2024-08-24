package net.vg.structurevoidable.client.gui.screen.option;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.platform.Window;
import com.mojang.serialization.Codec;
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

public class ClientOptionScreen extends OptionsSubScreen {

    private OptionsList list;

    public ClientOptionScreen(Screen parent) {
        super(parent, Minecraft.getInstance().options, Component.translatable("config.client.title"));
    }


    @Override
    protected void init() {
        assert this.minecraft != null;

        list = new OptionsList(this.minecraft, this.width, this.height, 32, this.height - 32, 25);
        Window window = this.minecraft.getWindow();

        // Define client-specific options here
        OptionInstance<Boolean> barrierBehavior = OptionInstance.createBoolean(
                "config.barrier.behavior",
                OptionInstance.cachedConstantTooltip(Component.translatable("tooltip.barrier.behavior")),
                ModConfigs.BARRIER_BEHAVIOR,
                value -> ModConfigs.BARRIER_BEHAVIOR = value
        );

        OptionInstance<Boolean> outlineVisible = OptionInstance.createBoolean(
                "config.outline.always.visible",
                OptionInstance.cachedConstantTooltip(Component.translatable("tooltip.outline.always.visible")),
                ModConfigs.OUTLINE_VISIBLE,
                value -> ModConfigs.OUTLINE_VISIBLE = value
        );

        OptionInstance<Boolean> fullBlockOutline = OptionInstance.createBoolean( // old
                "config.full.block.outline",
                OptionInstance.cachedConstantTooltip(Component.translatable("tooltip.full.block.outline")),
                ModConfigs.FULL_BLOCK_OUTLINE,
                value -> ModConfigs.FULL_BLOCK_OUTLINE = value
        );

        OptionInstance<String> outlineSize = new OptionInstance<>(
                "config.outline.size",
                OptionInstance.cachedConstantTooltip(Component.translatable("tooltip.outline.size")),
                (optionText, value) -> Component.translatable("outline.size." + value),
                new OptionInstance.Enum<>(
                        ImmutableList.of("none", "small", "medium", "large"),
                        Codec.STRING
                ),
                ModConfigs.OUTLINE_SIZE,
                value -> ModConfigs.OUTLINE_SIZE = value
        );


        OptionInstance<Boolean> fullBlockRender = OptionInstance.createBoolean(
                "config.full.block.render",
                OptionInstance.cachedConstantTooltip(Component.translatable("tooltip.full.block.render")),
                ModConfigs.FULL_BLOCK_RENDER,
                value -> ModConfigs.FULL_BLOCK_RENDER = value
        );

        OptionInstance<String> outlineColorOption = new OptionInstance<>(
                "config.outline.color",
                OptionInstance.cachedConstantTooltip(Component.translatable("tooltip.outline.color")),
                (optionText, value) -> Component.translatable("outline.color." + value),
                new OptionInstance.Enum<>(
                        ImmutableList.of("default", "void", "barrier"),
                        Codec.STRING
                ),
                ModConfigs.OUTLINE_COLOR,
                value -> ModConfigs.OUTLINE_COLOR = value
        );

        OptionInstance<Boolean> displayBlock = OptionInstance.createBoolean(
                "config.display.block",
                OptionInstance.cachedConstantTooltip(Component.translatable("tooltip.display.block")),
                ModConfigs.DISPLAY_BLOCK,
                value -> ModConfigs.DISPLAY_BLOCK = value
        );

        OptionInstance<String> blockType = new OptionInstance<>(
                "config.block.type",
                OptionInstance.cachedConstantTooltip(Component.translatable("tooltip.block.type")),
                (optionText, value) -> Component.translatable("block.type." + value),
                new OptionInstance.Enum<>(
                        ImmutableList.of("stone", "deepslate", "dirt", "netherrack", "endstone"),
                        Codec.STRING
                ),
                ModConfigs.BLOCK_TYPE,
                value -> ModConfigs.BLOCK_TYPE = value
        );



        OptionInstance<?>[] options = new OptionInstance[]{
                barrierBehavior,
                outlineVisible,
//                fullBlockOutline, //old

                outlineSize,

                fullBlockRender,
                outlineColorOption,
                displayBlock,
                blockType
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