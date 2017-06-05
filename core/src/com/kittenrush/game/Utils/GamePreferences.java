package com.kittenrush.game.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;

/**
 * Created by Tiago on 15/04/2014.
 */
public class GamePreferences {

    public static final GamePreferences instance = new GamePreferences();

    public Preferences gamePreferences;
    public GameProgress gameProgress;

    public boolean sound;
    public boolean music;

    private FileHandle file = Gdx.files.local("bin/aBtHnH.json");

    private GamePreferences() {
        gamePreferences = Gdx.app.getPreferences(Constants.GAME_PROGRESS);
    }

    public void loadMusic(){
        sound = gamePreferences.getBoolean("sound", true);
        music = gamePreferences.getBoolean("music", true);
    }

    public void saveGameState(GameProgress gameProgress) {
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        file.writeString(Base64Coder.encodeString(json.toJson(gameProgress)), false);
    }

    public void loadGameProgress() {
        Json json = new Json();
        if(file.exists()) gameProgress = json.fromJson(GameProgress.class, Base64Coder.decodeString(file.readString()));
    }

    public void loadSettings(){
    }

    public void saveSettings(){
    }

    public void saveMusic(){
        gamePreferences.putBoolean("sound", sound);
        gamePreferences.putBoolean("music", music);
        gamePreferences.flush();
    }
}
