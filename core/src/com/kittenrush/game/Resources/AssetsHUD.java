package com.kittenrush.game.Resources;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Tiago on 25/05/2014.
 */
public class AssetsHUD {

    public final TextureAtlas.AtlasRegion mapLimit;

    public AssetsHUD(TextureAtlas HUDAtlas){
        mapLimit = HUDAtlas.findRegion("mapLimit");

    }
}
