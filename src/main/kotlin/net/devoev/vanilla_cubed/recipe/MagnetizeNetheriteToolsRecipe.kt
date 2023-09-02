package net.devoev.vanilla_cubed.recipe

import com.google.gson.JsonObject
import net.devoev.vanilla_cubed.inventory.get
import net.devoev.vanilla_cubed.inventory.toList
import net.devoev.vanilla_cubed.item.isNetherite
import net.devoev.vanilla_cubed.item.magnetic
import net.minecraft.block.Blocks
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.item.ToolItem
import net.minecraft.network.PacketByteBuf
import net.minecraft.recipe.Recipe
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.RecipeType
import net.minecraft.util.Identifier
import net.minecraft.world.World

/**
 * Recipe that magnetizes demagnetized netherite tools.
 *
 * TODO: Prevent Classcast Exception by subclassing SmithingRecipe
 */
class MagnetizeNetheriteToolsRecipe(val identifier: Identifier) : Recipe<Inventory> {

    override fun matches(inventory: Inventory, world: World): Boolean {
        val (base, addition) = inventory.toList()
        return base.item is ToolItem
                && base.item.isNetherite()
                && !base.magnetic
                && addition.isOf(Items.NETHERITE_SCRAP)
    }

    override fun craft(inventory: Inventory): ItemStack {
        val res = inventory[0]
        res.magnetic = true
        return res
    }

    override fun fits(width: Int, height: Int): Boolean = width * height >= 2

    override fun getOutput(): ItemStack = ItemStack.EMPTY

    override fun createIcon(): ItemStack = ItemStack(Blocks.SMITHING_TABLE)

    override fun getId(): Identifier = identifier

    override fun getSerializer(): RecipeSerializer<*> = ModCraftingRecipes.MAGNETIZE_NETHERITE_TOOLS

    override fun getType(): RecipeType<*> = RecipeType.SMITHING

    class Serializer(private val factory: (Identifier) -> MagnetizeNetheriteToolsRecipe) : RecipeSerializer<MagnetizeNetheriteToolsRecipe> {
        override fun read(id: Identifier, json: JsonObject): MagnetizeNetheriteToolsRecipe = factory(id)

        override fun read(id: Identifier, buf: PacketByteBuf): MagnetizeNetheriteToolsRecipe = factory(id)

        override fun write(buf: PacketByteBuf?, recipe: MagnetizeNetheriteToolsRecipe?) = Unit
    }

}