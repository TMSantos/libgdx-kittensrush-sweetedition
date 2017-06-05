package com.kittenrush.game.Screens;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kittenrush.game.Managers.GameManager;

/**
 * Created by Tiago on 15/04/2014.
 */
public class AboutScreen extends AbstractGameScreen{

    private Stage mAboutScreenStage;

    public AboutScreen(GameManager mGame) {
        super(mGame);
    }

    @Override
    public void render(float deltaTime) {
        super.render(deltaTime);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public InputProcessor getInputProcessor() {
        return mAboutScreenStage;
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
