package net.devoev.vanilla_cubed.loot

import net.devoev.vanilla_cubed.item.ModItems
import net.devoev.vanilla_cubed.util.MapInitializer
import net.fabricmc.fabric.api.loot.v2.LootTableEvents
import net.minecraft.loot.LootPool
import net.minecraft.util.Identifier

/**
 * An object for initializing a custom loot table.
 */
object ModLootTables : MapInitializer<Identifier, LootPool.Builder>() {

    val ELDER_GUARDIAN = create("entities/elder_guardian") {
        constantRolls(1f)
        with(ModItems.ELDER_GUARDIAN_SHARD)
    }

    val BASTION_TREASURE = create("chests/bastion_treasure") {
        constantRolls(1f)
        with(ModItems.ANCIENT_GOLD_UPGRADE_SMITHING_TEMPLATE)
    }

    val BASTION_HOGLIN_STABLE = create("chests/bastion_hoglin_stable") {
        constantRolls(1f)
        withEmpty(9)
        with(ModItems.ANCIENT_GOLD_UPGRADE_SMITHING_TEMPLATE)
    }

    val BASTION_BRIDGE = create("chests/bastion_bridge", BASTION_HOGLIN_STABLE)

    val BASTION_OTHER = create("chests/bastion_other", BASTION_HOGLIN_STABLE)

    /**
     * Creates a new entry of the given [builder] under the id specified by [name].
     * Uses the `minecraft` namespace for the id.
     */
    fun create(name: String, builder: LootPool.Builder) = create(Identifier("minecraft", name), builder)

    /**
     * Creates a new entry of using the given [builderAction].
     */
    fun create(name: String, builderAction: LootPool.Builder.() -> Unit) = create(name, buildLootPool(builderAction))

    /**
     * Adds the given [builder] to this. Uses the `minecraft` namespace for the id.
     */
    operator fun set(name: String, builder: LootPool.Builder) = set(Identifier("minecraft", name), builder)

    /**
     * Registers all loot table callbacks.
     */
    override fun init() {
        LootTableEvents.MODIFY.register { _, _, id, table, _ ->
            if (contains(id)) table.pool(this[id])
        }
    }
}