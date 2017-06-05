package com.kittenrush.game.Screens;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kittenrush.game.Managers.GameManager;

/**
 * Created by Tiago on 15/04/2014.
 */
public class SplashScreenGameLogo extends AbstractGameScreen{

    private Stage mSplashScreenGameLogoStage;

    public SplashScreenGameLogo(GameManager mGame) {
        super(mGame);
    }

    @Override
    public void show() {
        super.show();
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
    public void hide() {
        super.hide();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public InputProcessor getInputProcessor() {
        return mSplashScreenGameLogoStage;
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
