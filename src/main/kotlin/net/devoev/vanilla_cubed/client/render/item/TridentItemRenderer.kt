package net.devoev.vanilla_cubed.client.render.item

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry.DynamicItemRenderer
import net.fabricmc.fabric.api.resource.ResourceReloadListenerKeys
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.render.entity.model.EntityModelLayers
import net.minecraft.client.render.entity.model.TridentEntityModel
import net.minecraft.client.render.item.ItemRenderer
import net.minecraft.client.render.model.BakedModel
import net.minecraft.client.render.model.json.ModelTransformation
import net.minecraft.client.util.ModelIdentifier
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier


@Environment(EnvType.CLIENT)
class TridentItemRenderer(private val itemId: Identifier, private val texture: Identifier) :
    DynamicItemRenderer, SimpleSynchronousResourceReloadListener {

    private val id: Identifier = Identifier(itemId.namespace, itemId.path + "_renderer")
    private val modelLayer: EntityModelLayer = EntityModelLayers.TRIDENT

    private lateinit var itemRenderer: ItemRenderer
    private lateinit var tridentEntityModel: TridentEntityModel
    private lateinit var tridentItemModel: BakedModel

    override fun getFabricId(): Identifier = id

    override fun getFabricDependencies(): Collection<Identifier> = listOf(ResourceReloadListenerKeys.MODELS)

    override fun reload(manager: ResourceManager?) {
        val mc: MinecraftClient = MinecraftClient.getInstance()
        itemRenderer = mc.itemRenderer
        tridentEntityModel = TridentEntityModel(mc.entityModelLoader.getModelPart(modelLayer))
        tridentItemModel = mc.bakedModelManager.getModel(
            ModelIdentifier(itemId.toString() + "_in_inventory", "inventory")
        )
        itemRendererGl = mc.itemRenderer
        tridentEntityModelGl = TridentEntityModel(mc.entityModelLoader.getModelPart(modelLayer))
        tridentItemModelGl = mc.bakedModelManager.getModel(
            ModelIdentifier(itemId.toString() + "_in_inventory", "inventory")
        )
    }

    override fun render(
        stack: ItemStack,
        renderMode: ModelTransformation.Mode,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        overlay: Int
    ) {
        //assert(tridentEntityModel != null)
        assert(tridentEntityModelGl != null)
        //if (!this::tridentEntityModel.isInitialized) return
        //if (tridentEntityModel == null) return

        if (renderMode == ModelTransformation.Mode.GUI || renderMode == ModelTransformation.Mode.GROUND || renderMode == ModelTransformation.Mode.FIXED) {
            println("Item branch!!!!!!!!!!!!!!!!")
            matrices.pop()
            matrices.push()
            itemRendererGl?.renderItem(
                stack, renderMode, false, matrices, vertexConsumers, light, overlay,
                tridentItemModelGl
            )
        } else {
            println("Entity branch!!!!!!!!!!!!!!!!")
            matrices.push()
            matrices.scale(1.0f, -1.0f, -1.0f)
            val vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(
                vertexConsumers, tridentEntityModelGl?.getLayer(
                    texture
                ), false, stack.hasGlint()
            )
            tridentEntityModelGl?.render(matrices, vertexConsumer, light, overlay, 1.0f, 1.0f, 1.0f, 1.0f)
            matrices.pop()
        }
    }
}

var itemRendererGl: ItemRenderer? = null
var tridentEntityModelGl: TridentEntityModel? = null
var tridentItemModelGl: BakedModel? = null