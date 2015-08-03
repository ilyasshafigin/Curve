/*
 *
 */
package ru.ildev.curve;

import ru.ildev.geom.Vector2;
import ru.ildev.geom.Vector3;
import ru.ildev.math.MoreMath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Shafigin Ilyas (Шафигин Ильяс) <Ilyas74>
 * @version 0.1.1
 */
public interface Path extends Curve {

    // Виды интерполяции.
    /**
     * Линейная интерполяция.
     */
    int LINEAR = 0;
    /**
     * Косинусоидная интерполяция.
     */
    int COSINE = 1;
    /**
     * Кобическая интерполяция.
     */
    int CUBIC = 2;
    /**  */
    int HERMITE = 3;
    /**  */
    int SPLINE = 4;

    /**
     * @return
     */
    int getInterpolation();

    /**
     * @param innterpolation
     */
    void setInterpolation(int innterpolation);

    /**
     * Класс кривой сплайна.
     *
     * @author Ilyas Shafigin <ilyas174<@>gmail.com>
     */
    class D1 extends Curve.D1 implements Path {

        /**
         * Список точек.
         */
        private List<Float> points = new ArrayList<>();
        /**  */
        private int interpolation = Path.LINEAR;

        /**
         * Стандартный конструктор.
         *
         * @param points список точек.
         */
        public D1(List<Float> points) {
            if (points == null) throw new NullPointerException("points == null");
            this.points = points;
        }

        /**
         * Получает список точек.
         *
         * @return список точек.
         */
        public List<Float> getPoints() {
            return this.points;
        }

        /**
         * @param points
         */
        public void setPoints(List<Float> points) {
            if (points == null) throw new NullPointerException("points == null");
            this.points = points;
        }

        @Override
        public int getInterpolation() {
            return this.interpolation;
        }

        @Override
        public void setInterpolation(int interpolation) {
            this.interpolation = interpolation;
        }

        /**
         * @param x
         */
        public void addPoint(float x) {
            this.points.add(x);
        }

        /**
         * @param index
         * @return
         */
        public float getPoint(int index) {
            return this.points.get(index);
        }

        @Override
        public float getPointAt(float t) {
            t = MoreMath.clamp(t, 0.0f, 1.0f);

            int size = this.points.size();
            float point = (size - 1) * t;
            int intPoint = (int) point;
            float weight = point - intPoint;

            int i1 = intPoint == 0 ? intPoint : intPoint - 1;
            int i2 = intPoint;
            int i3 = intPoint > size - 2 ? intPoint : intPoint + 1;
            int i4 = intPoint > size - 3 ? intPoint : intPoint + 2;

            float p1 = this.points.get(i1);
            float p2 = this.points.get(i2);
            float p3 = this.points.get(i3);
            float p4 = this.points.get(i4);

            switch (this.interpolation) {
                default:
                case LINEAR:
                    return Interpolator.linear(p2, p3, weight);
                case COSINE:
                    return Interpolator.cosine(p2, p3, weight);
                case CUBIC:
                    return Interpolator.cubic(p1, p2, p3, p4, weight);
                case HERMITE:
                    return Interpolator.hermite(p1, p2, p3, p4, 0, 0, weight);
                case SPLINE:
                    return Interpolator.spline(p1, p2, p3, p4, weight);
            }
        }

    }

    /**
     * Класс кривой сплайна.
     *
     * @author Ilyas Shafigin <ilyas174<@>gmail.com>
     */
    class D2 extends Curve.D2 implements Path {

        /**
         * Список точек.
         */
        private List<Vector2> points;
        /**  */
        private int interpolation;

        /**
         * @param points массив точек.
         */
        public D2(Vector2[] points) {
            this(points, LINEAR);
        }

        /**
         * @param points массив точек.
         */
        public D2(List<Vector2> points) {
            this(points, LINEAR);
        }

        /**
         * @param points        массив точек.
         * @param interpolation
         */
        public D2(Vector2[] points, int interpolation) {
            if (points == null) throw new NullPointerException("points == null");
            for (int i = 0; i < points.length; i++) {
                if (points[i] == null) throw new NullPointerException("points[" + i + "] == null");
            }
            this.points = new ArrayList<>(Arrays.asList(points));
            this.interpolation = interpolation;
        }

        /**
         * @param points        массив точек.
         * @param interpolation
         */
        public D2(List<Vector2> points, int interpolation) {
            if (points == null) throw new NullPointerException("points == null");
            int size = points.size();
            for (int i = 0; i < size; i++) {
                if (points.get(i) == null) throw new NullPointerException("points(" + i + ") == null");
            }
            this.points = new ArrayList<>(points);
            this.interpolation = interpolation;
        }

        /**
         * Получает массив точек.
         *
         * @return массив точек.
         */
        public List<Vector2> getPoints() {
            return this.points;
        }

