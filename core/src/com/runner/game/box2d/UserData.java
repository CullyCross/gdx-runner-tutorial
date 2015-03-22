package com.runner.game.box2d;

import com.runner.game.enums.UserDataType;

/**
 * Created by cullycross on 3/22/15.
 */
public abstract class UserData {

    protected UserDataType mUserDataType;
    protected float mWidth;
    protected float mHeight;

    public UserData() {

    }

    public UserData(float width, float height) {
        this.mWidth = width;
        this.mHeight = height;
    }

    public UserDataType getUserDataType() {
        return mUserDataType;
    }

    public float getWidth() {
        return mWidth;
    }

    public void setWidth(float width) {
        this.mWidth = width;
    }

    public float getHeight() {
        return mHeight;
    }

    public void setHeight(float height) {
        this.mHeight = height;
    }
}
