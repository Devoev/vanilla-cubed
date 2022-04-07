package net.devoev.vanilla_cubed.item

import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeInstance
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.ItemStack
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterial
import net.minecraft.world.World

/**
 * Applies the given [modifiers] if the [entity] has this item [selected].
 */
fun applyAttributeModifiers(entity: LivingEntity, selected: Boolean, modifiers: Map<EntityAttribute, EntityAttributeModifier>) {
    for (entry in modifiers) {
        val (attribute, modifier) = entry
        val attributeInstance: EntityAttributeInstance = entity.getAttributeInstance(attribute) ?: continue

        if (selected && !attributeInstance.hasModifier(modifier)) {
            attributeInstance.addTemporaryModifier(modifier)
        } else if (!selected && attributeInstance.hasModifier(modifier)) {
            attributeInstance.removeModifier(modifier)
        }
    }
}

class AttributeSwordItem(material: ToolMaterial, attackDamage: Int, attackSpeed: Float, settings: Settings,
                         private val modifiers: Map<EntityAttribute, EntityAttributeModifier>)
    : SwordItem(material, attackDamage, attackSpeed, settings) {

    override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        if (entity is LivingEntity) applyAttributeModifiers(entity, selected, modifiers)
    }
}