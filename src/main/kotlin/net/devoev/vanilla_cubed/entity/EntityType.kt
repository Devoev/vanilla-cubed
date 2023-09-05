package net.devoev.vanilla_cubed.entity

import net.devoev.vanilla_cubed.util.entityTexture
import net.minecraft.entity.EntityType
import net.minecraft.registry.Registries
import net.minecraft.util.Identifier

val EntityType<*>.id: Identifier
    get() = Registries.ENTITY_TYPE.getId(this)

val EntityType<*>.texture: Identifier
    get() = id.entityTexture