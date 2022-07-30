package net.devoev.vanilla_cubed.item.behavior

import net.devoev.vanilla_cubed.util.isInCave
import net.minecraft.entity.Entity
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack
import net.minecraft.world.World

object MiningBonusBehavior : ConditionalBehavior<ArmorItem>(ApplyEffectBehavior(StatusEffects.HASTE)) {

    override fun condition(item: ArmorItem, stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean): Boolean
        = entity?.isInCave() ?: false
}