package ru.ildev.curve;

import ru.ildev.geom.Vector2;
import ru.ildev.geom.Vector3;

/**
 * Класс квадратичной кривой Безье. Она строится по трем точкам. Первая и третья точки - это начало и конец кривой.
 * Вторая точка - контрольная точка.
 *
 * @author Ilyas Shafigin <ilyas174<@>gmail.com>
 * @version 0.0.0
 */
public interface QuadraticBezierCurve extends Curve {

    /**
     *
     */
    class D1 extends Curve.D1 {

        /**
         * Точка начала кривой.
         */
        private float start;
        /**
         * Контрольная точка кривой.
         */
        private float control;
        /**
         * Точка конца кривой.
         */
        private float end;

        /**
         * Стандартный конструктор.
         *
         * @param start   начало кривой.
         * @param control контрольная точка кривой.
         * @param end     конец кривой.
         */
        public D1(float start, float control, float end) {
            this.start = start;
            this.control = control;
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
        public float getControl() {
            return this.control;
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
            return this.b2(t, this.start, this.control, this.end);
        }

        private float b2(float t, float p0, float p1, float p2) {
            return p0 * (1 - t) * (1 - t) + 2 * p1 * (1 - t) * t + p2 * t * t;
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
         * Контрольная точка кривой.
         */
        private Vector2 control = new Vector2();
        /**
         * Точка конца кривой.
         */
        private Vector2 end = new Vector2();

        /**
         * Стандартный конструктор.
         *
         * @param start   начало кривой.
         * @param control контрольная точка кривой.
         * @param end     конец кривой.
         */
        public D2(Vector2 start, Vector2 control, Vector2 end) {
            if (start == null) throw new NullPointerException("start == null");
            if (control == null) throw new NullPointerException("control == null");
            if (end == null) throw new NullPointerException("end == null");

            this.start.copy(start);
            this.control.copy(control);
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
        public Vector2 getControl() {
            return this.control;
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
            float tx = this.b2(t, this.start.x, this.control.x, this.end.x);
            float ty = this.b2(t, this.start.y, this.control.y, this.end.y);
            return new Vector2(tx, ty);
        }

        @Override
        public Vector2 getTangentAt(float t) {
            float tx = this.tangent(t, this.start.x, this.control.x, this.end.x);
            float ty = this.tangent(t, this.start.y, this.control.y, this.end.y);

            Vector2 tangent = new Vector2(tx, ty);
            tangent.normalize();
            return tangent;
        }

        private float b2(float t, float p0, float p1, float p2) {
            return p0 * (1 - t) * (1 - t) + 2 * p1 * (1 - t) * t + p2 * t * t;
        }

        private float tangent(float t, float p0, float p1, float p2) {
            return 2 * (1 - t) * (p1 - p0) + 2 * t * (p2 - p1);
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
         * Контрольная точка кривой.
         */
        private Vector3 control = new Vector3();
        /**
         * Точка конца кривой.
         */
        private Vector3 end = new Vector3();

        /**
         * Стандартный конструктор.
         *
         * @param start   начало кривой.
         * @param control контрольная точка кривой.
         * @param end     конец кривой.
         */
        public D3(Vector3 start, Vector3 control, Vector3 end) {
            if (start == null) throw new NullPointerException("start == null");
            if (control == null) throw new NullPointerException("control == null");
            if (end == null) throw new NullPointerException("end == null");

            this.start.copy(start);
            this.control.copy(control);
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
        public Vector3 getControl() {
            return this.control;
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
            float tx = this.b2(t, this.start.x, this.control.x, this.end.x);
            float ty = this.b2(t, this.start.y, this.control.y, this.end.y);
            float tz = this.b2(t, this.start.z, this.control.z, this.end.z);
            return new Vector3(tx, ty, tz);
        }

        private float b2(float t, float p0, float p1, float p2) {
            return p0 * (1 - t) * (1 - t) + 2 * p1 * (1 - t) * t + p2 * t * t;
        }

    }

}
