package net.vg.structurevoidable.mixin.client.option;

import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.vg.structurevoidable.StructureVoidable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameOptions.class)
public class GameOptionsMixin {

    // Shadowing the operatorItemsTab field to allow modification
    @Shadow
    @Final
    @Mutable
    private SimpleOption<Boolean> operatorItemsTab;

    /**
     * Inject into the constructor to modify the default value of operatorItemsTab.
     * Allowing the operator items tab to be on by default
     *
     * @param ci CallbackInfo for the constructor.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void modifyOperatorItemsTab(CallbackInfo ci) {
        StructureVoidable.LOGGER.debug("Modifying operatorItemsTab default value to true");
        this.operatorItemsTab = SimpleOption.ofBoolean("options.operatorItemsTab", true);
    }
}
