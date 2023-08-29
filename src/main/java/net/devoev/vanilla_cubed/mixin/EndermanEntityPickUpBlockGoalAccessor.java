package net.devoev.vanilla_cubed.mixin;

import net.minecraft.entity.mob.EndermanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "net/minecraft/entity/mob/EndermanEntity$PickUpBlockGoal")
public interface EndermanEntityPickUpBlockGoalAccessor {

    @Accessor("enderman")
    EndermanEntity getEnderman();
}
