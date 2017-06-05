package com.kittenrush.game;

import com.badlogic.gdx.assets.AssetManager;
import com.kittenrush.game.Managers.GameManager;
import com.kittenrush.game.Managers.ResourceManager;
import com.kittenrush.game.Screens.GameScreen;
import com.kittenrush.game.Screens.LevelSelectorScreen.pack1.LevelSelectorScreenPack1;
import com.kittenrush.game.Screens.MenuScreen;
import com.kittenrush.game.Screens.OptionsScreen;

public class KittenRushFat extends GameManager {

    @Override
    public void create() {

        ResourceManager.instance.initResourceManager(new AssetManager());

        setScreen(new GameScreen(this,2),null);
        //setScreen(new LevelSelectorScreenPack1(this),null);
        //setScreen(new MenuScreen(this),null);
        //setScreen(new OptionsScreen(this),null);
    }
}
