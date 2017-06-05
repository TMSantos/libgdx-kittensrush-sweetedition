package com.kittenrush.game.Game.Objects.CustomPlatforms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.kittenrush.game.Game.Objects.Platform;
import com.kittenrush.game.Utils.BodyEditorLoader;

/**
 * Created by Tiago on 30-08-2014.
 */
public class MovingPlatform extends Platform {

    private String JSON_FILE_PATH;
    private String FIXTURE_NAME;
    private Vector2 m_PatrolRange;
    private Vector2 m_velocity;
    private MOVE_DIRECTION move_direction;
    private Vector2 xPathMaxPosition;
    private Vector2 yPathMaxPosition;

    public MovingPlatform(World b2World, float width, float height, float positionX,
                          float positionY, int texture,String json_path,String fixtureName,
                          Vector2 patrolRange,Vector2 platformVelocity, String moveDirection) {

        super(b2World, width, height, positionX, positionY, texture);

        JSON_FILE_PATH = json_path;
        FIXTURE_NAME = fixtureName;
        m_PatrolRange = patrolRange;
        m_velocity = platformVelocity;
        init(b2World,texture);
        setDirection(moveDirection);
    }

    private void setDirection(String moveDirection) {
        if(moveDirection.equals("xDirectionRight")) {
            move_direction = MOVE_DIRECTION.X_DIRECTION;
            body.setLinearVelocity(m_velocity);
            xPathMaxPosition = new Vector2();
            xPathMaxPosition.x = position.x + m_PatrolRange.x;
            xPathMaxPosition.y = position.x - m_PatrolRange.x;
        }else if(moveDirection.equals("yDirectionUp")) {
            move_direction = MOVE_DIRECTION.Y_DIRECTION;
            body.setLinearVelocity(m_velocity.scl(-1));
            yPathMaxPosition = new Vector2();
            yPathMaxPosition.x = position.y + m_PatrolRange.y;
            yPathMaxPosition.y = position.y - m_PatrolRange.y;
        }else if(moveDirection.equals("yDirectionDown")) {
            move_direction = MOVE_DIRECTION.Y_DIRECTION;
            body.setLinearVelocity(m_velocity);
            yPathMaxPosition = new Vector2();
            yPathMaxPosition.x = position.y + m_PatrolRange.y;
            yPathMaxPosition.y = position.y - m_PatrolRange.y;
        }
    }

    public void update(float deltaTime) {
        switch (move_direction){
            case X_DIRECTION: moveX();
                            break;
            case Y_DIRECTION: moveY();
                             break;
            default: break;
        }

    }

    private void moveX() {
        if(body.getPosition().x > xPathMaxPosition.x){
            body.setLinearVelocity(m_velocity.scl(-1f));
        }else if(body.getPosition().x < xPathMaxPosition.y){
            body.setLinearVelocity(m_velocity.scl(-1f));
        }
    }

    private void moveY() {
        if(body.getPosition().y > yPathMaxPosition.x){
            body.setLinearVelocity(m_velocity.scl(-1f));
        }else if(body.getPosition().y < yPathMaxPosition.y){
            body.setLinearVelocity(m_velocity.scl(-1f));
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
    }

    @Override
    public void init(World world, int texture) {
        body = createBody(world);
        createTexture(texture);
    }

    @Override
    public Body createBody(World world) {
        BodyEditorLoader loader  = new BodyEditorLoader(Gdx.files.internal(JSON_FILE_PATH));

        BodyDef customBox2DPlatformDef = new BodyDef();
        customBox2DPlatformDef.type = BodyDef.BodyType.KinematicBody;

        FixtureDef customBox2DPlatformFixtureDef = new FixtureDef();
        customBox2DPlatformFixtureDef.density = 1f;
        customBox2DPlatformFixtureDef.friction = 0.5f;
        customBox2DPlatformFixtureDef.restitution = 0.3f;

        Body customBox2DPlatformBody = world.createBody(customBox2DPlatformDef);

        loader.attachFixture(customBox2DPlatformBody,FIXTURE_NAME, customBox2DPlatformFixtureDef, dimension.x);

        customBox2DPlatformBody.setTransform(position.x,position.y,0);

        return customBox2DPlatformBody;

    }

    private enum MOVE_DIRECTION{
        X_DIRECTION,Y_DIRECTION
    }

}
