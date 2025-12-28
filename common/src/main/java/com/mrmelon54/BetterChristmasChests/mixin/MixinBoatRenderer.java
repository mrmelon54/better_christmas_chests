package com.mrmelon54.BetterChristmasChests.mixin;

#if MC_VER < MC_1_21_11
import com.mrmelon54.BetterChristmasChests.BetterChristmasChests;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BoatRenderer.class)
public class MixinBoatRenderer {
    @Redirect(method = "render(Lnet/minecraft/world/entity/vehicle/Boat;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ListModel;renderType(Lnet/minecraft/resources/ResourceLocation;)Lnet/minecraft/client/renderer/RenderType;"))
    private RenderType injectedRender(ListModel<Boat> instance, ResourceLocation resourceLocation) {
        String p = resourceLocation.getPath();
        int i = p.lastIndexOf('/');
        ResourceLocation loc = p.contains("chest_boat/") && BetterChristmasChests.CONFIG.isChristmas() && BetterChristmasChests.CONFIG.chestBoatEnabled
                ? new ResourceLocation("better_christmas_chests", "textures/entity/chest_boat/christmas_" + p.substring(i + 1))
                : resourceLocation;
        return instance.renderType(loc);
    }
}
#else

import com.mrmelon54.BetterChristmasChests.BetterChristmasChests;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.state.BoatRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BoatRenderer.class)
public class MixinBoatRenderer {
    @Redirect(method = "renderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/EntityModel;renderType(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/client/renderer/rendertype/RenderType;"))
    private RenderType redirectRenderType(EntityModel<BoatRenderState> entityModel, Identifier identifier) {
        return entityModel.renderType((BetterChristmasChests.CONFIG.isChristmas() && BetterChristmasChests.CONFIG.chestBoatEnabled) ? BetterChristmasChests.getChristmasTexture(identifier) : identifier);
    }
}
#endif
