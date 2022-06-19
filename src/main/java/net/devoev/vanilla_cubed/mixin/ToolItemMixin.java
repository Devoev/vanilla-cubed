package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.behavior.InventoryTickBehavior;
import net.devoev.vanilla_cubed.item.behavior.MagneticBehavior;
import net.devoev.vanilla_cubed.util.ItemKt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * A mixin to change tool items.
 */
@Mixin(Item.class)
public class ToolItemMixin implements InventoryTickBehavior<Item> {

    private InventoryTickBehavior<Item> delegate = new MagneticBehavior(5.5, 0.5);

    /**
     * Makes netherite items magnetic, when held in main hand.
     */
    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void attractFromNetherite(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo info) {
        if (!((Object) this instanceof ToolItem item)) return;
        if (!ItemKt.isNetherite(item) || ! (entity instanceof LivingEntity livingEntity)) return;

        boolean selectedOrOffHand = selected || livingEntity.getOffHandStack().equals(stack);
        inventoryTick(item, stack, world, entity, slot, selectedOrOffHand);
    }

    @Override
    public void inventoryTick(@NotNull Item item, @Nullable ItemStack stack, @Nullable World world, @Nullable Entity entity, int slot, boolean selected) {
        delegate.inventoryTick(item, stack, world, entity, slot, selected);
    }
}
