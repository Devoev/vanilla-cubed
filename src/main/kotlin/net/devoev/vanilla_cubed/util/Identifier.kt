package net.devoev.vanilla_cubed.util

import net.minecraft.util.Identifier

/**
 * The texture [Identifier] of this id. Using path *textures/entity* and filetype *png*.
 */
val Identifier.texture get() = Identifier(namespace, "textures/entity/$path.png")