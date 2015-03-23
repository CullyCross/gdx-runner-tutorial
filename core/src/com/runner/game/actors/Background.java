package com.runner.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.runner.game.utils.Constants;

/**
 * Created by cullycross on 3/23/15.
 */
public class Background extends Actor {

    private final TextureRegion mTextureRegion;

    private Rectangle mTextureRegionBounds1;
    private Rectangle mTextureRegionBounds2;

    private int mSpeed = 100;

    public Background() {
        mTextureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.BACKGROUND_IMAGE_PATH)));
        mTextureRegionBounds1 = new Rectangle(0 - Constants.APP_WIDTH / 2, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
        mTextureRegionBounds2 = new Rectangle(Constants.APP_WIDTH / 2, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
    }

    @Override
    public void act(float delta) {
        if (leftBoundsReached(delta)) {
            resetBounds();
        } else {
            updateXBounds(-delta);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(mTextureRegion, mTextureRegionBounds1.x, mTextureRegionBounds1.y, Constants.APP_WIDTH,
                Constants.APP_HEIGHT);
        batch.draw(mTextureRegion, mTextureRegionBounds2.x, mTextureRegionBounds2.y, Constants.APP_WIDTH,
                Constants.APP_HEIGHT);
    }

    private boolean leftBoundsReached(float delta) {
        return (mTextureRegionBounds2.x - (delta * mSpeed)) <= 0;
    }

    private void updateXBounds(float delta) {
        mTextureRegionBounds1.x += delta * mSpeed;
        mTextureRegionBounds2.x += delta * mSpeed;
    }

    private void resetBounds() {
        mTextureRegionBounds1 = mTextureRegionBounds2;
        mTextureRegionBounds2 = new Rectangle(Constants.APP_WIDTH, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
    }

}
