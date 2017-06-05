package com.kittenrush.game.Managers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.kittenrush.game.Resources.*;
import com.kittenrush.game.Utils.Constants;

/**
 * Created by Tiago on 15/04/2014.
 */
public class ResourceManager implements Disposable{

    public static final ResourceManager instance = new ResourceManager();

    private AssetManager assetManager;

    public AssetFonts fonts;
    public AssetFood food;
    public AssetMap mapAssets;
    public AssetCat catAssets;
    public AssetsHUD hudAssets;

    public ResourceManager() {}

    public void initResourceManager(AssetManager mAssetManager){
        assetManager = mAssetManager;

        assetManager.load(Constants.FOOD_ATLAS, TextureAtlas.class);
        assetManager.load(Constants.CAT_ATLAS, TextureAtlas.class);
        assetManager.load(Constants.HUD_ATLAS, TextureAtlas.class);
        assetManager.load(Constants.MAP_ASSETS_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas  foodAtlas = assetManager.get(Constants.FOOD_ATLAS);

        for (Texture t : foodAtlas.getTextures()) {
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        TextureAtlas  catAtlas = assetManager.get(Constants.CAT_ATLAS);

        for (Texture t : catAtlas.getTextures()) {
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        TextureAtlas  HUDAtlas = assetManager.get(Constants.HUD_ATLAS);

        for (Texture t : HUDAtlas.getTextures()) {
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        TextureAtlas  MapAtlas = assetManager.get(Constants.MAP_ASSETS_ATLAS);

        for (Texture t : MapAtlas.getTextures()) {
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        fonts = new AssetFonts();
        food = new AssetFood(foodAtlas);
        catAssets = new AssetCat(catAtlas);
        hudAssets = new AssetsHUD(HUDAtlas);
        mapAssets = new AssetMap(MapAtlas);
    }

    @Override
    public void dispose() {

    }


}
