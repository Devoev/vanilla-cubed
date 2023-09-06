package net.devoev.vanilla_cubed.mixin;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Item.Settings.class)
public interface ItemSettingsAccessor {

    @Accessor("maxCount")
    int getMaxCount();

    @Accessor("maxDamage")
    int getMaxDamage();

    @Accessor("recipeRemainder")
    Item recipeRemainder();

    @Accessor("rarity")
    Rarity getRarity();

    @Accessor("foodComponent")
    FoodComponent getFood();

    @Accessor("fireproof")
    boolean getFireproof();
}
