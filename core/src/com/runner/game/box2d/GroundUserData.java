package com.runner.game.box2d;

import com.runner.game.enums.UserDataType;

/**
 * Created by cullycross on 3/22/15.
 */
public class GroundUserData extends UserData {

    public GroundUserData() {
        super();
        mUserDataType = UserDataType.GROUND;
    }
}