        /**
         * @param points
         */
        public void setPoints(Vector2[] points) {
            if (points == null) throw new NullPointerException("points == null");
            for (int i = 0; i < points.length; i++) {
                if (points[i] == null) throw new NullPointerException("points[" + i + "] == null");
            }
            this.points = new ArrayList<>(Arrays.asList(points));
        }

        /**
         * @param points
         */
        public void setPoints(List<Vector2> points) {
            if (points == null) throw new NullPointerException("points == null");
            int size = points.size();
            for (int i = 0; i < size; i++) {
                if (points.get(i) == null) throw new NullPointerException("points(" + i + ") == null");
            }
            this.points = new ArrayList<>(points);
        }

        @Override
        public int getInterpolation() {
            return this.interpolation;
        }

        @Override
        public void setInterpolation(int interpolation) {
            this.interpolation = interpolation;
        }

        /**
         * @param x
         * @param y
         */
        public void addPoint(float x, float y) {
            this.points.add(new Vector2(x, y));
        }

        /**
         * @param point
         */
        public void addPoint(Vector2 point) {
            if (point == null) return;
            this.points.add(point);
        }

        /**
         * @param index
         * @return
         */
        public Vector2 getPoint(int index) {
            return this.points.get(index);
        }

        @Override
        public Vector2 getPointAt(float t) {
            t = MoreMath.clamp(t, 0.0f, 1.0f);

            int size = this.points.size();
            float point = (size - 1) * t;
            int intPoint = (int) point;
            float weight = point - intPoint;

            int i1 = intPoint == 0 ? intPoint : intPoint - 1;
            int i2 = intPoint;
            int i3 = intPoint > size - 2 ? intPoint : intPoint + 1;
            int i4 = intPoint > size - 3 ? intPoint : intPoint + 2;

            Vector2 p1 = this.points.get(i1);
            Vector2 p2 = this.points.get(i2);
            Vector2 p3 = this.points.get(i3);
            Vector2 p4 = this.points.get(i4);

            Vector2 p = new Vector2();
            switch (this.interpolation) {
                default:
                case LINEAR: {
                    p.x = Interpolator.linear(p2.x, p3.x, weight);
                    p.y = Interpolator.linear(p2.y, p3.y, weight);
                    break;
                }
                case COSINE: {
                    p.x = Interpolator.cosine(p2.x, p3.x, weight);
                    p.y = Interpolator.cosine(p2.y, p3.y, weight);
                    break;
                }
                case CUBIC: {
                    p.x = Interpolator.cubic(p1.x, p2.x, p3.x, p4.x, weight);
                    p.y = Interpolator.cubic(p1.y, p2.y, p3.y, p4.y, weight);
                    break;
                }
                case HERMITE: {
                    p.x = Interpolator.hermite(p1.x, p2.x, p3.x, p4.x, 0, 0, weight);
                    p.y = Interpolator.hermite(p1.y, p2.y, p3.y, p4.y, 0, 0, weight);
                    break;
                }
                case SPLINE: {
                    p.x = Interpolator.spline(p1.x, p2.x, p3.x, p4.x, weight);
                    p.y = Interpolator.spline(p1.y, p2.y, p3.y, p4.y, weight);
                    break;
                }
            }
            return p;
        }
    /*
        @Override
        public Vector2 getTangentAt(float t) {
            float point = (this.points.getLength - 1) * t;
            int intPoint = (int) point;
            float weight = point - intPoint;

            int c1 = intPoint == 0 ? intPoint : intPoint - 1;
            int c2 = intPoint;
            int c3 = intPoint > this.points.getLength - 2 ? intPoint : intPoint + 1;
            int c4 = intPoint > this.points.getLength - 3 ? intPoint : intPoint + 2;

            Vector2 p1 = this.points[c1];
            Vector2 p2 = this.points[c2];
            Vector2 p3 = this.points[c3];
            Vector2 p4 = this.points[c4];

            Vector2 p = new Vector2();
            p.x = this.tangent(weight, p1.x, p2.x, p3.x, p4.x);
            p.y = this.tangent(weight, p1.y, p2.y, p3.y, p4.y);
            p.normalize();
            return p;
        }
    */

        private float tangent(float t, float p0, float p1, float p2, float p3) {
            return 6 * t * t - 6 * t + (3 * t * t - 4 * t + 1) + (-6 * t * t + 6 * t) + (3 * t * t - 2 * t);
        }

    }

    /**
     * Класс кривой сплайна.
     *
     * @author Ilyas Shafigin <ilyas174<@>gmail.com>
     */
    class D3 extends Curve.D3 implements Path {

        /**
         * Список точек.
         */
        private List<Vector3> points;
        /**  */
        private int interpolation = Path.LINEAR;

        /**
         * @param points массив точек.
         */
        public D3(Vector3[] points) {
            if (points == null) throw new NullPointerException("points == null");
            for (int i = 0; i < points.length; i++) {
                if (points[i] == null) throw new NullPointerException("points[" + i + "] == null");
            }
            this.points = new ArrayList<>(Arrays.asList(points));
        }

