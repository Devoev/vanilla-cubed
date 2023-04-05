package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.util.SetInitializer
import net.devoev.vanilla_cubed.util.math.toFloat
import net.devoev.vanilla_cubed.util.math.toGlobalPos
import net.minecraft.client.item.CompassAnglePredicateProvider
import net.minecraft.client.item.ModelPredicateProviderRegistry
import net.minecraft.client.item.UnclampedModelPredicateProvider
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.util.Identifier

object ModModelPredicateProvider : SetInitializer<ModelPredicate>() {

    init {
        create(ModItems.AMETHYST_COMPASS, "angle", ANGLE_PREDICATE_PROVIDER)
        create(ModItems.AMETHYST_COMPASS, "charged", CHARGED_PREDICATE_PROVIDER)
        create(Items.ENCHANTED_BOOK, "gilded") { stack,_,_,_ -> stack.gilded.toFloat() }
        create(ModItems.ENDERITE_TRIDENT, "throwing", THROWING_PREDICATE_PROVIDER)
        create(ModItems.INFUSED_FIREWORK_ROCKET, "infusion_lvl", INFUSION_LVL_PREDICATE_PROVIDER)
    }

    fun create(item: Item, predicateName: String, predicateProvider: UnclampedModelPredicateProvider)
        = create(Triple(item, Identifier(predicateName), predicateProvider))

    override fun init() = forEach { ModelPredicateProviderRegistry.register(it.first, it.second, it.third) }
}

typealias ModelPredicate = Triple<Item, Identifier, UnclampedModelPredicateProvider>

val THROWING_PREDICATE_PROVIDER = UnclampedModelPredicateProvider { stack, _, entity, _ ->
    (entity != null && entity.isUsingItem && entity.activeItem == stack).toFloat()
}

/**
 * The predicate provider for this compasses angle.
 */
val ANGLE_PREDICATE_PROVIDER = CompassAnglePredicateProvider { world, stack, _ -> stack.targetPos?.toGlobalPos(world) }

/**
 * The predicate provider for indicate, whether the compass is charged.
 */
val CHARGED_PREDICATE_PROVIDER = UnclampedModelPredicateProvider { stack, _, _, _ -> stack.charged.toFloat() }

/**
 * The predicate provider to provide the infusion level.
 */
val INFUSION_LVL_PREDICATE_PROVIDER = UnclampedModelPredicateProvider { stack, _, _, _ -> (stack.infusionLvl - 1f) / 2 }