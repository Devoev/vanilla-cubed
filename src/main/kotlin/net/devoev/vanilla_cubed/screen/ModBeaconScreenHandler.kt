package net.devoev.vanilla_cubed.screen

import net.devoev.vanilla_cubed.block.entity.ModBeaconBlockEntity
import net.devoev.vanilla_cubed.block.entity.beacon.upgrades.BeaconUpgrade
import net.minecraft.block.Blocks
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemStack
import net.minecraft.screen.ArrayPropertyDelegate
import net.minecraft.screen.PropertyDelegate
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerContext
import net.minecraft.screen.slot.Slot
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
 * Screen handler of [ModBeaconBlockEntity].
 *
 * @param syncId Screen handlers sync id.
 * @param inventory Inventory of the beacon.
 * @param propertyDelegate Delegated properties of the beacon.
 * @param context Screen handler context of the beacon.
 */
class ModBeaconScreenHandler(
    syncId: Int,
    inventory: PlayerInventory,
    val propertyDelegate: PropertyDelegate,
    val context: ScreenHandlerContext
) : ScreenHandler(ModScreenHandlerTypes.MOD_BEACON, syncId) {

    /**
     * The active upgrade of the beacon.
     */
    val upgrades: MutableList<BeaconUpgrade?>
        get() = propertyDelegate.upgrades.toMutableList()

    /**
     * The total levels of the beacon.
     */
    val totalLevels: IntArray
        get() = propertyDelegate.totalLevels

    /**
     * The remaining levels of the beacon.
     */
    val remainingLevels: IntArray
        get() = propertyDelegate.remainingLevels

    constructor(syncId: Int, inventory: PlayerInventory) : this(
        syncId,
        inventory,
        ArrayPropertyDelegate(UPGRADE_RANGE.last + 1),
        ScreenHandlerContext.EMPTY
    )

    init {
        checkDataCount(propertyDelegate, 5)
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

    override fun quickMove(player: PlayerEntity?, index: Int): ItemStack {
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

    override fun setProperty(id: Int, value: Int) {
        super.setProperty(id, value)
        sendContentUpdates()
    }
}