package net.vg.structurevoidable.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.vg.structurevoidable.Constants;
import net.vg.structurevoidable.block.entity.StructureVoidBlockEntity;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderType;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.vg.structurevoidable.config.ModConfigs;

public class StructureVoidBlockEntityRenderer implements BlockEntityRenderer<StructureVoidBlockEntity> {

    public StructureVoidBlockEntityRenderer() {
        Constants.LOGGER.debug("StructureVoidBlockEntityRenderer initialized.");
    }

    @Override
    public void render(StructureVoidBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Constants.LOGGER.debug("Rendering StructureVoidBlockEntity at position: {}", blockEntity.getBlockPos());
        BlockPos blockPos = blockEntity.getBlockPos();
        Vec3 cameraPos = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();

        double distanceSquared = blockPos.distToCenterSqr(cameraPos.x, cameraPos.y, cameraPos.z);

        if (distanceSquared > 128) {
            return; // Don't render if too far away
        }

        if (ModConfigs.OUTLINE_VISIBLE && !ModConfigs.DISPLAY_BLOCK) {
            renderInvisibleBlocks(blockEntity, bufferSource, poseStack);
        } else if (ModConfigs.OUTLINE_VISIBLE) {
            BlockState blockState = switch (ModConfigs.BLOCK_TYPE) {
                case "deepslate" -> Blocks.DEEPSLATE.defaultBlockState();
                case "dirt" -> Blocks.DIRT.defaultBlockState();
                case "netherrack" -> Blocks.NETHERRACK.defaultBlockState();
                case "endstone" -> Blocks.END_STONE.defaultBlockState();
                default -> Blocks.STONE.defaultBlockState();
            };
            BlockRenderDispatcher dispatcher = Minecraft.getInstance().getBlockRenderer();

            poseStack.pushPose();
            poseStack.translate(0, 0, 0);  // Adjust if needed to align with the block position

            dispatcher.renderSingleBlock(blockState, poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);

            poseStack.popPose();
        }
    }

    private void renderInvisibleBlocks(StructureVoidBlockEntity blockEntity, MultiBufferSource bufferSource, PoseStack poseStack) {
        BlockGetter level = blockEntity.getLevel();
        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.lines());

        BlockPos blockPos = blockEntity.getBlockPos();

        if (level != null) {

            for (BlockPos blockPos3 : BlockPos.betweenClosed(blockPos, blockPos.offset(1, 1, 1))) {
                if (level.getBlockState(blockPos3).is(Blocks.STRUCTURE_VOID)) {
                    float f = 0.0F;
                    double d, e, g, h, i, j;
                    if (ModConfigs.FULL_BLOCK_RENDER) {
                        d = (float) (blockPos3.getX() - blockPos.getX()) + 0F - f;
                        e = (float) (blockPos3.getY() - blockPos.getY()) + 0F - f;
                        g = (float) (blockPos3.getZ() - blockPos.getZ()) + 0F - f;
                        h = (float) (blockPos3.getX() - blockPos.getX()) + 1F + f;
                        i = (float) (blockPos3.getY() - blockPos.getY()) + 1F + f;
                        j = (float) (blockPos3.getZ() - blockPos.getZ()) + 1F + f;
                    } else {
                        d = (float) (blockPos3.getX() - blockPos.getX()) + 0.45F - f;
                        e = (float) (blockPos3.getY() - blockPos.getY()) + 0.45F - f;
                        g = (float) (blockPos3.getZ() - blockPos.getZ()) + 0.45F - f;
                        h = (float) (blockPos3.getX() - blockPos.getX()) + 0.55F + f;
                        i = (float) (blockPos3.getY() - blockPos.getY()) + 0.55F + f;
                        j = (float) (blockPos3.getZ() - blockPos.getZ()) + 0.55F + f;
                    }

                    float red, green, blue;
                    float alpha = 1.0F;
                    switch (ModConfigs.OUTLINE_COLOR) {
                        case "void":
                            red = 0.14F;
                            green = 0.70F;
                            blue = 0.78F;
                            break;
                        case "barrier":
                            red = 1.0F;
                            green = 0.0F;
                            blue = 0.0F;
                            break;
                        default:
                            red = 1.0F;
                            green = 0.75F;
                            blue = 0.75F;
                            break;
                    }

                    Constants.LOGGER.debug("Rendering outline at {}: Color - R:{}, G:{}, B:{}, A:{}", blockPos3, red, green, blue, alpha);
                    LevelRenderer.renderLineBox(poseStack, vertexConsumer, d, e, g, h, i, j, red, green, blue, alpha);
                }
            }
        }
    }
}