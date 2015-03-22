package com.runner.game.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.runner.game.box2d.UserData;

/**
 * Created by cullycross on 3/22/15.
 */
public abstract class GameActor extends Actor {

    protected Body mBody;
    protected UserData mUserData;

    public GameActor(Body body) {
        this.mBody = body;
        this.mUserData = (UserData) body.getUserData();
    }

    public abstract UserData getUserData();
}
