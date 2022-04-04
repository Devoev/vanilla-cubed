package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.materials.ModArmorMaterials;
import net.devoev.vanilla_cubed.materials.ModToolMaterials;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {

    private final Map<EquipmentSlot, ItemStack> savedEquipment = new HashMap<>();
    private final Map<Integer, ItemStack> savedItems = new HashMap<>();

    /**
     * Removes all enderite items before death and saves them in the savedItems list.
     */
    @Inject(method = "dropAll", at = @At(value = "HEAD"))
    private void saveItems(CallbackInfo info) {
        PlayerInventory inventory = (PlayerInventory) (Object) this;

        for (ItemStack stack : inventory.armor)
            if (stack.getItem() instanceof ArmorItem armorItem)
                saveStack(stack, armorItem.getSlotType(), inventory, savedEquipment);

        saveStack(inventory.offHand.get(0), EquipmentSlot.OFFHAND, inventory, savedEquipment);

        for (ItemStack stack : inventory.main)
            saveStack(stack, inventory.getSlotWithStack(stack), inventory, savedItems);
    }

    /**
     * Adds all saved enderite items back to the players inventory.
     */
    @Inject(method = "dropAll", at = @At(value = "TAIL"))
    private void giveBackItems(CallbackInfo info) {
        PlayerInventory inv = (PlayerInventory) (Object) this;
        savedEquipment.forEach(inv.player::equipStack);
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

    /**
     * Saves the given stack to the saveTo map. Removes the stack from the players inventory.
     * Only saves the stack, if item is of enderite material.
     */
    private <K> void saveStack(ItemStack stack, K key, PlayerInventory inventory, Map<K, ItemStack> saveTo) {
        if (!isEnderite(stack.getItem())) return;
        saveTo.put(key, stack);
        inventory.removeOne(stack);
    }
}
