package com.kittenrush.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kittenrush.game.Managers.GameManager;
import com.kittenrush.game.Screens.transitions.ScreenTransition;
import com.kittenrush.game.Screens.transitions.ScreenTransitionFade;
import com.kittenrush.game.Utils.Constants;
import com.kittenrush.game.Utils.GamePreferences;

/**
 * Created by Tiago on 15/04/2014.
 */
public class OptionsScreen extends AbstractGameScreen{

    private Stage mOptionsScreenStage;
    private Skin mOptionsScreenSkin;

    private Image mOptionsScreenBackground;

    private Button mSoundButtonOn;
    private Button mMusicButtonOn;
    private Button mSoundButtonOff;
    private Button mMusicButtonOff;
    private Button mHelpButton;

    private Button mBackButton;

    public OptionsScreen(GameManager mGame) {
        super(mGame);
    }

    @Override
    public void show() {
        Gdx.input.setCatchBackKey(true);

        mOptionsScreenStage = new Stage(new FitViewport(Constants.VIEWPORT_GUI_WIDTH,Constants.VIEWPORT_GUI_HEIGHT));

        buildOptionsScreen();
    }

    private void buildOptionsScreen() {
        mOptionsScreenSkin = new Skin(Gdx.files.internal(Constants.OPTIONS_SCREEN_SKIN),
                new TextureAtlas(Constants.OPTIONS_SCREEN_ATLAS));

        Texture backgroundTexture = new Texture("Screens/optionsScreen/background.png");
        backgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        mOptionsScreenBackground = new Image(backgroundTexture);

        mSoundButtonOn = new Button(mOptionsScreenSkin,"SoundOn");
        mSoundButtonOn.setPosition(180,1042);

        mSoundButtonOff = new Button(mOptionsScreenSkin,"SoundOff");
        mSoundButtonOff.setPosition(180,1042);

        mMusicButtonOn = new Button(mOptionsScreenSkin,"MusicOn");
        mMusicButtonOn.setPosition(180,835);

        mMusicButtonOff = new Button(mOptionsScreenSkin,"MusicOff");
        mMusicButtonOff.setPosition(180,835);

        mHelpButton = new Button(mOptionsScreenSkin,"Help");
        mHelpButton.setPosition(178,640);

        mBackButton = new Button(mOptionsScreenSkin,"Back");
        mBackButton.setPosition(100,90);

        mOptionsScreenStage.addActor(mOptionsScreenBackground);
        mOptionsScreenStage.addActor(mSoundButtonOn);
        mOptionsScreenStage.addActor(mSoundButtonOff);
        mOptionsScreenStage.addActor(mMusicButtonOn);
        mOptionsScreenStage.addActor(mMusicButtonOff);
        mOptionsScreenStage.addActor(mHelpButton);
        mOptionsScreenStage.addActor(mBackButton);

        createListeners();

        loadSettings();
    }

    private void createListeners() {

        mSoundButtonOn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mSoundButtonOn.setVisible(false);
                mSoundButtonOff.setVisible(true);
                saveSettings();
            }
        });

        mSoundButtonOff.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mSoundButtonOn.setVisible(true);
                mSoundButtonOff.setVisible(false);
                saveSettings();
            }
        });

        mMusicButtonOn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mMusicButtonOn.setVisible(false);
                mMusicButtonOff.setVisible(true);
                saveSettings();
            }
        });

        mMusicButtonOff.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mMusicButtonOn.setVisible(true);
                mMusicButtonOff.setVisible(false);
                saveSettings();
            }
        });

        mBackButton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                goToMenuScreen();
            }
        });
    }

    @Override
    public void render(float deltaTime) {
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            goToMenuScreen();
        }

        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mOptionsScreenStage.act(deltaTime);
        mOptionsScreenStage.draw();
    }

    public void saveSettings(){
        GamePreferences prefs = GamePreferences.instance;
        prefs.sound = mSoundButtonOn.isVisible();
        prefs.music = mMusicButtonOn.isVisible();
        prefs.saveMusic();
    }

    public void loadSettings(){
        GamePreferences prefs = GamePreferences.instance;
        prefs.loadMusic();

        if(prefs.music){
            mMusicButtonOn.setVisible(true);
            mMusicButtonOff.setVisible(false);
        }else{
            mMusicButtonOn.setVisible(false);
            mMusicButtonOff.setVisible(true);
        }

        if(prefs.sound){
            mSoundButtonOn.setVisible(true);
            mSoundButtonOff.setVisible(false);
        }else{
            mSoundButtonOn.setVisible(false);
            mSoundButtonOff.setVisible(true);
        }

    }

    public void goToMenuScreen(){
        game.setScreen(new MenuScreen(game),null);
    }

    @Override
    public void resize(int width, int height) {
        mOptionsScreenStage.getViewport().update();
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
        return mOptionsScreenStage;
    }

    @Override
    public void resume() {
        mOptionsScreenStage.getViewport().update();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
