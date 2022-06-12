package net.devoev.vanilla_cubed.item.behavior

import net.minecraft.item.Item

/**
 * An [Item] whose behavior can be changed trough composition.
 */
interface BehaviorComposition<in T : Item> {

    /**
     * The set of modified behaviors.
     */
    val behaviors: ItemBehaviors<T>
}