package com.runner.game.utils;

import com.badlogic.gdx.physics.box2d.Body;
import com.runner.game.box2d.UserData;
import com.runner.game.enums.UserDataType;

/**
 * Created by cullycross on 3/22/15.
 */
public class BodyUtils {

    public static boolean isRunner(Body body) {
        UserData userData = (UserData) body.getUserData();
        return userData != null && userData.getUserDataType() == UserDataType.RUNNER;
    }

    public static boolean isGround(Body body) {
        UserData userData = (UserData) body.getUserData();
        return userData != null && userData.getUserDataType() == UserDataType.GROUND;
    }
}
