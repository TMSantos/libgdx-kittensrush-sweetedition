package com.kittenrush.game.Utils;

/**
 * Created by Tiago on 15/04/2014.
 */
public class Constants {

    public static final float VIEWPORT_WIDTH = 10f;
    public static final float VIEWPORT_HEIGHT = 16f;

    public static final float VIEWPORT_GUI_WIDTH = 1200.0f;
    public static final float VIEWPORT_GUI_HEIGHT = 1920.0f;

    // Angle of rotation for dead zone (no movement)
    public static final float ACCEL_ANGLE_DEAD_ZONE = 5.0f;

    // Max angle of rotation needed to gain maximum movement velocity
    public static final float ACCEL_MAX_ANGLE_MAX_MOVEMENT = 20.0f;

    public static final String GAME_PROGRESS = "gameprogress.prefs";

    //FONTS
    public static final String CAT_FONT = "Fonts/kittenrush.fnt";


    //-------------------------SCREENS--------------------------------
    //MAIN MENU
    public static final String MAIN_MENU_SKIN = "Screens/menuScreen/mainmenu.json";
    public static final String MAIN_MENU_ATLAS = "Screens/menuScreen/menuscreen.atlas";

    //OPTIONS SCREEN
    public static final String OPTIONS_SCREEN_SKIN = "Screens/optionsScreen/optionsscreen.json";
    public static final String OPTIONS_SCREEN_ATLAS = "Screens/optionsScreen/optionsscreen.atlas";

    //LEVEL SELECTOR SKIN PACK 1
    public static final String LEVEL_SELECTOR_PACK_1_SKIN = "Screens/levelSelectorScreen/pack1/levelselectorscreenpack1.json";
    public static final String LEVEL_SELECTOR_PACK_1_ATLAS = "Screens/levelSelectorScreen/pack1/levelselectorscreen1.atlas";

    //PAUSE GAME SCREEN
    public static final String PAUSE_GAME_SCREEN_SKIN = "Screens/pauseScreen/pausescreen.json";
    public static final String PAUSE_GAME_SCREEN_ATLAS = "Screens/pauseScreen/pausescreen.atlas";

    //WIN-LOST SCREENS
    public static final String WIN_LOST_SCREEN_SKIN = "Screens/winLostScreen/winlostscreen.json";
    public static final String WIN_LOST_SCREEN_ATLAS = "Screens/winLostScreen/winlostscreen.atlas";

    //------------------------ASSETS----------------------------------------------
    //CAT
    public static final String CAT_ATLAS = "Cat/cat.atlas";

    //HUD
    public static final String HUD_ATLAS = "HUD/hud.atlas";

    //MAP ASSETS
    public static final String MAP_ASSETS_ATLAS = "Levels/pack1/platforms.atlas";

    //FIRST PACK
    public static final String LEVEL_PACK1_JSON = "Levels/pack1/levelsPack1.json";
    public static final String FIRST_LEVEL_BACKGROUND = "Levels/pack1/firstBackground.png";
    public static final String FOOD_ATLAS = "Levels/pack1/food.atlas";

    //MAPS
    public static final String LEVEL1_MAP = "Levels/maps/level";

    //MAP OBJECTS
    public static final int SUPER_CAKE = 0;
    public static final int CAKE_TYPE = 1;
    public static final int MOVING_PLATFORM = 2;
    public static final int PLATFORM_STATIC = 3;
    public static final int CARAMEL_PLATFORM = 4;
    public static final int SPIKES_PLATFORM = 5;
    public static final int WALL_SPIKES = 6;
    public static final int CHEESE_PLAT = 7;
    public static final int TRAP_PLATFORM = 8;
    public static final int BROKEN_PLATFORM = 9;
    public static final int CUSTOM_PHYSIC_PLATFORM = 10;
    public static final int MOVING_FOOD = 11;
    public static final int VEGETABLE_TYPE = 12;
}


