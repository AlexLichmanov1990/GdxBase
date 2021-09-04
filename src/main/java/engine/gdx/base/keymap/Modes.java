package engine.gdx.base.keymap;

import com.badlogic.gdx.Input;

public enum Modes {
    LSHIFT(Input.Keys.SHIFT_LEFT),
    RSHIFT(Input.Keys.SHIFT_RIGHT),
    LALT(Input.Keys.ALT_LEFT),
    RALT(Input.Keys.ALT_RIGHT),
    LCTRL(Input.Keys.CONTROL_LEFT),
    RCTRL(Input.Keys.CONTROL_RIGHT);

    private int mode;

    Modes(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }
}
