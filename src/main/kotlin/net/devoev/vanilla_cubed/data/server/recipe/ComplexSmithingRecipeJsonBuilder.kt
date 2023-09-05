package net.devoev.vanilla_cubed.data.server.recipe

import com.google.gson.JsonObject
import net.devoev.vanilla_cubed.recipe.SpecialSmithingRecipeSerializer
import net.minecraft.data.server.recipe.RecipeJsonBuilder
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.book.CraftingRecipeCategory
import net.minecraft.util.Identifier
import java.util.function.Consumer

class ComplexSmithingRecipeJsonBuilder(private val serializer: SpecialSmithingRecipeSerializer<*>) : RecipeJsonBuilder() {

    fun offerTo(exporter: Consumer<RecipeJsonProvider>, recipeId: String) {
        exporter.accept(object : CraftingRecipeJsonProvider(CraftingRecipeCategory.MISC) {

            override fun getSerializer(): RecipeSerializer<*> = this@ComplexSmithingRecipeJsonBuilder.serializer

            override fun getRecipeId(): Identifier = Identifier(recipeId)

            override fun toAdvancementJson(): JsonObject? = null

            override fun getAdvancementId(): Identifier = Identifier("")
        })
    }
}