        /**
         * @param points массив точек.
         */
        public D3(List<Vector3> points) {
            if (points == null) throw new NullPointerException("points == null");
            int size = points.size();
            for (int i = 0; i < size; i++) {
                if (points.get(i) == null) throw new NullPointerException("points(" + i + ") == null");
            }
            this.points = new ArrayList<>(points);
        }

        /**
         * Получает массив точек.
         *
         * @return массив точек.
         */
        public List<Vector3> getPoints() {
            return this.points;
        }

        /**
         * @param points
         */
        public void setPoints(Vector3[] points) {
            if (points == null) throw new NullPointerException("points == null");
            for (int i = 0; i < points.length; i++) {
                if (points[i] == null) throw new NullPointerException("points[" + i + "] == null");
            }
            this.points = new ArrayList<>(Arrays.asList(points));
        }

        /**
         * @param points
         */
        public void setPoints(List<Vector3> points) {
            if (points == null) throw new NullPointerException("points == null");
            int size = points.size();
            for (int i = 0; i < size; i++) {
                if (points.get(i) == null) throw new NullPointerException("points(" + i + ") == null");
            }
            this.points = new ArrayList<>(points);
        }

        @Override
        public int getInterpolation() {
            return this.interpolation;
        }

        @Override
        public void setInterpolation(int interpolation) {
            this.interpolation = interpolation;
        }

        /**
         * @param x
         * @param y
         * @param z
         */
        public void addPoint(float x, float y, float z) {
            this.points.add(new Vector3(x, y, z));
        }

        /**
         * @param point
         */
        public void addPoint(Vector3 point) {
            if (point == null) return;
            this.points.add(point);
        }

        /**
         * @param index
         * @return
         */
        public Vector3 getPoint(int index) {
            return this.points.get(index);
        }

        @Override
        public Vector3 getPointAt(float t) {
            t = MoreMath.clamp(t, 0.0f, 1.0f);

            int size = this.points.size();
            float point = (size - 1) * t;
            int intPoint = (int) point;
            float weight = point - intPoint;

            int i1 = intPoint == 0 ? intPoint : intPoint - 1;
            int i2 = intPoint;
            int i3 = intPoint > size - 2 ? intPoint : intPoint + 1;
            int i4 = intPoint > size - 3 ? intPoint : intPoint + 2;

            Vector3 p1 = this.points.get(i1);
            Vector3 p2 = this.points.get(i2);
            Vector3 p3 = this.points.get(i3);
            Vector3 p4 = this.points.get(i4);

            Vector3 p = new Vector3();
            switch (this.interpolation) {
                default:
                case LINEAR: {
                    p.x = Interpolator.linear(p2.x, p3.x, weight);
                    p.y = Interpolator.linear(p2.y, p3.y, weight);
                    p.z = Interpolator.linear(p2.z, p3.z, weight);
                    break;
                }
                case COSINE: {
                    p.x = Interpolator.cosine(p2.x, p3.x, weight);
                    p.y = Interpolator.cosine(p2.y, p3.y, weight);
                    p.z = Interpolator.cosine(p2.z, p3.z, weight);
                    break;
                }
                case CUBIC: {
                    p.x = Interpolator.cubic(p1.x, p2.x, p3.x, p4.x, weight);
                    p.y = Interpolator.cubic(p1.y, p2.y, p3.y, p4.y, weight);
                    break;
                }
                case HERMITE: {
                    p.x = Interpolator.hermite(p1.x, p2.x, p3.x, p4.x, 0, 0, weight);
                    p.y = Interpolator.hermite(p1.y, p2.y, p3.y, p4.y, 0, 0, weight);
                    p.z = Interpolator.hermite(p1.z, p2.z, p3.z, p4.z, 0, 0, weight);
                    break;
                }
                case SPLINE: {
                    p.x = Interpolator.spline(p1.x, p2.x, p3.x, p4.x, weight);
                    p.y = Interpolator.spline(p1.y, p2.y, p3.y, p4.y, weight);
                    p.z = Interpolator.spline(p1.z, p2.z, p3.z, p4.z, weight);
                    break;
                }
            }
            return p;
        }
    /*
        @Override
        public Vector2 getTangentAt(float t) {
            float point = (this.points.getLength - 1) * t;
            int intPoint = (int) point;
            float weight = point - intPoint;

            int c1 = intPoint == 0 ? intPoint : intPoint - 1;
            int c2 = intPoint;
            int c3 = intPoint > this.points.getLength - 2 ? intPoint : intPoint + 1;
            int c4 = intPoint > this.points.getLength - 3 ? intPoint : intPoint + 2;

            Vector2 p1 = this.points[c1];
            Vector2 p2 = this.points[c2];
            Vector2 p3 = this.points[c3];
            Vector2 p4 = this.points[c4];

            Vector2 p = new Vector2();
            p.x = this.tangent(weight, p1.x, p2.x, p3.x, p4.x);
            p.y = this.tangent(weight, p1.y, p2.y, p3.y, p4.y);
            p.normalize();
            return p;
        }
    */

    }

}
