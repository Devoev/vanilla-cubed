package net.devoev.vanilla_cubed.item.tool

import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeInstance
import net.minecraft.entity.attribute.EntityAttributeModifier
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
    fun inventoryTick(entity: Entity?, selected: Boolean) {
        if (entity !is LivingEntity || modifiers == null) return
        for (entry in modifiers!!) {
            val (attribute, modifier) = entry
            val attributeInstance: EntityAttributeInstance = entity.getAttributeInstance(attribute) ?: continue

            if (selected && !attributeInstance.hasModifier(modifier)) {
                attributeInstance.addTemporaryModifier(modifier)
            } else if (!selected && attributeInstance.hasModifier(modifier)) {
                attributeInstance.removeModifier(modifier)
            }
        }
    }
}