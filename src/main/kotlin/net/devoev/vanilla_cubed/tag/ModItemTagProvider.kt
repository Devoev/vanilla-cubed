package net.devoev.vanilla_cubed.tag

import net.devoev.vanilla_cubed.item.ModItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.item.ArmorItem
import net.minecraft.item.Item
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.ItemTags
import net.minecraft.registry.tag.TagKey
import java.util.concurrent.CompletableFuture

class ModItemTagProvider(output: FabricDataOutput, completableFuture: CompletableFuture<RegistryWrapper.WrapperLookup>)
    : FabricTagProvider.ItemTagProvider(output, completableFuture) {

    override fun configure(arg: RegistryWrapper.WrapperLookup) {
        create(ItemTags.TRIMMABLE_ARMOR) {
            add(*ModItems.values.filterIsInstance<ArmorItem>().toTypedArray())
        }
    }

    /**
     * Creates a new [FabricTagProvider.FabricTagBuilder] for the given [tagKey] and performs the [builderAction].
     */
    private fun create(tagKey: TagKey<Item>, builderAction: FabricTagBuilder.() -> Unit) {
        getOrCreateTagBuilder(tagKey).builderAction()
    }
}