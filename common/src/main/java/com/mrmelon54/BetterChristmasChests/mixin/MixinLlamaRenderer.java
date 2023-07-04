package com.mrmelon54.BetterChristmasChests.mixin;

import com.google.common.collect.Maps;
import com.mrmelon54.BetterChristmasChests.BetterChristmasChests;
import net.minecraft.Util;
import net.minecraft.client.model.LlamaModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LlamaRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.animal.horse.Llama.Variant;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Map;

// Llamas can have chests equipped

@Mixin(LlamaRenderer.class)
public class MixinLlamaRenderer extends MobRenderer<Llama, LlamaModel<Llama>> {
    @Shadow
    @Final
    private static ResourceLocation CREAMY;
    @Shadow
    @Final
    private static ResourceLocation WHITE;
    @Shadow
    @Final
    private static ResourceLocation BROWN;
    @Shadow
    @Final
    private static ResourceLocation GRAY;

    @Unique
    private static final Map<Variant, ResourceLocation> LOCATION_BY_VARIANT = Util.make(Maps.newEnumMap(Variant.class), enumMap -> {
        enumMap.put(Variant.CREAMY, CREAMY);
        enumMap.put(Variant.WHITE, WHITE);
        enumMap.put(Variant.BROWN, BROWN);
        enumMap.put(Variant.GRAY, GRAY);
    });

    @Unique
    private static final Map<Variant, ResourceLocation> CHRISTMAS_TEXTURES = Util.make(Maps.newEnumMap(Variant.class), enumMap -> {
        enumMap.put(Variant.CREAMY, new ResourceLocation("better_christmas_chests:textures/entity/llama/christmas_creamy.png"));
        enumMap.put(Variant.WHITE, new ResourceLocation("better_christmas_chests:textures/entity/llama/christmas_white.png"));
        enumMap.put(Variant.BROWN, new ResourceLocation("better_christmas_chests:textures/entity/llama/christmas_brown.png"));
        enumMap.put(Variant.GRAY, new ResourceLocation("better_christmas_chests:textures/entity/llama/christmas_gray.png"));
    });

    public MixinLlamaRenderer(EntityRendererProvider.Context context, LlamaModel<Llama> entityModel, float f) {
        super(context, entityModel, f);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Llama entity) {
        return BetterChristmasChests.isChristmas() && BetterChristmasChests.config.llamaEnabled
                ? CHRISTMAS_TEXTURES.get(entity.getVariant())
                : LOCATION_BY_VARIANT.get(entity.getVariant());
    }
}
