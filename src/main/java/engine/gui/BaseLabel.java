package engine.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.DistanceFieldFont;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class BaseLabel extends Label {
    protected static final float DEFAULT_SIZE = 32f;
    protected static ShaderProgram shader = DistanceFieldFont.createDistanceFieldShader();
    protected float size = 24f;
    protected float smoothing = 1.5f;

    protected BaseLabel(CharSequence text, LabelStyle style) {
        super(text, style);
    }

    public static BaseLabel newInstance(CharSequence text, BitmapFont font, float size, Color color) {
        LabelStyle style = new LabelStyle();
        style.font = font;
        style.fontColor = color;
        BaseLabel instance = new BaseLabel(text, style);
        instance.size = size;
        instance.setFontScale(size / DEFAULT_SIZE);
        return instance;
    }

    public static void setShader(ShaderProgram shader) {
        BaseLabel.shader = shader;
    }

    public void setSize(float size) {
        this.size = size;
        this.setFontScale(this.size / BaseLabel.DEFAULT_SIZE);
    }

    public BaseLabel setSmoothing(float smoothing) {
        this.smoothing = smoothing;
        return this;
    }

//    @Override
//    public void setText(CharSequence newText) {
//        super.setText(newText);
//        pack();
//    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setShader(shader);
        shader.setUniformf("u_smoothing", smoothing);
        super.draw(batch, parentAlpha);
        batch.setShader(null);
    }
}
