package com.runner.game.box2d;

import com.runner.game.enums.UserDataType;

/**
 * Created by cullycross on 3/22/15.
 */
public class GroundUserData extends UserData {

    public GroundUserData(float width, float height) {
        super(width, height);
        mUserDataType = UserDataType.GROUND;
    }
}
