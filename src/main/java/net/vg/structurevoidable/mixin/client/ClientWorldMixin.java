package net.vg.structurevoidable.mixin.client;

import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.Blocks;
import net.minecraft.world.GameMode;
import net.vg.structurevoidable.StructureVoidable;
import net.vg.structurevoidable.config.ModConfigs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {

    /**
     * Injects into the getBlockParticle method to potentially modify the Structure Void behavior to match that of Barriers and Lights.
     *
     * @param cir CallbackInfoReturnable to modify the return value.
     */
    @Inject(method = "getBlockParticle", at = @At("HEAD"), cancellable = true)
    private void injectGetBlockParticle(CallbackInfoReturnable<Block> cir) {
        StructureVoidable.LOGGER.debug("Injecting into getBlockParticle method");

        // Check if the barrier behavior is enabled in the configuration
        if (ModConfigs.BARRIER_BEHAVIOR) {
            MinecraftClient client = MinecraftClient.getInstance();

            // Check if the player is in creative mode
            if (client.interactionManager.getCurrentGameMode() == GameMode.CREATIVE) {
                ItemStack itemStack = client.player.getMainHandStack();
                Item item = itemStack.getItem();

                // Check if the item in the player's main hand is a BlockItem
                if (item instanceof BlockItem) {
                    Block block = ((BlockItem) item).getBlock();

                    // If the block is STRUCTURE_VOID, set the return value to this block
                    if (block == Blocks.STRUCTURE_VOID) {
                        StructureVoidable.LOGGER.debug("Player is holding STRUCTURE_VOID in creative mode, displaying Structure Voids");
                        cir.setReturnValue(block);
                    }
                }
            }
        }
    }
}
