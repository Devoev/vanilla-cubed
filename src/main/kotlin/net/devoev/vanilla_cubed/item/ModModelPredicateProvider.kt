package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.util.ListInitializer
import net.minecraft.client.item.ModelPredicateProviderRegistry
import net.minecraft.client.item.UnclampedModelPredicateProvider
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.util.Identifier

object ModModelPredicateProvider : ListInitializer<ModelPredicate>() {

    init {
        create(ModItems.AMETHYST_COMPASS, "angle", AmethystCompass.anglePredicateProvider)
        create(ModItems.AMETHYST_COMPASS, "charged", AmethystCompass.chargedPredicateProvider)
        create(Items.ENCHANTED_BOOK, "gilded") { stack, _, _, _ ->
            if (stack.nbt?.getBoolean("gilded") == true) 1f else 0f
        }
    }

    fun create(item: Item, predicateName: String, predicateProvider: UnclampedModelPredicateProvider)
        = create(Triple(item, Identifier(predicateName), predicateProvider))

    override fun init() = forEach { ModelPredicateProviderRegistry.register(it.first, it.second, it.third) }
}

typealias ModelPredicate = Triple<Item, Identifier, UnclampedModelPredicateProvider>