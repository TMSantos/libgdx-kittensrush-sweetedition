package com.kittenrush.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kittenrush.game.Controllers.WorldController;
import com.kittenrush.game.Controllers.WorldRenderer;
import com.kittenrush.game.Managers.GameManager;
import com.kittenrush.game.Screens.LevelSelectorScreen.pack1.LevelSelectorScreenPack1;
import com.kittenrush.game.Utils.Constants;
import com.kittenrush.game.Utils.GamePreferences;
import com.kittenrush.game.Utils.GameProgress;
import com.kittenrush.game.Utils.LevelCompleted;

/**
 * Created by Tiago on 15/04/2014.
 */
public class GameScreen extends AbstractGameScreen{

    private int m_LevelSelected;

    private Stage mGameScreenStage;

    private Stage mPauseScreenStage;
    private Skin mPauseScreenSkin;

    private Stage mWinScreenStage;
    private Stage mLostScreenStage;

    private Image mStarOne;
    private Image mStarTwo;
    private Image mStarThree;

    private Skin mEndGameScreensSkin;

    private Texture backgroundTexture;

    private Image mImgGameScreenBackground;
    private Image mMapLimit1;
    private Image mMapLimit2;

    private boolean m_bindWinScreen;

    private boolean m_callThirdStar;
    private float m_thirdStarTimer;

    //TOUCHPAD
    private Touchpad touchpad;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Skin touchpadSkin;
    private Drawable touchBackground;
    private Drawable touchKnob;
    private Vector2 touchPadValues;


    private WorldController m_worldController;
    private WorldRenderer m_worldRenderer;

    public GameScreen(GameManager game){
        super(game);
    }

    public GameScreen(GameManager game,int levelNumber){
        super(game);
        m_LevelSelected = levelNumber;
    }

    @Override
    public void show() {
        Gdx.input.setCatchBackKey(true);
        m_bindWinScreen = true;
        m_callThirdStar = false;
        m_thirdStarTimer = 0;
        mGameScreenStage = new Stage(new FitViewport(Constants.VIEWPORT_GUI_WIDTH,Constants.VIEWPORT_GUI_HEIGHT));

        m_worldController = new WorldController(game);
        m_worldController.setLevelNumber(m_LevelSelected);
        m_worldController.init();
        m_worldRenderer = new WorldRenderer(m_worldController);

        buildGameScreenResources();
        buildPauseGameScreen();
        buildWinGameScreen();
        buildLostGameScreen();
        buildTouchPad();

    }

    private void buildPauseGameScreen() {
        mPauseScreenStage = new Stage(new FitViewport(Constants.VIEWPORT_GUI_WIDTH,Constants.VIEWPORT_GUI_HEIGHT));
    }

    private void buildWinGameScreen() {

        mWinScreenStage = new Stage(new FitViewport(Constants.VIEWPORT_GUI_WIDTH,Constants.VIEWPORT_GUI_HEIGHT));

        mEndGameScreensSkin = new Skin(Gdx.files.internal(Constants.WIN_LOST_SCREEN_SKIN),
                new TextureAtlas(Constants.WIN_LOST_SCREEN_ATLAS));

        backgroundTexture = new Texture("Screens/winLostScreen/background.png");
        backgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Image mWinScreenBackground = new Image(backgroundTexture);

        Image mWinImage = new Image(mEndGameScreensSkin,"Win");
        mWinImage.setPosition(350,1120);

        Button mNextLvlButton = new Button(mEndGameScreensSkin,"NextLevel");
        mNextLvlButton.setPosition(210,725);

        mNextLvlButton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                //touchpad.invalidate();
                //nextLevel();

                //temporary
                Gdx.input.setInputProcessor(mGameScreenStage);
                m_worldController.restart();
                touchpad.invalidate();
                m_bindWinScreen = true;
                mStarOne.setVisible(false);
                mStarTwo.setVisible(false);
                mStarThree.setVisible(false);

            }
        });

        Button mWinMenuButton = new Button(mEndGameScreensSkin,"Menu");
        mWinMenuButton.setPosition(210,535);

        mWinMenuButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                m_bindWinScreen = true;
                game.setScreen(new MenuScreen(game), null);
            }
        });

        mStarOne = new Image(mEndGameScreensSkin,"Star");
        mStarOne.setPosition(245,1405);
        mStarOne.setVisible(false);

