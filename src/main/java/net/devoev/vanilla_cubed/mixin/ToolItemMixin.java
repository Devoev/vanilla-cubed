package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.behavior.BehaviorModifier;
import net.devoev.vanilla_cubed.item.behavior.InventoryTickParams;
import net.devoev.vanilla_cubed.item.behavior.MagneticBehavior;
import net.devoev.vanilla_cubed.item.tool.NetheriteKt;
import net.devoev.vanilla_cubed.util.ItemKt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

/**
 * A mixin to change tool items.
 */
@Mixin(Item.class)
public class ToolItemMixin {

    private final BehaviorModifier<Item, InventoryTickParams> modifier =
            new MagneticBehavior(5.5, 0.4, 200)
                    .runIf(params -> NetheriteKt.isMagnetic(Objects.requireNonNull(params.getStack())));

    /**
     * Makes netherite items magnetic, when held in main hand.
     */
    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void attractFromNetherite(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo info) {
        if (!((Object) this instanceof ToolItem item)) return;
        if (!ItemKt.isNetherite(item) || ! (entity instanceof LivingEntity livingEntity)) return;

        boolean selectedOrOffHand = selected || livingEntity.getOffHandStack().equals(stack);
        modifier.accept(item, new InventoryTickParams(stack, world, entity, slot, selectedOrOffHand));
    }
}
