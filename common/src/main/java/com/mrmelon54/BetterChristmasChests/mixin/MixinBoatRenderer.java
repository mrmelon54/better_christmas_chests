package com.mrmelon54.BetterChristmasChests.mixin;

import com.mojang.datafixers.util.Pair;
import com.mrmelon54.BetterChristmasChests.BetterChristmasChests;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BoatRenderer.class)
public class MixinBoatRenderer {
    @Redirect(method = "render(Lnet/minecraft/world/entity/vehicle/Boat;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lcom/mojang/datafixers/util/Pair;getFirst()Ljava/lang/Object;"))
    private Object injectedRender(Pair<ResourceLocation, ListModel<Boat>> instance) {
        ResourceLocation first = instance.getFirst();
        String p = first.getPath();
        int i = p.lastIndexOf('/');
        return p.contains("chest_boat/") && BetterChristmasChests.isChristmas() && BetterChristmasChests.config.chestBoatEnabled
                ? new ResourceLocation("better_christmas_chests", "textures/entity/chest_boat/christmas_" + p.substring(i + 1))
                : first;
    }
}
