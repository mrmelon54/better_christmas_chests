package com.mrmelon54.BetterChristmasChests.mixin;

import com.mrmelon54.BetterChristmasChests.BetterChristmasChests;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

#if MC_VER < MC_1_21_11
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.EnderChestBlockEntity;
import net.minecraft.world.level.block.entity.TrappedChestBlockEntity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.function.Consumer;
#else import net.minecraft.client.renderer.MaterialMapper;
import org.jetbrains.annotations.NotNull;
import net.minecraft.client.renderer.blockentity.state.ChestRenderState;
import net.minecraft.resources.Identifier;
#endif

@Mixin(Sheets.class)
public abstract class MixinSheets {
    #if MC_VER < MC_1_21_11
    @Shadow
    @Final
    public static ResourceLocation CHEST_SHEET;
    #else
    @Shadow
    @Final
    @NotNull
    public static MaterialMapper CHEST_MAPPER;
    #endif

    #if MC_VER < MC_1_21_11
    @Shadow
    private static Material chooseMaterial(ChestType chestType, Material material, Material material2, Material material3) {
        return null;
    }
    #else
    @Shadow
    private static Material chooseMaterial(ChestType chestType, Material doubleMaterial, Material leftMaterial, Material rightMaterial) {
        return null;
    }
    #endif

    #if MC_VER < MC_1_21_11
    @Shadow
    @Final
    public static Material ENDER_CHEST_LOCATION;
    @Shadow
    @Final
    public static Material CHEST_TRAP_LOCATION;
    @Shadow
    @Final
    public static Material CHEST_TRAP_LOCATION_LEFT;
    @Shadow
    @Final
    public static Material CHEST_TRAP_LOCATION_RIGHT;
    #endif
    @Shadow
    @Final
    public static Material CHEST_XMAS_LOCATION;
    @Shadow
    @Final
    public static Material CHEST_XMAS_LOCATION_LEFT;
    #if MC_VER < MC_1_21_11
    @Shadow
    @Final
    public static Material CHEST_XMAS_LOCATION_RIGHT;
    @Shadow
    @Final
    public static Material CHEST_LOCATION;
    @Shadow
    @Final
    public static Material CHEST_LOCATION_LEFT;
    @Shadow
    @Final
    public static Material CHEST_LOCATION_RIGHT;
    #endif
    @Shadow
    @Final
    public static Material CHEST_XMAS_LOCATION_RIGHT;
    @Unique
    private static final Material ENDER_XMAS_LOCATION = better_christmas_chests$chestXmasMaterial("ender");
    @Unique
    private static final Material CHEST_TRAP_XMAS_LOCATION = better_christmas_chests$chestXmasMaterial("trapped");
    @Unique
    private static final Material CHEST_TRAP_XMAS_LOCATION_LEFT = better_christmas_chests$chestXmasMaterial("trapped_left");
    @Unique
    private static final Material CHEST_TRAP_XMAS_LOCATION_RIGHT = better_christmas_chests$chestXmasMaterial("trapped_right");

    #if MC_VER >= MC_1_21_11
    @Unique
    private static final Material COPPER_CHEST_XMAS_LOCATION = better_christmas_chests$chestXmasMaterial("copper");
    @Unique
    private static final Material COPPER_CHEST_XMAS_LOCATION_LEFT = better_christmas_chests$chestXmasMaterial("copper_left");
    @Unique
    private static final Material COPPER_CHEST_XMAS_LOCATION_RIGHT = better_christmas_chests$chestXmasMaterial("copper_right");
    @Unique
    private static final Material EXPOSED_COPPER_CHEST_XMAS_LOCATION = better_christmas_chests$chestXmasMaterial("copper_exposed");
    @Unique
    private static final Material EXPOSED_COPPER_CHEST_XMAS_LOCATION_LEFT = better_christmas_chests$chestXmasMaterial("copper_exposed_left");
    @Unique
    private static final Material EXPOSED_COPPER_CHEST_XMAS_LOCATION_RIGHT = better_christmas_chests$chestXmasMaterial("copper_exposed_right");
    @Unique
    private static final Material WEATHERED_COPPER_CHEST_XMAS_LOCATION = better_christmas_chests$chestXmasMaterial("copper_weathered");
    @Unique
    private static final Material WEATHERED_COPPER_CHEST_XMAS_LOCATION_LEFT = better_christmas_chests$chestXmasMaterial("copper_weathered_left");
    @Unique
    private static final Material WEATHERED_COPPER_CHEST_XMAS_LOCATION_RIGHT = better_christmas_chests$chestXmasMaterial("copper_weathered_right");
    @Unique
    private static final Material OXIDIZED_COPPER_CHEST_XMAS_LOCATION = better_christmas_chests$chestXmasMaterial("copper_oxidized");
    @Unique
    private static final Material OXIDIZED_COPPER_CHEST_XMAS_LOCATION_LEFT = better_christmas_chests$chestXmasMaterial("copper_oxidized_left");
    @Unique
    private static final Material OXIDIZED_COPPER_CHEST_XMAS_LOCATION_RIGHT = better_christmas_chests$chestXmasMaterial("copper_oxidized_right");
    #endif

    @Unique
    private static Material better_christmas_chests$chestXmasMaterial(String variant) {
        #if MC_VER < MC_1_21_11
        return new Material(CHEST_SHEET, new ResourceLocation("better_christmas_chests:entity/chest/christmas_" + variant));
        #else
        return CHEST_MAPPER.apply(Identifier.fromNamespaceAndPath(BetterChristmasChests.MOD_ID, "christmas_" + variant));
        #endif
    }

