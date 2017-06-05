package com.kittenrush.game.Game.Objects.FoodObjects;

import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Tiago on 22/04/2014.
 */
public class NormalFood extends Food{

    public NormalFood(World world, int type, float positionX, float positionY,int texture) {
        super(world, type, positionX, positionY,texture);
        init(world,texture);
    }

    @Override
    public void init(World world, int texture) {
        mDestroyed = false;
        dimension.set(2.508333f,2.46667f);
        origin.set(1.25f,1.25f);
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
        sugarFoodShape.setRadius(0.5f);

        FixtureDef sugarFoodFixture = new FixtureDef();
        sugarFoodFixture.isSensor = true;
        sugarFoodFixture.shape = sugarFoodShape;

        foodBody.createFixture(sugarFoodFixture);

        sugarFoodShape.dispose();

        switch (foodType){
            case SUGAR:
                 foodBody.getFixtureList().get(0).setUserData("sugarFood");
                foodBody.setUserData("sugarFood");
                break;
            case VEGETABLE: foodBody.getFixtureList().get(0).setUserData("vegetableFood");
                foodBody.setUserData("vegetableFood");
                break;
        }

        return foodBody;
    }

}
