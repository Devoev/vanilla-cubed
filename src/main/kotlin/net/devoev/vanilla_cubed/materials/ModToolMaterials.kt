package net.devoev.vanilla_cubed.materials

import net.devoev.vanilla_cubed.item.ModItems
import net.minecraft.item.Item
import net.minecraft.item.ToolMaterial
import net.minecraft.item.ToolMaterials
import net.minecraft.recipe.Ingredient

/**
 * All modded tool materials.
 */
enum class ModToolMaterials(
    private val miningLevel: Int,
    private val itemDurability: Int,
    private val miningSpeed: Float,
    private val attackDamage: Float,
    private val enchantability: Int,
    private val repairIngredient: Ingredient
) : ToolMaterial {

    AMETHYST(miningLevel = 4, miningSpeed = 10F, repairItem = ModItems.CHARGED_AMETHYST_CRYSTAL),
    ANCIENT_GOLD(miningLevel = 4, enchantability = 22, repairItem = ModItems.ANCIENT_GOLD_INGOT),
    ENDERITE(miningLevel = 4, repairItem = ModItems.ENDERITE_INGOT),
    DRAGON_SCALE(miningLevel = 4, repairItem = ModItems.INFUSED_DRAGON_SCALE);

    /**
     * Constructs a new [ToolMaterial]. Default values are the stats of [ToolMaterials.DIAMOND].
     */
    constructor(miningLevel: Int = 3,
                itemDurability: Int = 1561,
                miningSpeed: Float = 8F,
                attackDamage: Float = 3F,
                enchantability: Int = 10,
                repairItem: Item)
            : this(miningLevel, itemDurability, miningSpeed, attackDamage, enchantability, Ingredient.ofItems(repairItem))

    override fun getDurability(): Int = itemDurability

    override fun getMiningSpeedMultiplier(): Float = miningSpeed

    override fun getAttackDamage(): Float = attackDamage

    override fun getMiningLevel(): Int = miningLevel

    override fun getEnchantability(): Int = enchantability

    override fun getRepairIngredient(): Ingredient = repairIngredient
}