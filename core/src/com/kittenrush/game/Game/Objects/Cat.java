package com.kittenrush.game.Game.Objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.kittenrush.game.Managers.ResourceManager;
/**
 * Created by Tiago on 17/04/2014.
 */
public class Cat extends GameObject {

    private World mWorld;
    private Vector2 velocity;
    private float mCatMaxSpeed;
    private float m_super_fat_timer;
    final static float MAX_VELOCITY_X = 5f;

    private int kgGained;
    public boolean sugarFoodCollected;
    public boolean vegetableFoodCollected;
    public boolean superCakeCollected;

    private MassData mMass;

    private TextureRegion mCatTexture;

    private boolean m_superFat;

    public boolean stunned;
    private float m_stun_timer;

    private static final float FAT_LEVEL_1 = 0.625f;
    private static final float FAT_LEVEL_2 = 0.695f;
    private static final float FAT_LEVEL_3 = 0.77f;
    private static final float FAT_LEVEL_4 = 0.96f;
    private static final float FAT_LEVEL_5 = 1.2f;
    private static final float FAT_LEVEL_6 = 1.55f;
    private static final float FAT_LEVEL_7 = 1.75f;

    private static final float CAT_SUPER_FAT = 2.7f;

    public Cat(World world){
      super();
      mWorld = world;
      init();
    }

    public void init() {
        body = createBody(mWorld);
        setPosition(new Vector2(5f, 5f));
        body.setTransform(getPosition(), 0);
        kgGained=0;
        mMass = new MassData();
        sugarFoodCollected = false;
        vegetableFoodCollected = false;
        superCakeCollected = false;
        m_superFat = false;
        m_super_fat_timer = 0;
        stunned = false;
        m_stun_timer = 0;
        createResources();
        updateCatData();
    }

    private void createResources() {
        mCatTexture = ResourceManager.instance.catAssets.catNormal;
    }

    public void update(float deltaTime) {
        if(stunned){
            m_stun_timer+=deltaTime;
            body.setGravityScale(0);
            body.setLinearVelocity(0f,0f);
            if(m_stun_timer > 3f){
                stunned = false;
                m_stun_timer = 0;
                body.setGravityScale(1);
            }
            return;
        }
         capVelocity();
         updateCatKg();
         if(!superCakeCollected)  upgradeCatFatness();
         else{
             superFatMode();
             m_super_fat_timer+= deltaTime;
             if(m_super_fat_timer > 7f){
                 normalMode();
             }
         }

         updateCatData();
    }

    private void updateCatKg() {
        if(vegetableFoodCollected){
            if(kgGained > 0) kgGained-=2;
            vegetableFoodCollected = false;
        }else if(sugarFoodCollected){
            kgGained+=2;
            sugarFoodCollected = false;
        }
    }

    private void normalMode() {
        m_super_fat_timer = 0;
        superCakeCollected = false;
        m_superFat= false;
    }

    private void superFatMode() {
      if (!m_superFat){
        mCatTexture = ResourceManager.instance.catAssets.catSuperFat;
        body.getFixtureList().get(0).getShape().setRadius(CAT_SUPER_FAT);
        m_superFat = true;
      }
    }

