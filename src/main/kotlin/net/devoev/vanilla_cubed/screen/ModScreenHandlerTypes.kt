package net.devoev.vanilla_cubed.screen

import net.devoev.vanilla_cubed.util.RegistryManager
import net.minecraft.registry.Registries
import net.minecraft.resource.featuretoggle.FeatureFlags
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerType

/**
 * All modded screen handler types.
 */
@Suppress("unused")
object ModScreenHandlerTypes : RegistryManager<ScreenHandlerType<out ScreenHandler>>(Registries.SCREEN_HANDLER) {

    val MOD_BEACON = create("mod_beacon", screenHandlerTypeOf(::ModBeaconScreenHandler))
}

/**
 * Creates a [ScreenHandlerType] from the given [factory].
 */
fun <T : ScreenHandler> screenHandlerTypeOf(factory: ScreenHandlerType.Factory<T>) = ScreenHandlerType(factory, FeatureFlags.VANILLA_FEATURES)