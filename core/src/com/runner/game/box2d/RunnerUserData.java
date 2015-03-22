package com.runner.game.box2d;

import com.badlogic.gdx.math.Vector2;
import com.runner.game.enums.UserDataType;
import com.runner.game.utils.Constants;

/**
 * Created by cullycross on 3/22/15.
 */
public class RunnerUserData extends UserData {

    private Vector2 mJumpingLinearImpulse;

    private final Vector2 mRunningPosition =
            new Vector2(Constants.RUNNER_X, Constants.RUNNER_Y);
    private final Vector2 mDodgePosition =
            new Vector2(Constants.RUNNER_DODGE_X, Constants.RUNNER_DODGE_Y);

    public RunnerUserData(float width, float height) {
        super(width, height);
        mJumpingLinearImpulse = Constants.RUNNER_JUMPING_LINEAR_IMPULSE;
        mUserDataType = UserDataType.RUNNER;
    }

    public Vector2 getJumpingLinearImpulse() {
        return mJumpingLinearImpulse;
    }

    public void setJumpingLinearImpulse(Vector2 impulse) {
        this.mJumpingLinearImpulse = impulse;
    }

    public float getDodgeAngle() {
        return (float) (-90f * (Math.PI / 180f));
    }

    public Vector2 getRunningPosition() {
        return mRunningPosition;
    }

    public Vector2 getDodgePosition() {
        return mDodgePosition;
    }

    public float getHitAngularImpulse() {
        return Constants.RUNNER_HIT_ANGULAR_IMPULSE;
    }
}
