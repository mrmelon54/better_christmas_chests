package com.mrmelon54.BetterChristmasChests.mixin;

#if MC_VER < MC_1_21_11
/**
 * This is just a dummy class/mixin to make the compiler happy.
 */
@Mixin(net.minecraft.Util.class)
public class MixinMinecartRenderer {}
#else

import com.mrmelon54.BetterChristmasChests.BetterChristmasChests;
import net.minecraft.client.renderer.special.ChestSpecialRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.resources.Identifier;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChestSpecialRenderer.class)
public class MixinChestSpecialRenderer {
    @Shadow
    @Final
    private MaterialSet materials;

    @Shadow
    @Final
    private Material material;

    @Redirect(method = "submit", at = @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/special/ChestSpecialRenderer;material:Lnet/minecraft/client/resources/model/Material;", opcode = Opcodes.GETFIELD))
    Material replaceXmasMaterial(ChestSpecialRenderer instance) {
        if (BetterChristmasChests.CONFIG.isChristmas() && BetterChristmasChests.CONFIG.minecartWithChestEnabled) {
            Identifier texture = material.texture();
            if (texture.toString().equals("minecraft:entity/chest/normal")) {
                texture = Identifier.withDefaultNamespace("entity/chest/christmas");
            } else {
                texture = BetterChristmasChests.getChristmasTexture(texture);
            }
            return new Material(material.atlasLocation(), texture);
        }
        return material;
    }
}
#endif
