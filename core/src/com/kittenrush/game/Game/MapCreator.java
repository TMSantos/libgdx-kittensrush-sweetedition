package com.kittenrush.game.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.kittenrush.game.Game.Objects.*;
import com.kittenrush.game.Game.Objects.CustomPlatforms.*;
import com.kittenrush.game.Game.Objects.FoodObjects.Food;
import com.kittenrush.game.Game.Objects.FoodObjects.MovingFood;
import com.kittenrush.game.Game.Objects.FoodObjects.NormalFood;
import com.kittenrush.game.Game.Objects.FoodObjects.SuperCake;
import com.kittenrush.game.Utils.Constants;

/**
 * Created by Tiago on 05/07/2014.
 */
public class MapCreator {

    public static MapData mapData;
    public static Array<Food> mapFoodList;
    public static Array<Platform> mapStaticPlatforms;
    private static final String CUSTOM_PLATFORMS_DIRECTORY = "Levels/pack1/CustomBox2DPlatforms/";

    public static void CreateMap(World world,int levelNumber){
        mapData = new MapData();
        mapFoodList = new Array<Food>();
        mapStaticPlatforms = new Array<Platform>();

        createWorldGround(world, new Vector2(0, 0), new Vector2(10, 0));
        createWorldGround(world, new Vector2(0.5f, 0), new Vector2(0.5f,400f));
        createWorldGround(world, new Vector2(9.5f, 0), new Vector2(9.5f,400f));

        Json json = new Json();
        mapData = json.fromJson(MapData.class, Gdx.files.internal(Constants.LEVEL1_MAP + levelNumber + ".json"));

        bindMap(mapData,world);
    }

    private static void bindMap(MapData mapData,World world) {
        for(MapObject food : mapData.food){
            if(food.getObjectType() == Constants.CAKE_TYPE) mapFoodList.add(new NormalFood(world, 1, food.getPositionX(), food.getPositionY(),food.getTexture()));
            else if(food.getObjectType() == Constants.VEGETABLE_TYPE) mapFoodList.add(new NormalFood(world,2,food.getPositionX(),food.getPositionY(),food.getTexture()));
            else if(food.getObjectType() == Constants.SUPER_CAKE) mapFoodList.add(new SuperCake(world,food.getPositionX(),food.getPositionY()));
            else if(food.getObjectType() == Constants.MOVING_FOOD) mapFoodList.add(new MovingFood(world,food.getObjectName(),food.getPositionX(),food.getPositionY(),food.getTexture(),
                                                                                    food.getPatrolX(),food.getPatrolY(),food.getVelocityX(),food.getVelocityY(),food.getMoveDirection()));
        }

        for(MapObject platform : mapData.platforms){
            if(platform.getObjectType() == Constants.PLATFORM_STATIC){
                mapStaticPlatforms.add(new StaticPlatform(world,platform.getWidth(),platform.getHeight(),platform.getPositionX(),platform.getPositionY(),platform.getTexture()));
            }else if(platform.getObjectType() == Constants.CARAMEL_PLATFORM){
                mapStaticPlatforms.add(new CaramelPlatform(world,platform.getWidth(),platform.getHeight(),platform.getPositionX(),platform.getPositionY(),platform.getTexture()));
            }else if(platform.getObjectType() == Constants.SPIKES_PLATFORM){
                mapStaticPlatforms.add(new SpikePlatform(world,platform.getWidth(),platform.getHeight(),platform.getPositionX(),platform.getPositionY(),platform.getTexture(),
                        new Vector2(platform.getPatrolX(),platform.getPatrolY()),
                        new Vector2(platform.getVelocityX(),platform.getVelocityY()),
                        platform.getMoveDirection()));
            }else if(platform.getObjectType() == Constants.WALL_SPIKES){
                mapStaticPlatforms.add(new WallSpikePlatform(world,platform.getWidth(),platform.getHeight(),platform.getPositionX(),platform.getPositionY(),platform.getTexture()));
            }else if(platform.getObjectType() == Constants.CHEESE_PLAT){
                mapStaticPlatforms.add(new CheesePlatform(world,platform.getWidth(),platform.getHeight(),platform.getPositionX(),platform.getPositionY(),platform.getTexture()));
            }else if(platform.getObjectType() == Constants.BROKEN_PLATFORM){
                mapStaticPlatforms.add(new BrokenPlatform(world,platform.getWidth(),platform.getHeight(),platform.getPositionX(),platform.getPositionY(),platform.getTexture()));
            }else if(platform.getObjectType() == Constants.CUSTOM_PHYSIC_PLATFORM){
                mapStaticPlatforms.add(new CustomShapePlatform(world,platform.getWidth(),
                        platform.getHeight(),platform.getPositionX(),platform.getPositionY(),platform.getTexture(),
                        CUSTOM_PLATFORMS_DIRECTORY + platform.getObjectName() + ".json",platform.getObjectName(),
                        platform.isFlipX(),platform.isFlipY()));
            }else if(platform.getObjectType() == Constants.MOVING_PLATFORM){
                mapStaticPlatforms.add(new MovingPlatform(world,platform.getWidth(),
                        platform.getHeight(),platform.getPositionX(),platform.getPositionY(),platform.getTexture(),
                        CUSTOM_PLATFORMS_DIRECTORY + platform.getObjectName() + ".json",platform.getObjectName(),
                        new Vector2(platform.getPatrolX(),platform.getPatrolY()),
                        new Vector2(platform.getVelocityX(),platform.getVelocityY()),
                        platform.getMoveDirection()));
            }else if(platform.getObjectType() == Constants.TRAP_PLATFORM){
                mapStaticPlatforms.add(new TrapPlatform(world,platform.getWidth(),
                        platform.getHeight(),platform.getPositionX(),platform.getPositionY(),platform.getTexture(),
                        CUSTOM_PLATFORMS_DIRECTORY + platform.getObjectName() + ".json",platform.getObjectName(),platform.getRotate(),platform.getTrapType(),
                        platform.getPatrolX(),platform.getPatrolY()));
            }
        }
    }

    private static void createWorldGround(World b2World,Vector2 startPoint,Vector2 endPoint) {
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.type = BodyDef.BodyType.StaticBody;
        Body groundBody = b2World.createBody(groundBodyDef);

        EdgeShape groundShape = new EdgeShape();

        groundShape.set(startPoint,endPoint);

        FixtureDef groundFixture = new FixtureDef();
        groundFixture.shape = groundShape;
        groundFixture.density = 0f;
        groundFixture.friction = 0f;
        groundBody.createFixture(groundFixture);
        groundBody.getFixtureList().get(0).setUserData("ground");
        groundShape.dispose();
    }

    public static Array<Food> getMapFoodList() {
        return mapFoodList;
    }

    public static Array<Platform> getMapStaticPlatforms() {
        return mapStaticPlatforms;
    }


}

