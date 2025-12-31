package com.mrmelon54.BetterChristmasChests.models;

#if MC_VER >= MC_1_21_11

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mrmelon54.BetterChristmasChests.BetterChristmasChests;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.select.SelectItemModelProperty;
import net.minecraft.util.Util;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.concurrent.TimeUnit;

@Environment(EnvType.CLIENT)
public enum ChristmasEnabled implements SelectItemModelProperty<Boolean> {
    ENABLED, DISABLED;

    private static final long UPDATE_INTERVAL_MS = TimeUnit.SECONDS.toMillis(1);
    public static final Codec<Boolean> VALUE_CODEC = Codec.BOOL;
    private static final MapCodec<ChristmasEnabled> DATA_MAP_CODEC = MapCodec.unit(DISABLED);
    public static final Type<ChristmasEnabled, Boolean> TYPE = Type.create(DATA_MAP_CODEC, VALUE_CODEC);
    private long nextUpdateTimeMs;
    private Boolean lastResult = false;

    @Override
    public Boolean get(@NonNull ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int seed, @NonNull ItemDisplayContext displayContext) {
        long l = Util.getMillis();
        if (l > nextUpdateTimeMs) {
            lastResult = update();
            nextUpdateTimeMs = l + UPDATE_INTERVAL_MS;
        }
        return lastResult;
    }

    private boolean update() {
        return BetterChristmasChests.CONFIG.isChristmas();
    }

    @Override
    public @NonNull Type<? extends SelectItemModelProperty<Boolean>, Boolean> type() {
        return TYPE;
    }

    @Override
    public @NonNull Codec<Boolean> valueCodec() {
        return VALUE_CODEC;
    }
}
#endif
