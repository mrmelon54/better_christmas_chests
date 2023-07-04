package com.mrmelon54.BetterChristmasChests.fabriclike;

import com.mrmelon54.BetterChristmasChests.BetterChristmasChests;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return BetterChristmasChests::createConfigScreen;
    }
}
