package com.mrmelon54.BetterChristmasChests.mixin;

import com.mrmelon54.BetterChristmasChests.BetterChristmasChests;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.EnderChestBlockEntity;
import net.minecraft.world.level.block.entity.TrappedChestBlockEntity;
import net.minecraft.world.level.block.state.properties.ChestType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;

@Mixin(Sheets.class)
public abstract class MixinSheets {
    @Shadow
    @Final
    public static ResourceLocation CHEST_SHEET;

    @Shadow
    @Final
    public static Material ENDER_CHEST_LOCATION;

    @Shadow
    private static Material chooseMaterial(ChestType chestType, Material material, Material material2, Material material3) {
        return null;
    }

    @Shadow
    @Final
    public static Material CHEST_TRAP_LOCATION;
    @Shadow
    @Final
    public static Material CHEST_TRAP_LOCATION_LEFT;
    @Shadow
    @Final
    public static Material CHEST_TRAP_LOCATION_RIGHT;
    @Shadow
    @Final
    public static Material CHEST_XMAS_LOCATION;
    @Shadow
    @Final
    public static Material CHEST_XMAS_LOCATION_LEFT;
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
    @Unique
    private static final Material ENDER_XMAS_LOCATION = chestXmasMaterial("ender");
    @Unique
    private static final Material CHEST_TRAP_XMAS_LOCATION = chestXmasMaterial("trapped");
    @Unique
    private static final Material CHEST_TRAP_XMAS_LOCATION_LEFT = chestXmasMaterial("trapped_left");
    @Unique
    private static final Material CHEST_TRAP_XMAS_LOCATION_RIGHT = chestXmasMaterial("trapped_right");

    @Unique
    private static Material chestXmasMaterial(String variant) {
        return new Material(CHEST_SHEET, new ResourceLocation("better_christmas_chests:entity/chest/christmas_" + variant));
    }

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
}
