package net.devoev.vanilla_cubed.block.entity.beacon_upgrade

import net.devoev.vanilla_cubed.block.entity.ModBeaconBlockEntity
import net.minecraft.block.BlockState
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.world.World

/**
 * A beacon upgrade that applies the given [effect] to all players in the range.
 */
class StatusEffectUpgrade(private val effect: StatusEffect) : BeaconUpgrade {
    override fun invoke(world: World, pos: BlockPos, state: BlockState, blockEntity: ModBeaconBlockEntity) {
        //TODO: Update implementation
        if (world.isClient) {
            return
        }
        val beaconLevel = 4
        val d: Double = (beaconLevel * 10 + 10).toDouble()
        val i = 0
        val j: Int = (9 + beaconLevel * 2) * 20
        val box = Box(pos).expand(d).stretch(0.0, world.height.toDouble(), 0.0)
        val list = world.getNonSpectatingEntities(PlayerEntity::class.java, box)
        for (playerEntity in list) {
            playerEntity.addStatusEffect(StatusEffectInstance(effect, j, i, true, true))
        }
    }
}