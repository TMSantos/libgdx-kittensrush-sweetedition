package com.kittenrush.game.Screens.LevelSelectorScreen.pack1;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Tiago on 02/06/2014.
 */
public class CatSelector extends Image{

    private Animation catAnimation = null;

    private float stateTime = 0;

    public CatSelector(Animation catAnimation) {
        super(catAnimation.getKeyFrame(0));
        this.catAnimation = catAnimation;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        ((TextureRegionDrawable)getDrawable()).setRegion(catAnimation.getKeyFrame(stateTime+=delta, true));
        super.act(delta);
    }
}
