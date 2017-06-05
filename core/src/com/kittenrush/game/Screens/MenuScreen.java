package com.kittenrush.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kittenrush.game.Managers.GameManager;
import com.kittenrush.game.Screens.LevelSelectorScreen.pack1.LevelSelectorScreenPack1;
import com.kittenrush.game.Screens.transitions.ScreenTransition;
import com.kittenrush.game.Screens.transitions.ScreenTransitionFade;
import com.kittenrush.game.Utils.Constants;

import java.util.Stack;

/**
 * Created by Tiago on 15/04/2014.
 */
public class MenuScreen extends AbstractGameScreen{

    private Stage mMainMenuStage;
    private Skin mMainMenuSkin;

    private Image mImgMainMenuBackground;

    private Button mPlayButton;
    private Button mOptionsButton;
    private Button mCreditsButton;
    private Button mExitButton;
    private Button mStarButton;

    public MenuScreen(GameManager game) {
        super(game);
    }

    @Override
    public void show() {
        Gdx.input.setCatchBackKey(true);

        mMainMenuStage = new Stage(new FitViewport(Constants.VIEWPORT_GUI_WIDTH,Constants.VIEWPORT_GUI_HEIGHT));
        //mMainMenuStage = new Stage(new FillViewport(Constants.VIEWPORT_GUI_WIDTH,Constants.VIEWPORT_GUI_HEIGHT));
        //mMainMenuStage = new Stage(new ScreenViewport(new OrthographicCamera(Constants.VIEWPORT_GUI_WIDTH,Constants.VIEWPORT_GUI_HEIGHT)));

        buildMainMenu();

    }

    private void buildMainMenu() {
        mMainMenuSkin = new Skin(Gdx.files.internal(Constants.MAIN_MENU_SKIN),
                new TextureAtlas(Constants.MAIN_MENU_ATLAS));

        Texture backgroundTexture = new Texture("Screens/menuScreen/background.png");
        backgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        mImgMainMenuBackground = new Image(backgroundTexture);
        mPlayButton = new Button(mMainMenuSkin,"Play");
        mOptionsButton = new Button(mMainMenuSkin,"Options");
        mCreditsButton = new Button(mMainMenuSkin,"Credits");
        mExitButton = new Button(mMainMenuSkin,"Exit");
        mStarButton = new Button(mMainMenuSkin,"Star");

        mPlayButton.setPosition(240,1060);
        mOptionsButton.setPosition(242,881);
        mCreditsButton.setPosition(243,695);
        mExitButton.setPosition(325,550);
        mStarButton.setPosition(800,135);

        mMainMenuStage.addActor(mImgMainMenuBackground);
        mMainMenuStage.addActor(mPlayButton);
        mMainMenuStage.addActor(mOptionsButton);
        mMainMenuStage.addActor(mCreditsButton);
        mMainMenuStage.addActor(mExitButton);
        mMainMenuStage.addActor(mStarButton);

        createButtonListeners();
    }

    private void createButtonListeners() {
        mPlayButton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                /*ScreenTransition transition = ScreenTransitionFade.init(0.15f);
                game.setScreen(new LevelSelectorScreenPack1(game),transition);*/
                game.setScreen(new LevelSelectorScreenPack1(game),null);
            }
        });

        mOptionsButton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                //ScreenTransition transition = ScreenTransitionFade.init(0.15f);
                game.setScreen(new OptionsScreen(game),null);
            }
        });

        mExitButton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float deltaTime) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mMainMenuStage.act(deltaTime);
        mMainMenuStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        mMainMenuStage.getViewport().update();
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
        return mMainMenuStage;
    }

    @Override
    public void resume() {
        mMainMenuStage.getViewport().update();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
