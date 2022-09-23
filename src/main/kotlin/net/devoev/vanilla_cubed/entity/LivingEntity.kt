package net.devoev.vanilla_cubed.util

import net.devoev.vanilla_cubed.item.armor.ModArmorMaterials
import net.devoev.vanilla_cubed.item.isMadeOf
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ArmorMaterials

/**
 * Returns true, if the entity wears a full set of armor of the given [armorMaterial].
 */
fun LivingEntity.wearsFullArmor(armorMaterial: ArmorMaterial) = armorItems.all { it.item.isMadeOf(armorMaterial) }

/**
 * Returns true, if the entity wears a full set of [ModArmorMaterials.ENDERITE] armor.
 */
fun LivingEntity.wearsEnderite() = wearsFullArmor(ModArmorMaterials.ENDERITE)

/**
 * Returns true, if the entity wears a full set of [ModArmorMaterials.ANCIENT_GOLD] armor.
 */
fun LivingEntity.wearsAncientGold() = wearsFullArmor(ModArmorMaterials.ANCIENT_GOLD)

/**
 * Returns true, if the entity wears a full set of [ModArmorMaterials.DRAGON_SCALE] armor.
 */
fun LivingEntity.wearsDragonScale() = wearsFullArmor(ModArmorMaterials.DRAGON_SCALE)

/**
 * Returns true, if the entity wears a full set of [ModArmorMaterials.AMETHYST] armor.
 */
fun LivingEntity.wearsAmethyst() = wearsFullArmor(ModArmorMaterials.AMETHYST)

/**
 * Returns true, if the entity wears a full set of [ArmorMaterials.NETHERITE] armor.
 */
fun LivingEntity.wearsNetherite() = wearsFullArmor(ArmorMaterials.NETHERITE)