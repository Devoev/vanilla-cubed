package net.devoev.vanilla_cubed.recipe

import net.devoev.vanilla_cubed.data.server.recipe.ComplexSmithingRecipeJsonBuilder
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.ComplexRecipeJsonBuilder
import net.minecraft.data.server.recipe.RecipeJsonProvider
import java.util.function.Consumer

class ModRecipeProvider(generator: FabricDataGenerator) : FabricRecipeProvider(generator) {
    override fun generateRecipes(exporter: Consumer<RecipeJsonProvider>) {
        ComplexRecipeJsonBuilder.create(ModCraftingRecipes.INFUSED_FIREWORK_ROCKET).offerTo(exporter, "firework_rocket_infused")
        ComplexSmithingRecipeJsonBuilder(ModCraftingRecipes.MAGNETIZE_NETHERITE_TOOLS).offerTo(exporter, "magnetize_netherite_tools")
    }
}