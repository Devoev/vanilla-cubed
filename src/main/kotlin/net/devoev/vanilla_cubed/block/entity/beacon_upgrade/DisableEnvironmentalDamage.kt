package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

/**
 * A beacon upgrade that prevents environmental damage like fire spread.
 */
val disableEnvironmentalDamage = beaconUpgrade {

    activate {
        println("active!")
    }

    deactivate {
        println("de-active!")
    }
}