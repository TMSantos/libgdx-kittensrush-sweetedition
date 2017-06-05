package com.kittenrush.game.Screens.transitions;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Tiago on 16/04/2014.
 */
public interface ScreenTransition {

    public float getDuration ();

    public abstract void render (SpriteBatch batch, Texture currScreen, Texture nextScreen, float alpha);

}
