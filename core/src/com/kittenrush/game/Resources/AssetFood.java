package com.kittenrush.game.Resources;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Tiago on 03/05/2014.
 */
public class AssetFood {

    public final TextureAtlas.AtlasRegion initialCake;
    public final TextureAtlas.AtlasRegion cake1;
    public final TextureAtlas.AtlasRegion cake2;
    public final TextureAtlas.AtlasRegion cake3;
    public final TextureAtlas.AtlasRegion cake4;
    public final TextureAtlas.AtlasRegion cake5;
    public final TextureAtlas.AtlasRegion superCake;

    public final TextureAtlas.AtlasRegion vegetable1;
    public final TextureAtlas.AtlasRegion vegetable2;
    public final TextureAtlas.AtlasRegion vegetable3;

    public AssetFood(TextureAtlas foodAtlas){
        initialCake = foodAtlas.findRegion("initialcake");
        cake1 = foodAtlas.findRegion("cake1");
        cake2 = foodAtlas.findRegion("cake2");
        cake3 = foodAtlas.findRegion("cake3");
        cake4 = foodAtlas.findRegion("cake4");
        cake5 = foodAtlas.findRegion("cake5");
        superCake = foodAtlas.findRegion("supercake");

        vegetable1 = foodAtlas.findRegion("vegetable1");
        vegetable2 = foodAtlas.findRegion("vegetable2");
        vegetable3 = foodAtlas.findRegion("vegetable3");

    }
}
