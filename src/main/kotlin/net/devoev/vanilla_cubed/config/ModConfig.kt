package net.devoev.vanilla_cubed.config

import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer

@Config(name = "vanilla_cubed")
class ModConfig : ConfigData {

    /**
     * Whether to override the vanilla totem mechanics.
     */
    var overrideTotemMechanics = true

    /**
     * Whether Elder Guardians should drop Elder Guardian Shards
     */
    var elderGuardianShardDrop = true

    /**
     * Whether the vanilla Beacons should be replaced with the modded version.
     */
    var overrideBeaconMechanics = true

    companion object {

        /**
         * The config instance.
         */
        val config: ModConfig
            get() = AutoConfig.getConfigHolder(ModConfig::class.java).config

        fun init() {
            AutoConfig.register(ModConfig::class.java, ::GsonConfigSerializer)
        }
    }
}