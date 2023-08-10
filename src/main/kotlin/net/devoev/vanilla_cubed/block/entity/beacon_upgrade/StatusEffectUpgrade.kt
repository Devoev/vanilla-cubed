package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.devoev.vanilla_cubed.client.gui.screen.ingame.BeaconUpgradeTier
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity
import java.util.function.Predicate

/**
 * A beacon upgrade that applies the given [effect] to all entities of [entityType] in the range.
 * @property effect Status effect to apply.
 * @property amplifier Level of the effect.
 * @property predicate Filter fo entity selection.
 */
class StatusEffectUpgrade<out T : LivingEntity>(
    private val entityType: EntityType<out T>,
    private val effect: StatusEffect,
    private val amplifier: Int,
    private val predicate: Predicate<T>
) : BeaconUpgrade by tickUpgrade({ world, _, _ ->
    val tier = BeaconUpgradeTier.levelToTier(currentLevel)
    val duration: Int = (9 + tier * 2) * 20

    val entities = world.getEntitiesByType(entityType, beaconRange, predicate)
    for (entity in entities) {
        entity.addStatusEffect(StatusEffectInstance(effect, duration, amplifier, true, true))
    }
})

/**
 * Creates a [StatusEffectUpgrade] that applies to non spectating players.
 */
fun playerStatusEffectUpgradeOf(effect: StatusEffect, amplifier: Int)
    = StatusEffectUpgrade<PlayerEntity>(EntityType.PLAYER, effect, amplifier) { !it.isSpectator }

/**
 * Creates a [StatusEffectUpgrade] that applies to all entity predicate pairs in [selectors].
 */
fun <T : LivingEntity> statusEffectUpgradeOf(
    selectors: Map<EntityType<out T>, Predicate<T>>,
    effect: StatusEffect,
    amplifier: Int
): BeaconUpgrade {
    return selectors
        .map { StatusEffectUpgrade(it.key, effect, amplifier, it.value) }
        .reduce<BeaconUpgrade, StatusEffectUpgrade<T>> { acc, new -> acc andThen new }
}