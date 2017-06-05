package com.kittenrush.game.Game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.kittenrush.game.Game.Objects.Cat;
import com.kittenrush.game.Game.Objects.FoodObjects.Food;
import com.kittenrush.game.Game.Objects.FoodObjects.MovingFood;
import com.kittenrush.game.Game.Objects.FoodObjects.NormalFood;
import com.kittenrush.game.Game.Objects.Platform;

/**
 * Created by Tiago on 15/04/2014.
 */
public class Level {

    public World b2World;
    public Cat cat;
    public Array<Food> foodList;
    public Array<Platform> staticPlatforms;
    public Array<Body> bodyCleanUp;

    private int m_currentLevel;

    public Level(int levelNumber){
        init(levelNumber);
    }

    public void init(int level){

        b2World = new World(new Vector2(0, LevelInformationFactory.instance.levels.get(level-1).gravity), true);
        cat = new Cat(b2World);
        cat.setCatMaxSpeed(LevelInformationFactory.instance.levels.get(level-1).catMaxSpeed);
        m_currentLevel = level;
        MapCreator.CreateMap(b2World,m_currentLevel);
        foodList = MapCreator.getMapFoodList();
        staticPlatforms = MapCreator.getMapStaticPlatforms();
        bodyCleanUp = new Array<Body>();
        createCollisionListener();
    }

    public void restart(){
        //b2World.destroyBody(cat.getBody());
        b2World.getBodies(bodyCleanUp);

        for(Body body : bodyCleanUp){
            b2World.destroyBody(body);
        }
        cat.init();
        MapCreator.CreateMap(b2World,m_currentLevel);
        foodList = MapCreator.getMapFoodList();
        staticPlatforms = MapCreator.getMapStaticPlatforms();
    }

    public void update(float deltaTime){
        b2World.step(deltaTime, 8, 3);

        cat.update(deltaTime);

        for(Food food : foodList){
            food.update(deltaTime);
        }

        for(Platform platform : staticPlatforms){
            platform.update(deltaTime);
        }

        if(cat.getBody().getPosition().x > 10) cat.getBody().setTransform(9f,cat.getBody().getPosition().y,0);
        else if(cat.getBody().getPosition().x < 0) cat.getBody().setTransform(1f,cat.getBody().getPosition().y,0);


        if(cat.getBody().getAngularVelocity() > 2f) cat.getBody().setAngularVelocity(1.9f);
        if(cat.getBody().getAngularVelocity() < -2f) cat.getBody().setAngularVelocity(-1.9f);

    }

    public void render(SpriteBatch batch){

        for(Food normalFood : foodList){
            normalFood.render(batch);
        }

        for(Platform platform : staticPlatforms){
            platform.render(batch);
        }

        cat.render(batch);
    }

    private void createCollisionListener() {
        b2World.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();

                if(fixtureA == null || fixtureB == null || fixtureA.getUserData() == null || fixtureB.getUserData() == null) return;

                if(fixtureA.getUserData().equals("cat") && fixtureB.getUserData().equals("sugarFood")){
                    cat.sugarFoodCollected = true;
                    fixtureB.getBody().setUserData("destroy");
                }
                if(fixtureB.getUserData().equals("cat") && fixtureA.getUserData().equals("sugarFood")){
                    cat.sugarFoodCollected = true;
                    fixtureA.getBody().setUserData("destroy");
                }

                if(fixtureA.getUserData().equals("cat") && fixtureB.getUserData().equals("vegetableFood")){
                    cat.vegetableFoodCollected = true;
                    fixtureB.getBody().setUserData("destroy");
                }
                if(fixtureB.getUserData().equals("cat") && fixtureA.getUserData().equals("vegetableFood")){
                    cat.vegetableFoodCollected = true;
                    fixtureA.getBody().setUserData("destroy");
                }

                if(fixtureA.getUserData().equals("cat") && fixtureB.getUserData().equals("spikes")){
                    cat.stunned = true;
                }
                if(fixtureB.getUserData().equals("cat") && fixtureA.getUserData().equals("spikes")){
                    cat.stunned = true;
                }

                if(fixtureA.getUserData().equals("cat") && fixtureB.getUserData().equals("superCake")){
                    cat.superCakeCollected = true;
                    fixtureB.getBody().setUserData("destroy");
                }
                if(fixtureB.getUserData().equals("cat") && fixtureA.getUserData().equals("superCake")){
                    cat.superCakeCollected = true;
                    fixtureA.getBody().setUserData("destroy");
                }

                if(fixtureA.getUserData().equals("cat") && fixtureB.getUserData().equals("brokenPlatform")){
                    if(!cat.isSuperFat()) fixtureB.getBody().getFixtureList().get(0).setUserData("Static");
                    fixtureB.getBody().setGravityScale(-2);


                }
                if(fixtureB.getUserData().equals("cat") && fixtureA.getUserData().equals("brokenPlatform")){
                    if(!cat.isSuperFat()) fixtureA.getBody().getFixtureList().get(0).setUserData("Static");
                    fixtureA.getBody().setGravityScale(-2);

                }

                if(fixtureA.getUserData().equals("cat") && fixtureB.getUserData().equals("cheesePlatform")){
                    fixtureB.getBody().setGravityScale(-2);


                }
                if(fixtureB.getUserData().equals("cat") && fixtureA.getUserData().equals("cheesePlatform")){
                    fixtureA.getBody().setGravityScale(-2);

                }

                //Trap Platforms
                if(fixtureA.getUserData().equals("cat") && fixtureB.getUserData().equals("TrapPlatformSensor")){
                    fixtureB.getBody().setUserData("Trigged");
                }

                if(fixtureB.getUserData().equals("cat") && fixtureA.getUserData().equals("TrapPlatformSensor")){
                    fixtureA.getBody().setUserData("Trigged");
                }

            }

            @Override
            public void endContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();

                if(fixtureA == null || fixtureB == null || fixtureA.getUserData() == null || fixtureB.getUserData() == null) return;

                if(fixtureA.getUserData().equals("cat") && fixtureB.getUserData().equals("brokenPlatform")){
                    if(cat.isSuperFat()){
                        fixtureB.setSensor(true);
                    }


                }
                if(fixtureB.getUserData().equals("cat") && fixtureA.getUserData().equals("brokenPlatform")){
                    if(cat.isSuperFat()){
                        fixtureA.setSensor(true);
                    }
                }

                if(fixtureA.getUserData().equals("cat") && fixtureB.getUserData().equals("cheesePlatform")){
                   fixtureB.setSensor(true);


                }
                if(fixtureB.getUserData().equals("cat") && fixtureA.getUserData().equals("cheesePlatform")){
                   fixtureA.setSensor(true);
                }

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

    }

}
