package net.devoev.vanilla_cubed.loot

/**
 * An object for initializing all [loot table callbacks][ModLootTableCallback].
 */
object ModLootTableCallbacks {

    /**
     * Registers all loot table callbacks.
     */
    fun init() {
        ElderGuardianLootTable.register()
    }
}