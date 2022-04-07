package net.devoev.vanilla_cubed.mixin;

import net.devoev.vanilla_cubed.item.ModItems;
import net.devoev.vanilla_cubed.util.ItemKt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.stream.StreamSupport;

/**
 * A mixin for the amethyst crystal.
 */
@Mixin(Entity.class)
public class EntityMixin {

    /**
     * Charges the amethyst crystal when struck by lightning.
     */
    @Inject(method = "onStruckByLightning", at = @At("HEAD"))
    private void chargeAmethystCrystal(ServerWorld world, LightningEntity lightning, CallbackInfo info) {
        if (!( (Object) this instanceof ItemEntity item && item.getStack().getItem().equals(ModItems.INSTANCE.getAMETHYST_CRYSTAL()))) return;

        item.setStack(new ItemStack(ModItems.INSTANCE.getCHARGED_AMETHYST_CRYSTAL(), item.getStack().getCount()));
        item.setInvulnerable(true);
        item.setInvisible(false);
        item.setOnFire(false);
    }

    /**
     * Full set of dragon scale armor negates all fall damage.
     */
    @Inject(method = "isInvulnerableTo", at = @At("HEAD"), cancellable = true)
    private void isInvulnerableToFallDamage(DamageSource damageSource, CallbackInfoReturnable<Boolean> info) {
        if ((Object) this instanceof PlayerEntity player
            && StreamSupport.stream(player.getArmorItems().spliterator(), false).allMatch(stack -> ItemKt.isDragonScale(stack.getItem())))
            info.setReturnValue(true);
    }
}
