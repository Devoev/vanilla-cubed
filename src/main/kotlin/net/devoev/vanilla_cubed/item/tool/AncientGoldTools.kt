package net.devoev.vanilla_cubed.item.tool

import net.devoev.vanilla_cubed.item.ModItemGroup
import net.devoev.vanilla_cubed.item.toSettings
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectUtil
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.ItemStack

private val ancientGoldData = ToolData(material = ModToolMaterials.ANCIENT_GOLD, settings = ModItemGroup.TOOLS.toSettings())

private fun applyHarmfulEffect(target: LivingEntity?): Unit {
    TODO("add StatusEffectHelper")
}

object AncientGoldSword : ModSwordItem(ancientGoldData) {

    override fun postHit(stack: ItemStack?, target: LivingEntity?, attacker: LivingEntity?): Boolean {
        applyHarmfulEffect(target)
        return super.postHit(stack, target, attacker)
    }
}