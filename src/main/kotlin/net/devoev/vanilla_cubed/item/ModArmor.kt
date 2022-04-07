package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.util.isMadeOf
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ItemStack
import net.minecraft.world.World

/**
 * Armor that has a special full armor effect.
 */
abstract class ModArmor(material: ArmorMaterial, slot: EquipmentSlot, settings: Settings) : ArmorItem(material, slot, settings) {

    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        if (!world.isClient && entity is PlayerEntity && fullArmor(entity))
            applyFullArmorEffect(entity)

        super.inventoryTick(stack, world, entity, slot, selected)
    }

    /**
     * Returns true, if the given player has the full set of this armor equipped.
     */
    private fun fullArmor(player: PlayerEntity): Boolean = player.armorItems.all { it.item.isMadeOf(material) }

    /**
     * A full armor effect.
     */
    abstract fun applyFullArmorEffect(player: PlayerEntity)
}