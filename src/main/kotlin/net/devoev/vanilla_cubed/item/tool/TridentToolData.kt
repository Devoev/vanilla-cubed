package net.devoev.vanilla_cubed.item.tool

import net.devoev.vanilla_cubed.item.copy
import net.devoev.vanilla_cubed.item.modifier.ItemModifiers
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.TridentEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolMaterial
import net.minecraft.item.TridentItem
import net.minecraft.world.World

/**
 * Data needed for the creation of a [TridentItem].
 */
data class TridentToolData(
    val material: ToolMaterial,
    val settings: Item.Settings,
    val entityProvider: EntityProvider,
    val modifiers: ItemModifiers<Item>,
) {

    /**
     * Creates a new [TridentToolData] with the [Item.Settings.maxDamage] property set to [durability]`*0.16`.
     */
    fun withDurability(durability: Int): TridentToolData {
        return copy(settings = settings.copy().maxDamageIfAbsent((durability * 0.16).toInt()))
    }
}

typealias EntityProvider = (World, LivingEntity, ItemStack) -> TridentEntity
