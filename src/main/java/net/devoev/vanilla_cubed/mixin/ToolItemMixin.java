package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.Magnetic;
import net.devoev.vanilla_cubed.util.ItemKt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
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
public class ToolItemMixin {

    /**
     * Makes netherite items magnetic, when held in main hand.
     */
    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void attractFromNetherite(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo info) {
        if (!((Object) this instanceof ToolItem)) return;

        Stream<ItemStack> items = StreamSupport.stream(entity.getItemsHand().spliterator(), false);
        if (items.anyMatch(v -> ItemKt.isMadeOf(v.getItem(), ToolMaterials.NETHERITE)))
            this.attractItems(entity, 8, 0.5);
    }

    /**
     * Attracts all items in range to the given entity.
     */
    public void attractItems(@NotNull Entity entity, double range, double speed) {
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        List<ItemEntity> items = entity.getEntityWorld().getEntitiesByType(
                EntityType.ITEM,
                new Box(x - range, y - range, z - range, x + range, y + range, z + range),
                EntityPredicates.VALID_ENTITY);

        for (ItemEntity item : items) {
            item.setPickupDelay(0);
            Vec3d itemVector = new Vec3d(item.getX(), item.getY(), item.getZ());
            Vec3d playerVector = new Vec3d(x, y + 0.75, z);
            item.move(null, playerVector.subtract(itemVector).normalize().multiply(speed));
        }
    }
}
