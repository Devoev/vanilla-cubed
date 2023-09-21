package net.devoev.vanilla_cubed.recipe

import net.devoev.vanilla_cubed.potion.ModPotions
import net.minecraft.item.Items
import net.minecraft.potion.Potions
import net.minecraft.recipe.BrewingRecipeRegistry

object ModBrewingRecipes {
    init {
        BrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, Items.NETHERITE_SCRAP, ModPotions.MAGNETIC)
        BrewingRecipeRegistry.registerPotionRecipe(ModPotions.MAGNETIC, Items.REDSTONE, ModPotions.LONG_MAGNETIC)
        BrewingRecipeRegistry.registerPotionRecipe(ModPotions.MAGNETIC, Items.GLOWSTONE_DUST, ModPotions.STRONG_MAGNETIC)
    }
}