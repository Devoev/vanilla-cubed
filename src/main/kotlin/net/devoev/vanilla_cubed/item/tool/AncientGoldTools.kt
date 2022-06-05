package net.devoev.vanilla_cubed.item.tool

import net.devoev.vanilla_cubed.entity.effect.StatusEffectHelper
import net.devoev.vanilla_cubed.item.ModItemGroup
import net.devoev.vanilla_cubed.item.toSettings
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.ItemStack
import kotlin.random.Random

private val ancientGoldData = ToolData(material = ModToolMaterials.ANCIENT_GOLD, settings = ModItemGroup.TOOLS.toSettings())

/**
 * Applies a random harmful [StatusEffectInstance] to the given target with a 10% probability.
 */
private fun applyHarmfulEffect(target: LivingEntity?): Unit {
    val effect = StatusEffectHelper.randomHarmful(100..200, 0..2)
    if (Random.nextDouble() < 0.9 || effect.effectType.isInstant) return
    target?.addStatusEffect(effect)
}

object AncientGoldSword : ModSwordItem(ancientGoldData) {

    override fun postHit(stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean {
        applyHarmfulEffect(target)
        return super.postHit(stack, target, attacker)
    }
}