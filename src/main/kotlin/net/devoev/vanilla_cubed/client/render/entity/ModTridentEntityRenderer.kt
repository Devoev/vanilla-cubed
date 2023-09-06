package net.devoev.vanilla_cubed.client.render.entity

import net.devoev.vanilla_cubed.entity.projectile.ModTridentEntity
import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.item.id
import net.devoev.vanilla_cubed.util.entityTexture
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.model.EntityModelLayers
import net.minecraft.client.render.entity.model.TridentEntityModel
import net.minecraft.client.render.item.ItemRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.RotationAxis

class ModTridentEntityRenderer(ctx: EntityRendererFactory.Context) : EntityRenderer<ModTridentEntity>(ctx) {

    private val model = TridentEntityModel(ctx.getPart(EntityModelLayers.TRIDENT))

    override fun getTexture(entity: ModTridentEntity): Identifier = entity.texture

    override fun render(
        entity: ModTridentEntity,
        yaw: Float,
        tickDelta: Float,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int
    ) {
        matrices.push()
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevYaw, entity.yaw) - 90.0f))
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevPitch, entity.pitch) + 90.0f))
        val vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(
            vertexConsumers,
            this.model.getLayer(getTexture(entity)),
            false,
            entity.isEnchanted
        )
        this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f)
        matrices.pop()
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light)
    }

    companion object {
        val TEXTURE = ModItems.ENDERITE_TRIDENT.id.entityTexture
    }
}