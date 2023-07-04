package com.mrmelon54.BetterChristmasChests.forge;

import com.mrmelon54.BetterChristmasChests.BetterChristmasChests;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BetterChristmasChests.MOD_ID)
public class BetterChristmasChestsForge {
    public BetterChristmasChestsForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(BetterChristmasChests.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenFactory.class, () -> new ConfigScreenFactory((mc, screen) -> BetterChristmasChests.createConfigScreen(screen)));
        BetterChristmasChests.init();
    }
}
