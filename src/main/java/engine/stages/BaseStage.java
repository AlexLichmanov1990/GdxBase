package engine.stages;

import engine.game.IGameProperties;
import engine.keymap.KeyActions;
import engine.keymap.KeyMapAdapter;
import engine.managers.BaseGameManager;
import engine.stages.viewports.AspectViewport;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class BaseStage extends Stage {

    private static final int F12 = 255;

    private static boolean debug = false;
    protected LoadStrategy next;
    protected KeyMapAdapter keyMapAdapter;

    public BaseStage() {
        this(
                AspectViewport.getInstance(),
                new SpriteBatch(2500, null)
        );
    }

    public BaseStage(Viewport vp, Batch batch) {
        super(vp, batch);
        keyMapAdapter = new KeyMapAdapter();
        batch.enableBlending();
        this.addListener(new InputListener() {
            @Override
            public boolean keyTyped(InputEvent event, char character) {
                if (event.getKeyCode() == F12) {
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
            public boolean keyDown(InputEvent event, int keycode) {
                IGameProperties gameProps = BaseGameManager.getInstance().getGameProperties();
                if (gameProps != null) {
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_ONE)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_ONE);
                        keyMapAdapter.oneAction();
                    }
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_TWO)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_TWO);
                        keyMapAdapter.twoAction();
                    }
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_THREE)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_THREE);
                        keyMapAdapter.threeAction();
                    }
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_SPACE)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_SPACE);
                        keyMapAdapter.spaceAction();
                    }
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_LCTRL_SPACE)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_LCTRL_SPACE);
                        keyMapAdapter.lctrlSpaceAction();
                    }
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_LALT_SPACE)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_LALT_SPACE);
                        keyMapAdapter.laltSpaceAction();
                    }
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_L_SHIFT_SPACE)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_L_SHIFT_SPACE);
                        keyMapAdapter.lshiftSpaceAction();
                    }
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_ESCAPE)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_ESCAPE);
                        escapeAction();
                    }
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_A)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_A);
                        keyMapAdapter.aActionDown();
                    }
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_D)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_D);
                        keyMapAdapter.dActionDown();
                    }
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_S)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_S);
                        keyMapAdapter.sActionDown();
                    }
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_W)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_W);
                        keyMapAdapter.wActionDown();
                    }
                    return true;
                }
                return super.keyDown(event, keycode);
            }

            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                IGameProperties gameProps = BaseGameManager.getInstance().getGameProperties();
                if (gameProps != null) {
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_ONE)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_ONE);
                        keyMapAdapter.oneAction();
                    }
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_TWO)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_TWO);
                        keyMapAdapter.twoAction();
                    }
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_THREE)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_THREE);
                        keyMapAdapter.threeAction();
                    }
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_SPACE)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_SPACE);
                        keyMapAdapter.spaceAction();
                    }
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_LCTRL_SPACE)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_LCTRL_SPACE);
                        keyMapAdapter.lctrlSpaceAction();
                    }
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_LALT_SPACE)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_LALT_SPACE);
                        keyMapAdapter.laltSpaceAction();
                    }
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_L_SHIFT_SPACE)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_L_SHIFT_SPACE);
                        keyMapAdapter.lshiftSpaceAction();
                    }
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_ESCAPE)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_ESCAPE);
                        escapeAction();
                    }
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_A)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_A);
                        keyMapAdapter.aActionUp();
                    }
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_D)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_D);
                        keyMapAdapter.dActionUp();
                    }
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_S)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_S);
                        keyMapAdapter.sActionUp();
                    }
                    if (gameProps.getKeyMap().changingState(KeyActions.ACTION_W)) {
                        gameProps.getKeyMap().update(KeyActions.ACTION_W);
                        keyMapAdapter.wActionUp();
                    }
                    return true;
                }
                return super.keyUp(event, keycode);
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

    public abstract void dispose();

    public void onShow() {
    }

    @Override
    public void act(float delta) {
        super.act(delta * BaseGameManager.getInstance().getGameSpeed());
    }
}
