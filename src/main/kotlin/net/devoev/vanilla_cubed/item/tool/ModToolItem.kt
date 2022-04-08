package net.devoev.vanilla_cubed.item.tool

import net.minecraft.entity.Entity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.*
import net.minecraft.world.World


class ModSwordItem(material: ToolMaterial, attackDamage: Int, attackSpeed: Float, settings: Settings,
                   override val modifiers: Map<EntityAttribute, EntityAttributeModifier>? = null)
    : SwordItem(material, attackDamage, attackSpeed, settings), AttributeToolItem {

    override fun inventoryTick(stack: ItemStack, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        super<AttributeToolItem>.inventoryTick(stack, entity, selected)
    }
}

class ModShovelItem(material: ToolMaterial, attackDamage: Float, attackSpeed: Float, settings: Settings,
                    override val modifiers: Map<EntityAttribute, EntityAttributeModifier>? = null)
    : ShovelItem(material, attackDamage, attackSpeed, settings), AttributeToolItem {

    override fun inventoryTick(stack: ItemStack, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        super<AttributeToolItem>.inventoryTick(stack, entity, selected)
    }
}

class ModPickaxeItem(material: ToolMaterial, attackDamage: Int, attackSpeed: Float, settings: Settings,
                     override val modifiers: Map<EntityAttribute, EntityAttributeModifier>? = null)
    : PickaxeItem(material, attackDamage, attackSpeed, settings), AttributeToolItem {

    override fun inventoryTick(stack: ItemStack, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        super<AttributeToolItem>.inventoryTick(stack, entity, selected)
    }
}

class ModAxeItem(material: ToolMaterial, attackDamage: Float, attackSpeed: Float, settings: Settings,
                 override val modifiers: Map<EntityAttribute, EntityAttributeModifier>? = null)
    : AxeItem(material, attackDamage, attackSpeed, settings), AttributeToolItem {

    override fun inventoryTick(stack: ItemStack, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        super<AttributeToolItem>.inventoryTick(stack, entity, selected)
    }
}

class ModHoeItem(material: ToolMaterial, attackDamage: Int, attackSpeed: Float, settings: Settings,
                 override val modifiers: Map<EntityAttribute, EntityAttributeModifier>? = null)
    : HoeItem(material, attackDamage, attackSpeed, settings), AttributeToolItem {

    override fun inventoryTick(stack: ItemStack, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
        super<AttributeToolItem>.inventoryTick(stack, entity, selected)
    }
}