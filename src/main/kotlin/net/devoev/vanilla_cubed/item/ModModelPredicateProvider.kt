package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.item.tool.ModTridentItem
import net.devoev.vanilla_cubed.util.SetInitializer
import net.devoev.vanilla_cubed.util.math.toFloat
import net.minecraft.client.item.ModelPredicateProviderRegistry
import net.minecraft.client.item.UnclampedModelPredicateProvider
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.util.Identifier

object ModModelPredicateProvider : SetInitializer<ModelPredicate>() {

    init {
        create(ModItems.AMETHYST_COMPASS, "angle", AmethystCompass.ANGLE_PREDICATE_PROVIDER)
        create(ModItems.AMETHYST_COMPASS, "charged", AmethystCompass.CHARGED_PREDICATE_PROVIDER)
        create(Items.ENCHANTED_BOOK, "gilded") { stack,_,_,_ -> stack.gilded.toFloat() }
        create(ModItems.ENDERITE_TRIDENT, "throwing", ModTridentItem.THROWING_PREDICATE_PROVIDER)
        create(ModItems.INFUSED_FIREWORK_ROCKET, "infusion_lvl", InfusedFireworkRocket.INFUSION_LVL_PREDICATE_PROVIDER)
    }

    fun create(item: Item, predicateName: String, predicateProvider: UnclampedModelPredicateProvider)
        = create(Triple(item, Identifier(predicateName), predicateProvider))

    override fun init() = forEach { ModelPredicateProviderRegistry.register(it.first, it.second, it.third) }
}

typealias ModelPredicate = Triple<Item, Identifier, UnclampedModelPredicateProvider>