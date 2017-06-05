package com.kittenrush.game.Game.Objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.kittenrush.game.Utils.KittenRushUtils;

/**
 * Created by Tiago on 28/05/2014.
 */
public abstract class Platform extends GameObject{

    public TextureRegion PlatformReg;
    public Sprite PlatformSprite;

    public Platform(World b2World, float width, float height, float positionX, float positionY, int texture) {
        dimension.set(width,height);
        origin.set((width/2),(height/2));
        position.set(positionX, positionY);
    }

    public void createTexture(int texture) {
        PlatformReg = KittenRushUtils.getPlatformRegion(texture);

        PlatformSprite = new Sprite(PlatformReg);
        PlatformSprite.setSize(dimension.x,dimension.y);
        PlatformSprite.setOrigin(origin.x, origin.y);
        PlatformSprite.setPosition(body.getPosition().x-body.getLocalCenter().x, body.getPosition().y-body.getLocalCenter().y);

    }

    @Override
    public void render(SpriteBatch batch) {
        PlatformSprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
        PlatformSprite.setPosition(body.getPosition().x - origin.x, body.getPosition().y - origin.y);
        PlatformSprite.draw(batch);
    }

    public abstract void init(World world,int texture);

    public void update(float deltaTime){

    }

    @Override
    public abstract Body createBody(World world);
}
