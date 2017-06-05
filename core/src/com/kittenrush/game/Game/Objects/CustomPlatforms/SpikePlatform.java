package com.kittenrush.game.Game.Objects.CustomPlatforms;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.kittenrush.game.Game.Objects.Platform;

/**
 * Created by Tiago on 13/08/2014.
 */
public class SpikePlatform extends Platform {

    private Vector2 m_PatrolRange;
    private Vector2 m_velocity;
    private MOVE_DIRECTION move_direction;
    private boolean isMovingPlatform;

    public SpikePlatform(World b2World, float width, float height, float positionX, float positionY, int texture,
                         Vector2 patrolRange, Vector2 platformVelocity, String moveDirection) {
        super(b2World, width, height, positionX, positionY, texture);
        if(!patrolRange.isZero()) isMovingPlatform = true;
        m_PatrolRange = patrolRange;
        m_velocity = platformVelocity;
        init(b2World, texture);
        if(isMovingPlatform) setMoveDirection(moveDirection);
    }

    @Override
    public void init(World world,int texture) {
        body = createBody(world);
        createTexture(texture);


    }

    @Override
    public Body createBody(World world) {
        BodyDef boxDef = new BodyDef();

        if(isMovingPlatform) boxDef.type = BodyDef.BodyType.KinematicBody;
        else boxDef.type = BodyDef.BodyType.StaticBody;

        Body platformBody = world.createBody(boxDef);

        PolygonShape boxShape = new PolygonShape();

        float removeXFromBorder = 2.2f;
        float removeYFromBorder = 4f;
        float boxShapeWidth;
        float boxShapeHeight;

        boxShapeWidth = (float)(dimension.x/removeXFromBorder);
        boxShapeHeight = (float)(dimension.y/removeYFromBorder);

        boxShape.setAsBox(boxShapeWidth, boxShapeHeight);

        FixtureDef boxFixture = new FixtureDef();
        boxFixture.friction = 0.1f;
        boxFixture.density = 0.1f;
        boxFixture.shape = boxShape;

        platformBody.createFixture(boxFixture);
        platformBody.getFixtureList().get(0).setUserData("staticPlatform");

        boxShape.dispose();

        PolygonShape spikesSensor = new PolygonShape();
        spikesSensor.setAsBox(dimension.x / 2.2f, 0.1f, new Vector2(0f, -0.5f), 0);
        FixtureDef spikesSensorFixture = new FixtureDef();
        spikesSensorFixture.isSensor = true;
        spikesSensorFixture.shape = spikesSensor;
        platformBody.createFixture(spikesSensorFixture);
        spikesSensor.dispose();

        platformBody.getFixtureList().get(1).setUserData("spikes");
        platformBody.setTransform(position.x,position.y,0);

        return platformBody;
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
    }

    public void update(float deltaTime) {
        if(!isMovingPlatform) return;

        switch (move_direction){
            case X_DIRECTION: moveX();
                break;
            case Y_DIRECTION: moveY();
                break;
            case SQUARE_DIRECTION: moveSquare();
                break;
            default: break;
        }

    }

    private void moveX() {
        float maxPosition = position.x + m_PatrolRange.x;
        float minPosition = position.x - m_PatrolRange.x;

        if(body.getPosition().x > maxPosition){
            body.setLinearVelocity(m_velocity.scl(-1f));
        }else if(body.getPosition().x < minPosition){
            body.setLinearVelocity(m_velocity.scl(-1f));
        }
    }

    private void moveY() {
        float maxPosition = position.y + m_PatrolRange.y;
        float minPosition = position.y - m_PatrolRange.y;

        if(body.getPosition().y > maxPosition){
            body.setLinearVelocity(m_velocity.scl(-1f));
        }else if(body.getPosition().y < minPosition){
            body.setLinearVelocity(m_velocity.scl(-1f));
        }
    }

    private void moveSquare() {}

    public void setMoveDirection(String moveDirection) {
        if(moveDirection.equals("xDirectionRight")) {
            move_direction = MOVE_DIRECTION.X_DIRECTION;
            body.setLinearVelocity(m_velocity);
        }else if(moveDirection.equals("yDirectionUp")) {
            move_direction = MOVE_DIRECTION.Y_DIRECTION;
            body.setLinearVelocity(m_velocity.scl(-1));
        }else if(moveDirection.equals("yDirectionDown")) {
            move_direction = MOVE_DIRECTION.Y_DIRECTION;
            body.setLinearVelocity(m_velocity);
        }
    }

    private enum MOVE_DIRECTION{
        X_DIRECTION,Y_DIRECTION,SQUARE_DIRECTION
    }

}
