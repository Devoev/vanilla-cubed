package net.devoev.vanilla_cubed.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Rarity

class ElderGuardianShard : Item(FabricItemSettings()
    .group(ItemGroup.MATERIALS)
    .rarity(Rarity.RARE)
)