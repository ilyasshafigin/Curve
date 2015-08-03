/*
 *
 */
package ru.ildev.curve;

import ru.ildev.geom.Vector2;
import ru.ildev.geom.Vector3;

/**
 * @author Ilyas Shafigin <ilyas174<@>gmail.com>
 * @version 0.1.1
 */
public interface LineCurve extends Curve {

    /**
     * Класс линии. Она строится по двум точкам.
     *
     * @author Ilyas Shafigin <ilyas174<@>gmail.com>
     */
    public static class D1 extends Curve.D1 implements LineCurve {

        /**
         * Начальная точка.
         */
        private float start;
        /**
         * Конечная точка.
         */
        private float end;

        /**
         * Стандартный конструктор.
         *
         * @param start начальная точка.
         * @param end   конечная точка.
         */
        public D1(float start, float end) {
            this.start = start;
            this.end = end;
        }

        /**
         * Получает начальную точку.
         *
         * @return начальную точку.
         */
        public float getStart() {
            return this.start;
        }

        /**
         * Получает конечную точку.
         *
         * @return конечную точку.
         */
        public float getEnd() {
            return this.end;
        }

        @Override
        public float getPointAt(float t) {
            return t * this.start + (1 - t) * this.end;
        }

    }

    /**
     * Класс линии. Она строится по двум точкам.
     *
     * @author Ilyas Shafigin <ilyas174<@>gmail.com>
     */
    public class D2 extends Curve.D2 implements LineCurve {

        /**
         * Начальная точка.
         */
        private Vector2 start = new Vector2();
        /**
         * Конечная точка.
         */
        private Vector2 end = new Vector2();

        /**
         * Стандартный конструктор.
         *
         * @param start начальная точка.
         * @param end   конечная точка.
         */
        public D2(Vector2 start, Vector2 end) {
            if (start == null) throw new NullPointerException("start == null");
            if (end == null) throw new NullPointerException("end == null");

            this.start.copy(start);
            this.end.copy(end);
        }

        /**
         * Получает начальную точку.
         *
         * @return начальную точку.
         */
        public Vector2 getStart() {
            return this.start;
        }

        /**
         * Получает конечную точку.
         *
         * @return конечную точку.
         */
        public Vector2 getEnd() {
            return this.end;
        }

        @Override
        public Vector2 getPointAt(float t) {
            Vector2 point = new Vector2();
            point.x = t * this.start.x + (1 - t) * this.end.x;
            point.y = t * this.start.y + (1 - t) * this.end.y;
            return point;
        }

        @Override
        public Vector2 getTangentAt(float t) {
            Vector2 tangent = this.start.to(this.end);
            tangent.normalize();
            return tangent;
        }

    }

    /**
     * Класс линии. Она строится по двум точкам.
     *
     * @author Ilyas Shafigin <ilyas174<@>gmail.com>
     */
    public static class D3 extends Curve.D3 implements LineCurve {

        /**
         * Начальная точка.
         */
        private Vector3 start = new Vector3();
        /**
         * Конечная точка.
         */
        private Vector3 end = new Vector3();

        /**
         * Стандартный конструктор.
         *
         * @param start начальная точка.
         * @param end   конечная точка.
         */
        public D3(Vector3 start, Vector3 end) {
            if (start == null) throw new NullPointerException("start == null");
            if (end == null) throw new NullPointerException("end == null");

            this.start.copy(start);
            this.end.copy(end);
        }

        /**
         * Получает начальную точку.
         *
         * @return начальную точку.
         */
        public Vector3 getStart() {
            return this.start;
        }

        /**
         * Получает конечную точку.
         *
         * @return конечную точку.
         */
        public Vector3 getEnd() {
            return this.end;
        }

        @Override
        public Vector3 getPointAt(float t) {
            Vector3 point = new Vector3();
            point.x = t * this.start.x + (1 - t) * this.end.x;
            point.y = t * this.start.y + (1 - t) * this.end.y;
            point.z = t * this.start.z + (1 - t) * this.end.z;
            return point;
        }

    }

}