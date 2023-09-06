package net.devoev.vanilla_cubed.mixin;

import kotlin.random.Random;
import net.devoev.vanilla_cubed.block.entity.beacon_upgrade.DisableMobGriefingUpgrade;
import net.devoev.vanilla_cubed.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.stream.IntStream;

@Mixin(EnderDragonEntity.class)
public class EnderDragonEntityMixin extends MobEntity {

    @Shadow
    public int ticksSinceDeath;

    protected EnderDragonEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "updatePostDeath", at = @At("HEAD"))
    private void dropDragonScale(CallbackInfo info) {
        if (ticksSinceDeath != 150) return;

        Box box = new Box(getBlockPos());
        List<PlayerEntity> players = getWorld().getEntitiesByClass(
                PlayerEntity.class,
                box.expand(128D),
                EntityPredicates.EXCEPT_SPECTATOR
        );

        int amount = players.size() + Random.Default.nextInt(-1, 2);
        IntStream.rangeClosed(1, Math.max(amount, 1)).forEach(i -> dropItem(ModItems.INSTANCE.getDRAGON_SCALE()));
    }

    /**
     * @see DisableMobGriefingUpgrade
     */
    @Inject(method = "destroyBlocks", at = @At("HEAD"), cancellable = true)
    private void disableEnderDragonBlockDestruction(Box box, CallbackInfoReturnable<Boolean> cir) {
        EnderDragonEntity entity = (EnderDragonEntity) (Object) this;
        DisableMobGriefingUpgrade.INSTANCE.disableEnderDragonBlockDestruction(entity.getPos(), cir);
    }
}
