package net.vg.structurevoidable.mixin.block;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.vg.structurevoidable.Constants;
import net.vg.structurevoidable.block.entity.StructureVoidBlockEntity;
import net.vg.structurevoidable.config.ModConfigs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StructureVoidBlock.class)
public abstract class StructureVoidBlockMixin extends Block implements EntityBlock {

    public StructureVoidBlockMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "getRenderShape", at = @At("HEAD"), cancellable = true)
    public void getRenderShape(BlockState state, CallbackInfoReturnable<RenderShape> cir) {
        Constants.LOGGER.debug("Changing render type to MODEL for StructureVoidBlock");
        cir.setReturnValue(RenderShape.MODEL);
    }

    @Inject(method = "getShape", at = @At("HEAD"), cancellable = true)
    public void getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (ModConfigs.FULL_BLOCK_OUTLINE) {
            Constants.LOGGER.debug("Setting outline shape to full cube for StructureVoidBlock");
            VoxelShape fullCube = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);
            cir.setReturnValue(fullCube);
        }
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        Constants.LOGGER.debug("Creating StructureVoidBlockEntity at position: {}", pos);
        return new StructureVoidBlockEntity(pos, state);
    }
}