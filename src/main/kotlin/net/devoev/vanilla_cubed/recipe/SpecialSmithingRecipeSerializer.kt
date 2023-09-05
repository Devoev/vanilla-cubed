package net.devoev.vanilla_cubed.recipe

import com.google.gson.JsonObject
import net.minecraft.network.PacketByteBuf
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.SmithingRecipe
import net.minecraft.util.Identifier

class SpecialSmithingRecipeSerializer<T : SmithingRecipe>(private val factory: (Identifier) -> T) : RecipeSerializer<T> {

    override fun read(id: Identifier, json: JsonObject?): T = factory(id)

    override fun read(id: Identifier, buf: PacketByteBuf?): T = factory(id)

    override fun write(buf: PacketByteBuf?, recipe: T) = Unit
}