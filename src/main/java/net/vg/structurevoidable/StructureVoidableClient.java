package net.vg.structurevoidable;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.vg.structurevoidable.block.entity.ModBlockEntities;
import net.vg.structurevoidable.client.render.block.entity.StructureVoidBlockEntityRenderer;

public class StructureVoidableClient implements ClientModInitializer {


	/**
	 * This method is called when Minecraft is ready to load client-specific mods.
	 * It sets up client-side logic, such as rendering.
	 */
	@Override
	public void onInitializeClient() {
		// Log the initialization start
		StructureVoidable.LOGGER.info("Initializing client-side components for Structure Voidable...");

		// Register the block entity renderer for the Structure Void Block Entity
		StructureVoidable.LOGGER.info("Registering Structure Void Block Entity Renderer...");
		BlockEntityRendererFactories.register(ModBlockEntities.STRUCTURE_VOID_BLOCK_ENTITY, StructureVoidBlockEntityRenderer::new);

		// Log the successful initialization
		StructureVoidable.LOGGER.info("Client-side components initialized for Structure Voidable");
	}
}