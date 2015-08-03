/*
 *
 */
package ru.ildev.curve;

import ru.ildev.math.MoreMath;

/**
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 * @version 0.0.0
 */
public final class Interpolator {

    /**
     * @param y1
     * @param y2
     * @param t
     * @return
     */
    public static float linear(float y1, float y2, float t) {
        return y1 * (1.0f - t) + y2 * t;
    }

    /**
     * @param y1
     * @param y2
     * @param t1
     * @return
     */
    public static float cosine(float y1, float y2, float t1) {
        float t2 = (1.0f - MoreMath.cos(t1 * MoreMath.PI)) / 2.0f;
        return y1 * (1.0f - t2) + y2 * t2;
    }

    /**
     * @param y0
     * @param y1
     * @param y2
     * @param y3
     * @param t1
     * @return
     */
    public static float cubic(float y0, float y1, float y2, float y3, float t1) {
        float t2 = t1 * t1;
        float a0 = y3 - y2 - y0 + y1;
        float a1 = y0 - y1 - a0;
        float a2 = y2 - y0;
        float a3 = y1;

        return a0 * t1 * t2 + a1 * t2 + a2 * t1 + a3;
    }

    /**
     * @param y0
     * @param y1
     * @param y2
     * @param y3
     * @param t1
     * @param tension 1 is high, 0 normal, -1 is low
     * @param bias    0 is even, positive is towards first segment, negative towards the other
     * @return
     */
    public static float hermite(float y0, float y1, float y2, float y3, float t1, float tension, float bias) {
        float t2 = t1 * t1;
        float t3 = t2 * t1;
        float m0 = (y1 - y0) * (1.0f + bias) * (1.0f - tension) / 2.0f +
                (y2 - y1) * (1.0f - bias) * (1.0f - tension) / 2.0f;
        float m1 = (y2 - y1) * (1.0f + bias) * (1.0f - tension) / 2.0f +
                (y3 - y2) * (1.0f - bias) * (1.0f - tension) / 2.0f;
        float a0 = 2.0f * t3 - 3.0f * t2 + 1.0f;
        float a1 = t3 - 2.0f * t2 + t1;
        float a2 = t3 - t2;
        float a3 = -2.0f * t3 + 3.0f * t2;

        return a0 * y1 + a1 * m0 + a2 * m1 + a3 * y2;
    }

    /**
     * @param y0
     * @param y1
     * @param y2
     * @param y3
     * @param t
     * @return
     */
    public static float spline(float y0, float y1, float y2, float y3, float t) {
        return y1 + 0.5f * t * (y2 - y0 + t * (2 * y0 - 5 * y1 + 4 * y2 - y3 + t * (3 * (y1 - y2) + y3 - y0)));
    }

}
