package com.mrmelon54.BetterChristmasChests.mixin;

import com.mrmelon54.BetterChristmasChests.BetterChristmasChests;
import net.minecraft.Util;
import net.minecraft.client.model.ChestedHorseModel;
import net.minecraft.client.renderer.entity.AbstractHorseRenderer;
import net.minecraft.client.renderer.entity.ChestedHorseRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.horse.AbstractChestedHorse;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;

// Donkeys and mules can have chests attached to them

@Mixin(ChestedHorseRenderer.class)
public abstract class MixinChestedHorseRenderer<T extends AbstractChestedHorse> extends AbstractHorseRenderer<T, ChestedHorseModel<T>> {
    @Shadow
    @Final
    private static Map<EntityType<?>, ResourceLocation> MAP;

    @Unique
    private static final Map<EntityType<?>, ResourceLocation> CHRISTMAS_TEXTURES = Util.make(new HashMap<>(), hashMap -> {
        hashMap.put(EntityType.DONKEY, new ResourceLocation("better_christmas_chests:textures/entity/horse/christmas_donkey.png"));
        hashMap.put(EntityType.MULE, new ResourceLocation("better_christmas_chests:textures/entity/horse/christmas_mule.png"));
    });

    public MixinChestedHorseRenderer(EntityRendererProvider.Context context, ChestedHorseModel<T> horseModel, float f) {
        super(context, horseModel, f);
    }

    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/animal/horse/AbstractChestedHorse;)Lnet/minecraft/resources/ResourceLocation;", at = @At("HEAD"), cancellable = true)
    private void getXmasTextureLocation(T abstractChestedHorse, CallbackInfoReturnable<ResourceLocation> cir) {
        cir.setReturnValue(BetterChristmasChests.CONFIG.isChristmas() && BetterChristmasChests.CONFIG.donkeyEnabled
                ? CHRISTMAS_TEXTURES.get(abstractChestedHorse.getType())
                : MAP.get(abstractChestedHorse.getType()));
    }
}
