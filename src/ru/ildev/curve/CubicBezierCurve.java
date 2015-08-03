package ru.ildev.curve;

import ru.ildev.geom.Vector2;
import ru.ildev.geom.Vector3;

/**
 * Класс кубической кривой Безье. Она строится по четырем точкам. Первая и четвертая - начало и конец соответственно,
 * остальные две - контрольные точки.
 *
 * @author Ilyas Shafigin <ilyas174<@>gmail.com>
 * @version 0.0.0
 */
public interface CubicBezierCurve extends Curve {

    /**
     *
     */
    class D1 extends Curve.D1 {

        /**
         * Точка начала кривой.
         */
        private float start;
        /**
         * Первая контрольная точка кривой.
         */
        private float control1;
        /**
         * Вторая контрольная точка кривой.
         */
        private float control2;
        /**
         * Точка конца кривой.
         */
        private float end;

        /**
         * Стандартный конструктор.
         *
         * @param start    точка начала кривой.
         * @param control1 первая контрольная точка.
         * @param control2 вторая контрольная точка.
         * @param end      точка конца кривой.
         */
        public D1(float start, float control1, float control2, float end) {
            this.start = start;
            this.control1 = control1;
            this.control2 = control2;
            this.end = end;
        }

        /**
         * Получает точку начала кривой.
         *
         * @return точку начала кривой.
         */
        public float getStart() {
            return this.start;
        }

        /**
         * Получает первую контрольную точку.
         *
         * @return первую контрольную точку.
         */
        public float getControl1() {
            return this.control1;
        }

        /**
         * Получает вторую контрольную точку.
         *
         * @return вторую контрольную точку.
         */
        public float getControl2() {
            return this.control2;
        }

        /**
         * Получает точку конца кривой.
         *
         * @return точку конца кривой.
         */
        public float getEnd() {
            return this.end;
        }

        @Override
        public float getPointAt(float t) {
            return this.b3(t, this.start, this.control1, this.control2, this.end);
        }

        private float b3(float t, float p0, float p1, float p2, float p3) {
            return p0 * (1 - t) * (1 - t) * (1 - t) + 3 * p1 * (1 - t) * (1 - t) * t + 3 * p2 * (1 - t) * t * t +
                    p3 * t * t * t;
        }

    }

    /**
     *
     */
    class D2 extends Curve.D2 {

        /**
         * Точка начала кривой.
         */
        private Vector2 start = new Vector2();
        /**
         * Первая контрольная точка кривой.
         */
        private Vector2 control1 = new Vector2();
        /**
         * Вторая контрольная точка кривой.
         */
        private Vector2 control2 = new Vector2();
        /**
         * Точка конца кривой.
         */
        private Vector2 end = new Vector2();

        /**
         * Стандартный конструктор.
         *
         * @param start    точка начала кривой.
         * @param control1 первая контрольная точка.
         * @param control2 вторая контрольная точка.
         * @param end      точка конца кривой.
         */
        public D2(Vector2 start, Vector2 control1, Vector2 control2, Vector2 end) {
            if (start == null) throw new NullPointerException("start == null");
            if (control1 == null) throw new NullPointerException("control1 == null");
            if (control2 == null) throw new NullPointerException("control2 == null");
            if (end == null) throw new NullPointerException("end == null");

            this.start.copy(start);
            this.control1.copy(control1);
            this.control2.copy(control2);
            this.end.copy(end);
        }

        /**
         * Получает точку начала кривой.
         *
         * @return точку начала кривой.
         */
        public Vector2 getStart() {
            return this.start;
        }

        /**
         * Получает первую контрольную точку.
         *
         * @return первую контрольную точку.
         */
        public Vector2 getControl1() {
            return this.control1;
        }

        /**
         * Получает вторую контрольную точку.
         *
         * @return вторую контрольную точку.
         */
        public Vector2 getControl2() {
            return this.control2;
        }

