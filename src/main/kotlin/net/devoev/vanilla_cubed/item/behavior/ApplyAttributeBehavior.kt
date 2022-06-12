package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeInstance
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolItem
import net.minecraft.world.World

/**
 * An [InventoryTickBehavior] that applies the [modifier] to the [attribute] of the entity holding the specified item.
 */
class ApplyAttributeBehavior(val attribute: EntityAttribute, val modifier: EntityAttributeModifier)
    : InventoryTickBehavior<ToolItem> {

    override fun invoke(item: ToolItem, stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
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
        if (item !is BehaviorComposition<*>) return false

        val behavior = item.behaviors.inventoryTickBehavior
        return behavior is ApplyAttributeBehavior && behavior.attribute == attribute
        // return item is AttributeToolItem && item.modifiers?.contains(attribute) ?: false
    }
}