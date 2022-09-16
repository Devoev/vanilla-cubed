package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.item.trident.EnderiteTrident
import net.devoev.vanilla_cubed.util.ListInitializer
import net.devoev.vanilla_cubed.util.math.toFloat
import net.minecraft.client.item.ModelPredicateProviderRegistry
import net.minecraft.client.item.UnclampedModelPredicateProvider
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.util.Identifier

object ModModelPredicateProvider : ListInitializer<ModelPredicate>() {

    init {
        create(ModItems.AMETHYST_COMPASS, "angle", AmethystCompass.anglePredicateProvider)
        create(ModItems.AMETHYST_COMPASS, "charged", AmethystCompass.chargedPredicateProvider)
        create(Items.ENCHANTED_BOOK, "gilded") { stack,_,_,_ -> stack.gilded.toFloat() }
        create(ModItems.ENDERITE_TRIDENT, "throwing", EnderiteTrident.THROWING_PREDICATE_PROVIDER)
    }

    fun create(item: Item, predicateName: String, predicateProvider: UnclampedModelPredicateProvider)
        = create(Triple(item, Identifier(predicateName), predicateProvider))

    override fun init() = forEach { ModelPredicateProviderRegistry.register(it.first, it.second, it.third) }
}

typealias ModelPredicate = Triple<Item, Identifier, UnclampedModelPredicateProvider>