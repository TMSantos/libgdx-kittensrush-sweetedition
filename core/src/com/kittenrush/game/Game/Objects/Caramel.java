package com.kittenrush.game.Game.Objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Pool;
import com.kittenrush.game.Managers.ResourceManager;

/**
 * Created by Tiago on 15/07/2014.
 */
public class Caramel extends GameObject implements Pool.Poolable {

    public float maxTimeAlive;

    private float m_emitterX;
    private float m_emitterY;
    private int m_direction;
    private boolean m_isAlive;
    
    private TextureRegion caramel;
    private Sprite caramelSprite;


    public Caramel(World b2world,float emiterX,float emitterY,int direction){
        super();
        m_emitterX = emiterX;
        m_emitterY = emitterY;
        m_direction = direction;
        m_isAlive = false;
        body = createBody(b2world);
        body.setGravityScale(0f);
        body.setFixedRotation(true);
        caramel = ResourceManager.instance.mapAssets.caramel;
        caramelSprite = new Sprite(caramel);
        caramelSprite.setSize(1.2f,1.2f);
        caramelSprite.setOrigin(0.5f,0.5f);
        caramelSprite.setPosition(body.getPosition().x-body.getLocalCenter().x, body.getPosition().y-body.getLocalCenter().y);
        init();
    }

    public void init(){
        m_isAlive = true;
        body.setTransform(new Vector2(m_emitterX + (m_emitterX /2f), m_emitterY), 0);
        body.setLinearVelocity(new Vector2(5f*m_direction, -8f));
        body.applyLinearImpulse(new Vector2(-0.5f, -0.5f), new Vector2(m_emitterX, m_emitterY), true);
        maxTimeAlive = 0;
    }

    public void update(float deltaTime) {
        maxTimeAlive += deltaTime;
    }

    @Override
    public void render(SpriteBatch batch) {
        caramelSprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
        caramelSprite.setPosition(body.getPosition().x - 0.5f, body.getPosition().y - 0.5f);
        caramelSprite.draw(batch);
    }

    @Override
    public Body createBody(World world) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = false;
        def.allowSleep = true;
        def.bullet = true;

        Body caramelBody = world.createBody(def);

        CircleShape caramelShape = new CircleShape();
        caramelShape.setRadius(0.5f);

        caramelBody.createFixture(caramelShape,1);
        caramelShape.dispose();

        caramelBody.getFixtureList().get(0).setUserData("caramel");

        return caramelBody;
    }

    @Override
    public void reset() {
        body.setTransform(-10f,-10f, 0);
        m_isAlive = false;
    }

    public boolean isAlive() {
        return m_isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        m_isAlive = isAlive;
    }

}
