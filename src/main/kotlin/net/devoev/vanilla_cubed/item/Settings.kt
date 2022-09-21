package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.mixin.ItemSettingsAccessor
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item

/**
 * Returns a copy of this [Item.Settings].
 */
fun Item.Settings.copy(): FabricItemSettings {
    this as ItemSettingsAccessor
    return FabricItemSettings()
        .maxCount(maxCount)
        .maxDamage(maxDamage)
        .recipeRemainder(recipeRemainder())
        .group(group)
        .rarity(rarity)
        .food(food)
        .let { if (fireproof) it.fireproof() else it }
}