package com.runner.game.box2d;

import com.runner.game.enums.UserDataType;

/**
 * Created by cullycross on 3/22/15.
 */
public abstract class UserData {

    protected UserDataType mUserDataType;

    public UserData() {

    }

    public UserDataType getUserDataType() {
        return mUserDataType;
    }
}
