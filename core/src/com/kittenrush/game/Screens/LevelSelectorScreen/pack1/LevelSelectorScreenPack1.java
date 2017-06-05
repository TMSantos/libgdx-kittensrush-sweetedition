package com.kittenrush.game.Screens.LevelSelectorScreen.pack1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kittenrush.game.Game.LevelInformation;
import com.kittenrush.game.Managers.GameManager;
import com.kittenrush.game.Screens.AbstractGameScreen;
import com.kittenrush.game.Screens.GameScreen;
import com.kittenrush.game.Screens.MenuScreen;
import com.kittenrush.game.Screens.OptionsScreen;
import com.kittenrush.game.Screens.transitions.ScreenTransition;
import com.kittenrush.game.Screens.transitions.ScreenTransitionFade;
import com.kittenrush.game.Utils.Constants;
import com.kittenrush.game.Utils.GamePreferences;
import com.kittenrush.game.Utils.GameProgress;
import com.kittenrush.game.Utils.LevelCompleted;

/**
 * Created by Tiago on 15/04/2014.
 */
public class LevelSelectorScreenPack1 extends AbstractGameScreen {

    private Stage mLevelSelectorScreenStage;
    private Skin mLevelSelectorPack1Skin;
    private Image mImgLevelSelectorBackground;

    private CatSelector mCatSelector;

    private Button mMenuButton;
    private Button mOptionsButton;

    private Button mLeftButton;
    private Button mRightButton;

    private Button mLevel1Button;
    private Button mLevel2Button;
    private Button mLevel3Button;
    private Button mLevel4Button;
    private Button mLevel5Button;
    private Button mLevel6Button;
    private Button mLevel7Button;
    private Button mLevel8Button;
    private Button mLevel9Button;
    private Button mLevel10Button;
    private Button mLevel11Button;

    private Array<LevelCompleted> mSaveData;

    public LevelSelectorScreenPack1(GameManager mGame) {
        super(mGame);
    }

    @Override
    public void show() {
        Gdx.input.setCatchBackKey(true);

        mLevelSelectorScreenStage = new Stage(new FitViewport(Constants.VIEWPORT_GUI_WIDTH,Constants.VIEWPORT_GUI_HEIGHT));

        GamePreferences.instance.loadGameProgress();
        mSaveData = new Array<LevelCompleted>();

        if(GamePreferences.instance.gameProgress != null && !GamePreferences.instance.gameProgress.levelsCompleted.isEmpty()) {

            for (LevelCompleted level : GamePreferences.instance.gameProgress.levelsCompleted) {
                mSaveData.add(level);
            }
        }

        buildLevelSelectorMenu();
    }

