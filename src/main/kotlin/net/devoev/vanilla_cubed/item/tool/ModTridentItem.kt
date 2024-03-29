package net.devoev.vanilla_cubed.item.tool

import net.devoev.vanilla_cubed.item.modifier.ItemModifiers
import net.devoev.vanilla_cubed.item.modifier.inventoryTick
import net.devoev.vanilla_cubed.item.modifier.postHit
import net.devoev.vanilla_cubed.item.modifier.postMine
import net.devoev.vanilla_cubed.util.math.toFloat
import net.minecraft.block.BlockState
import net.minecraft.client.item.ClampedModelPredicateProvider
import net.minecraft.client.item.TooltipContext
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.MovementType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolMaterial
import net.minecraft.item.TridentItem
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.stat.Stats
import net.minecraft.text.Text
import net.minecraft.util.UseAction
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * A custom [TridentItem].
 */
class ModTridentItem(private val data: TridentToolData)
    : TridentItem(data.settings), ToolMaterialItem {

    init {
        require(maxDamage > 0) { "$maxDamage must be greater than 0!" }
        data.settings.maxDamageIfAbsent((data.material.durability * 0.16).toInt())
    }

    constructor(
        material: ToolMaterial,
        settings: Settings,
        entityProvider: EntityProvider,
        modifiers: ItemModifiers<Item> = emptyList(),
        tooltips: Collection<List<Text>> = emptyList())
            : this(TridentToolData(material, settings, entityProvider, modifiers, tooltips.flatten()))

    override val material: ToolMaterial = data.material

    override fun getUseAction(stack: ItemStack?): UseAction = UseAction.SPEAR

    override fun inventoryTick(stack: ItemStack, world: World, entity: Entity, slot: Int, selected: Boolean) {
        data.modifiers.inventoryTick(this, stack, world, entity, slot, selected)
        super.inventoryTick(stack, world, entity, slot, selected)
    }

    override fun postHit(stack: ItemStack, target: LivingEntity, attacker: LivingEntity): Boolean {
        data.modifiers.postHit(this, stack, target, attacker)
        return super.postHit(stack, target, attacker)
    }

    override fun postMine(stack: ItemStack, world: World, state: BlockState, pos: BlockPos, miner: LivingEntity): Boolean {
        data.modifiers.postMine(this, stack, world, state, pos, miner)
        return super.postMine(stack, world, state, pos, miner)
    }

    override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>, context: TooltipContext) {
        super.appendTooltip(stack, world, tooltip, context)
        data.appendTooltips(tooltip)
    }

    override fun onStoppedUsing(stack: ItemStack?, world: World?, user: LivingEntity?, remainingUseTicks: Int) {
        if (stack == null || world == null || user !is PlayerEntity) return

        val i = getMaxUseTime(stack) - remainingUseTicks
        val j = EnchantmentHelper.getRiptide(stack)
        if (i < 10 || (j > 0 && !user.isTouchingWaterOrRain)) return

        if (!world.isClient) {
            stack.damage(1, user) { it.sendToolBreakStatus(user.activeHand) }
            if (j == 0) {
                val tridentEntity = data.entityProvider(world, user, stack)
                tridentEntity.setVelocity(user, user.pitch, user.yaw, 0f, 2f, 1f)
                if (user.abilities.creativeMode)
                    tridentEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY

                world.spawnEntity(tridentEntity)
                world.playSoundFromEntity(
                    null,
                    tridentEntity,
                    SoundEvents.ITEM_TRIDENT_THROW,
                    SoundCategory.PLAYERS,
                    1.0f,
                    1.0f
                )
                if (!user.abilities.creativeMode)
                    user.inventory.removeOne(stack)

                user.incrementStat(Stats.USED.getOrCreateStat(this))

            }
        }

        if (j > 0) {
            val f: Float = user.yaw
            val g: Float = user.pitch
            var h = -sin(f * 0.017453292) * cos(g * 0.017453292)
            var k = -sin(g * 0.017453292)
            var l = cos(f * 0.017453292) * cos(g * 0.017453292)
            val m = sqrt(h * h + k * k + l * l)
            val n = 3.0f * ((1.0f + j.toFloat()) / 4.0f)
            h *= n / m
            k *= n / m
            l *= n / m
            user.addVelocity(h, k, l)
            user.useRiptide(20)
            if (user.isOnGround())
                user.move(MovementType.SELF, Vec3d(0.0, 1.1999999284744263, 0.0))

            val soundEvent = if (j >= 3) {
                SoundEvents.ITEM_TRIDENT_RIPTIDE_3
            } else if (j == 2) {
                SoundEvents.ITEM_TRIDENT_RIPTIDE_2
            } else {
                SoundEvents.ITEM_TRIDENT_RIPTIDE_1
            }
            world.playSoundFromEntity(
                null,
                user,
                soundEvent,
                SoundCategory.PLAYERS,
                1.0f,
                1.0f
            )
        }
    }
}

/**
 * The predicate provider providing the throwing state of a trident.
 */
val THROWING_PREDICATE_PROVIDER = ClampedModelPredicateProvider { stack, _, entity, _ ->
    (entity != null && entity.isUsingItem && entity.activeItem == stack).toFloat()
}