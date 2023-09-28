package net.devoev.vanilla_cubed

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import me.shedaniel.autoconfig.AutoConfig
import net.devoev.vanilla_cubed.config.ModConfig

object VanillaCubedModMenu : ModMenuApi {

    override fun getModConfigScreenFactory(): ConfigScreenFactory<*> {
        return ConfigScreenFactory { AutoConfig.getConfigScreen(ModConfig::class.java, it).get() }
    }
}