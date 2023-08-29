package net.devoev.vanilla_cubed.block.enums

import net.minecraft.block.enums.RailShape

/**
 * Whether this rail shape is curved and not straight.
 */
val RailShape.isCurved: Boolean
    get() = this == RailShape.SOUTH_EAST || this == RailShape.SOUTH_WEST || this == RailShape.NORTH_WEST || this == RailShape.NORTH_EAST