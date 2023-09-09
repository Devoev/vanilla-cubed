package net.devoev.vanilla_cubed.item.modifier

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeInstance
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.Item

/**
 * An [InventoryTickModifier] that applies the [modifier] to the [attribute] of the entity holding the specified item.
 */
@Deprecated("Apply status effects instead of attributes manually", replaceWith = ReplaceWith("ApplyToolStatusEffectBehavior"))
class ApplyAttributeItem(val attribute: EntityAttribute, val modifier: EntityAttributeModifier)
    : InventoryTickModifier<Item> {

    override fun accept(item: Item, params: InventoryTickParams) {
        if (params.world!!.isClient) return
        val (stack,_,entity,_,selected) = params

        if (entity !is LivingEntity) return
        val sel = selected && entity.offHandStack != stack

        val attributeInstance: EntityAttributeInstance = entity.getAttributeInstance(attribute) ?: return

        if (sel && !attributeInstance.hasModifier(modifier)) {
            attributeInstance.addTemporaryModifier(modifier)
        } else if (!sel && attributeInstance.hasModifier(modifier) && !selectedSameModifier(entity, attribute)) {
            attributeInstance.removeModifier(modifier)
        }
    }

    /**
     * Returns whether the given [entity] has an item selected, that changes the [attribute] value.
     */
    private fun selectedSameModifier(entity: LivingEntity, attribute: EntityAttribute): Boolean {
        val item = entity.getStackInHand(entity.activeHand).item
        if (item !is Behaviors<*>) return false

        val behavior = item.inventoryTickModifier
        return behavior is ApplyAttributeItem && behavior.attribute == attribute
    }
}