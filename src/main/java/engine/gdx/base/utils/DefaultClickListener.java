package engine.gdx.base.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class DefaultClickListener extends ClickListener {
    @Override
    public boolean isOver(Actor actor, float x, float y) {
        if (isActorVisible(actor)) {
            return super.isOver(actor, x, y);
        }
        return false;
    }

    /**
     * Проверка на кликабельность актора
     *
     * @param actor
     * @return
     */
    private boolean isActorVisible(Actor actor) {
        if (actor == null) {
            // если актор нулевой,
            // то он некликабелен
            return false;
        }
        if (!actor.isVisible()) {
            // невидимые акторы некликабельны
            return false;
        }
        Actor parent = actor.getParent();
        while (parent != null) {
            // идем по иерархии, пока не встретим невидимого родителя
            if (!parent.isVisible()) {
                // если родитель невидим, то актор некликабелен
                return false;
            }
            parent = parent.getParent();
        }
        // если актор не добавлен на сцену, то он некликабелен
        if (actor.getStage() == null) {
            return false;
        }

        // все проверки прошли, актор клакается
        return true;
    }
}
