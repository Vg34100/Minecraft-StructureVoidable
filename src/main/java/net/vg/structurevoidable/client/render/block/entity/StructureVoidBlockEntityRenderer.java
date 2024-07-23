package net.vg.structurevoidable.client.render.block.entity;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.vg.structurevoidable.block.entity.StructureVoidBlockEntity;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.block.Blocks;
import net.vg.structurevoidable.config.ModConfigs;
import net.vg.structurevoidable.StructureVoidable;

import java.util.Iterator;

public class StructureVoidBlockEntityRenderer implements BlockEntityRenderer<StructureVoidBlockEntity> {

    // Constructor for the renderer
    public StructureVoidBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        StructureVoidable.LOGGER.debug("StructureVoidBlockEntityRenderer initialized.");
    }

    @Override
    public void render(StructureVoidBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        StructureVoidable.LOGGER.debug("Rendering StructureVoidBlockEntity at position: {}", entity.getPos());

        // Check if the outline is visible as per the configuration
        if (ModConfigs.OUTLINE_VISIBLE) {
            renderInvisibleBlocks(entity, vertexConsumers, matrices);
        }
    }

    private void renderInvisibleBlocks(StructureVoidBlockEntity entity, VertexConsumerProvider vertexConsumers, MatrixStack matrices) {
        BlockView blockView = entity.getWorld();
        // Get the vertex consumer for drawing lines
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getLines());

        BlockPos blockPos = entity.getPos();

        if (blockView != null) {
            BlockPos blockPos2 = blockPos;
            Iterator<BlockPos> var8 = BlockPos.iterate(blockPos2, blockPos2.add(1, 1, 1)).iterator();

            while (var8.hasNext()) {
                BlockPos blockPos3 = var8.next();
                if (blockView.getBlockState(blockPos3).isOf(Blocks.STRUCTURE_VOID)) {
                    float f = 0.0F;
                    double d, e, g, h, i, j;
                    if (ModConfigs.FULL_BLOCK_RENDER) {
                        // Full block Structure Void render
                        d = (double) ((float) (blockPos3.getX() - blockPos.getX()) + 0F - f);
                        e = (double) ((float) (blockPos3.getY() - blockPos.getY()) + 0F - f);
                        g = (double) ((float) (blockPos3.getZ() - blockPos.getZ()) + 0F - f);
                        h = (double) ((float) (blockPos3.getX() - blockPos.getX()) + 1F + f);
                        i = (double) ((float) (blockPos3.getY() - blockPos.getY()) + 1F + f);
                        j = (double) ((float) (blockPos3.getZ() - blockPos.getZ()) + 1F + f);
                    } else {
                        // Standard Structure Void render
                        d = (double) ((float) (blockPos3.getX() - blockPos.getX()) + 0.45F - f);
                        e = (double) ((float) (blockPos3.getY() - blockPos.getY()) + 0.45F - f);
                        g = (double) ((float) (blockPos3.getZ() - blockPos.getZ()) + 0.45F - f);
                        h = (double) ((float) (blockPos3.getX() - blockPos.getX()) + 0.55F + f);
                        i = (double) ((float) (blockPos3.getY() - blockPos.getY()) + 0.55F + f);
                        j = (double) ((float) (blockPos3.getZ() - blockPos.getZ()) + 0.55F + f);
                    }

                    float red, green, blue, alpha = 0F;
                    switch (ModConfigs.OUTLINE_COLOR) {
                        case "void":
                            // Set the color to blue rgb(14, 70, 78)
                            red = 0.14F;
                            green = 0.70F;
                            blue = 0.78F;
                            alpha = 1.0F;
                            break;
                        case "barrier":
                            red = 1.0F;
                            green = 0.0F;
                            blue = 0.0F;
                            alpha = 1.0F;
                            break;
                        default:
                            // The default structure void render color
                            red = 1.0F;
                            green = 0.75F;
                            blue = 0.75F;
                            alpha = 1.0F;
                            break;
                    }

                    // Log the color and position details
                    StructureVoidable.LOGGER.debug("Rendering outline at {}: Color - R:{}, G:{}, B:{}, A:{}", blockPos3, red, green, blue, alpha);

                    WorldRenderer.drawBox(matrices, vertexConsumer, d, e, g, h, i, j, red, green, blue, alpha);
                }
            }
        }
    }
}
