package net.devoev.vanilla_cubed.screen

import net.minecraft.block.Blocks
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ArrayPropertyDelegate
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerContext
import net.minecraft.screen.slot.Slot
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * TODO: Implement functions similar to BeaconScreenHandler
 *
 * @param syncId Screen handlers sync id.
 * @param inventory Inventory of the beacon.
 * @param propertyDelegate Delegated properties of the beacon.
 */
class ModBeaconScreenHandler(
    syncId: Int,
    inventory: Inventory,
    private val propertyDelegate: PropertyDelegate
) : ScreenHandler(ModScreenHandlerTypes.MOD_BEACON, syncId) {

    private val context: ScreenHandlerContext = ScreenHandlerContext.EMPTY
    val properties: Int
        get() = propertyDelegate.get(0)

    constructor(syncId: Int, inventory: Inventory) : this(syncId, inventory, ArrayPropertyDelegate(3))

    init {
        checkDataCount(propertyDelegate, 3)
        addProperties(propertyDelegate)
        for (k in 0..2) {
            for (l in 0..8) {
                addSlot(Slot(inventory, l + k * 9 + 9, 36 + l * 18, 137 + k * 18))
            }
        }
        for (k in 0..8) {
            addSlot(Slot(inventory, k, 36 + k * 18, 195))
        }
    }

    override fun transferSlot(player: PlayerEntity?, index: Int): ItemStack {
        var itemStack = ItemStack.EMPTY
        val slot = slots[index]
        if (slot.hasStack()) {
            val itemStack2 = slot.stack
            itemStack = itemStack2.copy()
            if (index == 0) {
                if (!insertItem(itemStack2, 1, 37, true)) {
                    return ItemStack.EMPTY
                }
                slot.onQuickTransfer(itemStack2, itemStack)
            }
            if (itemStack2.isEmpty) {
                slot.stack = ItemStack.EMPTY
            } else {
                slot.markDirty()
            }
            if (itemStack2.count == itemStack.count) {
                return ItemStack.EMPTY
            }
            slot.onTakeItem(player, itemStack2)
        }
        return itemStack
    }

    override fun canUse(player: PlayerEntity?): Boolean {
        return context.get({ world: World, pos: BlockPos ->
            if (!world.getBlockState(pos).isOf(Blocks.BEACON)) {
                return@get false
            }
            player!!.squaredDistanceTo(
                pos.x.toDouble() + 0.5,
                pos.y.toDouble() + 0.5,
                pos.z.toDouble() + 0.5
            ) <= 64.0
        }, true)
    }

}