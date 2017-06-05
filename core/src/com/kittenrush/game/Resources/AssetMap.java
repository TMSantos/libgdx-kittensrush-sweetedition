package com.kittenrush.game.Resources;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Tiago on 12/05/2014.
 */
public class AssetMap {

    public final TextureAtlas.AtlasRegion platform1;
    public final TextureAtlas.AtlasRegion platform2;
    public final TextureAtlas.AtlasRegion platform3;
    public final TextureAtlas.AtlasRegion caramelPlatform;
    public final TextureAtlas.AtlasRegion caramelPlatform2;
    public final TextureAtlas.AtlasRegion caramel;
    public final TextureAtlas.AtlasRegion spikePlatform;
    public final TextureAtlas.AtlasRegion wallSpikes;
    public final TextureAtlas.AtlasRegion cheesePlat1;
    public final TextureAtlas.AtlasRegion cheesePlat2;
    public final TextureAtlas.AtlasRegion brokenPlatform1;
    public final TextureAtlas.AtlasRegion brokenPlatform2;
    public final TextureAtlas.AtlasRegion brokenPlatform3;
    public final TextureAtlas.AtlasRegion brokenPlatform4;
    public final TextureAtlas.AtlasRegion brokenPlatform5;
    public final TextureAtlas.AtlasRegion brokenPlatform6;
    public final TextureAtlas.AtlasRegion cookiePlatform;

    public AssetMap(TextureAtlas mapAtlas){
        platform1 = mapAtlas.findRegion("platform1");
        platform2 = mapAtlas.findRegion("platform2");
        platform3 = mapAtlas.findRegion("platform3");
        caramelPlatform = mapAtlas.findRegion("caramelplatform");
        caramel = mapAtlas.findRegion("caramel");
        spikePlatform = mapAtlas.findRegion("spikeplatform");
        wallSpikes = mapAtlas.findRegion("spikes");
        cheesePlat1 = mapAtlas.findRegion("cheeseplat");
        cheesePlat2 = mapAtlas.findRegion("cheeseplat2");
        brokenPlatform1 = mapAtlas.findRegion("brokenplat1");
        brokenPlatform2 = mapAtlas.findRegion("brokenplat2");
        brokenPlatform3 = mapAtlas.findRegion("brokenplat3");
        brokenPlatform4 = mapAtlas.findRegion("brokenplat4");
        brokenPlatform5 = mapAtlas.findRegion("brokenplat5");
        brokenPlatform6 = mapAtlas.findRegion("brokenplat6");
        caramelPlatform2 = mapAtlas.findRegion("caramelplatform2");
        cookiePlatform = mapAtlas.findRegion("cookiePlatform");

    }
}
