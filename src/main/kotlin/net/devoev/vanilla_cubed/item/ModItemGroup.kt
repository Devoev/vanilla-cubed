package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.VanillaCubed
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import java.util.function.Supplier

/**
 * All modded item groups.
 */
object ModItemGroup {

    val MATERIALS: ItemGroup = create("materials") { ModItems.AMETHYST_CRYSTAL }
    val TOOLS: ItemGroup = create("tools") { ModItems.AMETHYST_PICKAXE }

    /**
     * Creates a new modded [ItemGroup].
     */
    private fun create(name: String, icon: Supplier<Item>): ItemGroup = FabricItemGroupBuilder.build(VanillaCubed.id(name)) { ItemStack(icon.get()) }
}