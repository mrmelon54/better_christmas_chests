package com.mrmelon54.BetterChristmasChests.mixin;

#if MC_VER < MC_1_21_11
/**
 * This is just a dummy class/mixin to make the compiler happy.
 */
@Mixin(net.minecraft.Util.class)
public class MixinRaftRenderer {}
#else

import com.mrmelon54.BetterChristmasChests.BetterChristmasChests;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.RaftRenderer;
import net.minecraft.client.renderer.entity.state.BoatRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RaftRenderer.class)
public class MixinRaftRenderer {
    @Redirect(method = "renderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/EntityModel;renderType(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/client/renderer/rendertype/RenderType;"))
    private RenderType redirectRenderType(EntityModel<BoatRenderState> entityModel, Identifier identifier) {
        return entityModel.renderType((BetterChristmasChests.CONFIG.isChristmas() && BetterChristmasChests.CONFIG.chestBoatEnabled) ? BetterChristmasChests.getChristmasTexture(identifier) : identifier);
    }
}
#endif
