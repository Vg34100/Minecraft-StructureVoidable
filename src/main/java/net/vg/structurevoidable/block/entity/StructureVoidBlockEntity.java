package net.vg.structurevoidable.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class StructureVoidBlockEntity extends BlockEntity {
    public StructureVoidBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.STRUCTURE_VOID_BLOCK_ENTITY, pos, state);
    }

    // Additional logic for your custom block entity
}