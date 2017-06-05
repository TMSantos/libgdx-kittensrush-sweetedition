package com.kittenrush.game.Game.Objects.CustomPlatforms;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.kittenrush.game.Game.Objects.Platform;
import com.kittenrush.game.Utils.BodyEditorLoader;

/**
 * Created by Tiago on 22/08/2014.
 */
public class CustomShapePlatform extends Platform {

    private String JSON_FILE_PATH;
    private String FIXTURE_NAME;
    private boolean m_FlipX;
    private boolean m_FlipY;

    public CustomShapePlatform(World b2World, float width, float height, float positionX,
                               float positionY, int texture, String json_path,String fixtureName,
                               boolean flipX,boolean flipY) {
        super(b2World, width, height, positionX, positionY, texture);
        JSON_FILE_PATH = json_path;
        FIXTURE_NAME = fixtureName;
        m_FlipX = flipX;
        m_FlipY = flipY;
        init(b2World,texture);

    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
    }

    @Override
    public void init(World world, int texture) {
        body = createBody(world);
        createTexture(texture);

        if(m_FlipX && m_FlipY) PlatformSprite.flip(true,true);
        if(m_FlipX && !m_FlipY) PlatformSprite.flip(true,false);
        if(!m_FlipX && m_FlipY) PlatformSprite.flip(false,true);
    }

    @Override
    public Body createBody(World world) {
        BodyEditorLoader loader  = new BodyEditorLoader(Gdx.files.internal(JSON_FILE_PATH));

        BodyDef customBox2DPlatformDef = new BodyDef();
        customBox2DPlatformDef.type = BodyDef.BodyType.StaticBody;

        FixtureDef customBox2DPlatformFixtureDef = new FixtureDef();
        customBox2DPlatformFixtureDef.density = 1f;
        customBox2DPlatformFixtureDef.friction = 0.5f;
        customBox2DPlatformFixtureDef.restitution = 0.3f;

        Body customBox2DPlatformBody = world.createBody(customBox2DPlatformDef);

        loader.attachFixture(customBox2DPlatformBody,FIXTURE_NAME, customBox2DPlatformFixtureDef, dimension.x);

        customBox2DPlatformBody.setTransform(position.x,position.y,0);

        return customBox2DPlatformBody;
    }
}
