package net.devoev.vanilla_cubed.item.tool

import net.devoev.vanilla_cubed.item.modifier.*
import net.devoev.vanilla_cubed.item.tool.data.ToolDataSet.Companion.BASE_ATTACK_DAMAGE
import net.devoev.vanilla_cubed.item.tool.data.ToolDataSet.Companion.BASE_ATTACK_SPEED
import net.devoev.vanilla_cubed.item.tool.data.TridentToolDataSet
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.TridentEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolMaterial
import net.minecraft.world.World

/**
 * A [ToolBuilder] that also constructs a [ModTridentItem].
 */
open class TridentToolBuilder(data: TridentToolDataSet,
                              behaviors: Behaviors<Item>,
                              entityProvider: (World, LivingEntity, ItemStack) -> TridentEntity)
    : ToolBuilder(data, behaviors) {

    constructor(
        material: ToolMaterial,
        attackDamageAmounts: List<Float> = BASE_ATTACK_DAMAGE,
        attackSpeedAmounts: List<Float> = BASE_ATTACK_SPEED,
        settings: Item.Settings = FabricItemSettings(),
        entityProvider: (World, LivingEntity, ItemStack) -> TridentEntity,
        inventoryTickModifier: InventoryTickModifier<Item> = INVENTORY_TICK_DEFAULT,
        postHitModifier: PostHitModifier<Item> = POST_HIT_DEFAULT,
        postMineModifier: PostMineModifier<Item> = POST_MINE_DEFAULT
    ) : this(
        TridentToolDataSet.of(material, attackDamageAmounts, attackSpeedAmounts, settings),
        DataBehaviors(inventoryTickModifier, postHitModifier, postMineModifier),
        entityProvider
    )

    open val trident = ModTridentItem(entityProvider, data.trident.material, data.trident.settings, behaviors)

    operator fun component6() = trident
}