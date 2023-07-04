package com.mrmelon54.BetterChristmasChests.fabric;

import com.mrmelon54.BetterChristmasChests.fabriclike.BetterChristmasChestsFabricLike;
import net.fabricmc.api.ModInitializer;

public class BetterChristmasChestsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BetterChristmasChestsFabricLike.init();
    }
}
