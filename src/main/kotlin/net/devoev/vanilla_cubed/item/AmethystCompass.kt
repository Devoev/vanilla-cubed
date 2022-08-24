package net.devoev.vanilla_cubed.item

import net.minecraft.client.item.CompassAnglePredicateProvider.CompassTarget
import net.minecraft.client.render.DimensionEffects.Overworld
import net.minecraft.item.CompassItem
import net.minecraft.item.Item
import net.minecraft.server.world.ServerEntityManager
import net.minecraft.server.world.ServerWorld
import net.minecraft.tag.TagKey
import net.minecraft.util.math.GlobalPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.gen.structure.Structure.Context
import java.sql.Struct

object AmethystCompass : Item(ModItemGroup.TOOLS.toSettings()) {

    val target = CompassTarget { world, stack, entity -> CompassItem.createSpawnPos(world) }
}

val AmethystCompassTarget = CompassTarget { world, stack, entity ->
    if (world !is ServerWorld) return@CompassTarget null

    val pos = world.locateStructure(
        TagKey.of(Registry.STRUCTURE_KEY, Registry.STRUCTURE_KEY.registry),
        entity.blockPos,
        5,
        true
    )
    GlobalPos.create(world.toServerWorld().registryKey, pos)

    /*val positions = world.registryManager.get(Registry.STRUCTURE_KEY).entrySet
        .map { it.value.getStructurePosition(null).get().position }
        .map { GlobalPos.create(world.registryKey, it) }

    val distances = positions.map { Vec3d(it.pos.x.toDouble(), it.pos.y.toDouble(), it.pos.z.toDouble()) }
        .map { entity.pos.subtract(it).length() }

    (positions zip distances).minBy { it.second }.first*/
}