    private void upgradeCatFatness() {

        if(kgGained < 4){
            if(mCatTexture != ResourceManager.instance.catAssets.catNormal)
                    mCatTexture = ResourceManager.instance.catAssets.catNormal;
            body.getFixtureList().get(0).getShape().setRadius(FAT_LEVEL_1);
        }else if(kgGained >= 4 && kgGained <= 8){
            if(mCatTexture != ResourceManager.instance.catAssets.catFat1)
                    mCatTexture = ResourceManager.instance.catAssets.catFat1;
            body.getFixtureList().get(0).getShape().setRadius(FAT_LEVEL_2);
        }else if(kgGained >= 10 && kgGained < 14){
            if(mCatTexture != ResourceManager.instance.catAssets.catFat2)
                    mCatTexture = ResourceManager.instance.catAssets.catFat2;
            body.getFixtureList().get(0).getShape().setRadius(FAT_LEVEL_3);
        }else if(kgGained >= 14 && kgGained < 18){
            if(mCatTexture != ResourceManager.instance.catAssets.catFat3)
                mCatTexture = ResourceManager.instance.catAssets.catFat3;
            body.getFixtureList().get(0).getShape().setRadius(FAT_LEVEL_4);
        }else if(kgGained >= 18 && kgGained < 22){
            if(mCatTexture != ResourceManager.instance.catAssets.catFat4)
                    mCatTexture = ResourceManager.instance.catAssets.catFat4;
            body.getFixtureList().get(0).getShape().setRadius(FAT_LEVEL_5);
        }else if(kgGained >= 22 && kgGained < 24){
            if(mCatTexture != ResourceManager.instance.catAssets.catFat5)
                    mCatTexture = ResourceManager.instance.catAssets.catFat5;
            body.getFixtureList().get(0).getShape().setRadius(FAT_LEVEL_6);
        }else if(kgGained >= 24){
            if(mCatTexture != ResourceManager.instance.catAssets.catFat6)
                mCatTexture = ResourceManager.instance.catAssets.catFat6;
            body.getFixtureList().get(0).getShape().setRadius(FAT_LEVEL_7);
        }
    }

    private void updateCatData() {
        position.x =  body.getPosition().x - origin.x;
        position.y =  body.getPosition().y - origin.y;
        dimension.x =  body.getFixtureList().get(0).getShape().getRadius()*2;
        dimension.y = body.getFixtureList().get(0).getShape().getRadius()*2;
        origin.x = dimension.x / 2;
        origin.y = dimension.y / 2;
        rotation = body.getAngle() * MathUtils.radiansToDegrees;
    }

    private void capVelocity() {
        velocity = body.getLinearVelocity();

        if(Math.abs(velocity.y) > mCatMaxSpeed){
            velocity.y = Math.signum(velocity.y) * mCatMaxSpeed;
            body.setLinearVelocity(velocity.x, velocity.y);
        }

        if(Math.abs(velocity.x) > MAX_VELOCITY_X) {
            velocity.x = Math.signum(velocity.x) * MAX_VELOCITY_X;
            body.setLinearVelocity(velocity.x, velocity.y);
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(mCatTexture.getTexture(),
                   position.x,
                   position.y,
                   origin.x,
                   origin.y,
                   dimension.x,
                   dimension.y,
                   scale.x,
                   scale.y,
                   rotation,
                   mCatTexture.getRegionX(),
                   mCatTexture.getRegionY(),
                   mCatTexture.getRegionWidth(),
                   mCatTexture.getRegionHeight(),
                   false,
                   false);
    }

    @Override
    public Body createBody(World world){
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = false;
        def.allowSleep = false;

        Body catBody = world.createBody(def);
        CircleShape catShape = new CircleShape();
        catShape.setRadius(0.5f);

        FixtureDef playerPhysicsFixture = new FixtureDef();
        playerPhysicsFixture.density = 0.5f;
        playerPhysicsFixture.friction = 0.1f;
        playerPhysicsFixture.shape = catShape;
        catBody.createFixture(playerPhysicsFixture);

        catShape.dispose();

        catBody.getFixtureList().get(0).setUserData("cat");

        return catBody;
    }

    public void move(float accelX,float accelY){
        if(stunned){
            body.setLinearVelocity(0,0);
            return;
        }

        body.setLinearVelocity(accelX*mCatMaxSpeed,accelY*mCatMaxSpeed);
    }

    public void up(float accelY) {
        if(stunned){
            body.setLinearVelocity(0,0);
            return;
        }
        body.applyLinearImpulse(0,accelY/5, body.getPosition().x, body.getPosition().y,true);
    }

    public float getKgGained() {
        return kgGained;
    }

    public void setCatMaxSpeed(float CatMaxSpeed) {
        mCatMaxSpeed = CatMaxSpeed;
    }

    public boolean isSuperFat() {
        return m_superFat;
    }


}

