package net.devoev.vanilla_cubed.client.gui.screen.ingame

/**
 * A beacon upgrade tier of a given [value] and [type].
 * @property value Value between 0 and 4. A value of 0 means the upgrade is always activated.
 * @property type Block upgrade category.
 */
data class BeaconUpgradeTier(val value: Int, val type: Type) {

    /**
     * Checks whether the given [level] is high enough to activate the upgrade for this tier.
     * @return True, if [level] is high enough.
     */
    fun checkLevel(level: Int): Boolean {
        return tierToLevel(value) <= level
    }

    /**
     * Checks whether the given [levels] values are high enough to activate the upgrade for this tier.
     * @return True, if [levels] are high enough.
     */
    fun checkLevel(levels: IntArray): Boolean {
        return if (type==Type.NONE) true
        else checkLevel(levels[type.idx])
    }

    init {
        require(value in 0..4) { "Value $value must be between 0 and 4!" }
    }

    /**
     * Block category type.
     * @property idx The index in the levels arrays.
     */
    enum class Type(val idx: Int) {
        IRON(0), GOLD(1), EMERALD(2), DIAMOND(3), NONE(-1)
    }

    companion object {

        /**
         * An empty [BeaconUpgradeTier].
         */
        val EMPTY = BeaconUpgradeTier(0, Type.NONE)

        /**
         * The required level for the given [tier].
         */
        fun tierToLevel(tier: Int): Int {
            return when(tier) {
                1 -> 9
                2 -> 34
                3 -> 83
                4 -> 164
                else -> -1
            }
        }

        /**
         * The tier the given [level] corresponds to.
         */
        fun levelToTier(level: Int): Int {
            return when(level) {
                in 0..<9 -> 0
                in 9..<34 -> 1
                in 34..<83 -> 2
                in 83..<164 -> 3
                164 -> 4
                else -> -1
            }
        }
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
