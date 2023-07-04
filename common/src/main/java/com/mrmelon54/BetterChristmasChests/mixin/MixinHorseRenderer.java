package com.mrmelon54.BetterChristmasChests.mixin;

import com.google.common.collect.Maps;
import com.mrmelon54.BetterChristmasChests.BetterChristmasChests;
import net.minecraft.Util;
import net.minecraft.client.model.HorseModel;
import net.minecraft.client.renderer.entity.AbstractHorseRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HorseRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.Variant;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

// Well horses can't actually have chests equipped, but I added it anyway in case a mod enables it

@Mixin(HorseRenderer.class)
public abstract class MixinHorseRenderer extends AbstractHorseRenderer<Horse, HorseModel<Horse>> {
    @Shadow
    @Final
    private static Map<Variant, ResourceLocation> LOCATION_BY_VARIANT;

    @Unique
    private static final Map<Variant, ResourceLocation> CHRISTMAS_TEXTURES = Util.make(Maps.newEnumMap(Variant.class), enumMap -> {
        enumMap.put(Variant.WHITE, new ResourceLocation("better_christmas_chests:textures/entity/horse/christmas_horse_white.png"));
        enumMap.put(Variant.CREAMY, new ResourceLocation("better_christmas_chests:textures/entity/horse/christmas_horse_white.png"));
        enumMap.put(Variant.CHESTNUT, new ResourceLocation("better_christmas_chests:textures/entity/horse/christmas_horse_white.png"));
        enumMap.put(Variant.BROWN, new ResourceLocation("better_christmas_chests:textures/entity/horse/christmas_horse_white.png"));
        enumMap.put(Variant.BLACK, new ResourceLocation("better_christmas_chests:textures/entity/horse/christmas_horse_white.png"));
        enumMap.put(Variant.GRAY, new ResourceLocation("better_christmas_chests:textures/entity/horse/christmas_horse_white.png"));
        enumMap.put(Variant.DARK_BROWN, new ResourceLocation("better_christmas_chests:textures/entity/horse/christmas_horse_white.png"));
    });

    public MixinHorseRenderer(EntityRendererProvider.Context context, HorseModel<Horse> horseModel, float f) {
        super(context, horseModel, f);
    }

    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/animal/horse/Horse;)Lnet/minecraft/resources/ResourceLocation;", at = @At("HEAD"), cancellable = true)
    private void getXmasTextureLocation(Horse horse, CallbackInfoReturnable<ResourceLocation> cir) {
        cir.setReturnValue(BetterChristmasChests.isChristmas() && BetterChristmasChests.config.horseEnabled
                ? CHRISTMAS_TEXTURES.get(horse.getVariant())
                : LOCATION_BY_VARIANT.get(horse.getVariant()));
    }
}