    #if MC_VER < MC_1_21_11
    @Inject(method = "getAllMaterials", at = @At("RETURN"))
    private static void getXmasMaterials(Consumer<Material> consumer, CallbackInfo ci) {
        consumer.accept(ENDER_XMAS_LOCATION);
        consumer.accept(CHEST_TRAP_XMAS_LOCATION);
        consumer.accept(CHEST_TRAP_XMAS_LOCATION_LEFT);
        consumer.accept(CHEST_TRAP_XMAS_LOCATION_RIGHT);
    }

    @Inject(method = "chooseMaterial(Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/level/block/state/properties/ChestType;Z)Lnet/minecraft/client/resources/model/Material;", at = @At("HEAD"), cancellable = true)
    private static void chooseXmasMaterial(BlockEntity blockEntity, ChestType chestType, boolean bl, CallbackInfoReturnable<Material> cir) {
        boolean isChristmas = BetterChristmasChests.CONFIG.isChristmas();
        if (blockEntity instanceof EnderChestBlockEntity)
            cir.setReturnValue(isChristmas && BetterChristmasChests.CONFIG.enderChestEnabled
                    ? ENDER_XMAS_LOCATION
                    : ENDER_CHEST_LOCATION);
        else if (blockEntity instanceof TrappedChestBlockEntity)
            cir.setReturnValue(isChristmas && BetterChristmasChests.CONFIG.trappedChestEnabled
                    ? chooseMaterial(chestType, CHEST_TRAP_XMAS_LOCATION, CHEST_TRAP_XMAS_LOCATION_RIGHT, CHEST_TRAP_XMAS_LOCATION_RIGHT)
                    : chooseMaterial(chestType, CHEST_TRAP_LOCATION, CHEST_TRAP_LOCATION_LEFT, CHEST_TRAP_LOCATION_RIGHT));
        else if (blockEntity instanceof ChestBlockEntity)
            cir.setReturnValue(isChristmas && BetterChristmasChests.CONFIG.chestEnabled
                    ? chooseMaterial(chestType, CHEST_XMAS_LOCATION, CHEST_XMAS_LOCATION_LEFT, CHEST_XMAS_LOCATION_RIGHT)
                    : chooseMaterial(chestType, CHEST_LOCATION, CHEST_LOCATION_LEFT, CHEST_LOCATION_RIGHT));
    }
    #else
    @Inject(method = "chooseMaterial(Lnet/minecraft/client/renderer/blockentity/state/ChestRenderState$ChestMaterialType;Lnet/minecraft/world/level/block/state/properties/ChestType;)Lnet/minecraft/client/resources/model/Material;", at = @At("HEAD"), cancellable = true)
    private static void chooseXmasMaterial(ChestRenderState.ChestMaterialType chestMaterialType, ChestType chestType, CallbackInfoReturnable<Material> cir) {
        if (BetterChristmasChests.CONFIG.isChristmas()) {
            switch (chestMaterialType) {
                case ENDER_CHEST -> {
                    if (BetterChristmasChests.CONFIG.enderChestEnabled) cir.setReturnValue(ENDER_XMAS_LOCATION);
                }
                case CHRISTMAS, REGULAR -> chooseXmasMaterial(cir, BetterChristmasChests.CONFIG.chestEnabled, chestType, CHEST_XMAS_LOCATION, CHEST_XMAS_LOCATION_LEFT, CHEST_XMAS_LOCATION_RIGHT);
                case TRAPPED -> chooseXmasMaterial(cir, BetterChristmasChests.CONFIG.trappedChestEnabled, chestType, CHEST_TRAP_XMAS_LOCATION, CHEST_TRAP_XMAS_LOCATION_LEFT, CHEST_TRAP_XMAS_LOCATION_RIGHT);
                case COPPER_UNAFFECTED -> chooseXmasMaterial(cir, BetterChristmasChests.CONFIG.copperChestEnabled, chestType, COPPER_CHEST_XMAS_LOCATION, COPPER_CHEST_XMAS_LOCATION_LEFT, COPPER_CHEST_XMAS_LOCATION_RIGHT);
                case COPPER_EXPOSED -> chooseXmasMaterial(cir, BetterChristmasChests.CONFIG.copperChestEnabled, chestType, EXPOSED_COPPER_CHEST_XMAS_LOCATION, EXPOSED_COPPER_CHEST_XMAS_LOCATION_LEFT, EXPOSED_COPPER_CHEST_XMAS_LOCATION_RIGHT);
                case COPPER_WEATHERED -> chooseXmasMaterial(cir, BetterChristmasChests.CONFIG.copperChestEnabled, chestType, WEATHERED_COPPER_CHEST_XMAS_LOCATION, WEATHERED_COPPER_CHEST_XMAS_LOCATION_LEFT, WEATHERED_COPPER_CHEST_XMAS_LOCATION_RIGHT);
                case COPPER_OXIDIZED -> chooseXmasMaterial(cir, BetterChristmasChests.CONFIG.copperChestEnabled, chestType, OXIDIZED_COPPER_CHEST_XMAS_LOCATION, OXIDIZED_COPPER_CHEST_XMAS_LOCATION_LEFT, OXIDIZED_COPPER_CHEST_XMAS_LOCATION_RIGHT);
            }
        }
    }

    @Unique
    private static void chooseXmasMaterial(CallbackInfoReturnable<Material> cir, boolean chestEnabled, ChestType chestType, Material doubleMaterial, Material leftMaterial, Material rightMaterial) {
        if (chestEnabled) {
            cir.setReturnValue(chooseMaterial(chestType, doubleMaterial, leftMaterial, rightMaterial));
        }
    }
    #endif
}
