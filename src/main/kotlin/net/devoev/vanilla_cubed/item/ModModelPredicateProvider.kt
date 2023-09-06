package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.item.tool.THROWING_PREDICATE_PROVIDER
import net.devoev.vanilla_cubed.util.SetInitializer
import net.devoev.vanilla_cubed.util.math.toFloat
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.item.ClampedModelPredicateProvider
import net.minecraft.client.item.ModelPredicateProviderRegistry
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.util.Identifier

@Environment(EnvType.CLIENT)
object ModModelPredicateProvider : SetInitializer<ModelPredicate>() {

    init {
        create(ModItems.AMETHYST_COMPASS, "angle", ANGLE_PREDICATE_PROVIDER)
        create(ModItems.AMETHYST_COMPASS, "charged", CHARGED_PREDICATE_PROVIDER)
        create(Items.ENCHANTED_BOOK, "gilded") { stack,_,_,_ -> stack.gilded.toFloat() }
        create(ModItems.ENDERITE_TRIDENT, "throwing", THROWING_PREDICATE_PROVIDER)
        create(ModItems.INFUSED_FIREWORK_ROCKET, "infusion_lvl", INFUSION_LVL_PREDICATE_PROVIDER)
    }

    fun create(item: Item, predicateName: String, predicateProvider: ClampedModelPredicateProvider)
        = create(Triple(item, Identifier(predicateName), predicateProvider))

    override fun init() = forEach { ModelPredicateProviderRegistry.register(it.first, it.second, it.third) }
}

typealias ModelPredicate = Triple<Item, Identifier, ClampedModelPredicateProvider>