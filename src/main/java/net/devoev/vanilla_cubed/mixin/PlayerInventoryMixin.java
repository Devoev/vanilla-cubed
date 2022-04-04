package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.materials.ModArmorMaterials;
import net.devoev.vanilla_cubed.materials.ModToolMaterials;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {

    private final List<ItemStack> savedItems = new ArrayList<>();

    /**
     * Removes all enderite items before death and saves them in the savedItems list.
     */
    @Inject(method = "dropAll", at = @At(value = "HEAD"))
    private void saveItems(CallbackInfo info) {
        PlayerInventory inv = (PlayerInventory) (Object) this;
        List<ItemStack> items = Stream.of(inv.main, inv.armor, inv.offHand).flatMap(Collection::stream).toList();

        for (ItemStack stack : items)
            if (isEnderite(stack.getItem())) {
                savedItems.add(stack);
                inv.removeOne(stack);
            }
    }

    /**
     * Adds all saved enderite items back to the players inventory.
     */
    @Inject(method = "dropAll", at = @At(value = "TAIL"))
    private void giveBackItems(CallbackInfo info) {
        PlayerInventory inv = (PlayerInventory) (Object) this;
        savedItems.forEach(inv::insertStack);
    }

    /**
     * @return Whether the given item is of enderite material.
     */
    private boolean isEnderite(Item item) {
        if (item instanceof ToolItem)
            return ((ToolItem) item).getMaterial().equals(ModToolMaterials.ENDERITE);
        if (item instanceof ArmorItem)
            return ((ArmorItem) item).getMaterial().equals(ModArmorMaterials.ENDERITE);
        return false;
    }
}
