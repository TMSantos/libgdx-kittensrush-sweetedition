package com.kittenrush.game.Controllers;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.kittenrush.game.Game.Level;
import com.kittenrush.game.Game.LevelInformationFactory;
import com.kittenrush.game.Managers.GameManager;
import com.kittenrush.game.Utils.CameraHelper;
import com.kittenrush.game.Utils.GamePreferences;
import com.kittenrush.game.Utils.GameProgress;
import com.kittenrush.game.Utils.LevelCompleted;

/**
 * Created by Tiago on 15/04/2014.
 */
public class WorldController {

    private GameManager m_game;

    public Level level;

    public CameraHelper cameraHelper;

    private int m_levelNumber;

    private int m_levelCompletedStars;

    public GAME_STATE gameState;

    public WorldController (GameManager game) {
        m_game = game;
    }

    public void init(){
        LevelInformationFactory.instance.init();
        level = new Level(m_levelNumber);

        cameraHelper = new CameraHelper();
        cameraHelper.setTarget(level.cat);
        cameraHelper.setTargetMaxSpeed(LevelInformationFactory.instance.levels.get(m_levelNumber-1).catMaxSpeed);
        cameraHelper.setCameraSpeed(LevelInformationFactory.instance.levels.get(m_levelNumber-1).cameraSpeed);
        cameraHelper.setLevelFinal(LevelInformationFactory.instance.levels.get(m_levelNumber-1).mapEnd);

        gameState = GAME_STATE.RUNNING;
    }

    public void update(float deltaTime,Vector2 touchPadValues){
        level.update(deltaTime);
        cameraHelper.update(deltaTime);

        if(!level.cat.isSuperFat() && cameraHelper.getPosition().y-9 > level.cat.getBody().getPosition().y) {
            cameraHelper.setCameraSpeed(0f);
            gameState = GAME_STATE.LOST;
        }else if(level.cat.isSuperFat() && cameraHelper.getPosition().y-10 > level.cat.getBody().getPosition().y){
            cameraHelper.setCameraSpeed(0f);
            gameState = GAME_STATE.LOST;
        }else if(level.cat.getBody().getPosition().y-8 > LevelInformationFactory.instance.levels.get(m_levelNumber-1).mapEnd){
            gameState = GAME_STATE.WIN;
            getCollectedStars();
            saveGame();
        }
        handleInputGame(deltaTime,touchPadValues);

    }

    private void saveGame() {
        GamePreferences.instance.loadGameProgress();
        GameProgress previousSave = GamePreferences.instance.gameProgress;

        if(previousSave == null){
            previousSave = new GameProgress();
        }else {
            LevelCompleted level = new LevelCompleted();
            level.levelNumber = m_levelNumber;
            level.numberStars = m_levelCompletedStars;

            if (GamePreferences.instance.gameProgress.levelsCompleted.isEmpty()) {
                previousSave.levelsCompleted.add(level);
            } else {
                for (LevelCompleted levelCompleted : GamePreferences.instance.gameProgress.levelsCompleted) {
                    if (levelCompleted.levelNumber == level.levelNumber && levelCompleted.numberStars >= level.numberStars) {
                        return;
                    } else if (levelCompleted.levelNumber == level.levelNumber && levelCompleted.numberStars < level.numberStars) {
                        previousSave.levelsCompleted.remove(levelCompleted);
                        previousSave.levelsCompleted.add(level);
                    } else {
                        previousSave.levelsCompleted.add(level);
                        return;
                    }
                }
            }
        }

        GamePreferences.instance.saveGameState(previousSave);
    }

    private void getCollectedStars() {
        if(level.cat.getKgGained() <= 16f)
        {
            m_levelCompletedStars = 1;
        }else if(level.cat.getKgGained() > 16f && level.cat.getKgGained() < 22f)
        {
            m_levelCompletedStars = 2;
        }else if(level.cat.getKgGained() >= 22f)
        {
            m_levelCompletedStars = 3;
        }
    }

    public int getLevelCompletedStars() {
        return m_levelCompletedStars;
    }


    public void restart() {
        level.restart();
        cameraHelper.setPosition(level.cat.getPosition().x,level.cat.getPosition().y);
        cameraHelper.setTarget(level.cat);
        cameraHelper.setCameraSpeed(LevelInformationFactory.instance.levels.get(m_levelNumber-1).cameraSpeed);
        cameraHelper.setLevelFinal(LevelInformationFactory.instance.levels.get(m_levelNumber-1).mapEnd);
        gameState = GAME_STATE.RUNNING;

    }

    private void handleInputGame (float deltaTime,Vector2 touchPadValues) {
        if(touchPadValues.x != 0 || touchPadValues.y != 0)level.cat.move(touchPadValues.x*2,touchPadValues.y*2);

    }

    public int getLevelNumber() {
        return m_levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        m_levelNumber = levelNumber;
    }

    public enum GAME_STATE{
        RUNNING,PAUSE,WIN,LOST
    }
}

