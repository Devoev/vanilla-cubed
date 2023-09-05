package net.devoev.vanilla_cubed.recipe

import net.devoev.vanilla_cubed.data.server.recipe.ComplexSmithingRecipeJsonBuilder
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.ComplexRecipeJsonBuilder
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.recipe.SpecialRecipeSerializer
import java.util.function.Consumer

class ModRecipeProvider(generator: FabricDataOutput) : FabricRecipeProvider(generator) {

    override fun generate(exporter: Consumer<RecipeJsonProvider>) {
        with(exporter) {
            createSpecialCraftingRecipe("firework_rocket_infused", ModCraftingRecipes.INFUSED_FIREWORK_ROCKET)
            createSpecialSmithingRecipe("magnetize_netherite_tools", ModCraftingRecipes.MAGNETIZE_NETHERITE_TOOLS)
        }
    }

    /**
     * Creates a hardcoded special crafting recipe using the [serializer] and registers it under the given [recipeId].
     */
    private fun Consumer<RecipeJsonProvider>.createSpecialCraftingRecipe(recipeId: String, serializer: SpecialRecipeSerializer<*>) {
        ComplexRecipeJsonBuilder(serializer).offerTo(this, recipeId)
    }

    /**
     * Creates a hardcoded special smithing recipe using the [serializer] and registers it under the given [recipeId].
     */
    private fun Consumer<RecipeJsonProvider>.createSpecialSmithingRecipe(recipeId: String, serializer: SpecialSmithingRecipeSerializer<*>) {
        ComplexSmithingRecipeJsonBuilder(serializer).offerTo(this, recipeId)
    }
}