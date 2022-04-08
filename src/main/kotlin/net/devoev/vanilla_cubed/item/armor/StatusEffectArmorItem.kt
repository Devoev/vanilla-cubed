package net.devoev.vanilla_cubed.item.armor

import net.devoev.vanilla_cubed.util.isMadeOf
import net.minecraft.entity.Entity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.world.World

/**
 * An [ArmorItem] that gives the player a [StatusEffect] when wearing the full set of armor.
 */
interface StatusEffectArmorItem {

    /**
     * The effect to apply
     */
    val statusEffect: StatusEffectInstance?

    /**
     * Applies the [statusEffect] the the player if he wears a full set of armor.
     */
    fun inventoryTick(world: World, entity: Entity, material: ArmorMaterial) {
        if (!world.isClient && entity is PlayerEntity && fullArmor(entity, material) && statusEffect != null)
            entity.addStatusEffect(statusEffect)
    }

    /**
     * Returns true, if the given player has the full set of this armor equipped.
     */
    private fun fullArmor(player: PlayerEntity, material: ArmorMaterial): Boolean = player.armorItems.all { it.item.isMadeOf(material) }
}