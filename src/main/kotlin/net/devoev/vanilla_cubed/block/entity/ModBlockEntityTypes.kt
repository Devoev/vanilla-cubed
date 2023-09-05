package net.devoev.vanilla_cubed.block.entity

import net.devoev.vanilla_cubed.util.RegistryManager
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.registry.Registries

object ModBlockEntityTypes : RegistryManager<BlockEntityType<out BlockEntity>>(Registries.BLOCK_ENTITY_TYPE) {

    val MOD_BEACON = create("mod_beacon", ::ModBeaconBlockEntity, Blocks.BEACON)

    fun <T : BlockEntity> create(name: String, factory: FabricBlockEntityTypeBuilder.Factory<T>, block: Block)
        = create(name, blockEntityTypeFrom(factory, block))

    /**
     * Creates a new [BlockEntityType] from the given [factory].
     */
    private fun <T : BlockEntity> blockEntityTypeFrom(
        factory: FabricBlockEntityTypeBuilder.Factory<T>,
        block: Block): BlockEntityType<T> = FabricBlockEntityTypeBuilder.create(factory, block).build()
}