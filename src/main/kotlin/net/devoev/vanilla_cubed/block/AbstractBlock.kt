package net.devoev.vanilla_cubed.block

import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.registry.tag.TagKey

/**
 * Returns true, if the [AbstractBlock.AbstractBlockState.isIn] method returns true for any of the given [tags].
 */
fun AbstractBlock.AbstractBlockState.isIn(tags: Collection<TagKey<Block>>): Boolean = tags.any { this.isIn(it) }