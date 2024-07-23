package net.vg.structurevoidable.mixin.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.vg.structurevoidable.StructureVoidable;
import net.vg.structurevoidable.block.entity.StructureVoidBlockEntity;
import net.vg.structurevoidable.config.ModConfigs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StructureVoidBlock.class)
public abstract class StructureVoidBlockMixin extends Block implements BlockEntityProvider {

    // Constructor to initialize the StructureVoidBlockMixin with block settings
    public StructureVoidBlockMixin(Settings settings) {
        super(settings);
    }

    /**
     * Inject into the getRenderType method to change the render type to MODEL.
     *
     * @param state BlockState of the block.
     * @param cir   CallbackInfoReturnable to modify the return value.
     */
    @Inject(method = "getRenderType", at = @At("HEAD"), cancellable = true)
    public void getRenderType(BlockState state, CallbackInfoReturnable<BlockRenderType> cir) {
        StructureVoidable.LOGGER.debug("Changing render type to MODEL for StructureVoidBlock");
        cir.setReturnValue(BlockRenderType.MODEL);  // Change the render type to MODEL
    }

    /**
     * Inject into the getOutlineShape method to change the outline shape based on configuration.
     * ModConfigs.FULL_BLOCK_OUTLINE -> Changing the outline shape to be a full cube
     *
     * @param state   BlockState of the block.
     * @param world   BlockView of the world.
     * @param pos     Block position.
     * @param context ShapeContext of the block.
     * @param cir     CallbackInfoReturnable to modify the return value.
     */
    @Inject(method = "getOutlineShape", at = @At("HEAD"), cancellable = true)
    public void getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (ModConfigs.FULL_BLOCK_OUTLINE) {
            StructureVoidable.LOGGER.debug("Setting outline shape to full cube for StructureVoidBlock");
            VoxelShape fullCube = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);
            cir.setReturnValue(fullCube);  // Change the outline shape to a full cube
        }
    }

    /**
     * Override the createBlockEntity method to create a new StructureVoidBlockEntity.
     *
     * @param pos   Block position.
     * @param state BlockState of the block.
     * @return A new StructureVoidBlockEntity.
     */
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        StructureVoidable.LOGGER.debug("Creating StructureVoidBlockEntity at position: {}", pos);
        return new StructureVoidBlockEntity(pos, state);
    }
}
