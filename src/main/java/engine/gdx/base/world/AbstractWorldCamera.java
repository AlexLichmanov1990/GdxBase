package engine.gdx.base.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;

public abstract class AbstractWorldCamera extends OrthographicCamera {
    protected float worldWidth = 0;
    protected float worldHeight = 0;
    protected float windowAspect;

    public AbstractWorldCamera(float viewportHeight, float aspect) {
        super(viewportHeight * aspect, viewportHeight);
        windowAspect = aspect;
        setViewportHeight(viewportHeight);
    }

    public AbstractWorldCamera setViewportWidth(float width) {
        this.viewportWidth = width;
        this.viewportHeight = width / windowAspect;

        return this;
    }

    public AbstractWorldCamera setViewportSize(float width, float height) {
        this.viewportWidth = width;
        this.viewportHeight = height;
        return this;
    }

    public AbstractWorldCamera setViewportHeight(float height) {
        this.viewportWidth = height * windowAspect;
        this.viewportHeight = height;

        return this;
    }

    public AbstractWorldCamera setWorldSize(float width, float height) {
        if (worldWidth != width) {
            worldWidth = width;
        }
        if (worldHeight != height) {
            worldHeight = height;
        }
        return this;
    }

    protected AbstractWorldCamera setWindowAspect(float aspect) {
        this.windowAspect = aspect;
        return this;
    }

    public float getWindowAspect() {
        return windowAspect;
    }

    @Override
    public void update() {
        clampPosition();
        super.update();
    }

    private void clampPosition() {
        float minX = worldWidth * .5f;
        float maxX = worldWidth * .5f;
        float minY = worldHeight * .5f;
        float maxY = worldHeight * .5f;

        if (viewportWidth * zoom < worldWidth) {
            minX = viewportWidth * zoom * .5f;
            maxX = worldWidth - viewportWidth * zoom * .5f;
        }

        if (viewportHeight * zoom < worldHeight) {
            minY = viewportHeight * zoom * .5f;
            maxY = worldHeight - viewportHeight * zoom * .5f;
        }

        position.x = MathUtils.clamp(position.x, minX, maxX);
        position.y = MathUtils.clamp(position.y, minY, maxY);
    }
}
