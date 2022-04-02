package net.devoev.vanilla_cubed.item

import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ItemStack
import net.minecraft.world.World

/**
 * @property effect An optional full armor effect.
 */
class ModArmor(material: ArmorMaterial, slot: EquipmentSlot, settings: Settings, private val effect: StatusEffect? = null) : ArmorItem(material, slot, settings) {

    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        if (!world.isClient && entity is PlayerEntity && fullArmor(entity))
            entity.addStatusEffect(StatusEffectInstance(effect))

        super.inventoryTick(stack, world, entity, slot, selected)
    }

    /**
     * Returns true, if the given player has the full set of this armor equipped.
     */
    private fun fullArmor(player: PlayerEntity): Boolean = player.armorItems.map { it.item }.all { it is ArmorItem && it.material == material }
}