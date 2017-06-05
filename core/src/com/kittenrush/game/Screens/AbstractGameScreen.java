package com.kittenrush.game.Screens;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.kittenrush.game.Managers.GameManager;
import com.kittenrush.game.Managers.ResourceManager;

/**
 * Created by Tiago on 15/04/2014.
 */
public abstract class AbstractGameScreen implements Screen{

    public GameManager game;

    public AbstractGameScreen(GameManager mGame){
        game = mGame;
    }

    public void render(float deltaTime) {}

    public void resize(int width, int height) {}

    public void show() {}

    public void hide() {}

    public void pause() {}

    public abstract InputProcessor getInputProcessor ();

    public void resume () {
       // ResourceManager.instance.initResourceManager();
    }

    public void dispose () {
        ResourceManager.instance.dispose();
    }
}
