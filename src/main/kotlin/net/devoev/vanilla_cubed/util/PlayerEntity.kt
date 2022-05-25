package net.devoev.vanilla_cubed.util

import net.devoev.vanilla_cubed.item.armor.ModArmorMaterials
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ArmorMaterial

/**
 * Returns true, if the player wears a full set of armor of the given [armorMaterial].
 */
fun PlayerEntity.wearsFullArmor(armorMaterial: ArmorMaterial) = armorItems.all { it.item.isMadeOf(armorMaterial) }

/**
 * Returns true, if the player wears a full set of [ModArmorMaterials.ENDERITE] armor.
 */
fun PlayerEntity.wearsEnderite() = wearsFullArmor(ModArmorMaterials.ENDERITE)

/**
 * Returns true, if the player wears a full set of [ModArmorMaterials.ANCIENT_GOLD] armor.
 */
fun PlayerEntity.wearsAncientGold() = wearsFullArmor(ModArmorMaterials.ANCIENT_GOLD)

/**
 * Returns true, if the player wears a full set of [ModArmorMaterials.DRAGON_SCALE] armor.
 */
fun PlayerEntity.wearsDragonScale() = wearsFullArmor(ModArmorMaterials.DRAGON_SCALE)

/**
 * Returns true, if the player wears a full set of [ModArmorMaterials.AMETHYST] armor.
 */
fun PlayerEntity.wearsAmethyst() = wearsFullArmor(ModArmorMaterials.AMETHYST)