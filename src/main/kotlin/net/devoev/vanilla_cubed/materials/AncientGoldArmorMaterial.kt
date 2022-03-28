package net.devoev.vanilla_cubed.materials

import net.devoev.vanilla_cubed.item.ModItems
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents

object AncientGoldArmorMaterial : ArmorMaterial {

    val BASE_DURABILITY = listOf(13, 15, 16, 11)

    val PROTECTION_VALUES = listOf(3, 6, 8, 3)

    override fun getDurability(slot: EquipmentSlot?): Int = BASE_DURABILITY[slot?.entitySlotId!!] * 33

    override fun getProtectionAmount(slot: EquipmentSlot?): Int = PROTECTION_VALUES[slot?.entitySlotId!!]

    override fun getEnchantability(): Int = 10

    override fun getEquipSound(): SoundEvent = SoundEvents.ITEM_ARMOR_EQUIP_GOLD

    override fun getRepairIngredient(): Ingredient = Ingredient.ofItems(ModItems.ANCIENT_GOLD_INGOT)

    override fun getName(): String = "ancient_gold"

    override fun getToughness(): Float = 2F

    override fun getKnockbackResistance(): Float = 0F
}