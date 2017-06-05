package com.kittenrush.game.Game.Objects.FoodObjects;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Tiago on 06/09/2014.
 */
public class SuperCake extends Food {

    public SuperCake(World world,float positionX, float positionY) {
        super(world,1,positionX,positionY,0);
        init(world,0);
    }

    @Override
    public void init(World world, int texture) {
        mDestroyed = false;
        dimension.set(4f,4f);
        origin.set(2f,2f);
        body = createBody(m_world);
        body.setTransform(getPosition(),0);

        setDrawables(texture);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public Body createBody(World world) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.KinematicBody;
        def.fixedRotation = false;
        def.bullet = true;
        def.allowSleep = false;

        Body foodBody = world.createBody(def);

        CircleShape sugarFoodShape = new CircleShape();
        sugarFoodShape.setRadius(1f);

        FixtureDef sugarFoodFixture = new FixtureDef();
        sugarFoodFixture.isSensor = true;
        sugarFoodFixture.shape = sugarFoodShape;

        foodBody.createFixture(sugarFoodFixture);

        sugarFoodShape.dispose();
        foodBody.getFixtureList().get(0).setUserData("superCake");
        foodBody.setUserData("sugarFood");

        return foodBody;
    }
}
