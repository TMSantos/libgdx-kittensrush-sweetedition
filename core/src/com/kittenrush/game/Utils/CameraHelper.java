package com.kittenrush.game.Utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.kittenrush.game.Game.Objects.GameObject;

/**
 * Created by Tiago on 15/04/2014.
 */
public class CameraHelper {

    private final float MAX_ZOOM_IN = 0.25f;
    private final float MAX_ZOOM_OUT = 10.0f;
    private final float FOLLOW_SPEED_CHAR = 30.0f;

    private Vector2 position;
    private float zoom;
    private GameObject target;

    private float mLevelFinal;
    private float mCurrentCameraSpeed;
    private float mLevelCameraSpeed;

    private float mTargetMaxSpeed;

    private float distanceToPlayer;

    public boolean shouldUpdate;

    public CameraHelper () {
        position = new Vector2();
        zoom = 1f;
        shouldUpdate = true;
    }

    public void update (float deltaTime) {
        if (!shouldUpdate) return;
        if (!hasTarget()) return;

        position.lerp(target.getBody().getPosition(), FOLLOW_SPEED_CHAR * deltaTime);

        //position.y += mCurrentCameraSpeed * deltaTime; //uncomment this for normal use
        //position.y = target.getBody().getPosition().y; //uncomment this for developing

        distanceToPlayer = target.getBody().getPosition().y - position.y;

        if(distanceToPlayer > 3){// && target.getBody().getLinearVelocity().y > mLevelCameraSpeed){
            mCurrentCameraSpeed = target.getBody().getLinearVelocity().y + mTargetMaxSpeed;
        }else{
            mCurrentCameraSpeed = mLevelCameraSpeed;
        }

        // Prevent camera from moving down too far
        position.x = 5f;
        if (position.y < 7f) position.y = 7f;
        if (position.y > mLevelFinal) position.y = mLevelFinal;

    }

    public float getCameraSpeed() {
        return mLevelCameraSpeed;
    }

    public void setCameraSpeed(float CameraSpeed) {
        mLevelCameraSpeed = CameraSpeed;
        mCurrentCameraSpeed = CameraSpeed;
    }

    public void setPosition (float x, float y) {
        this.position.set(x, y);
    }

    public Vector2 getPosition () {
        return position;
    }

    public void addZoom (float amount) {
        setZoom(zoom + amount);
    }

    public void setZoom (float zoom) {
        this.zoom = MathUtils.clamp(zoom, MAX_ZOOM_IN, MAX_ZOOM_OUT);
    }

    public float getZoom () {
        return zoom;
    }

    public void setTarget (GameObject target) {
        this.target = target;
    }

    public GameObject getTarget () {
        return target;
    }

    public boolean hasTarget () {
        return target != null;
    }

    public boolean hasTarget (GameObject target) {
        return hasTarget() && this.target.equals(target);
    }

    public void applyTo (OrthographicCamera camera) {
        camera.position.x = position.x;
        camera.position.y = position.y;
        camera.zoom = zoom;
        camera.update();

    }

    public void setLevelFinal(float mLevelFinal) {
        this.mLevelFinal = mLevelFinal;
    }

    public void setTargetMaxSpeed(float TargetMaxSpeed) {
        mTargetMaxSpeed = TargetMaxSpeed;
    }

}