    private void buildLevelSelectorMenu() {
        mLevelSelectorPack1Skin = new Skin(Gdx.files.internal(Constants.LEVEL_SELECTOR_PACK_1_SKIN),
                new TextureAtlas(Constants.LEVEL_SELECTOR_PACK_1_ATLAS));

        Texture backgroundTexture = new Texture("Screens/levelSelectorScreen/pack1/background.png");
        backgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        mImgLevelSelectorBackground = new Image(backgroundTexture);

        Array<TextureAtlas.AtlasRegion> regions = new Array<TextureAtlas.AtlasRegion>();
        regions.add(mLevelSelectorPack1Skin.getAtlas().findRegion("catSelector"));

        Animation catAnimation = new Animation(1f,regions);
        mCatSelector = new CatSelector(catAnimation);
        mCatSelector.setPosition(500,130);
        mCatSelector.setVisible(false);

        mMenuButton = new Button(mLevelSelectorPack1Skin,"Menu");
        mMenuButton.setPosition(100,1650);

        mOptionsButton = new Button(mLevelSelectorPack1Skin,"Options");
        mOptionsButton.setPosition(650,1650);

        mLeftButton = new Button(mLevelSelectorPack1Skin,"Left");
        mLeftButton.setPosition(50,1200);

        mRightButton = new Button(mLevelSelectorPack1Skin,"Right");
        mRightButton.setPosition(960,1200);

        //TODO: MEGA CAGADAAAAAAAAAAAAAAAAAA , fazer design de um algoritmo decente quando ouver tempo.

        mLevel1Button = new Button(mLevelSelectorPack1Skin,mSaveData.size > 0 ? levelRegion(1,mSaveData.get(0).numberStars) : "Red");
        mLevel1Button.setPosition(490,96);

        mLevel2Button = new Button(mLevelSelectorPack1Skin,mSaveData.size > 1 ? levelRegion(2,mSaveData.get(1).numberStars) : "Yellow");
        mLevel2Button.setPosition(275,132);

        mLevel3Button = new Button(mLevelSelectorPack1Skin,mSaveData.size > 2 ? levelRegion(3,mSaveData.get(2).numberStars) : "Red");
        mLevel3Button.setPosition(96,209);

        mLevel4Button = new Button(mLevelSelectorPack1Skin,mSaveData.size > 3 ? levelRegion(4,mSaveData.get(3).numberStars) : "Yellow");
        mLevel4Button.setPosition(50,330);

        mLevel5Button = new Button(mLevelSelectorPack1Skin,mSaveData.size > 4 ? levelRegion(5,mSaveData.get(4).numberStars) : "Red");
        mLevel5Button.setPosition(147,436);

        mLevel6Button = new Button(mLevelSelectorPack1Skin,mSaveData.size > 5 ? levelRegion(6,mSaveData.get(5).numberStars) : "Yellow");
        mLevel6Button.setPosition(318,510);

        mLevel7Button = new Button(mLevelSelectorPack1Skin,mSaveData.size > 6 ? levelRegion(7,mSaveData.get(6).numberStars) : "Red");
        mLevel7Button.setPosition(218,600);

        mLevel8Button = new Button(mLevelSelectorPack1Skin,mSaveData.size > 7 ? levelRegion(8,mSaveData.get(7).numberStars) : "Yellow");
        mLevel8Button.setPosition(595,645);

        mLevel9Button = new Button(mLevelSelectorPack1Skin,mSaveData.size > 8 ? levelRegion(9,mSaveData.get(8).numberStars) : "Red");
        mLevel9Button.setPosition(800,585);

        mLevel10Button = new Button(mLevelSelectorPack1Skin,mSaveData.size > 9 ? levelRegion(10,mSaveData.get(9).numberStars) : "Yellow");
        mLevel10Button.setPosition(950,525);

        mLevel11Button = new Button(mLevelSelectorPack1Skin,mSaveData.size > 10 ? levelRegion(11,mSaveData.get(10).numberStars) : "Pink");
        mLevel11Button.setPosition(1028,312);

        mLevelSelectorScreenStage.addActor(mImgLevelSelectorBackground);

        mLevelSelectorScreenStage.addActor(mMenuButton);
        mLevelSelectorScreenStage.addActor(mOptionsButton);

        mLevelSelectorScreenStage.addActor(mLeftButton);
        mLevelSelectorScreenStage.addActor(mRightButton);

        mLevelSelectorScreenStage.addActor(mLevel1Button);
        mLevelSelectorScreenStage.addActor(mLevel2Button);
        mLevelSelectorScreenStage.addActor(mLevel5Button);
        mLevelSelectorScreenStage.addActor(mLevel4Button);
        mLevelSelectorScreenStage.addActor(mLevel3Button);
        mLevelSelectorScreenStage.addActor(mLevel7Button);
        mLevelSelectorScreenStage.addActor(mLevel6Button);
        mLevelSelectorScreenStage.addActor(mLevel8Button);
        mLevelSelectorScreenStage.addActor(mLevel9Button);
        mLevelSelectorScreenStage.addActor(mLevel10Button);
        mLevelSelectorScreenStage.addActor(mLevel11Button);

        mLevelSelectorScreenStage.addActor(mCatSelector);

        createListeners();
        createLevelListeners();
    }

    private void createLevelListeners() {

        mLevel1Button.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game,1),null);
            }
        });

        mLevel2Button.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(new GameScreen(game,2),null);
            }
        });

        mLevel3Button.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(new GameScreen(game,3),null);
            }
        });

        mLevel4Button.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(new GameScreen(game,4),null);
            }
        });

        mLevel5Button.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(new GameScreen(game,5),null);
            }
        });

        mLevel6Button.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(new GameScreen(game,6),null);
            }
        });

        mLevel7Button.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(new GameScreen(game,7),null);
            }
        });

        mLevel8Button.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(new GameScreen(game,8),null);
            }
        });

        mLevel9Button.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(new GameScreen(game,9),null);
            }
        });

        mLevel10Button.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(new GameScreen(game,10),null);
            }
        });

        mLevel11Button.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(new GameScreen(game,11),null);
            }
        });
    }

    private void createListeners() {

        mCatSelector.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Cat clicked");
                game.setScreen(new GameScreen(game,1),null);
            }
        });

        mMenuButton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                goToMenuScreen();
            }
        });

        mOptionsButton.addListener(new ClickListener(){

            @Override
            public void clicked(InputEvent event, float x, float y) {
                //ScreenTransition transition = ScreenTransitionFade.init(0.15f);
                game.setScreen(new OptionsScreen(game),null);
            }
        });
    }

    public String levelRegion(int level,int stars){
        String color;

        if(level % 2 == 0){
            color = "Yellow";
        }else if(level == 11){
            color = "Pink";
        }else{
            color = "Red";
        }

        if(stars == 0){
            return color;
        }else{
            return color+stars;
        }
    }

    @Override
    public void render(float deltaTime) {
        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            goToMenuScreen();
        }

        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mLevelSelectorScreenStage.act(deltaTime);
        mLevelSelectorScreenStage.draw();
    }

    public void goToMenuScreen(){
        game.setScreen(new MenuScreen(game),null);
    }

    @Override
    public void resize(int width, int height) {

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
        return mLevelSelectorScreenStage;
    }

    @Override
    public void resume() {
        mLevelSelectorScreenStage.getViewport().update();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
