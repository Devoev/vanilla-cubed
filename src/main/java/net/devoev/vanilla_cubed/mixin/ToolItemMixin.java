package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.Magnetic;
import net.devoev.vanilla_cubed.util.ItemKt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * A mixin to change tool items.
 */
@Mixin(Item.class)
public class ToolItemMixin implements Magnetic {

    /**
     * Makes netherite items magnetic, when held in main hand.
     */
    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void attractFromNetherite(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo info) {
        if (!((Object) this instanceof ToolItem item)) return;

        if (!ItemKt.isMadeOf(item, ToolMaterials.NETHERITE) || ! (entity instanceof LivingEntity livingEntity)) return;
            inventoryTick(entity, selected || livingEntity.getOffHandStack().equals(stack));
    }

    @Override
    public double getRange() {
        return 5.5;
    }

    @Override
    public double getSpeed() {
        return 0.5;
    }

    @Override
    public void attractItems(@NotNull Entity entity) {
        Magnetic.super.attractItems(entity);
    }
}
