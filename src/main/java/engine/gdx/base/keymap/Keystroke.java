package engine.gdx.base.keymap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Keystroke {
    private int mode;
    private int key;
    private boolean modeSet = false;

    private boolean state;

    public Keystroke(String keystroke) {
        if (keystroke == null || keystroke.length() <= 0) {
            throw new IllegalArgumentException("Keystroke cannot be null or empty!");
        }
        String[] tokens = keystroke.split("\\+");
        if (tokens.length > 1) {
            mode = Input.Keys.valueOf(tokens[0].trim());
            key = Input.Keys.valueOf(tokens[1].trim());
            modeSet = true;
        } else {
            key = Input.Keys.valueOf(tokens[0].trim());
        }
    }

    public boolean getState() {
        return state;
    }

    public void updateState() {
        state = isPressed();
    }

    public boolean isModeSet() {
        return this.modeSet;
    }

    public boolean isPressed() {
        if (isModePressed()) {
            if (!isModeSet()) return false;
            if (Gdx.input.isKeyPressed(mode) && Gdx.input.isKeyPressed(key)) return true;
        } else {
            if (isModeSet()) return false;
            if (Gdx.input.isKeyPressed(key)) return true;
        }
        return false;
    }

    public boolean isModePressed() {
        if (Gdx.input.isKeyPressed(Modes.LCTRL.getMode())) return true;
        if (Gdx.input.isKeyPressed(Modes.RCTRL.getMode())) return true;
        if (Gdx.input.isKeyPressed(Modes.LALT.getMode())) return true;
        if (Gdx.input.isKeyPressed(Modes.RALT.getMode())) return true;
        if (Gdx.input.isKeyPressed(Modes.LSHIFT.getMode())) return true;
        if (Gdx.input.isKeyPressed(Modes.RSHIFT.getMode())) return true;
        return false;
    }
}
