package net.devoev.vanilla_cubed.block.entity.beacon.upgrades

import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.effect.StatusEffects
import java.util.function.Predicate
import kotlin.reflect.KVisibility
import kotlin.reflect.full.staticProperties

/**
 * Selectors for entity types that are either in [SpawnGroup.CREATURE] or [SpawnGroup.MONSTER].
 */
private val selectors: Map<EntityType<out LivingEntity>, Predicate<LivingEntity>> = EntityType::class.staticProperties
    .filter { it.visibility == KVisibility.PUBLIC }
    .map { it.get() }
    .filterIsInstance<EntityType<out LivingEntity>>()
    .filter { it.spawnGroup == SpawnGroup.CREATURE || it.spawnGroup == SpawnGroup.MONSTER }
    .associateWith { Predicate { true } }

/**
 * Makes all nearby mobs glowing.
 */
val GlowingUpgrade = statusEffectUpgradeOf(selectors, StatusEffects.GLOWING, 0)