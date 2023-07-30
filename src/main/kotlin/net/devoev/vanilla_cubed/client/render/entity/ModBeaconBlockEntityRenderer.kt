package net.devoev.vanilla_cubed.client.render.entity

import net.devoev.vanilla_cubed.block.entity.ModBeaconBlockEntity
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.block.entity.BeaconBlockEntityRenderer
import net.minecraft.client.render.block.entity.BlockEntityRenderer
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory
import net.minecraft.client.util.math.MatrixStack

@Environment(EnvType.CLIENT)
class ModBeaconBlockEntityRenderer(ctx: BlockEntityRendererFactory.Context) : BlockEntityRenderer<ModBeaconBlockEntity> {

    override fun render(
        entity: ModBeaconBlockEntity,
        tickDelta: Float,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        overlay: Int
    ) {
        val time: Long = entity.world?.time ?: return
        val beamSegments: List<ModBeaconBlockEntity.ModBeamSegment> = entity.beamSegments
        var yOffset = 1
        for (i in beamSegments.indices) {
            val beamSegment = beamSegments[i]
            BeaconBlockEntityRenderer.renderBeam(
                matrices,
                vertexConsumers,
                BeaconBlockEntityRenderer.BEAM_TEXTURE,
                tickDelta,
                1.0f,
                time,
                yOffset,
                if (i == beamSegments.size - 1) BeaconBlockEntityRenderer.MAX_BEAM_HEIGHT else beamSegment.height,
                beamSegment.color,
                0.2f,
                0.25f
            )
            yOffset += beamSegment.height
        }
    }
}