package com.runner.game.actors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.runner.game.box2d.UserData;
import com.runner.game.utils.Constants;

/**
 * Created by cullycross on 3/22/15.
 */
public abstract class GameActor extends Actor {

    protected Body mBody;
    protected UserData mUserData;
    protected Rectangle mScreenRectangle;

    @Override
    public void act(float delta) {
        super.act(delta);

        if(mBody.getUserData() != null) {
            updateRectangle();
        } else {
            remove();
        }
    }

    public GameActor(Body body) {
        this.mBody = body;
        this.mUserData = (UserData) body.getUserData();
        mScreenRectangle = new Rectangle();
    }

    public abstract UserData getUserData();

    private void updateRectangle() {
        mScreenRectangle.x = transformToScreen(mBody.getPosition().x - mUserData.getWidth() / 2);
        mScreenRectangle.y = transformToScreen(mBody.getPosition().y - mUserData.getHeight() / 2);
        mScreenRectangle.width = transformToScreen(mUserData.getWidth());
        mScreenRectangle.height = transformToScreen(mUserData.getHeight());
    }

    protected float transformToScreen(float n) {
        return Constants.WORLD_TO_SCREEN * n;
    }
}
