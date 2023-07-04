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
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.HashMap;
import java.util.Map;

@Mixin(UndeadHorseRenderer.class)
public class MixinUndeadHorseRenderer extends AbstractHorseRenderer<AbstractHorse, HorseModel<AbstractHorse>> {
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

    @Override
    public @NotNull ResourceLocation getTextureLocation(AbstractHorse entity) {
        return BetterChristmasChests.isChristmas() && BetterChristmasChests.config.zombieHorseEnabled
                ? CHRISTMAS_TEXTURES.get(entity.getType())
                : MAP.get(entity.getType());
    }
}
