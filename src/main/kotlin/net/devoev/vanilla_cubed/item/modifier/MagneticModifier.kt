package net.devoev.vanilla_cubed.item.modifier

import net.devoev.vanilla_cubed.entity.effect.ModStatusEffects
import net.devoev.vanilla_cubed.item.isMadeOf
import net.devoev.vanilla_cubed.item.isNetherite
import net.devoev.vanilla_cubed.item.magnetic
import net.devoev.vanilla_cubed.mixin.EntityMixin
import net.devoev.vanilla_cubed.mixin.ItemMixin
import net.minecraft.entity.Entity
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolMaterials
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.world.World

/**
 * Applies the [ModStatusEffects.MAGNETIC] effect to players holding the item.
 * Only applies if [ItemStack.magnetic] is `true`.
 * @see ToolMaterials.NETHERITE
 */
val MagneticModifier = toolStatusEffectModifierOf(ModStatusEffects.MAGNETIC, 1, 1)
    .runIf { _, stack, _, _, _, _ -> stack.magnetic }

val MAGNETIC_TEXT: Text = toolStatusEffectTextOf(ModStatusEffects.MAGNETIC, "II")

/**
 * Injects the [MagneticModifier] into netherite tools.
 * @see ItemMixin.setMagneticModifierToNetherite
 */
fun setMagneticModifierToNetherite(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
    if (stack.item.isMadeOf(ToolMaterials.NETHERITE) && entity is LivingEntity) {
        MagneticModifier.inventoryTick(stack.item, stack, world, entity, slot, selected || entity.offHandStack == stack)
    }
}

/**
 * Demagnetizes netherite tools when thrown into lava.
 *
 * TODO: Tie to MagneticModifier not netherite
 * @see EntityMixin.demagnetize
 */
fun ItemEntity.demagnetize() {
    if (!stack.item.isNetherite() || !stack.magnetic || !isInLava) return

    stack.magnetic = false
    if (!world.isClient) {
        world.playSound(null, blockPos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.AMBIENT, 10f, 1f)
    } else {
        repeat(5) {
            world.addParticle(ParticleTypes.LAVA, x, y, z, 1.0, 1.0, 1.0)
        }
    }
}