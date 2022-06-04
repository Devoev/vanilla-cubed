package net.devoev.vanilla_cubed.item.tool

import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeInstance
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolItem

/**
 * A [ToolItem] that modifies [attributes][EntityAttribute] of the entity holding the item.
 */
interface AttributeToolItem {

    /**
     * The applied modifiers.
     */
    val modifiers: Map<EntityAttribute, EntityAttributeModifier>?

    /**
     * Applies the [modifiers] to the [entity] each tick if tool is [selected].
     */
    fun inventoryTick(stack: ItemStack, entity: Entity?, selected: Boolean) {
        if (entity !is LivingEntity || modifiers == null) return
        val sel = selected && entity.offHandStack != stack

        for (entry in modifiers!!) {
            val (attribute, modifier) = entry
            val attributeInstance: EntityAttributeInstance = entity.getAttributeInstance(attribute) ?: continue

            if (sel && !attributeInstance.hasModifier(modifier)) {
                attributeInstance.addTemporaryModifier(modifier)
            } else if (!sel && attributeInstance.hasModifier(modifier) && !selectedSameModifier(entity, attribute)) {
                attributeInstance.removeModifier(modifier)
            }
        }
    }

    /**
     * Returns whether the given [entity] has a [AttributeToolItem] selected, that changes the [attribute] value.
     */
    private fun selectedSameModifier(entity: LivingEntity, attribute: EntityAttribute): Boolean {
        val item = entity.getStackInHand(entity.activeHand).item
        return item is AttributeToolItem && item.modifiers?.contains(attribute) ?: false
    }
}