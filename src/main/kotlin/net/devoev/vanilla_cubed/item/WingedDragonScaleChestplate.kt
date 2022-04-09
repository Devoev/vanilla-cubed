package net.devoev.vanilla_cubed.item

import net.devoev.vanilla_cubed.item.armor.ModArmorMaterials
import net.fabricmc.fabric.api.entity.event.v1.FabricElytraItem
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ArmorItem

object WingedDragonScaleChestplate : ArmorItem(ModArmorMaterials.DRAGON_SCALE, EquipmentSlot.CHEST, ModItemGroup.COMBAT.toSettings()), FabricElytraItem