package com.kittenrush.game.Game.Objects.CustomPlatforms;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.kittenrush.game.Game.Objects.Platform;

/**
 * Created by Tiago on 13/08/2014.
 */
public class WallSpikePlatform extends Platform {


    public WallSpikePlatform(World b2World, float width, float height, float positionX, float positionY, int texture) {
        super(b2World, width, height, positionX, positionY, texture);
        init(b2World,texture);
    }

    @Override
    public void init(World world,int texture) {
        body = createBody(world);
        createTexture(texture);
        if(position.x > 5f)PlatformSprite.rotate90(true);
        else PlatformSprite.rotate90(false);
    }

    @Override
    public Body createBody(World world) {
        BodyDef boxDef = new BodyDef();
        boxDef.type = BodyDef.BodyType.StaticBody;

        Body platformBody = world.createBody(boxDef);

        PolygonShape spikesSensor = new PolygonShape();

        if(position.x > 5f){
            spikesSensor.setAsBox(dimension.x / 3f, dimension.y / 2.2f, new Vector2(0.3f,0),0);
        }else{
            spikesSensor.setAsBox(dimension.x / 3f, dimension.y / 2.2f, new Vector2(-0.3f,0),0);
        }
        FixtureDef spikesSensorFixture = new FixtureDef();
        spikesSensorFixture.isSensor = true;
        spikesSensorFixture.shape = spikesSensor;
        platformBody.createFixture(spikesSensorFixture);
        spikesSensor.dispose();

        platformBody.getFixtureList().get(0).setUserData("spikes");

        platformBody.setTransform(position.x,position.y,0);

        return platformBody;
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
    }

    public void update(float deltaTime) {}
}
