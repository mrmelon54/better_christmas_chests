package com.mrmelon54.BetterChristmasChests.forge;

import com.mrmelon54.BetterChristmasChests.BetterChristmasChests;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BetterChristmasChests.MOD_ID)
public class BetterChristmasChestsForge {
    public BetterChristmasChestsForge() {
        EventBuses.registerModEventBus(BetterChristmasChests.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        BetterChristmasChests.init();
    }
}
