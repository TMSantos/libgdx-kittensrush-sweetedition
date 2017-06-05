package com.kittenrush.game.Game.Objects.CustomPlatforms;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.kittenrush.game.Game.Objects.Caramel;
import com.kittenrush.game.Game.Objects.Platform;
import com.kittenrush.game.Interfaces.IAction;

/**
 * Created by Tiago on 15/07/2014.
 */
public class CaramelPlatform extends Platform {

    private float fireTimer;
    private boolean flippedX;

    private final Array<Caramel> activeCaramels = new Array<Caramel>();

    // bullet pool.
    private final Pool<Caramel> bulletPool = new Pool<Caramel>() {
        @Override
        protected Caramel newObject() {
            return new Caramel(body.getWorld(),
                    flippedX ? (body.getPosition().x - (body.getPosition().x/2)) : (body.getPosition().x + (body.getPosition().x/2)),
                    body.getPosition().y,
                    flippedX ? -1 : 1);
        }
    };

    public CaramelPlatform(World b2World, float width,float height,float positionX, float positionY,int texture){
        super(b2World,width,height,positionX,positionY,texture);
        init(b2World,texture);
        if(texture == 15)
            flippedX = true;
    }

    @Override
    public void init(World world, int texture) {
        body = createBody(world);
        createTexture(texture);
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

        boxShapeWidth = (float)(dimension.x/removeFromBorder);
        boxShapeHeight = (float)(dimension.y/removeFromBorder);

        boxShape.setAsBox(boxShapeWidth, boxShapeHeight);

        FixtureDef boxFixture = new FixtureDef();
        boxFixture.friction = 0.1f;
        boxFixture.density = 0.1f;
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

        for(Caramel caramel : activeCaramels){
            caramel.render(batch);
        }
    }

    public void update(float deltaTime) {
        fireTimer++;

        if(fireTimer > 100){
            Caramel caramel;
            int len = activeCaramels.size;
            for (int i = len; --i >= 0;) {
                caramel = activeCaramels.get(i);
                if (caramel.isAlive() == true) {
                    activeCaramels.removeIndex(i);
                    bulletPool.free(caramel);
                }
            }

            Caramel item = bulletPool.obtain();
            item.init();
            activeCaramels.add(item);
            fireTimer = 0;
        }


    }
}
