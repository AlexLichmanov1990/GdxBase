package engine.gdx.base.keymap;

import engine.gdx.base.propsparser.PropsParser;
import engine.gdx.base.propsparser.PropsRow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyMap {
    private Map<String, Keystroke> keymap;

    public KeyMap(String file) {
        keymap = new HashMap<>();
        List<PropsRow> props = PropsParser.parse(file);
        for (PropsRow prop : props) {
            try {
                keymap.put(prop.getName(), new Keystroke(prop.getValue()));
            } catch (IllegalArgumentException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void update(String action) {
        Keystroke keystroke = keymap.get(action);
        if (keystroke != null) {
            keystroke.updateState();
        }
    }

    public boolean isPressed(String action) {
        Keystroke keystroke = keymap.get(action);
        if (keystroke != null) {
            return keystroke.isPressed();
        }
        return false;
    }

    public boolean getState(String action) {
        Keystroke keystroke = keymap.get(action);
        if (keystroke != null) {
            return keystroke.getState();
        }
        return false;
    }

    public boolean changingState(String action) {
        return getState(action) != isPressed(action);
    }
}
