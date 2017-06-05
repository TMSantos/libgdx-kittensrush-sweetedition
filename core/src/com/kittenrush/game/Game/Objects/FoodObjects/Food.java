package com.kittenrush.game.Game.Objects.FoodObjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.kittenrush.game.Game.Objects.GameObject;
import com.kittenrush.game.Managers.ResourceManager;

/**
 * Created by Tiago on 06/09/2014.
 */
public abstract class Food extends GameObject {

    public boolean mDestroyed;
    public FOOD_TYPE foodType;

    public TextureRegion food;
    public Sprite foodSprite;

    public Food(World world, int type, float positionX, float positionY, int texture){
        super();
        if(type == 1) foodType = FOOD_TYPE.SUGAR;
        else if(type == 2) foodType = FOOD_TYPE.VEGETABLE;
        m_world = world;
        mDestroyed = false;
        position.set(positionX,positionY);
    }

    public void update(float deltaTime) {
        if(!mDestroyed && body.getUserData().equals("destroy"))
        {
            destroy();
        }

    }

    public void setDrawables(int texture) {
            switch (texture) {
                case 0:
                    food = ResourceManager.instance.food.superCake;
                    break;
                case 1:
                    food = ResourceManager.instance.food.cake1;
                    break;
                case 2:
                    food = ResourceManager.instance.food.cake2;
                    break;
                case 3:
                    food = ResourceManager.instance.food.cake3;
                    break;
                case 4:
                    food = ResourceManager.instance.food.cake4;
                    break;
                case 5:
                    food = ResourceManager.instance.food.cake5;
                    break;
                case 6:
                    food = ResourceManager.instance.food.vegetable1;
                    break;
                case 7:
                    food = ResourceManager.instance.food.vegetable2;
                    break;
                case 8:
                    food = ResourceManager.instance.food.vegetable3;
                    break;
            }

        foodSprite = new Sprite(food);
        foodSprite.setSize(dimension.x, dimension.y);
        foodSprite.setOrigin(origin.x, origin.y);
        foodSprite.setPosition(body.getPosition().x-body.getLocalCenter().x, body.getPosition().y-body.getLocalCenter().y);
    }

    public void destroy(){
        body.getWorld().destroyBody(body);
        mDestroyed = true;
    }

    public abstract void init(World world,int texture);
    public abstract Body createBody(World world);

    @Override
    public void render(SpriteBatch batch) {
        if(mDestroyed) return;

        foodSprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
        foodSprite.setPosition(body.getPosition().x - origin.x, body.getPosition().y - origin.y);
        foodSprite.draw(batch);

    }

    public enum FOOD_TYPE{
        SUGAR,VEGETABLE
    }
}
