package com.mrmelon54.BetterChristmasChests.mixin;

#if MC_VER < MC_1_21_11
/**
 * This is just a dummy class/mixin to make the compiler happy.
 */
@Mixin(net.minecraft.Util.class)
public class MixinSelectItemModelProperties {}
#else

import com.mrmelon54.BetterChristmasChests.models.ChristmasEnabledItemModelProperty;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperties;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SelectItemModelProperties.class)
@Environment(EnvType.CLIENT)
public class MixinSelectItemModelProperties {
    @Shadow
    @Final
    private static ExtraCodecs.LateBoundIdMapper<Identifier, SelectItemModelProperty.Type<?, ?>> ID_MAPPER;

    @Inject(method = "bootstrap", at = @At(value = "TAIL"))
    private static void add_christmas_local_time(CallbackInfo ci) {
        ID_MAPPER.put(Identifier.fromNamespaceAndPath("better_christmas_chests", "christmas_enabled"), ChristmasEnabledItemModelProperty.TYPE);
    }
}
#endif
