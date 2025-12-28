package com.mrmelon54.BetterChristmasChests;

import com.mrmelon54.infrastructury.Infrastructury;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.Identifier;

#if MC_VER < MC_1_21_11
import com.mrmelon54.BetterChristmasChests.models.ChristmasChestModelProvider;
import com.mrmelon54.BetterChristmasChests.utils.ChestBoatArray;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
#endif

import java.util.function.Supplier;

public class BetterChristmasChests {
    public static final String MOD_ID = "better_christmas_chests";
    public static ConfigStructure CONFIG = AutoConfig.register(ConfigStructure.class, JanksonConfigSerializer::new).get();

    public static void init() {
        Infrastructury.registerConfigScreen((mc, screen) -> createConfigScreen(screen).get());

        #if MC_VER < MC_1_21_11
        ChristmasChestModelProvider christmasChestModelProvider = new ChristmasChestModelProvider();
        ItemProperties.register(Items.CHEST_MINECART, new ResourceLocation("christmas_chest"), christmasChestModelProvider);
        for (Item chestBoat : ChestBoatArray.ChestBoats)
            ItemProperties.register(chestBoat, new ResourceLocation("christmas_chest"), christmasChestModelProvider);
        #endif
    }

    #if MC_VER >= MC_1_21_11
    public static Identifier getChristmasTexture(Identifier identifier) {
        String[] a = identifier.getPath().split("/");
        if (a.length >= 1) {
            a[a.length - 1] = "christmas_" + a[a.length - 1];
        }
        return Identifier.fromNamespaceAndPath(MOD_ID, String.join("/", a));
    }
    #endif

    public static Supplier<Screen> createConfigScreen(Screen screen) {
        return AutoConfig.getConfigScreen(ConfigStructure.class, screen);
    }
}
