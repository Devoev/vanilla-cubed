package net.devoev.vanilla_cubed.client.gui.screen.ingame

/**
 * A beacon upgrade of a given [tier] and [type].
 * @property tier Value between 0 and 4. A value of 0 means the upgrade is always activated.
 * @property type Block upgrade category.
 */
data class BeaconUpgradeTier(val tier: Int, val type: Type) {

    /**
     * Checks whether the given [level] is high enough to activate the upgrade for this tier.
     * @return True, if [level] is high enough.
     */
    private fun checkLevel(level: Int): Boolean {
        return when(tier) {
            1 -> level >= 9
            2 -> level >= 34
            3 -> level >= 83
            4 -> level >= 164
            else -> true
        }
    }

    /**
     * Checks whether the given [levels] values are high enough to activate the upgrade for this tier.
     * @return True, if [levels] are high enough.
     */
    fun checkLevel(levels: IntArray): Boolean {
        return when(type) {
            Type.IRON -> checkLevel(levels[0])
            Type.GOLD -> checkLevel(levels[1])
            Type.EMERALD -> checkLevel(levels[2])
            Type.DIAMOND -> checkLevel(levels[3])
            else -> checkLevel(0)
        }
    }

    init {
        assert(tier in 0..4) { "Tier $tier must be between 0 and 4!" }
    }

    enum class Type {
        IRON, GOLD, EMERALD, DIAMOND, NONE
    }

    companion object {

        /**
         * An empty [BeaconUpgradeTier].
         */
        val EMPTY = BeaconUpgradeTier(0, Type.NONE)
    }
}

/**
 * Creates a [BeaconUpgradeTier] of type [BeaconUpgradeTier.Type.IRON] and the given [tier].
 */
fun ironTierOf(tier: Int) = BeaconUpgradeTier(tier, BeaconUpgradeTier.Type.IRON)

/**
 * Creates a [BeaconUpgradeTier] of type [BeaconUpgradeTier.Type.GOLD] and the given [tier].
 */
fun goldTierOf(tier: Int) = BeaconUpgradeTier(tier, BeaconUpgradeTier.Type.GOLD)

/**
 * Creates a [BeaconUpgradeTier] of type [BeaconUpgradeTier.Type.EMERALD] and the given [tier].
 */
fun emeraldTierOf(tier: Int) = BeaconUpgradeTier(tier, BeaconUpgradeTier.Type.EMERALD)

/**
 * Creates a [BeaconUpgradeTier] of type [BeaconUpgradeTier.Type.DIAMOND] and the given [tier].
 */
fun diamondTierOf(tier: Int) = BeaconUpgradeTier(tier, BeaconUpgradeTier.Type.DIAMOND)
