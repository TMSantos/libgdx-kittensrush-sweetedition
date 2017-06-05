package com.kittenrush.game.Game.Objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Tiago on 16/04/2014.
 */
public abstract class GameObject {

    protected Vector2 position;
    protected Vector2 dimension;
    protected Vector2 origin;
    protected Vector2 scale;
    protected float rotation;
    protected Body body;
    protected boolean shouldRender;

    protected World m_world;

    public GameObject () {
        position = new Vector2();
        dimension = new Vector2();
        origin = new Vector2();
        scale = new Vector2(1, 1);
        shouldRender = true;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Body getBody(){
        return body;
    }

    public void render (SpriteBatch batch){
        if(!shouldRender) return;
    };

    public abstract Body createBody(World world);
}
