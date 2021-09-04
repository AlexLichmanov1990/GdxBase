package engine.gui.drawable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class ColorDrawable implements Drawable {

    private Pixmap pixmap;
    private Sprite texture;
    private Color color;

    public ColorDrawable(Color color) {
        this.color = color;
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                texture = createTexture();
            }
        });
    }

    public ColorDrawable setColor(Color color) {
        this.color = color;
        return this;
    }

    private Sprite createTexture() {
        pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.drawPixel(0, 0, Color.rgba8888(1f, 1f, 1f, 1f));
        Sprite t = new Sprite(new Texture(pixmap));
        return t;
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        if (texture != null) {
            Color color = batch.getColor();
            batch.setColor(this.color);
            batch.draw(this.texture, x, y, width, height);
            batch.setColor(color);
        }
    }

    @Override
    public float getLeftWidth() {
        return 0;
    }

    @Override
    public void setLeftWidth(float leftWidth) {

    }

    @Override
    public float getRightWidth() {
        return 0;
    }

    @Override
    public void setRightWidth(float rightWidth) {

    }

    @Override
    public float getTopHeight() {
        return 0;
    }

    @Override
    public void setTopHeight(float topHeight) {

    }

    @Override
    public float getBottomHeight() {
        return 0;
    }

    @Override
    public void setBottomHeight(float bottomHeight) {

    }

    @Override
    public float getMinWidth() {
        return 0;
    }

    @Override
    public void setMinWidth(float minWidth) {

    }

    @Override
    public float getMinHeight() {
        return 0;
    }

    @Override
    public void setMinHeight(float minHeight) {

    }
}
