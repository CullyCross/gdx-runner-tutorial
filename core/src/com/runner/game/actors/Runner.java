package com.runner.game.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.runner.game.box2d.RunnerUserData;

/**
 * Created by cullycross on 3/22/15.
 */
public class Runner extends GameActor {

    private boolean mJumping;
    private boolean mDodging;
    private boolean mHit;

    @Override
    public RunnerUserData getUserData() {
        return (RunnerUserData) mUserData;
    }

    public Runner(Body body) {
        super(body);
    }

    public void jump() {

        if(!mJumping || !mDodging || !mHit) {
            mBody.applyLinearImpulse(getUserData().getJumpingLinearImpulse(), mBody.getWorldCenter(), true);
            mJumping = true;
        }
    }

    public void landed() {
        mJumping = false;
    }

    public void dodge() {
        if(!mJumping || !mHit) {
            mBody.setTransform(getUserData().getDodgePosition(), getUserData().getDodgeAngle());
            mDodging = true;
        }
    }

    public void stopDodge() {
        mDodging = false;
        if (!mHit) {
            mBody.setTransform(getUserData().getRunningPosition(), 0f);
        }
    }

    public boolean isDodging() {
        return mDodging;
    }

    public void hit() {
        mBody.applyAngularImpulse(getUserData().getHitAngularImpulse(), true);
        mHit = true;
    }

    public boolean isHit() {
        return mHit;
    }
}
