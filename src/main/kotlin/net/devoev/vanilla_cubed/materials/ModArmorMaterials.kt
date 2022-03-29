package net.devoev.vanilla_cubed.materials

import net.devoev.vanilla_cubed.item.ModItems
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents

enum class ArmorMaterials(
    private val _name: String,
    private val durabilityMultiplier: Int,
    private val protectionAmounts: IntArray,
    private val enchantability: Int,
    private val equipSound: SoundEvent,
    private val toughness: Float,
    private val knockbackResistance: Float,
    private val repairIngredient: Ingredient
) : ArmorMaterial {

    ANCIENT_GOLD("ancient_gold", 33, intArrayOf(3, 6, 8, 3), 10, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 2.0f, 0.0f, ModItems.ANCIENT_GOLD_INGOT);

    constructor(name: String, durabilityMultiplier: Int, protectionAmounts: IntArray, enchantability: Int, equipSound: SoundEvent, toughness: Float, knockbackResistance: Float, vararg repairItems: Item)
        : this(name, durabilityMultiplier, protectionAmounts, enchantability, equipSound, toughness, knockbackResistance, Ingredient.ofItems(*repairItems))

    override fun getDurability(slot: EquipmentSlot): Int = BASE_DURABILITY[slot.entitySlotId] * durabilityMultiplier

    override fun getProtectionAmount(slot: EquipmentSlot): Int = protectionAmounts[slot.entitySlotId]

    override fun getEnchantability(): Int = enchantability

    override fun getEquipSound(): SoundEvent = equipSound

    override fun getRepairIngredient(): Ingredient = repairIngredient

    override fun getName(): String = _name

    override fun getToughness(): Float = toughness

    override fun getKnockbackResistance(): Float = knockbackResistance

    companion object {
        private val BASE_DURABILITY: IntArray = intArrayOf(13, 15, 16, 11)
    }
}