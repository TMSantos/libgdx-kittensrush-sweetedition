package com.kittenrush.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.kittenrush.game.Utils.Constants;

/**
 * Created by Tiago on 31/05/2014.
 */
public class LevelInformationFactory {


    public static LevelInformationFactory instance = new LevelInformationFactory();
    public Array<LevelInformation> levels;

    public void init(){
        Json json = new Json();
        instance = json.fromJson(LevelInformationFactory.class, Gdx.files.internal(Constants.LEVEL_PACK1_JSON));
    }
}
