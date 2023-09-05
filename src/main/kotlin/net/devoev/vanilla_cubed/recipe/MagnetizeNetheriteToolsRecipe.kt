package net.devoev.vanilla_cubed.recipe

import net.devoev.vanilla_cubed.inventory.get
import net.devoev.vanilla_cubed.inventory.toList
import net.devoev.vanilla_cubed.item.isNetherite
import net.devoev.vanilla_cubed.item.magnetic
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.item.ToolItem
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.SmithingRecipe
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.util.Identifier
import net.minecraft.world.World

/**
 * Recipe that magnetizes demagnetized netherite tools by combination with a [Items.NETHERITE_SCRAP].
 */
class MagnetizeNetheriteToolsRecipe(private val id: Identifier) : SmithingRecipe {

    override fun matches(inventory: Inventory, world: World): Boolean {
        val (template, base, addition) = inventory.toList()
        return testTemplate(template) && testBase(base) && testAddition(addition)
    }

    override fun craft(inventory: Inventory, registryManager: DynamicRegistryManager): ItemStack = inventory[0].copy().apply { magnetic = true }

    override fun getOutput(registryManager: DynamicRegistryManager?): ItemStack = ItemStack.EMPTY

    override fun getId(): Identifier = id

    override fun getSerializer(): RecipeSerializer<*> = ModCraftingRecipes.MAGNETIZE_NETHERITE_TOOLS
    override fun testTemplate(stack: ItemStack): Boolean = stack.isEmpty

    override fun testBase(stack: ItemStack): Boolean = stack.item is ToolItem && stack.item.isNetherite() && !stack.magnetic

    override fun testAddition(stack: ItemStack): Boolean = stack.isOf(Items.NETHERITE_SCRAP)
}