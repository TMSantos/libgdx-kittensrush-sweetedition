package com.kittenrush.game.Resources;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Tiago on 24/05/2014.
 */
public class AssetCat {

    public final TextureAtlas.AtlasRegion catNormal;
    public final TextureAtlas.AtlasRegion catFat1;
    public final TextureAtlas.AtlasRegion catFat2;
    public final TextureAtlas.AtlasRegion catFat3;
    public final TextureAtlas.AtlasRegion catFat4;
    public final TextureAtlas.AtlasRegion catFat5;
    public final TextureAtlas.AtlasRegion catFat6;
    public final TextureAtlas.AtlasRegion catSuperFat;

    public AssetCat(TextureAtlas catAtlas){
        catNormal = catAtlas.findRegion("stand1");
        catFat1 = catAtlas.findRegion("fat1");
        catFat2 = catAtlas.findRegion("fat2");
        catFat3 = catAtlas.findRegion("fat3");
        catFat4 = catAtlas.findRegion("fat4");
        catFat5 = catAtlas.findRegion("fat5");
        catFat6 = catAtlas.findRegion("fat6");
        catSuperFat = catAtlas.findRegion("superfatcat");

    }

}
