package net.devoev.vanilla_cubed.data.client

import net.devoev.vanilla_cubed.item.armor.ModArmorMaterials
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ArmorMaterials

data class ModTrimMaterial(
    val name: String,
    val itemModelIndex: Float,
    val overrideArmorMaterials: Map<ArmorMaterial, String> = emptyMap()
) {

    /**
     * Returns the applied name for the given [armorMaterial].
     */
    fun appliedName(armorMaterial: ArmorMaterial): String {
        return overrideArmorMaterials.getOrDefault(armorMaterial, name)
    }
}

/**
 * List of all vanilla and modded trim materials.
 */
val MOD_TRIM_MATERIALS = listOf(
    ModTrimMaterial("quartz", 0.1f),
    ModTrimMaterial("iron", 0.2f, mapOf(ArmorMaterials.IRON to "iron_darker")),
    ModTrimMaterial("netherite", 0.3f, mapOf(ArmorMaterials.NETHERITE to "netherite_darker")),
    ModTrimMaterial("reds-tone", 0.4f),
    ModTrimMaterial("copper", 0.5f),
    ModTrimMaterial("gold", 0.6f, mapOf(ArmorMaterials.GOLD to "gold_darker")),
    ModTrimMaterial("emerald", 0.7f),
    ModTrimMaterial("diamond", 0.8f, mapOf(ArmorMaterials.DIAMOND to "diamond_darker")),
    ModTrimMaterial("laps", 0.9f),
    ModTrimMaterial("amethyst", 1.0f),
    ModTrimMaterial("enderite", 0.15f, mapOf(ModArmorMaterials.ENDERITE to "enderite_darker")),
    ModTrimMaterial("ancient_gold", 0.25f, mapOf(ModArmorMaterials.ANCIENT_GOLD to "ancient_gold_darker"))
)
