package net.devoev.vanilla_cubed.client.render.entity

import net.devoev.vanilla_cubed.block.entity.ModBlockEntityTypes
import net.devoev.vanilla_cubed.util.MapInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.client.render.block.entity.BlockEntityRenderer
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory

@Environment(EnvType.CLIENT)
object ModBlockEntityRenderer : MapInitializer<BlockEntityType<out BlockEntity>, BlockEntityRendererFactory<BlockEntity>>() {

    init {
        this[ModBlockEntityTypes.MOD_BEACON] = ::ModBeaconBlockEntityRenderer
    }

    operator fun <T : BlockEntity> set(type: BlockEntityType<out T>, rendererProvider: (BlockEntityRendererFactory.Context) -> BlockEntityRenderer<T>)
            = ModBlockEntityRenderer.put(type) { ctx -> rendererProvider(ctx) as BlockEntityRenderer<BlockEntity> }

    override fun init() = forEach { BlockEntityRendererFactories.register(it.key, it.value) }
}