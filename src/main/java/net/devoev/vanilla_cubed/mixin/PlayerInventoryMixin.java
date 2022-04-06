package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.util.ItemKt;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {

    private final Map<EquipmentSlot, ItemStack> savedEquipment = new HashMap<>();
    private final Map<Integer, ItemStack> savedItems = new HashMap<>();
    private final List<ItemStack> autoSavedItems = new ArrayList<>();

    /**
     * Removes all enderite items before death and saves them in the savedItems list.
     */
    @Inject(method = "dropAll", at = @At(value = "HEAD"))
    private void saveItems(CallbackInfo info) {
        PlayerInventory inventory = (PlayerInventory) (Object) this;

        //Offhand
        saveStack(inventory.offHand.get(0), EquipmentSlot.OFFHAND, inventory, savedEquipment);

        //Main
        for (ItemStack stack : inventory.main)
            saveStack(stack, inventory.getSlotWithStack(stack), inventory, savedItems);

        //Armor
        for (ItemStack stack : inventory.armor)
            if (stack.getItem() instanceof ArmorItem armorItem)
                if (EnchantmentHelper.hasBindingCurse(stack))
                    autoSaveStack(stack, inventory);
                else
                    saveStack(stack, armorItem.getSlotType(), inventory, savedEquipment);
    }

    /**
     * Adds all saved enderite items back to the players inventory.
     */
    @Inject(method = "dropAll", at = @At(value = "TAIL"))
    private void giveBackItems(CallbackInfo info) {
        PlayerInventory inventory = (PlayerInventory) (Object) this;
        savedEquipment.forEach(inventory.player::equipStack);
        savedItems.forEach(inventory::insertStack);
        autoSavedItems.forEach(inventory::insertStack);
    }

    /**
     * Saves the given stack to the saveTo map. Removes the stack from the players inventory.
     * Only saves the stack, if item is of enderite material.
     */
    private <K> void saveStack(ItemStack stack, K key, PlayerInventory inventory, Map<K, ItemStack> saveTo) {
        if (!ItemKt.isEnderite(stack.getItem())) return;
        saveTo.put(key, stack);
        inventory.removeOne(stack);
    }

    /**
     * Saves the stacks that must be given a free inventory slot.
     */
    private void autoSaveStack(ItemStack stack, PlayerInventory inventory) {
        if (savedItems.size() + autoSavedItems.size() >= inventory.main.size()) return;
        autoSavedItems.add(stack);
        inventory.removeOne(stack);
    }
}
