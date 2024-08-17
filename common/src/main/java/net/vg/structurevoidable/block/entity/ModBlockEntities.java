package net.vg.structurevoidable.block.entity;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.vg.structurevoidable.Constants;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Constants.MOD_ID, Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<StructureVoidBlockEntity>> STRUCTURE_VOID_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            "structure_void_block_entity",
            () -> BlockEntityType.Builder.of(StructureVoidBlockEntity::new, Blocks.STRUCTURE_VOID).build(null)
    );

    public static void register() {
        Constants.LOGGER.debug("Registering block entities for {}", Constants.MOD_NAME);
        BLOCK_ENTITIES.register();
        Constants.LOGGER.info("Registered block entity: {}", STRUCTURE_VOID_BLOCK_ENTITY.getId());
    }
}