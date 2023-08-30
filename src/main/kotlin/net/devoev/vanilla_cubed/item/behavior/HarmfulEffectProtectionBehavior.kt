package net.devoev.vanilla_cubed.item.behavior

import net.devoev.vanilla_cubed.util.wearsFullArmor
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.item.ArmorItem
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents

/**
 * Protects the wearer from harmful status effects.
 */
val HarmfulEffectProtectionBehavior = InventoryTickBehavior<ArmorItem> { item, params ->

    val (stack,world,entity,_,_) = params

    if (world == null || entity !is LivingEntity || !entity.wearsFullArmor(item.material) || !entity.armorItems.contains(stack))
        return@InventoryTickBehavior

    entity.statusEffects
        .filter { it.effectType.category == StatusEffectCategory.HARMFUL }
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

