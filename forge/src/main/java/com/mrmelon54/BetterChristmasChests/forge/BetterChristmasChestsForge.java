package com.mrmelon54.BetterChristmasChests.forge;

import dev.architectury.platform.forge.EventBuses;
import com.mrmelon54.BetterChristmasChests.BetterChristmasChests;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BetterChristmasChests.MOD_ID)
public class BetterChristmasChestsForge {
    public BetterChristmasChestsForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(BetterChristmasChests.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        BetterChristmasChests.init();
    }
}
