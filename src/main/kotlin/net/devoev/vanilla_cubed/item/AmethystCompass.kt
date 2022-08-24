package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.world.gen.structure.StructureHelper
import net.minecraft.client.item.CompassAnglePredicateProvider.CompassTarget
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.tag.TagKey
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.math.GlobalPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.registry.Registry
import net.minecraft.world.World
import net.minecraft.world.gen.structure.StructureKeys
import net.minecraft.world.gen.structure.Structures

object AmethystCompass : Item(ModItemGroup.TOOLS.toSettings()) {

    var targetPos: GlobalPos? = null

    val compassTarget = CompassTarget { _,_,_ -> targetPos }

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        if (world !is ServerWorld || user == null) return super.use(world, user, hand)

        val positions = StructureHelper.tagKeys
            .mapNotNull { world.locateStructure(it, user.blockPos, 15, false) }
            .map { GlobalPos.create(world.registryKey, it) }

        val distances = positions
            .map { Vec3d(it.pos.x.toDouble(), it.pos.y.toDouble(), it.pos.z.toDouble()) }
            .map { user.pos.subtract(it).length() }

        targetPos = (positions zip distances).minBy { it.second }.first

        return super.use(world, user, hand)
    }
}