        /**
         * Получает точку конца кривой.
         *
         * @return точку конца кривой.
         */
        public Vector2 getEnd() {
            return this.end;
        }

        @Override
        public Vector2 getPointAt(float t) {
            float tx = this.b3(t, this.start.x, this.control1.x, this.control2.x, this.end.x);
            float ty = this.b3(t, this.start.y, this.control1.y, this.control2.y, this.end.y);
            return new Vector2(tx, ty);
        }

        @Override
        public Vector2 getTangentAt(float t) {
            float tx = this.tangent(t, this.start.x, this.control1.x, this.control2.x, this.end.x);
            float ty = this.tangent(t, this.start.y, this.control1.y, this.control2.y, this.end.y);

            Vector2 tangent = new Vector2(tx, ty);
            tangent.normalize();
            return tangent;
        }

        private float b3(float t, float p0, float p1, float p2, float p3) {
            return p0 * (1 - t) * (1 - t) * (1 - t) + 3 * p1 * (1 - t) * (1 - t) * t + 3 * p2 * (1 - t) * t * t +
                    p3 * t * t * t;
        }

        private float tangent(float t, float p0, float p1, float p2, float p3) {
            return -3 * p0 * (1 - t) * (1 - t) + 3 * p1 * (1 - t) * (1 - t) - 6 * p1 * t * (1 - t) + 6 * p2 * t * (1 - t) -
                    3 * p2 * t * t + 3 * p3 * t * t;
        }

    }

    /**
     *
     */
    class D3 extends Curve.D3 {

        /**
         * Точка начала кривой.
         */
        private Vector3 start = new Vector3();
        /**
         * Первая контрольная точка кривой.
         */
        private Vector3 control1 = new Vector3();
        /**
         * Вторая контрольная точка кривой.
         */
        private Vector3 control2 = new Vector3();
        /**
         * Точка конца кривой.
         */
        private Vector3 end = new Vector3();

        /**
         * Стандартный конструктор.
         *
         * @param start    точка начала кривой.
         * @param control1 первая контрольная точка.
         * @param control2 вторая контрольная точка.
         * @param end      точка конца кривой.
         */
        public D3(Vector3 start, Vector3 control1, Vector3 control2, Vector3 end) {
            if (start == null) throw new NullPointerException("start == null");
            if (control1 == null) throw new NullPointerException("control1 == null");
            if (control2 == null) throw new NullPointerException("control2 == null");
            if (end == null) throw new NullPointerException("end == null");

            this.start.copy(start);
            this.control1.copy(control1);
            this.control2.copy(control2);
            this.end.copy(end);
        }

        /**
         * Получает точку начала кривой.
         *
         * @return точку начала кривой.
         */
        public Vector3 getStart() {
            return this.start;
        }

        /**
         * Получает первую контрольную точку.
         *
         * @return первую контрольную точку.
         */
        public Vector3 getControl1() {
            return this.control1;
        }

        /**
         * Получает вторую контрольную точку.
         *
         * @return вторую контрольную точку.
         */
        public Vector3 getControl2() {
            return this.control2;
        }

        /**
         * Получает точку конца кривой.
         *
         * @return точку конца кривой.
         */
        public Vector3 getEnd() {
            return this.end;
        }

        @Override
        public Vector3 getPointAt(float t) {
            float tx = this.b3(t, this.start.x, this.control1.x, this.control2.x, this.end.x);
            float ty = this.b3(t, this.start.y, this.control1.y, this.control2.y, this.end.y);
            float tz = this.b3(t, this.start.z, this.control1.z, this.control2.z, this.end.z);
            return new Vector3(tx, ty, tz);
        }

        private float b3(float t, float p0, float p1, float p2, float p3) {
            return p0 * (1 - t) * (1 - t) * (1 - t) + 3 * p1 * (1 - t) * (1 - t) * t + 3 * p2 * (1 - t) * t * t +
                    p3 * t * t * t;
        }

    }

}
