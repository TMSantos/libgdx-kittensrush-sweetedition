package com.kittenrush.game.Utils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kittenrush.game.Managers.ResourceManager;

/**
 * Created by Tiago on 15/07/2014.
 */
public class KittenRushUtils {

    public static TextureRegion getPlatformRegion(int texture){

        switch (texture) {
            case 1: return ResourceManager.instance.mapAssets.platform1;
            case 2: return ResourceManager.instance.mapAssets.platform2;
            case 3: return ResourceManager.instance.mapAssets.platform3;
            case 4: return ResourceManager.instance.mapAssets.caramelPlatform;
            case 5: return ResourceManager.instance.mapAssets.spikePlatform;
            case 6: return ResourceManager.instance.mapAssets.wallSpikes;
            case 7: return ResourceManager.instance.mapAssets.cheesePlat1;
            case 8: return ResourceManager.instance.mapAssets.cheesePlat2;
            case 9: return ResourceManager.instance.mapAssets.brokenPlatform1;
            case 10: return ResourceManager.instance.mapAssets.brokenPlatform2;
            case 11: return ResourceManager.instance.mapAssets.brokenPlatform3;
            case 12: return ResourceManager.instance.mapAssets.brokenPlatform4;
            case 13: return ResourceManager.instance.mapAssets.brokenPlatform5;
            case 14: return ResourceManager.instance.mapAssets.brokenPlatform6;
            case 15: return ResourceManager.instance.mapAssets.caramelPlatform2;
            case 16: return ResourceManager.instance.mapAssets.cookiePlatform;

        }
        return null;
    }
}
