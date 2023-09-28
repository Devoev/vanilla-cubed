package net.devoev.vanilla_cubed.config

import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer

@Config(name = "vanilla_cubed")
class ModConfig : ConfigData {

    var overrideTotemMechanics = true
    var elderGuardianShardDrop = true
    var overrideBeaconMechanics = true

    companion object {

        fun get(): ModConfig = AutoConfig.getConfigHolder(ModConfig::class.java).config

        fun init() {
            AutoConfig.register(ModConfig::class.java, ::GsonConfigSerializer)
        }
    }
}