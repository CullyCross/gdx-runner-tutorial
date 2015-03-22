package com.runner.game.enums;

import com.runner.game.utils.Constants;

/**
 * Created by cullycross on 3/22/15.
 */
public enum EnemyType {

    RUNNING_SMALL(1f, 1f, Constants.ENEMY_X, Constants.RUNNING_SHORT_ENEMY_Y, Constants.ENEMY_DENSITY),
    RUNNING_WIDE(2f, 1f, Constants.ENEMY_X, Constants.RUNNING_SHORT_ENEMY_Y, Constants.ENEMY_DENSITY),
    RUNNING_LONG(1f, 2f, Constants.ENEMY_X, Constants.RUNNING_LONG_ENEMY_Y, Constants.ENEMY_DENSITY),
    RUNNING_BIG(2f, 2f, Constants.ENEMY_X, Constants.RUNNING_LONG_ENEMY_Y, Constants.ENEMY_DENSITY),
    FLYING_SMALL(1f, 1f, Constants.ENEMY_X, Constants.FLYING_ENEMY_Y, Constants.ENEMY_DENSITY),
    FLYING_WIDE(2f, 1f, Constants.ENEMY_X, Constants.FLYING_ENEMY_Y, Constants.ENEMY_DENSITY);

    private float mWidth;
    private float mHeight;
    private float mX;
    private float mY;
    private float mDensity;

    EnemyType(float width, float height, float x, float y, float density) {
        mWidth = width;
        mHeight = height;
        mX = x;
        mY = y;
        mDensity = density;
    }

    public float getWidth() {
        return mWidth;
    }

    public float getHeight() {
        return mHeight;
    }

    public float getX() {
        return mX;
    }

    public float getY() {
        return mY;
    }

    public float getDensity() {
        return mDensity;
    }
}
