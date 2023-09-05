package net.devoev.vanilla_cubed.client.render.item

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer
import net.fabricmc.fabric.api.resource.ResourceReloadListenerKeys
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
import net.minecraft.client.MinecraftClient
import net.minecraft.client.model.Model
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelLayers
import net.minecraft.client.render.entity.model.TridentEntityModel
import net.minecraft.client.render.item.ItemRenderer
import net.minecraft.client.render.model.BakedModel
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.client.util.ModelIdentifier
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier


@Environment(EnvType.CLIENT)
class TridentItemRenderer(private val itemId: Identifier, private val texture: Identifier) :
    DynamicItemRenderer, SimpleSynchronousResourceReloadListener {

    private val id: Identifier = Identifier(itemId.namespace, itemId.path + "_renderer")
    private val modelId: Identifier = Identifier(itemId.namespace, itemId.path + "_in_inventory")
    private val modelLayer: EntityModelLayer = EntityModelLayers.TRIDENT

    private lateinit var itemRenderer: ItemRenderer
    private lateinit var tridentEntityModel: Model
    private lateinit var tridentItemModel: BakedModel

    /**
     * Whether the `lateinit` vars are initialized.
     */
    private val initialized get() = this::itemRenderer.isInitialized
            && this::tridentEntityModel.isInitialized
            && this::tridentItemModel.isInitialized

    override fun getFabricId(): Identifier = id

    override fun getFabricDependencies(): Collection<Identifier> = listOf(ResourceReloadListenerKeys.MODELS)

    fun init() {
        if (initialized) return

        val client: MinecraftClient = MinecraftClient.getInstance()
        itemRenderer = client.itemRenderer
        tridentEntityModel = TridentEntityModel(client.entityModelLoader.getModelPart(modelLayer))
        tridentItemModel = client.bakedModelManager.getModel(
            ModelIdentifier(modelId, "inventory")
        )
    }

    override fun reload(manager: ResourceManager?) = init()

    override fun render(
        stack: ItemStack,
        renderMode: ModelTransformationMode,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        overlay: Int
    ) {
        init()

        if (renderMode == ModelTransformationMode.GUI || renderMode == ModelTransformationMode.GROUND || renderMode == ModelTransformationMode.FIXED) {
            matrices.pop()
            matrices.push()
            itemRenderer.renderItem(
                stack, renderMode, false, matrices, vertexConsumers, light, overlay,
                tridentItemModel
            )
        } else {
            matrices.push()
            matrices.scale(1.0f, -1.0f, -1.0f)
            val vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(
                vertexConsumers, tridentEntityModel.getLayer(
                    texture
                ), false, stack.hasGlint()
            )
            tridentEntityModel.render(matrices, vertexConsumer, light, overlay, 1.0f, 1.0f, 1.0f, 1.0f)
            matrices.pop()
        }
    }
}