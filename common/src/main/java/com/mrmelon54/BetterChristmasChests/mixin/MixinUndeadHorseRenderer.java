package com.mrmelon54.BetterChristmasChests.mixin;

import com.mrmelon54.BetterChristmasChests.BetterChristmasChests;
import net.minecraft.Util;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.renderer.entity.AbstractHorseRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.UndeadHorseRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;

@Mixin(UndeadHorseRenderer.class)
public abstract class MixinUndeadHorseRenderer extends AbstractHorseRenderer<AbstractHorse, HorseModel<AbstractHorse>> {
    @Shadow
    @Final
    private static Map<EntityType<?>, ResourceLocation> MAP;
    @Unique
    private static final Map<EntityType<?>, ResourceLocation> CHRISTMAS_TEXTURES = Util.make(new HashMap<>(), hashMap -> {
        hashMap.put(EntityType.ZOMBIE_HORSE, new ResourceLocation("better_christmas_chests:textures/entity/horse/christmas_horse_zombie.png"));
        hashMap.put(EntityType.SKELETON_HORSE, new ResourceLocation("better_christmas_chests:textures/entity/horse/christmas_horse_skeleton.png"));
    });

    public MixinUndeadHorseRenderer(EntityRendererProvider.Context context, HorseModel<AbstractHorse> horseModel, float f) {
        super(context, horseModel, f);
    }

    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/animal/horse/AbstractHorse;)Lnet/minecraft/resources/ResourceLocation;", at = @At("HEAD"), cancellable = true)
    private void getXmasTextureLocation(AbstractHorse abstractHorse, CallbackInfoReturnable<ResourceLocation> cir) {
        cir.setReturnValue(BetterChristmasChests.isChristmas() && BetterChristmasChests.config.zombieHorseEnabled
                ? CHRISTMAS_TEXTURES.get(abstractHorse.getType())
                : MAP.get(abstractHorse.getType()));
    }
}
