package net.devoev.vanilla_cubed.item.modifier

import net.devoev.vanilla_cubed.item.isNetherite
import net.devoev.vanilla_cubed.mixin.ItemMixin
import net.devoev.vanilla_cubed.text.translatableTextOf
import net.minecraft.entity.Entity
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.world.World

/**
 * Applies [StatusEffects.REGENERATION] and [StatusEffects.STRENGTH] when on fire.
 * TODO: Add new berserk status effect
 */
val BerserkModifier = armorStatusEffectModifierOf(StatusEffects.STRENGTH, 300, 1) andThen
        armorStatusEffectModifierOf(StatusEffects.REGENERATION, 300, 1) runIf
        { _, _, _, entity, _, _ -> entity.isOnFire }

val BERSERK_TEXT: Text = translatableTextOf("modifier", "berserk").formatted(Formatting.BLUE)

/**
 * Injects the [BerserkModifier] into netherite armor.
 *
 * TODO: Make independent of netherite
 * @see ItemMixin.setBerserkModifierToNetherite
 */
fun setBerserkModifierToNetherite(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
    if (stack.item.isNetherite() && stack.item is ArmorItem) {
        BerserkModifier.inventoryTick(stack.item as ArmorItem, stack, world, entity, slot, selected)
    }
}