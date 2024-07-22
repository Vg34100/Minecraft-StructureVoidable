package net.vg.structurevoidable.mixin;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.vg.structurevoidable.block.entity.StructureVoidBlockEntity;
import net.vg.structurevoidable.config.ModConfigs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StructureVoidBlock.class)
public abstract class StructureVoidBlockMixin extends Block implements BlockEntityProvider {

    public StructureVoidBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "getRenderType", at = @At("HEAD"), cancellable = true)
    public void getRenderType(BlockState state, CallbackInfoReturnable<BlockRenderType> cir) {
        cir.setReturnValue(BlockRenderType.MODEL);  // Change the render type to MODEL
    }

    @Inject(method = "getOutlineShape", at = @At("HEAD"), cancellable = true)
    public void getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (ModConfigs.FULL_BLOCK_OUTLINE) {
            VoxelShape fullCube = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);
            cir.setReturnValue(fullCube);  // Change the outline shape to a full cube
        }
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new StructureVoidBlockEntity(pos, state);
    }
}