package net.devoev.vanilla_cubed.tag

import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.item.armorItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.item.Item
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.ItemTags
import net.minecraft.registry.tag.TagKey
import java.util.concurrent.CompletableFuture

class ModItemTagProvider(output: FabricDataOutput, completableFuture: CompletableFuture<RegistryWrapper.WrapperLookup>)
    : FabricTagProvider.ItemTagProvider(output, completableFuture) {

    override fun configure(arg: RegistryWrapper.WrapperLookup) {
        // TODO: Fix trim material
//        create(ItemTags.TRIM_MATERIALS) {
//            add(ModItems.ANCIENT_GOLD_INGOT)
//            add(ModItems.ENDERITE_INGOT)
//        }

        create(ItemTags.TRIMMABLE_ARMOR) {
            add(ModItems.armorItems)
        }
    }

    /**
     * Creates a new [FabricTagProvider.FabricTagBuilder] for the given [tagKey] and performs the [builderAction].
     */
    private fun create(tagKey: TagKey<Item>, builderAction: FabricTagBuilder.() -> Unit) {
        getOrCreateTagBuilder(tagKey).builderAction()
    }
}

/**
 * Adds the given [elements] to the tag.
 */
fun <T> FabricTagProvider<T>.FabricTagBuilder.add(elements: Collection<T>) = elements.forEach(::add)