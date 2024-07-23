package net.vg.structurevoidable.client.gui.screen.option;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import net.vg.structurevoidable.config.ModConfigs;

public class ClientOptionScreen extends GameOptionsScreen {

    public ClientOptionScreen(Screen parent) {
        super(parent, MinecraftClient.getInstance().options, Text.translatable("config.client.title"));
    }

    @Override
    protected void addOptions() {
        // Define client-specific options here
        SimpleOption<Boolean> barrierBehavior = SimpleOption.ofBoolean(
                "config.barrier.behavior",
                SimpleOption.constantTooltip(Text.translatable("tooltip.barrier.behavior")),
                ModConfigs.BARRIER_BEHAVIOR,
                value -> ModConfigs.BARRIER_BEHAVIOR = value
        );

        SimpleOption<Boolean> outlineVisible = SimpleOption.ofBoolean(
                "config.outline.always.visible",
                SimpleOption.constantTooltip(Text.translatable("tooltip.outline.always.visible")),
                ModConfigs.OUTLINE_VISIBLE,
                value -> ModConfigs.OUTLINE_VISIBLE = value
        );

        SimpleOption<Boolean> fullBlockOutline = SimpleOption.ofBoolean(
                "config.full.block.outline",
                SimpleOption.constantTooltip(Text.translatable("tooltip.full.block.outline")),
                ModConfigs.FULL_BLOCK_OUTLINE,
                value -> ModConfigs.FULL_BLOCK_OUTLINE = value
        );

        SimpleOption<Boolean> fullBlockRender = SimpleOption.ofBoolean(
                "config.full.block.render",
                SimpleOption.constantTooltip(Text.translatable("tooltip.full.block.render")),
                ModConfigs.FULL_BLOCK_RENDER,
                value -> ModConfigs.FULL_BLOCK_RENDER = value
        );

        SimpleOption<String> outlineColorOption = new SimpleOption<>(
                "config.outline.color",
                SimpleOption.constantTooltip(Text.translatable("tooltip.outline.color")),
                (optionText, value) -> Text.translatable("outline.color." + value),
                new SimpleOption.PotentialValuesBasedCallbacks<>(ImmutableList.of("default", "void", "barrier"), Codec.STRING),
                ModConfigs.OUTLINE_COLOR,
                value -> ModConfigs.OUTLINE_COLOR = value
        );


        SimpleOption<?>[] options = new SimpleOption[]{
                barrierBehavior,
                outlineVisible,
                fullBlockOutline,
                fullBlockRender,
                outlineColorOption
                // Add more options here as needed
        };

        this.body.addAll(options);
    }
    @Override
    public void close() {
        ModConfigs.saveConfigs();
        this.client.setScreen(this.parent);
    }

    private static Text getGenericValueText(Text prefix, Text value) {
        return Text.translatable("options.generic_value", prefix, value);
    }
}
