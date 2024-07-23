package net.vg.structurevoidable.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.vg.structurevoidable.StructureVoidable;

public class StructureVoidBlockEntity extends BlockEntity {

    /**
     * Constructor for the Structure Void Block Entity.
     *
     * @param pos   The position of the block entity in the world.
     * @param state The state of the block at the given position.
     */
    public StructureVoidBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STRUCTURE_VOID_BLOCK_ENTITY, pos, state);
        StructureVoidable.LOGGER.debug("Creating StructureVoidBlockEntity at position: {}", pos);
    }
}
