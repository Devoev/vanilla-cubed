package net.devoev.vanilla_cubed.mixin;

import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(TridentEntity.class)
public interface TridentEntityAccessor {

    @Accessor("LOYALTY")
    static TrackedData<Byte> getLoyalty() {
        return null;
    }

    @Accessor("ENCHANTED")
    static TrackedData<Boolean> getEnchanted() {
        return null;
    }

    @Accessor("tridentStack")
    ItemStack getTridentStack();

    @Accessor("tridentStack")
    void setTridentStack(ItemStack stack);

    @Accessor("dealtDamage")
    boolean getDealtDamage();

    @Accessor("dealtDamage")
    void setDealtDamage(boolean dealtDamage);
}
