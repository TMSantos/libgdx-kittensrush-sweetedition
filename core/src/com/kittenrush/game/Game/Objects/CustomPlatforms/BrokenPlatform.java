package com.kittenrush.game.Game.Objects.CustomPlatforms;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import com.kittenrush.game.Game.Objects.Platform;

/**
 * Created by Tiago on 13/08/2014.
 */
public class BrokenPlatform extends Platform {

    public BrokenPlatform(World b2World, float width, float height, float positionX, float positionY, int texture) {
        super(b2World, width, height, positionX, positionY, texture);
        init(b2World,texture);
    }

    @Override
    public void init(World world,int texture) {
        body = createBody(world);
        createTexture(texture);

        body.setType(BodyDef.BodyType.DynamicBody);
        body.setGravityScale(0);
        body.getFixtureList().get(0).setUserData("brokenPlatform");

    }

    @Override
    public Body createBody(World world) {
        BodyDef boxDef = new BodyDef();
        boxDef.type = BodyDef.BodyType.StaticBody;

        Body platformBody = world.createBody(boxDef);

        PolygonShape boxShape = new PolygonShape();

        float removeFromBorder = 2.2f;
        float boxShapeWidth;
        float boxShapeHeight;

        boxShapeWidth = (float)(dimension.x/4f);
        boxShapeHeight = (float)(dimension.y/removeFromBorder);

        boxShape.setAsBox(boxShapeWidth, boxShapeHeight);

        FixtureDef boxFixture = new FixtureDef();
        boxFixture.friction = 0.1f;
        boxFixture.density = 0.1f;
        boxFixture.restitution = 1f;
        boxFixture.shape = boxShape;

        platformBody.createFixture(boxFixture);
        platformBody.getFixtureList().get(0).setUserData("staticPlatform");

        boxShape.dispose();

        platformBody.setTransform(position.x,position.y,0);

        return platformBody;
    }


    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
    }

    public void update(float deltaTime) {
        if(body.getFixtureList().get(0).getUserData().equals("Static")) body.setType(BodyDef.BodyType.StaticBody);

    }
}
