package net.devoev.vanilla_cubed.text

import net.devoev.vanilla_cubed.VanillaCubed
import net.minecraft.text.MutableText
import net.minecraft.text.Text
import net.minecraft.util.Util

/**
 * Creates a translatable text of the given [type] and [name] under the [VanillaCubed.id] namespace.
 */
fun translatableTextOf(type: String, name: String): MutableText
    = Text.translatable(Util.createTranslationKey(type, VanillaCubed.id(name)))