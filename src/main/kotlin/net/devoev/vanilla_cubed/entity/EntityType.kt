package net.devoev.vanilla_cubed.entity

import net.devoev.vanilla_cubed.util.entityTexture
import net.minecraft.entity.EntityType
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

val EntityType<*>.id: Identifier
    get() = Registry.ENTITY_TYPE.getId(this)

val EntityType<*>.entityTexture: Identifier
    get() = id.entityTexture