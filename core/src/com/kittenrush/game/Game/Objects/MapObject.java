package com.kittenrush.game.Game.Objects;

/**
 * Created by Tiago on 05/07/2014.
 */
public class MapObject {

    private int objectType;
    private float positionX;
    private float positionY;
    private float width;
    private float height;
    private int texture;
    private boolean flipX;
    private boolean flipY;
    private float patrolX;
    private float patrolY;
    private float velocityX;
    private float velocityY;
    private float rotate;
    private String moveDirection;
    private String trapType;
    private String objectName;

    public String getTrapType() {
        return trapType;
    }

    public float getRotate() {
        return rotate;
    }

    public String getMoveDirection() {
        return moveDirection;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public float getPatrolX() {
        return patrolX;
    }

    public float getPatrolY() {
        return patrolY;
    }

    public boolean isFlipX() {
        return flipX;
    }

    public boolean isFlipY() {
        return flipY;
    }

    public String getObjectName() {
        return objectName;
    }

    public int getObjectType() {
        return objectType;
    }

    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public int getTexture() {
        return texture;
    }

}
