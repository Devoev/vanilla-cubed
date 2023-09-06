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

    val VANILLA_CUBED: ItemGroup = create("vanilla_cubed", ItemStack(ModItems.GILDED_BOOK), ModItems.values.map { ItemStack(it) })

    /**
     * Creates a new modded [ItemGroup].
     */
    private fun create(name: String, icon: ItemStack, items: Collection<ItemStack> = listOf()): ItemGroup
        = create(name, itemGroupOf(name, icon, items))
}

/**
 * Creates a [ItemGroup] of the display name `"itemGroup.vanilla_cubed.$[name]"`.
 */
private fun itemGroupOf(name: String, icon: ItemStack, items: Collection<ItemStack>): ItemGroup = FabricItemGroup.builder()
    .displayName(Text.translatable("itemGroup.vanilla_cubed.$name"))
    .icon { icon }
    .entries { _, entries -> entries.addAll(items) }
    .build()