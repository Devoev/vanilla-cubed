package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.util.RegistryManager
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.text.Text

/**
 * All modded item groups.
 */
object ModItemGroup : RegistryManager<ItemGroup>(Registries.ITEM_GROUP) {

    val VANILLA_CUBED: ItemGroup = create("vanilla_cubed", ItemStack(ModItems.GILDED_BOOK))

    /**
     * Creates a new modded [ItemGroup].
     */
    private fun create(name: String, icon: ItemStack): ItemGroup = FabricItemGroup.builder()
        .icon { icon }
        .displayName(Text.translatable("itemGroup.vanilla_cubed.$name"))
        .build()
}