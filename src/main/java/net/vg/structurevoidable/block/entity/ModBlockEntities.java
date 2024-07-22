package net.vg.structurevoidable.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.vg.structurevoidable.StructureVoidable;

public class ModBlockEntities {
    public static BlockEntityType<StructureVoidBlockEntity> STRUCTURE_VOID_BLOCK_ENTITY;

    public static void registerBlockEntities() {
        STRUCTURE_VOID_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                Identifier.of(StructureVoidable.MOD_ID, "structure_void_block_entity"),
                BlockEntityType.Builder.create(StructureVoidBlockEntity::new, Blocks.STRUCTURE_VOID).build(null)
        );
    }
}
