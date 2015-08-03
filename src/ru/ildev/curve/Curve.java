/*
 *
 */
package ru.ildev.curve;

import ru.ildev.geom.Vector2;
import ru.ildev.geom.Vector3;
import ru.ildev.math.MoreMath;

/**
 * Общий интерфейс для всех кривых.
 *
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 * @version 0.2.2
 */
public interface Curve {

    /**
     * Получает размерность пространства, в котором расположена крмвая.
     *
     * @return размерность пространства.
     */
    int getDimension();

    /**
     * Абстрактный класс одномерных кривых.
     *
     * @author Ilyas Shafigin <ilyas174<@>gmail.com>
     */
    abstract class D1 implements Curve {

        /**
         * Кеш массива длин кривой.
         */
        protected float[] length = null;

        /**
         * Стандартный конструктор.
         */
        public D1() {
        }

        @Override
        public int getDimension() {
            return 1;
        }

        /**
         * Получает точку.
         *
         * @param t значение от 0 до 1.
         * @return точку.
         */
        public abstract float getPointAt(float t);

        /**
         * Получает массив точек.
         *
         * @param divisions количество точек.
         * @return массив точек.
         */
        public float[] getPoints(int divisions) {
            if (divisions == 0) return new float[0];

            float[] points = new float[divisions];
            for (int d = 0; d <= divisions; d++) {
                points[d] = this.getPointAt((float) d / divisions);
            }
            return points;
        }

        /**
         * Получает длину кривой.
         *
         * @return длину кривой.
         */
        public float getLength() {
            float[] lengths = this.getLengths();
            return lengths[lengths.length - 1];
        }

        /**
         * Получает массив длин кривой со стандартным количеством точек равным 200.
         *
         * @return массив длин кривой размером 200.
         */
        public float[] getLengths() {
            return this.getLengths(200);
        }

        /**
         * Получает массив длин кривой.
         *
         * @param divisions количество точек.
         * @return массив длин кривой.
         */
        public float[] getLengths(int divisions) {
            if (divisions == 0) return new float[0];

            if (this.length != null && this.length.length == divisions + 1) {
                return this.length;
            }

            float[] cache = new float[divisions];
            float current;
            float last = this.getPointAt(0);
            float length = 0.0f;

            for (int p = 1; p < divisions; p++) {
                current = this.getPointAt(p / divisions);
                length += MoreMath.abs(current - last);
                cache[p] = length;
                last = current;
            }

            this.length = cache;
            return cache;
        }
    }

    /**
     * Абстрактный класс кривых на плоскости.
     *
     * @author Ilyas Shafigin <ilyas174<@>gmail.com>
     */
    abstract class D2 implements Curve {

        /**
         * Кеш массива длин кривой.
         */
        protected float[] length = null;

        /**
         * Стандартный конструктор.
         */
        public D2() {
        }

        @Override
        public int getDimension() {
            return 2;
        }

        /**
         * Получает точку.
         *
         * @param t значение от 0 до 1.
         * @return точку.
         */
        public abstract Vector2 getPointAt(float t);

        /**
         * Получает вектор направления касательной к точке кривой.
         *
         * @param t значение от 0 до 1.
         * @return вектор касательной к точке.
         */
        public Vector2 getTangentAt(float t) {
            float delta = 0.0001f;
            float t1 = t - delta;
            float t2 = t + delta;

            if (t1 < 0.0) t1 = 0.0f;
            if (t2 > 1.0) t2 = 1.0f;

            Vector2 pt1 = this.getPointAt(t1);
            Vector2 pt2 = this.getPointAt(t2);

            Vector2 tangent = pt1.to(pt2);
            tangent.normalize();
            return tangent;
        }

        /**
         * Получает нормаль точки.
         *
         * @param t значение от 0 до 1.
         * @return нормаль точки.
         */
        public Vector2 getNormalAt(float t) {
            return this.getTangentAt(t).right();
        }

        /**
         * Получает массив точек.
         *
         * @param divisions количество точек.
         * @return массив точек.
         */
        public Vector2[] getPoints(int divisions) {
            if (divisions == 0) return new Vector2[0];

            Vector2[] points = new Vector2[divisions];
            for (int d = 0; d <= divisions; d++) {
                points[d] = this.getPointAt((float) d / divisions);
            }
            return points;
        }

        /**
         * Получает длину кривой.
         *
         * @return длину кривой.
         */
        public float getLength() {
            float[] lengths = this.getLengths();
            return lengths[lengths.length - 1];
        }

        /**
         * Получает массив длин кривой со стандартным количеством точек равным 200.
         *
         * @return массив длин кривой размером 200.
         */
        public float[] getLengths() {
            return this.getLengths(200);
        }

        /**
         * Получает массив длин кривой.
         *
         * @param divisions количество точек.
         * @return массив длин кривой.
         */
        public float[] getLengths(int divisions) {
            if (divisions == 0) return new float[0];

            if (this.length != null && this.length.length == divisions + 1)
                return this.length;

            float[] cache = new float[divisions];
            Vector2 current;
            Vector2 last = this.getPointAt(0);
            float length = 0.0f;

            for (int p = 1; p < divisions; p++) {
                current = this.getPointAt(p / divisions);
                length += current.getDistance(last);
                cache[p] = length;
                last = current;
            }

            this.length = cache;
            return cache;
        }

    }

    /**
     * Абстрактный класс кривых в пространстве.
     *
     * @author Ilyas Shafigin <ilyas174<@>gmail.com>
     */
    abstract class D3 implements Curve {

        /**
         * Кеш массива длин кривой.
         */
        protected float[] length = null;

        /**
         * Стандартный конструктор.
         */
        public D3() {
        }

        @Override
        public int getDimension() {
            return 3;
        }

        /**
         * Получает точку.
         *
         * @param t значение от 0 до 1.
         * @return точку.
         */
        public abstract Vector3 getPointAt(float t);

        /**
         * Получает массив точек.
         *
         * @param divisions количество точек.
         * @return массив точек.
         */
        public Vector3[] getPoints(int divisions) {
            if (divisions == 0) return new Vector3[0];

            Vector3[] points = new Vector3[divisions];
            for (int d = 0; d <= divisions; d++) {
                points[d] = this.getPointAt((float) d / divisions);
            }
            return points;
        }

        /**
         * Получает длину кривой.
         *
         * @return длину кривой.
         */
        public float getLength() {
            float[] lengths = this.getLengths();
            return lengths[lengths.length - 1];
        }

        /**
         * Получает массив длин кривой со стандартным количеством точек равным 200.
         *
         * @return массив длин кривой размером 200.
         */
        public float[] getLengths() {
            return this.getLengths(200);
        }

        /**
         * Получает массив длин кривой.
         *
         * @param divisions количество точек.
         * @return массив длин кривой.
         */
        public float[] getLengths(int divisions) {
            if (divisions == 0) return new float[0];

            if (this.length != null && this.length.length == divisions + 1)
                return this.length;

            float[] cache = new float[divisions];
            Vector3 current;
            Vector3 last = this.getPointAt(0);
            float length = 0.0f;

            for (int p = 1; p < divisions; p++) {
                current = this.getPointAt(p / divisions);
                length += current.distance(last);
                cache[p] = length;
                last = current;
            }

            this.length = cache;
            return cache;
        }

    }

}
