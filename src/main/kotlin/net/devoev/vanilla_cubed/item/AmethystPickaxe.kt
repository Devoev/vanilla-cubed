package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.materials.Amethyst
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.ItemGroup
import net.minecraft.item.PickaxeItem

object AmethystPickaxe : PickaxeItem(Amethyst, 1, 1F, FabricItemSettings().group(ItemGroup.TOOLS))