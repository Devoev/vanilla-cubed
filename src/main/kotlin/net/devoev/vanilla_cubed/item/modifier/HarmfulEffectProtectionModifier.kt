package net.devoev.vanilla_cubed.item.modifier

import net.devoev.vanilla_cubed.entity.wearsFullArmor
import net.devoev.vanilla_cubed.item.armor.ModArmor
import net.devoev.vanilla_cubed.text.translatableTextOf
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.item.ArmorItem
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.text.Text
import net.minecraft.util.Formatting

/**
 * Protects the wearer from harmful status effects.
 * @see ModArmor.ANCIENT_GOLD
 */
val HarmfulEffectProtectionModifier = InventoryTickModifier<ArmorItem> { stack, world, entity, _, _ ->

    if (entity !is LivingEntity || !entity.wearsFullArmor(material) || !entity.armorItems.contains(stack))
        return@InventoryTickModifier

    entity.statusEffects
        .filter {
            it.effectType.category == StatusEffectCategory.HARMFUL
                    && !it.isInfinite
                    && it.shouldShowIcon()
                    && it.shouldShowParticles()
        }
        .forEach {
            if (!world.isClient) {
                entity.removeStatusEffect(it.effectType)
                world.playSound(null, entity.blockPos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.PLAYERS, 0.3f, -3f)
            } else {
                repeat(30) {
                    world.addParticle(
                        ParticleTypes.SPORE_BLOSSOM_AIR,
                        entity.x,
                        entity.y + 1,
                        entity.z,
                        3.2,
                        3.2,
                        3.2)
                }
            }
        }
}

val HARMFUL_EFFECT_PROTECTION_TEXT: Text
    = translatableTextOf("modifier", "protection_from_harmful_effects").formatted(Formatting.BLUE)

