package engine.stages.viewports;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;

public class AspectViewport extends Viewport {
    private static float VIEWPORT_W = 1920;
    private static float VIEWPORT_H = 1080;
    private static float MIN_ASPECT = VIEWPORT_W / VIEWPORT_H;
    private static AspectViewport instance = null;

    private AspectViewport() {
        super();
        OrthographicCamera camera = new OrthographicCamera();
        this.setCamera(camera);
        this.setWorldWidth(VIEWPORT_W);
        this.setWorldHeight(VIEWPORT_H);
    }

    public static AspectViewport getInstance() {
        if (instance == null) instance = new AspectViewport();
        return instance;
    }

    public void setViewportSize(float width, float height) {
        VIEWPORT_W = width;
        VIEWPORT_H = height;
        MIN_ASPECT = VIEWPORT_W / VIEWPORT_H;
    }

    @Override
    public void update(int screenWidth, int screenHeight, boolean centerCamera) {
        float screenAspect1 = ((float) screenWidth / (float) screenHeight);
        float screenAspect2 = ((float) screenHeight / (float) screenWidth);

        if (screenAspect1 > MIN_ASPECT) {
            this.setWorldWidth(VIEWPORT_H * screenAspect1);
            this.setWorldHeight(VIEWPORT_H);
        } else if (screenAspect1 <= MIN_ASPECT) {
            this.setWorldWidth(VIEWPORT_W);
            this.setWorldHeight(VIEWPORT_W * screenAspect2);
        }
        this.setScreenBounds(0, 0, screenWidth, screenHeight);
        this.apply(true);
    }
}
