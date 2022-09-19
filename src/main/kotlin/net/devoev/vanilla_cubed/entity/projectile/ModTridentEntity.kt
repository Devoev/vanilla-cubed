package net.devoev.vanilla_cubed.entity.projectile

import net.devoev.vanilla_cubed.entity.entityTexture
import net.devoev.vanilla_cubed.mixin.TridentEntityAccessor
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.TridentEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.world.World

/**
 * A custom [TridentEntity].
 */
open class ModTridentEntity(entityType: EntityType<out TridentEntity>,
                            world: World) : TridentEntity(entityType, world) {

    private var tridentStack: ItemStack
        get() = (this as TridentEntityAccessor).tridentStack
        set(value) { (this as TridentEntityAccessor).tridentStack = value }

    /**
     * Whether this trident has hit a target (entity or ground).
     * Value equal to [TridentEntity.dealtDamage].
     */
    protected var targetHit: Boolean
        get() = (this as TridentEntityAccessor).dealtDamage
        set(value) { (this as TridentEntityAccessor).dealtDamage = value }

    val texture: Identifier
        get() = type.entityTexture

    constructor(world: World, owner: LivingEntity, stack: ItemStack, entityType: EntityType<out TridentEntity>)
            : this(entityType, world) {
        this.owner = owner
        tridentStack = stack.copy()
        setPosition(owner.eyePos)
        dataTracker[TridentEntityAccessor.getLoyalty()] = EnchantmentHelper.getLoyalty(stack).toByte()
        dataTracker[TridentEntityAccessor.getEnchanted()] = stack.hasGlint()
    }
}