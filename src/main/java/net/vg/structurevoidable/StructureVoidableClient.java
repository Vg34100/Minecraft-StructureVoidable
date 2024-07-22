package net.vg.structurevoidable;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.vg.structurevoidable.block.entity.ModBlockEntities;
import net.vg.structurevoidable.client.StructureVoidBlockEntityRenderer;

public class StructureVoidableClient implements ClientModInitializer {


	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		BlockEntityRendererFactories.register(ModBlockEntities.STRUCTURE_VOID_BLOCK_ENTITY, StructureVoidBlockEntityRenderer::new);
	}
}