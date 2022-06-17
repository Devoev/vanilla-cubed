package net.devoev.vanilla_cubed.item.behavior

import net.devoev.vanilla_cubed.item.armor.ModArmor
import net.devoev.vanilla_cubed.util.wearsFullArmor
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack
import net.minecraft.world.World

/**
 * An [InventoryTickBehavior] that applies the given [effect], when the entity is wearing a full set of armor.
 * @see ModArmor.AMETHYST
 */
class ApplyEffectBehavior(val effect: StatusEffect) : InventoryTickBehavior<ArmorItem> {
    override fun inventoryTick(item: ArmorItem, stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        if (entity is LivingEntity && entity.wearsFullArmor(item.material))
            entity.addStatusEffect(StatusEffectInstance(effect))
    }
}