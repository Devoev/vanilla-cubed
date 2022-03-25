package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.util.Identifiable
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Rarity

object ElderGuardianShard : ModItem(FabricItemSettings().group(ItemGroup.MATERIALS).rarity(Rarity.UNCOMMON)) {

    override val ID: String = "elder_guardian_shard"
}