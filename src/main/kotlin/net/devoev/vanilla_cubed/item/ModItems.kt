package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.util.RegistryManager
import net.minecraft.item.Item
import net.minecraft.util.registry.Registry

/**
 * The set of all mod items.
 */
object ModItems : RegistryManager<Item>(Registry.ITEM) {

    val ELDER_GUARDIAN_SHARD = create("elder_guardian_shard", ElderGuardianShard)
}