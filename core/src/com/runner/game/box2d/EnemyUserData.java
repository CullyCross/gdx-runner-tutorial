package com.runner.game.box2d;

import com.badlogic.gdx.math.Vector2;
import com.runner.game.enums.UserDataType;
import com.runner.game.utils.Constants;

/**
 * Created by cullycross on 3/22/15.
 */
public class EnemyUserData extends UserData {

    private Vector2 mLinearVelocity;

    public EnemyUserData(float width, float height) {
        super(width, height);
        mUserDataType = UserDataType.ENEMY;
        mLinearVelocity = Constants.ENEMY_LINEAR_VELOCITY;
    }

    public void setLinearVelocity(Vector2 linearVelocity) {
        this.mLinearVelocity = linearVelocity;
    }

    public Vector2 getLinearVelocity() {
        return mLinearVelocity;
    }
}
