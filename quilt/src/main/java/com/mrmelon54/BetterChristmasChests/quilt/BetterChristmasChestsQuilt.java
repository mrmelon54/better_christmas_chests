package com.mrmelon54.BetterChristmasChests.quilt;

import com.mrmelon54.BetterChristmasChests.BetterChristmasChests;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class BetterChristmasChestsQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        BetterChristmasChests.init();
    }
}
