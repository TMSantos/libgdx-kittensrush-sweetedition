package com.kittenrush.game.Game.Objects.FoodObjects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/**
 * Created by Tiago on 06/09/2014.
 */
public class MovingFood extends Food {

    private Vector2 mPatrolRange;
    private Vector2 mVelocity;
    private MOVE_DIRECTION move_direction;

    private Vector2 xMovement;
    private Vector2 yMovement;

    private Vector2 circleDirection;
    private Vector2 tmp;

    private float degreesPerSecond;

    public MovingFood(World world, String type, float positionX, float positionY,int texture,
                      float patrolX, float patrolY, float velocityX, float velocityY, String direction) {
        super(world, type.equals("veg") ? 2 : 1, positionX, positionY,texture);
        mPatrolRange = new Vector2(patrolX,patrolY);
        mVelocity = new Vector2(velocityX,velocityY);
        setDirection(direction);
        init(world,texture);
        body.setLinearVelocity(mVelocity);
    }

    @Override
    public void init(World world, int texture) {
        mDestroyed = false;
        dimension.set(2.508333f,2.46667f);
        origin.set(1.25f,1.25f);
        body = createBody(m_world);
        body.setTransform(position,0);

        setDrawables(texture);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        switch (move_direction){
            case X_DIRECTION: moveX();
                break;
            case Y_DIRECTION: moveY();
                break;
            case CIRCLE_DIRECTION: moveCircle(deltaTime);
                break;
            default: break;
        }

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

    private void setDirection(String direction) {
        if(direction.equals("X_DIRECTION")){
            move_direction = MOVE_DIRECTION.X_DIRECTION;
            xMovement = new Vector2();
            xMovement.x = position.x + mPatrolRange.x;
            xMovement.y = position.x - mPatrolRange.x;
        }
        else if (direction.equals("Y_DIRECTION")){
            move_direction = MOVE_DIRECTION.Y_DIRECTION;
            yMovement = new Vector2();
            yMovement.x = position.y + mPatrolRange.y;
            yMovement.y = position.y - mPatrolRange.y;

        }
        else if (direction.equals("CIRCLE_DIRECTION")){
            move_direction = MOVE_DIRECTION.CIRCLE_DIRECTION;
            degreesPerSecond = 360f;
            circleDirection = new Vector2(2.0f, 0.0f);
            tmp = new Vector2();
        }
    }

    private void moveX() {

        if(body.getPosition().x >= xMovement.x){
            body.setLinearVelocity(mVelocity.scl(-1f));
        }else if(body.getPosition().x < xMovement.y){
            body.setLinearVelocity(mVelocity.scl(-1f));
        }
    }

    private void moveY() {

        if(body.getPosition().y >  yMovement.x){
            body.setLinearVelocity(mVelocity.scl(-1f));
        }else if(body.getPosition().y < yMovement.y){
            body.setLinearVelocity(mVelocity.scl(-1f));
        }
    }

    private void moveCircle(float deltaTime) {
        circleDirection.rotate(degreesPerSecond * deltaTime);
        body.setLinearVelocity(tmp.set(circleDirection).scl(mVelocity));
    }

    private enum MOVE_DIRECTION{
        X_DIRECTION,Y_DIRECTION,CIRCLE_DIRECTION
    }
}
