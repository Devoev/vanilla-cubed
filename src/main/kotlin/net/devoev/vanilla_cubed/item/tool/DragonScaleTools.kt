package net.devoev.vanilla_cubed.item.tool

import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes
import net.devoev.vanilla_cubed.item.ModItemGroup
import net.devoev.vanilla_cubed.item.toSettings
import net.minecraft.entity.Entity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.AxeItem
import net.minecraft.item.HoeItem
import net.minecraft.item.ItemStack
import net.minecraft.item.PickaxeItem
import net.minecraft.item.ShovelItem
import net.minecraft.item.SwordItem
import net.minecraft.world.World

object DragonScaleTools : ToolBuilder(material = ModToolMaterials.DRAGON_SCALE, settings = ModItemGroup.TOOLS.toSettings()) {

    private val modifiers = mapOf(
        ReachEntityAttributes.REACH to
        EntityAttributeModifier("modifier", 1.5, EntityAttributeModifier.Operation.ADDITION)
    )

    override val sword: SwordItem = object : ModSwordItem(data), AttributeToolItem {
        override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
            applyModifiers(stack, entity, selected)
            super.inventoryTick(stack, world, entity, slot, selected)
        }

        override val modifiers: Map<EntityAttribute, EntityAttributeModifier> = this@DragonScaleTools.modifiers
    }

    override val shovel: ShovelItem = object : ModShovelItem(data), AttributeToolItem {
        override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
            applyModifiers(stack, entity, selected)
            super.inventoryTick(stack, world, entity, slot, selected)
        }

        override val modifiers: Map<EntityAttribute, EntityAttributeModifier> = this@DragonScaleTools.modifiers
    }

    override val pickaxe: PickaxeItem = object : ModPickaxeItem(data), AttributeToolItem {
        override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
            applyModifiers(stack, entity, selected)
            super.inventoryTick(stack, world, entity, slot, selected)
        }

        override val modifiers: Map<EntityAttribute, EntityAttributeModifier> = this@DragonScaleTools.modifiers
    }

    override val axe: AxeItem = object : ModAxeItem(data), AttributeToolItem {
        override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
            applyModifiers(stack, entity, selected)
            super.inventoryTick(stack, world, entity, slot, selected)
        }

        override val modifiers: Map<EntityAttribute, EntityAttributeModifier> = this@DragonScaleTools.modifiers
    }

    override val hoe: HoeItem = object : ModHoeItem(data), AttributeToolItem {
        override fun inventoryTick(stack: ItemStack?, world: World?, entity: Entity?, slot: Int, selected: Boolean) {
            applyModifiers(stack, entity, selected)
            super.inventoryTick(stack, world, entity, slot, selected)
        }

        override val modifiers: Map<EntityAttribute, EntityAttributeModifier> = this@DragonScaleTools.modifiers
    }
}