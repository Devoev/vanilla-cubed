package net.devoev.vanilla_cubed.item

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.item.ArmorItem

class ModModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {

    override fun generateBlockStateModels(blockStateModelGenerator: BlockStateModelGenerator) {

    }

    override fun generateItemModels(itemModelGenerator: ItemModelGenerator) {
        with(itemModelGenerator) {
            createArmor(ModItems.values.filterIsInstance<ArmorItem>())
        }
    }

    /**
     * Creates models and registers the given [armorItems].
     */
    private fun ItemModelGenerator.createArmor(armorItems: Collection<ArmorItem>) = armorItems.forEach(::registerArmor)
}