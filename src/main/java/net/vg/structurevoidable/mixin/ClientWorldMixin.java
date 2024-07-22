package net.vg.structurevoidable.mixin;

import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.Blocks;
import net.minecraft.world.GameMode;
import net.vg.structurevoidable.config.ModConfigs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {


    @Inject(method = "getBlockParticle", at = @At("HEAD"), cancellable = true)
    private void injectGetBlockParticle(CallbackInfoReturnable<Block> cir) {
        if (ModConfigs.BARRIER_BEHAVIOR) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.interactionManager.getCurrentGameMode() == GameMode.CREATIVE) {
                ItemStack itemStack = client.player.getMainHandStack();
                Item item = itemStack.getItem();
                if (item instanceof BlockItem) {
                    Block block = ((BlockItem) item).getBlock();
                    if (block == Blocks.STRUCTURE_VOID) {
                        cir.setReturnValue(block);
                    }
                }

            }
        }
    }

}