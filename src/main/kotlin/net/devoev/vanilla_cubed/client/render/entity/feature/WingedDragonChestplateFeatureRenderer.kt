package net.devoev.vanilla_cubed.client.render.entity.feature

import net.devoev.vanilla_cubed.VanillaCubed
import net.devoev.vanilla_cubed.item.ModItems
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.feature.FeatureRenderer
import net.minecraft.client.render.entity.feature.FeatureRendererContext
import net.minecraft.client.render.entity.model.ElytraEntityModel
import net.minecraft.client.render.entity.model.EntityModel
import net.minecraft.client.render.entity.model.EntityModelLayers
import net.minecraft.client.render.entity.model.EntityModelLoader
import net.minecraft.client.render.item.ItemRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity

@Environment(EnvType.CLIENT)
class WingedDragonChestplateFeatureRenderer<T : LivingEntity, M : EntityModel<T>>(
    context: FeatureRendererContext<T, M>, loader: EntityModelLoader) : FeatureRenderer<T, M>(context) {

    private val model = ElytraEntityModel<T>(loader.getModelPart(EntityModelLayers.ELYTRA))

    override fun render(matrixStack: MatrixStack, vertexConsumerProvider: VertexConsumerProvider?, i: Int, livingEntity: T, f: Float, g: Float, h: Float, j: Float, k: Float, l: Float) {
        val stack = livingEntity.getEquippedStack(EquipmentSlot.CHEST)
        if (!stack.isOf(ModItems.WINGED_DRAGON_SCALE_CHESTPLATE)) return

        matrixStack.push()
        matrixStack.translate(0.0, 0.0, 0.125)
        this.contextModel.copyStateTo(this.model)
        this.model.setAngles(livingEntity, f, g, j, k, l)
        val vertexConsumer = ItemRenderer.getArmorGlintConsumer(
            vertexConsumerProvider,
            RenderLayer.getArmorCutoutNoCull(TEXTURE),
            false,
            stack.hasGlint()
        )
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f)
        matrixStack.pop()
    }

    companion object {
        val TEXTURE = VanillaCubed.id("textures/entity/dragon_scale_chestplate_winged.png")
    }
}