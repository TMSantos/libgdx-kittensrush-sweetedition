package com.kittenrush.game.Game.Objects.CustomPlatforms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.kittenrush.game.Game.Objects.Platform;
import com.kittenrush.game.Utils.BodyEditorLoader;
import com.sun.javafx.geom.Edge;

/**
 * Created by Paula on 31-08-2014.
 */
public class TrapPlatform extends Platform{

    private String JSON_FILE_PATH;
    private String FIXTURE_NAME;
    private float m_rotate;
    private boolean m_finished;
    private TRAP_TYPE m_TrapType;
    private Vector2 mPatrol;
    private boolean mRotated;

    public TrapPlatform(World b2World, float width, float height, float positionX, float positionY, int texture,
                        String json_path,String fixtureName,float rotate,String trapType,float patrolX,float patrolY) {
        super(b2World, width, height, positionX, positionY, texture);
        JSON_FILE_PATH = json_path;
        FIXTURE_NAME = fixtureName;
        m_rotate = rotate;
        if(m_rotate != 0) mRotated = true;
        mPatrol = new Vector2(patrolX,patrolY);
        setTrapType(trapType);
        init(b2World,texture);
    }

    private void setTrapType(String trapType) {
        if(trapType.equals("L_TRAP_LEFT")) m_TrapType = TRAP_TYPE.L_TRAP_LEFT;
        else if(trapType.equals("L_TRAP_RIGHT")) m_TrapType = TRAP_TYPE.L_TRAP_RIGHT;
        else if(trapType.equals("L_TRAP_UP_RIGHT")) m_TrapType = TRAP_TYPE.L_TRAP_UP_RIGHT;

    }

    @Override
    public void init(World world, int texture) {
        body = createBody(world);
        createTexture(texture);
        if(mRotated)  body.setTransform(body.getPosition(),m_rotate);
        m_finished = false;
        
    }

    public void update(float deltaTime) {
        if(m_finished) return;

        if(body.getUserData().equals("Trigged")) {
            onTrigger();
        }
    }

    private void onTrigger() {
        switch (m_TrapType) {
            case L_TRAP_LEFT: LTrapLeft();
                break;
            case L_TRAP_RIGHT: LTrapRight();
                break;
            case L_TRAP_UP_RIGHT: LTrapUpRight();
                break;
        }
    }

    private void LTrapUpRight() {
        body.setLinearVelocity(0,mPatrol.y);

        if(body.getPosition().x > 7f){
            body.setLinearVelocity(0f,0f);
            m_finished = true;
        }else if(body.getPosition().y > position.y + 13f){
            body.setLinearVelocity(mPatrol.x,0f);
        }
    }

    private void LTrapRight() {
        body.setLinearVelocity(0,-mPatrol.y);

        if(body.getPosition().x > 9){
            body.setLinearVelocity(0f,0f);
            m_finished = true;
        }
        else if(body.getPosition().y < position.y - 7f){
           body.setLinearVelocity(mPatrol.x,0f);
        }
    }

    private void LTrapLeft() {
        body.setLinearVelocity(0,-mPatrol.y);

        if(body.getPosition().x < 1){
            body.setLinearVelocity(0f,0f);
            m_finished = true;
        }
        else if(body.getPosition().y < position.y - 7f){
            body.setLinearVelocity(-mPatrol.x,0f);
        }
        
    }

    @Override
    public Body createBody(World world) {
        BodyEditorLoader loader  = new BodyEditorLoader(Gdx.files.internal(JSON_FILE_PATH));

        BodyDef customBox2DPlatformDef = new BodyDef();
        customBox2DPlatformDef.type = BodyDef.BodyType.KinematicBody;

        FixtureDef customBox2DPlatformFixtureDef = new FixtureDef();
        customBox2DPlatformFixtureDef.density = 1f;
        customBox2DPlatformFixtureDef.friction = 0.5f;
        customBox2DPlatformFixtureDef.restitution = 0.1f;

        Body customBox2DPlatformBody = world.createBody(customBox2DPlatformDef);

        loader.attachFixture(customBox2DPlatformBody,FIXTURE_NAME, customBox2DPlatformFixtureDef, dimension.x);

        ///TRIGGER SENSOR///

        EdgeShape trapTriggerSensor = new EdgeShape();

        if(mRotated)
            trapTriggerSensor.set(-5,-10,-5,10);
        else {
            switch (m_TrapType) {
                case L_TRAP_LEFT:
                    break;
                case L_TRAP_RIGHT:
                    break;
                case L_TRAP_UP_RIGHT: trapTriggerSensor.set(-5, 3, 10, 3);
                    break;
            }

        }


        FixtureDef trapTriggerSensorFixture = new FixtureDef();
        trapTriggerSensorFixture.isSensor = true;
        trapTriggerSensorFixture.shape = trapTriggerSensor;
        customBox2DPlatformBody.createFixture(trapTriggerSensorFixture);
        trapTriggerSensor.dispose();

        customBox2DPlatformBody.setUserData("NotTrigged");
        customBox2DPlatformBody.getFixtureList().get(customBox2DPlatformBody.getFixtureList().size-1).setUserData("TrapPlatformSensor");

        customBox2DPlatformBody.setTransform(position.x,position.y,0);

        return customBox2DPlatformBody;
    }

    private enum TRAP_TYPE{
        L_TRAP_LEFT,L_TRAP_RIGHT,L_TRAP_UP_RIGHT
    }

}
