package com.mrmelon54.BetterChristmasChests;

import com.mrmelon54.BetterChristmasChests.config.ConfigStructure;
import com.mrmelon54.BetterChristmasChests.models.ChristmasChestModelProvider;
import com.mrmelon54.BetterChristmasChests.utils.ChestBoatArray;
import dev.architectury.registry.item.ItemPropertiesRegistry;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Calendar;

public class BetterChristmasChests {
    public static final String MOD_ID = "better_christmas_chests";
    public static ConfigStructure config;

    public static void init() {
        AutoConfig.register(ConfigStructure.class, JanksonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ConfigStructure.class).getConfig();

        ChristmasChestModelProvider christmasChestModelProvider = new ChristmasChestModelProvider();
        ItemPropertiesRegistry.register(Items.CHEST_MINECART, new ResourceLocation("christmas_chest"), christmasChestModelProvider);
        for (Item chestBoat : ChestBoatArray.ChestBoats)
            ItemPropertiesRegistry.register(chestBoat, new ResourceLocation("christmas_chest"), christmasChestModelProvider);
    }

    public static Screen createConfigScreen(Screen parent) {
        return AutoConfig.getConfigScreen(ConfigStructure.class, parent).get();
    }

    public static boolean isChristmasDates() {
        Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DATE);
        return calendar.get(Calendar.MONTH) == Calendar.DECEMBER && date >= 24 && date <= 26;
    }

    public static boolean isChristmas() {
        return switch (config.modeEnabled) {
            case ALWAYS -> true;
            case AT_CHRISTMAS -> BetterChristmasChests.isChristmasDates();
            case NEVER -> false;
        };
    }
}
