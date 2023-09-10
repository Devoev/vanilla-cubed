package net.devoev.vanilla_cubed.item.modifier

import net.devoev.vanilla_cubed.entity.effect.ModStatusEffects
import net.devoev.vanilla_cubed.item.isMadeOf
import net.devoev.vanilla_cubed.mixin.ItemMixin
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolMaterials
import net.minecraft.world.World

/**
 * Applies the [ModStatusEffects.MAGNETIC] effect to players holding the item.
 * @see ToolMaterials.NETHERITE
 */
val MagneticModifier = toolStatusEffectModifierOf(ModStatusEffects.MAGNETIC, 1, 1)

/**
 * Injects the [MagneticModifier] into netherite tools.
 * @see ItemMixin.setMagneticModifierToNetherite
 */
fun setMagneticModifierToNetherite(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
    if (stack.item.isMadeOf(ToolMaterials.NETHERITE) && entity is LivingEntity) {
        MagneticModifier.inventoryTick(stack.item, stack, world, entity, slot, selected || entity.offHandStack == stack)
    }
}