package engine.gdx.base.utils;

import com.badlogic.gdx.audio.Sound;

import java.util.Random;

public class Utils {
    private static Random r = new Random();

    public static float getRandomFloat() {
        return r.nextFloat();
    }

    public static float getRandomFloat(float min, float max) {
        return r.nextFloat() * Math.abs(max - min) + Math.min(min, max);
    }

    public static void playSound(Sound sound) {
        if (sound != null) {
            sound.play();
        }
    }

    public static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    public static float round(float value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (float) Math.round(value * scale) / scale;
    }

    public static String firstToUpper(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

    public static float dst(int x1, int y1, int x2, int y2) {
        return (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static boolean isInside(float rectX, float rectY, float rectWidth, float rectHeight, float pointX, float pointY) {
        if (pointX < rectX) return false;
        if (pointY < rectY) return false;
        if (pointX > rectX + rectWidth) return false;
        if (pointY > rectY + rectHeight) return false;
        return true;
    }
}
