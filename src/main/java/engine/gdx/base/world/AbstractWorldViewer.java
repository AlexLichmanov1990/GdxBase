package engine.gdx.base.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.utils.Disposable;

public abstract class AbstractWorldViewer <T extends AbstractWorld, V extends AbstractWorldCamera> extends WidgetGroup implements Disposable {
    protected T world;
    protected V camera;
    protected SpriteBatch worldBatch;
    private FrameBuffer fbo;
    private Sprite resultImage;

    public AbstractWorldViewer(T world, V camera) {
        this.world = world;
        this.camera = camera;
        this.worldBatch = new SpriteBatch();
        resultImage = new Sprite();
    }

    public void setupCamera(float viewportHeight) {
        camera.setViewportHeight(viewportHeight);
    }

    private boolean isNeedRebuild(int width, int height) {
        if (fbo == null) return true;
        if (fbo.getWidth() != width) return true;
        if (fbo.getHeight() != height) return true;
        return false;
    }

    private boolean rebuildFbo(int width, int height) {
        if (width == 0 || height == 0) {
            return false;
        }
        if (isNeedRebuild(width, height)) {
            disposeFbo();
            fbo = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, false);
        }
        return fbo != null;
    }

    private Sprite getSprite() {
        int width = (int)getWidth();
        int height = (int)getHeight();
        if (!rebuildFbo(width, height)) return null;

        camera.setWorldSize(world.getWidth(), world.getHeight());
        world.setCulling(
            camera.position.x - camera.viewportWidth * .5f,
            camera.position.y - camera.viewportHeight * .5f,
            camera.viewportWidth,
            camera.viewportHeight
        );
        camera.update();

        worldBatch.setProjectionMatrix(camera.combined);

        fbo.begin();
        worldBatch.begin();
        Gdx.gl.glClearColor(.5f, .5f, .5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.drawWorld(worldBatch);

        worldBatch.flush();
        worldBatch.end();
        fbo.end();
        Texture texture = fbo.getColorBufferTexture();
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        resultImage.setTexture(texture);

        resultImage.setU(0);
        resultImage.setV(0);
        resultImage.setU2(1);
        resultImage.setV2(1);

        resultImage.flip(false, true);
        return resultImage;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isVisible()) {
            batch.end();

            Sprite s = getSprite();

            batch.begin();
            if (s != null) {
                s.setBounds(getX(), getY(), getWidth(), getHeight());
                s.draw(batch);
//                batch.draw(s.getTexture(), 0, 0, getWidth(), getHeight());
            }
        }
    }

    private void disposeFbo() {
        if (fbo != null) {
            fbo.end();
            fbo.dispose();
        }
    }

    @Override
    public void dispose() {
        disposeFbo();

        if (worldBatch.isDrawing()) {
            worldBatch.end();
        }
        worldBatch.dispose();
    }
}
