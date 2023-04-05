package net.devoev.vanilla_cubed.block.entity

import net.minecraft.block.BlockState
import net.minecraft.block.entity.BeaconBlockEntity
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos

class ModBeaconBlockEntity(pos: BlockPos, state: BlockState) : BeaconBlockEntity(pos, state), NamedScreenHandlerFactory {


    override fun getDisplayName(): Text = Text.literal("Modded Beacon")
}