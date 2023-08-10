package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.passive.WolfEntity
import java.util.function.Predicate

/**
 * Map of all entities that are effected by strength.
 */
private val attacking: Map<EntityType<out LivingEntity>, Predicate<LivingEntity>> = mapOf(
    EntityType.IRON_GOLEM to Predicate { true },
    EntityType.WOLF to Predicate { (it as WolfEntity).isTamed }
)

/**
 * Map of all entities that are effected by regeneration.
 */
private val passive: Map<EntityType<out LivingEntity>, Predicate<LivingEntity>> = mapOf(
    EntityType.SNOW_GOLEM to Predicate { true },
    EntityType.VILLAGER to Predicate { true },
    EntityType.ALLAY to Predicate { true },
    EntityType.HORSE to Predicate { true },
    EntityType.MULE to Predicate { true },
    EntityType.DONKEY to Predicate { true },
    EntityType.LLAMA to Predicate { true }
)

/**
 * Creates and upgrade that increases the attack damage and health of allied mobs by given them strength and regeneration.
 */
val strengthenAlliedMobsUpgrade
    = statusEffectUpgradeOf(attacking, StatusEffects.STRENGTH, 1) andThen
        statusEffectUpgradeOf(attacking + passive, StatusEffects.REGENERATION, 1)