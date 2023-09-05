package net.devoev.vanilla_cubed.screen

import net.devoev.vanilla_cubed.util.RegistryManager
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.registry.Registries
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.util.registry.Registry

@Environment(EnvType.CLIENT)
object ModScreenHandlerTypes : RegistryManager<ScreenHandlerType<out ScreenHandler>>(Registries.SCREEN_HANDLER) {

    val MOD_BEACON = create("mod_beacon", ScreenHandlerType(::ModBeaconScreenHandler))
}