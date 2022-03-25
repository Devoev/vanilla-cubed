package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.util.RegistryManager
import net.minecraft.item.Item

/**
 * The set of all [mod items][ModItem].
 */
object ModItems : RegistryManager<Item, ModItem>() {

    val ELDER_GUARDIAN_SHARD = set(ElderGuardianShard)
}