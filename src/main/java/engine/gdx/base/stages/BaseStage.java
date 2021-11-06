package engine.gdx.base.stages;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;
import engine.gdx.base.keymap.KeyMapAdapter;
import engine.gdx.base.stages.viewports.AspectViewport;

public abstract class BaseStage extends Stage {

//    private static final int F12 = 255;

    private static boolean debug = false;
    protected LoadStrategy next;
    protected KeyMapAdapter keyMapAdapter = new KeyMapAdapter();

    public BaseStage() {
        this(
                AspectViewport.newInstance(),
                new SpriteBatch(2500, null)
        );
    }

    public BaseStage(Viewport vp, Batch batch) {
        super(vp, batch);
        batch.enableBlending();
        this.addListener(new InputListener() {
            @Override
            public boolean keyTyped(InputEvent event, char character) {
                if (event.getKeyCode() == Input.Keys.F12) {
                    setDebug();
                }
                return true;
            }
        });
        this.setDebugAll(debug);
        addListeners();
    }

    private void addListeners() {
        this.addListener(new InputListener() {
            @Override
            public boolean keyTyped(InputEvent event, char character) {
                if (event.getKeyCode() == Input.Keys.ESCAPE) {
                    escapeAction();
                    return true;
                }
                return super.keyTyped(event, character);
            }
        });
    }

    protected void escapeAction() {
        if (next != null) {
            next.load();
        }
    }

    public void setDebug() {
        this.debug = !debug;
        this.setDebugAll(debug);
    }

    public abstract void resize(int width, int height);

    public abstract void pause();

    public abstract void resume();

    public void dispose() {
        disposeRecursive(getRoot());
    }

    /**
     * Рекурсивно диспозит всех авторов на стейдже
     * <p>
     * Такой подход позволяет писать логику диспоза непосредственно в акторе
     * и не бояться, что он окажется причиной утечки памяти.
     *
     * @param actor
     */
    protected void disposeRecursive(Actor actor) {
        if (actor instanceof Group) {
            Array<Actor> children = ((Group) actor).getChildren();
            for (Actor a : children) {
                disposeRecursive(a);
            }
        }
        if (actor instanceof Disposable) {
            ((Disposable) actor).dispose();
        }
    }

    public void onShow() {
    }

//    @Override
//    public void act(float delta) {
//        super.act(delta * BaseGameManager.getInstance().getGameSpeed());
//    }
}
