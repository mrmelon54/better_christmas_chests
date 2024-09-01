package com.mrmelon54.BetterChristmasChests.models;

import com.mrmelon54.BetterChristmasChests.BetterChristmasChests;
import com.mrmelon54.BetterChristmasChests.utils.ChestBoatArray;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class ChristmasChestModelProvider implements ClampedItemPropertyFunction {
    @Override
    public float unclampedCall(ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity, int i) {
        if (itemStack == null) return 0;
        boolean isChristmas = BetterChristmasChests.CONFIG.isChristmas();
        if (!isChristmas) return 0;
        if (itemStack.getItem() == Items.CHEST_MINECART && BetterChristmasChests.CONFIG.minecartWithChestEnabled)
            return 1;
        if (Arrays.stream(ChestBoatArray.ChestBoats).anyMatch(item -> itemStack.getItem() == item) && BetterChristmasChests.CONFIG.chestBoatEnabled)
            return 1;
        return 0;
    }
}
