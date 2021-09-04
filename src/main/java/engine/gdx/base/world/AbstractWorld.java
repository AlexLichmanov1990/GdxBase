package engine.gdx.base.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

public abstract class AbstractWorld {

    protected Rectangle culling;
    private float width = 0;
    private float height = 0;

    public AbstractWorld(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Rectangle getCulling() {
        return culling;
    }

    public AbstractWorld setCulling(float x, float y, float width, float height) {
        if (this.culling == null) {
            this.culling = new Rectangle(x, y, width, height);
        }
        this.culling.x = x;
        this.culling.y = y;
        this.culling.width = width;
        this.culling.height = height;
        return this;
    }

    public AbstractWorld setCulling(Rectangle culling) {
        this.culling = culling;
        return this;
    }

    protected abstract void drawWorld(Batch batch);
}
