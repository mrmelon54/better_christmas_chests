package com.mrmelon54.BetterChristmasChests.mixin;

#if MC_VER < MC_1_21_11
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
        cir.setReturnValue(BetterChristmasChests.CONFIG.isChristmas() && BetterChristmasChests.CONFIG.zombieHorseEnabled
                ? CHRISTMAS_TEXTURES.get(abstractHorse.getType())
                : MAP.get(abstractHorse.getType()));
    }
}
#else

import com.mrmelon54.BetterChristmasChests.BetterChristmasChests;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.animal.equine.AbstractEquineModel;
import net.minecraft.client.renderer.entity.AbstractHorseRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.UndeadHorseRenderer;
import net.minecraft.client.renderer.entity.state.EquineRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(UndeadHorseRenderer.class)
@Environment(EnvType.CLIENT)
public abstract class MixinUndeadHorseRenderer extends AbstractHorseRenderer<AbstractHorse, EquineRenderState, AbstractEquineModel<EquineRenderState>> {
    public MixinUndeadHorseRenderer(EntityRendererProvider.Context context, AbstractEquineModel<EquineRenderState> adultModel, AbstractEquineModel<EquineRenderState> babyModel) {
        super(context, adultModel, babyModel);
    }

    @Inject(method = "getTextureLocation(Lnet/minecraft/client/renderer/entity/state/EquineRenderState;)Lnet/minecraft/resources/Identifier;", at = @At(value = "RETURN"), cancellable = true)
    private void getXmasTextureLocation(EquineRenderState equineRenderState, CallbackInfoReturnable<Identifier> cir) {
        if (BetterChristmasChests.CONFIG.isChristmas() && BetterChristmasChests.CONFIG.zombieHorseEnabled) {
            cir.setReturnValue(BetterChristmasChests.getChristmasTexture(cir.getReturnValue()));
        }
    }
}
#endif
