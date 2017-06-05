package com.kittenrush.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.kittenrush.game.Managers.ResourceManager;
import com.kittenrush.game.Utils.Constants;

/**
 * Created by Tiago on 15/04/2014.
 */
public class WorldRenderer implements Disposable{

    public OrthographicCamera mCamera;
    public OrthographicCamera mCameraGUI;
    private SpriteBatch mBatch;
    private WorldController mWorldController;

    private Box2DDebugRenderer b2debugRenderer;

    private BitmapFont fpsFont;

    public WorldRenderer (WorldController worldController) {
        this.mWorldController = worldController;
        init();
    }

    private void init () {
        mBatch = new SpriteBatch();

        mCamera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        mCamera.position.set(0, 0, 0);
        mCamera.update();

        mCameraGUI = new OrthographicCamera(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
        mCameraGUI.position.set(Constants.VIEWPORT_GUI_WIDTH/2,  Constants.VIEWPORT_GUI_HEIGHT/2, 0);
        mCameraGUI.setToOrtho(true);
        mCameraGUI.update();

        fpsFont = ResourceManager.instance.fonts.defaultNormal;

        b2debugRenderer = new Box2DDebugRenderer();
    }

    public void render () {
        renderWorld(mBatch);
        renderGui(mBatch);
    }

    private void renderWorld (SpriteBatch batch) {
        mWorldController.cameraHelper.applyTo(mCamera);
        batch.setProjectionMatrix(mCamera.combined);
        batch.begin();
        mWorldController.level.render(batch);
        batch.end();

        //b2debugRenderer.render(mWorldController.level.b2World, mCamera.combined);
    }

    private void renderGui (SpriteBatch batch) {
        batch.setProjectionMatrix(mCameraGUI.combined);
        batch.begin();
        renderGuiFpsCounter(batch);
        batch.end();
    }


    private void renderGuiFpsCounter (SpriteBatch batch) {
        float fpsX = 20;
        float fpsY = mCameraGUI.viewportHeight - 50;

        int fps = Gdx.graphics.getFramesPerSecond();

        if (fps >= 45) {
            fpsFont.setColor(0, 1, 0, 1);
        } else if (fps >= 30) {
            fpsFont.setColor(1, 1, 0, 1);
        } else {
            fpsFont.setColor(1, 0, 0, 1);
        }

        fpsFont.draw(batch, "fps: " + fps, fpsX, fpsY);
        fpsFont.setColor(1, 1, 1, 1);

        fpsFont.draw(batch, "Kg: " + mWorldController.level.cat.getKgGained(),  mCameraGUI.viewportWidth-(mCameraGUI.viewportWidth/3),0);


    }

    @Override
    public void dispose() {
        mBatch.dispose();
    }
}
