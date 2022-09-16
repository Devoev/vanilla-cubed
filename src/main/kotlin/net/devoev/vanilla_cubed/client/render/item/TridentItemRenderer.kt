package net.devoev.vanilla_cubed.client.render.item

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer
import net.fabricmc.fabric.api.resource.ResourceReloadListenerKeys
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelLayers
import net.minecraft.client.render.entity.model.TridentEntityModel
import net.minecraft.client.render.item.ItemRenderer
import net.minecraft.client.render.model.BakedModel
import net.minecraft.client.render.model.json.ModelTransformation
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier


class TridentItemRenderer(id: Identifier, texture: Identifier) :
    DynamicItemRenderer, SimpleSynchronousResourceReloadListener {
    private val id: Identifier
    private val tridentId: Identifier
    private val texture: Identifier
    private val modelLayer: EntityModelLayer = EntityModelLayers.TRIDENT
    private var itemRenderer: ItemRenderer? = null
    private var tridentModel: TridentEntityModel? = null
    private var inventoryTridentModel: BakedModel? = null

    init {
        this.id = Identifier(id.getNamespace(), id.getPath() + "_renderer")
        this.tridentId = id
        this.texture = texture
    }

    override fun getFabricId(): Identifier {
        return id
    }

    override fun getFabricDependencies(): Collection<Identifier> {
        return listOf(ResourceReloadListenerKeys.MODELS)
    }

    override fun reload(manager: ResourceManager?) {
//        val mc: MinecraftClient = MinecraftClient
//        itemRenderer = mc.getItemRenderer()
//        tridentModel = ImpaledTridentEntityModel(mc.getEntityModelLoader().getModelPart(modelLayer))
//        inventoryTridentModel =
//            mc.getBakedModelManager().getModel(ModelIdentifier(tridentId.toString() + "_in_inventory", "inventory"))
    }

    override fun render(
        stack: ItemStack,
        renderMode: ModelTransformation.Mode,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        overlay: Int
    ) {
        assert(tridentModel != null)
        if (renderMode == ModelTransformation.Mode.GUI || renderMode == ModelTransformation.Mode.GROUND || renderMode == ModelTransformation.Mode.FIXED) {
            matrices.pop() // cancel the previous transformation and pray that we are not breaking the state
            matrices.push()
            itemRenderer!!.renderItem(
                stack, renderMode, false, matrices, vertexConsumers, light, overlay,
                inventoryTridentModel
            )
        } else {
            matrices.push()
            matrices.scale(1.0f, -1.0f, -1.0f)
            val vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(
                vertexConsumers, tridentModel?.getLayer(
                    texture
                ), false, stack.hasGlint()
            )
            tridentModel?.render(matrices, vertexConsumer, light, overlay, 1.0f, 1.0f, 1.0f, 1.0f)
            matrices.pop()
        }
    }
}