package net.devoev.vanilla_cubed.entity.projectile

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
                            world: World,
                            val texture: Identifier) : TridentEntity(entityType, world) {

    private var tridentStack: ItemStack
        get() = (this as TridentEntityAccessor).tridentStack
        set(value) { (this as TridentEntityAccessor).tridentStack = value }

    constructor(world: World, owner: LivingEntity, stack: ItemStack, entityType: EntityType<out TridentEntity>, texture: Identifier)
            : this(entityType, world, texture) {
        this.owner = owner
        tridentStack = stack.copy()
        setPosition(owner.eyePos)
        dataTracker[TridentEntityAccessor.getLoyalty()] = EnchantmentHelper.getLoyalty(stack).toByte()
        dataTracker[TridentEntityAccessor.getEnchanted()] = stack.hasGlint()
    }

    protected fun setDealtDamage(dealtDamage: Boolean) {
        (this as TridentEntityAccessor).setDealtDamage(dealtDamage)
    }

    protected fun hasDealtDamage(): Boolean {
        return (this as TridentEntityAccessor).hasDealtDamage()
    }
}