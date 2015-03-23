package com.runner.game.actors;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.graphics.Texture;
import com.runner.game.box2d.GroundUserData;
import com.runner.game.box2d.UserData;
import com.runner.game.utils.Constants;

/**
 * Created by cullycross on 3/22/15.
 */
public class Ground extends GameActor {

    private final TextureRegion mTextureRegion;
    private Rectangle mTextureRegionBounds1;
    private Rectangle mTextureRegionBounds2;
    private int mSpeed = 10;

    public Ground(Body body) {
        super(body);

        mTextureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.GROUND_IMAGE_PATH)));
        mTextureRegionBounds1 = new Rectangle(0 - getUserData().getWidth() / 2, 0, getUserData().getWidth(),
                getUserData().getHeight());
        mTextureRegionBounds2 = new Rectangle(getUserData().getWidth() / 2, 0, getUserData().getWidth(),
                getUserData().getHeight());
    }

    @Override
    public GroundUserData getUserData() {
        return (GroundUserData) mUserData;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (leftBoundsReached(delta)) {
            resetBounds();
        } else {
            updateXBounds(-delta);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(mTextureRegion, mTextureRegionBounds1.x, mScreenRectangle.y, mScreenRectangle.getWidth(),
                mScreenRectangle.getHeight());
        batch.draw(mTextureRegion, mTextureRegionBounds2.x, mScreenRectangle.y, mScreenRectangle.getWidth(),
                mScreenRectangle.getHeight());
    }

    private boolean leftBoundsReached(float delta) {
        return (mTextureRegionBounds2.x - transformToScreen(delta * mSpeed)) <= 0;
    }

    private void updateXBounds(float delta) {
        mTextureRegionBounds1.x += transformToScreen(delta * mSpeed);
        mTextureRegionBounds2.x += transformToScreen(delta * mSpeed);
    }

    private void resetBounds() {
        mTextureRegionBounds1 = mTextureRegionBounds2;
        mTextureRegionBounds2 = new Rectangle(mTextureRegionBounds1.x + mScreenRectangle.width, 0, mScreenRectangle.width,
                mScreenRectangle.height);
    }

}
