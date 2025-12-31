package com.mrmelon54.BetterChristmasChests;

import com.mrmelon54.BetterChristmasChests.enums.ChristmasChestsEnabled;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

import java.util.Calendar;

@Config(name = "better_christmas_chests")
@Config.Gui.Background("better_christmas_chests:textures/gui/candy_cane.png")
public class ConfigStructure implements ConfigData {
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public ChristmasChestsEnabled modeEnabled = ChristmasChestsEnabled.AT_CHRISTMAS;

    // Enable charm integration
    public boolean charmPresentsEnabled = true;

    // Enable horse types separately
    // The normal, zombie and skeleton variants of the Christmas horse chests can't be seen in vanilla
    public boolean horseEnabled = true;
    public boolean zombieHorseEnabled = true;
    public boolean donkeyEnabled = true;
    public boolean llamaEnabled = true;

    // Enable chest types separately
    public boolean chestEnabled = true;
    public boolean trappedChestEnabled = true;
    public boolean enderChestEnabled = true;
    public boolean copperChestEnabled = true;

    // Enable minecart with chest type separately
    public boolean minecartWithChestEnabled = true;

    // Enable chest boat type separately
    public boolean chestBoatEnabled = true;

    private boolean isChristmasDates() {
        Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DATE);
        return calendar.get(Calendar.MONTH) == Calendar.DECEMBER && date >= 24 && date <= 26;
    }

    private boolean isDecemberDates() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) == Calendar.DECEMBER;
    }

    public boolean isChristmas() {
        return switch (modeEnabled) {
            case ALWAYS -> true;
            case AT_CHRISTMAS -> isChristmasDates();
            case DECEMBER -> isDecemberDates();
            case NEVER -> false;
        };
    }
}
