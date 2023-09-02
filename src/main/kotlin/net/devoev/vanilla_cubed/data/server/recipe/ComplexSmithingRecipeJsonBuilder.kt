package net.devoev.vanilla_cubed.data.server.recipe

import com.google.gson.JsonObject
import net.devoev.vanilla_cubed.VanillaCubed
import net.devoev.vanilla_cubed.recipe.MagnetizeNetheriteToolsRecipe
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.util.Identifier
import java.util.function.Consumer

class ComplexSmithingRecipeJsonBuilder(val serializer: MagnetizeNetheriteToolsRecipe.Serializer) {

    fun offerTo(exporter: Consumer<RecipeJsonProvider>, recipeId: String) {
        exporter.accept(object : RecipeJsonProvider {
            override fun serialize(json: JsonObject?) = Unit

            override fun getRecipeId(): Identifier = VanillaCubed.id(recipeId)

            override fun getSerializer(): RecipeSerializer<*> = this@ComplexSmithingRecipeJsonBuilder.serializer

            override fun toAdvancementJson(): JsonObject? = null

            override fun getAdvancementId(): Identifier = Identifier("")
        })
    }
}