//        mStarOne.addAction(sequence(scaleTo(3f,3f,0.5f),scaleTo(1f,1f,0.5f),run(new Runnable() {
//            public void run() {
//                mStarTwo.addAction(sequence(scaleTo(3f,3f,0.5f),scaleTo(1f,1f,0.5f)));
//            }
//        })));

        mStarTwo = new Image(mEndGameScreensSkin,"Star");
        mStarTwo.setPosition(428,1540);
        mStarTwo.setRotation(-30);
        mStarTwo.setVisible(false);

        mStarThree = new Image(mEndGameScreensSkin,"Star");
        mStarThree.setPosition(652,1580);
        mStarThree.setRotation(-65);
        mStarThree.setVisible(false);

        mWinScreenStage.addActor(mWinScreenBackground);
        mWinScreenStage.addActor(mWinImage);
        mWinScreenStage.addActor(mNextLvlButton);
        mWinScreenStage.addActor(mWinMenuButton);
        mWinScreenStage.addActor(mStarOne);
        mWinScreenStage.addActor(mStarTwo);
        mWinScreenStage.addActor(mStarThree);

    }

    private void buildLostGameScreen() {
        mLostScreenStage = new Stage(new FitViewport(Constants.VIEWPORT_GUI_WIDTH,Constants.VIEWPORT_GUI_HEIGHT));

        Image lostBackground = new Image(backgroundTexture);

        Image mLostImage = new Image(mEndGameScreensSkin,"Lost");
        mLostImage.setPosition(320,1120);

        Button mRestartButton = new Button(mEndGameScreensSkin,"Replay");
        mRestartButton.setPosition(210,725);

        mRestartButton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(mGameScreenStage);
                m_worldController.restart();
                touchpad.invalidate();
                mStarOne.setVisible(false);
                mStarTwo.setVisible(false);
                mStarThree.setVisible(false);

            }
        });

        Button mLostMenuButton = new Button(mEndGameScreensSkin,"Menu");
        mLostMenuButton.setPosition(210,535);

        mLostMenuButton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game),null);
            }
        });

        mLostScreenStage.addActor(lostBackground);
        mLostScreenStage.addActor(mLostImage);
        mLostScreenStage.addActor(mRestartButton);
        mLostScreenStage.addActor(mLostMenuButton);

    }

    private void nextLevel(){
        m_worldController.setLevelNumber(m_worldController.getLevelNumber()+1);
        m_worldController.init();
    }

    private void buildGameScreenResources() {

        Texture backgroundTexture = new Texture(Constants.FIRST_LEVEL_BACKGROUND);
        backgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        mImgGameScreenBackground = new Image(backgroundTexture);

        TextureAtlas hudAtlas = new TextureAtlas(Constants.HUD_ATLAS);

        mMapLimit1 = new Image(hudAtlas.findRegion("mapLimit"));
        mMapLimit1.setPosition(0,0);
        mMapLimit2 = new Image(hudAtlas.findRegion("mapLimit"));
        mMapLimit2.setPosition(1130,0);

        mGameScreenStage.addActor(mImgGameScreenBackground);
        mGameScreenStage.addActor(mMapLimit1);
        mGameScreenStage.addActor(mMapLimit2);
    }

    @Override
    public void render(float deltaTime) {
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.setScreen(new LevelSelectorScreenPack1(game),null);
        }

        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mGameScreenStage.act(deltaTime);
        mGameScreenStage.draw();

        m_worldRenderer.render();

        if(m_worldController.gameState == WorldController.GAME_STATE.RUNNING){

            touchPadValues.x = touchpad.getKnobPercentX();
            touchPadValues.y = touchpad.getKnobPercentY();
            m_worldController.update(deltaTime,touchPadValues);
        }else if(m_worldController.gameState == WorldController.GAME_STATE.LOST){
            Gdx.input.setInputProcessor(mLostScreenStage);
            mLostScreenStage.act(deltaTime);
            mLostScreenStage.draw();

        }else if(m_worldController.gameState == WorldController.GAME_STATE.WIN){
            Gdx.input.setInputProcessor(mWinScreenStage);


            if(m_bindWinScreen) bindWinScreen();
            if(m_callThirdStar){
                m_thirdStarTimer += deltaTime;
                if(m_thirdStarTimer > 1f){
                    m_thirdStarTimer = 0;
                    m_callThirdStar = false;
                    callThree();
                }
            }

            mWinScreenStage.act(deltaTime);
            mWinScreenStage.draw();
        }

    }

    private void bindWinScreen() {
        m_bindWinScreen = false;
        switch(m_worldController.getLevelCompletedStars()) {
            case 1:
                callOneStar();
                break;
            case 2:
                callTwoStars();
                break;
            case 3:
                callThreeStars();
                break;
            default:
                break;
        }

    }

    private void callOneStar() {
        mStarOne.setVisible(true);
        mStarOne.addAction(sequence(scaleTo(3f,3f,0.5f),scaleTo(1f,1f,0.5f)));
    }

    private void callTwoStars() {
        mStarOne.setVisible(true);
        mStarOne.addAction(sequence(scaleTo(3f,3f,0.5f),scaleTo(1f,1f,0.5f),run(new Runnable() {
            public void run() {
                mStarTwo.setVisible(true);
                mStarTwo.addAction(sequence(scaleTo(3f,3f,0.5f),scaleTo(1f,1f,0.5f)));

            }
        })));
    }

    private void callThreeStars() {
        mStarOne.setVisible(true);
        mStarOne.addAction(sequence(scaleTo(3f,3f,0.5f),scaleTo(1f,1f,0.5f),run(new Runnable() {
            public void run() {
                mStarTwo.setVisible(true);
                mStarTwo.addAction(sequence(scaleTo(3f,3f,0.5f),scaleTo(1f,1f,0.5f)));
                m_callThirdStar = true;

            }
        })));
    }

    private void callThree() {
        mStarThree.setVisible(true);
        mStarThree.addAction(sequence(scaleTo(3f,3f,0.5f),scaleTo(1f,1f,0.5f)));
    }

    private void buildTouchPad() {
        //Create a touchpad skin
        touchpadSkin = new Skin();
        //Set background image
        touchpadSkin.add("touchBackground", new Texture("HUD/touchBackground.png"));
        //Set knob image
        touchpadSkin.add("touchKnob", new Texture("HUD/touchKnob.png"));
        //Create TouchPad Style
        touchpadStyle = new Touchpad.TouchpadStyle();
        //Create Drawable's from TouchPad skin
        touchBackground = touchpadSkin.getDrawable("touchBackground");
        touchKnob = touchpadSkin.getDrawable("touchKnob");
        //Apply the Drawables to the TouchPad Style
        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;

        //Create new TouchPad with the created style
        touchpad = new Touchpad(10, touchpadStyle);
        //setBounds(x,y,width,height)
        touchpad.setBounds(750, 15, 400, 400);

        touchPadValues = new Vector2();
        mGameScreenStage.addActor(touchpad);

    }

    @Override
    public void resize(int width, int height) {
        mGameScreenStage.getViewport().update();
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
        return mGameScreenStage;
    }

    @Override
    public void resume() {
        mGameScreenStage.getViewport().update();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
