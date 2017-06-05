package com.kittenrush.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.kittenrush.game.Managers.GameManager;
import com.kittenrush.game.Utils.Constants;

/**
 * Created by Tiago on 15/04/2014.
 */
public class LoadingScreen extends AbstractGameScreen{

    private Stage mLoadScreen;

    private Texture mLoadScreenTexture;

    private Image mLoadScreenImage;

    public LoadingScreen(GameManager game){
        super(game);
    }

    @Override
    public void show() {
        mLoadScreen = new Stage();
        mLoadScreen.setViewport(new FitViewport(Constants.VIEWPORT_WIDTH,Constants.VIEWPORT_HEIGHT));

        mLoadScreenTexture = new Texture(Gdx.files.internal("LoadingScreen/loadScreen-FHD.jpg"));

        mLoadScreenImage = new Image(mLoadScreenTexture);

        Stack stack = new Stack();
        stack.setSize(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        stack.add(mLoadScreenImage);

        mLoadScreen.addActor(stack);
    }

    @Override
    public void render(float deltaTime) {
        mLoadScreen.act(deltaTime);

        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mLoadScreen.draw();

    }

    @Override
    public void resize(int width, int height){
        mLoadScreen.getViewport().update(width,height, true);
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
        return mLoadScreen;
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
