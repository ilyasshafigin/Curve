/*
 *
 */
package ru.ildev.curve;

import ru.ildev.geom.Vector2;
import ru.ildev.geom.Vector3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ilyas Shafigin <ilyas174<@>gmail.com>
 * @version 0.1.0
 */
public interface CurvePath extends Curve {

    /**
     * @author Ilyas Shafigin <ilyas174<@>gmail.com>
     */
    class D1 extends Curve.D1 implements CurvePath {

        /**  */
        private List<Curve.D1> curves = new ArrayList<>();

        /**
         *
         */
        public D1() {
        }

        /**
         * @param curve
         */
        public void add(Curve.D1 curve) {
            if (curve == null) throw new NullPointerException("curve == null");
            this.curves.add(curve);
        }

        /**
         *
         */
        public void closePath() {
            float startPoint = this.curves.get(0).getPointAt(0.0f);
            float endPoint = this.curves.get(this.curves.size() - 1).getPointAt(1.0f);

            if (startPoint != endPoint) {
                this.curves.add(new LineCurve.D1(endPoint, startPoint));
            }
        }

        @Override
        public float getPointAt(float t) {
            float d = t * this.getLength();
            float[] lengths = this.getLengths();

            for (int i = 0; i < lengths.length; i++) {
                if (lengths[i] >= d) {
                    Curve.D1 curve = this.curves.get(i);

                    float diff = lengths[i] - d;
                    float u = 1.0f - diff / curve.getLength();
                    return curve.getPointAt(u);
                }
            }

            return -1.0f;
        }

        @Override
        public float getLength() {
            float[] lengths = this.getLengths();
            return lengths[lengths.length - 1];
        }

        /**
         * @return
         */
        @Override
        public float[] getLengths() {
            int size = this.curves.size();
            float[] lengths = new float[size];
            float sum = 0.0f;
            for (int i = 0; i < size; i++) {
                sum += this.curves.get(i).getLength();
                lengths[i] = sum;
            }

            return lengths;
        }

    }

    /**
     * @author Ilyas Shafigin <ilyas174<@>gmail.com>
     */
    class D2 extends Curve.D2 implements CurvePath {

        /**  */
        private List<Curve.D2> curves = new ArrayList<>();

        /**
         *
         */
        public D2() {
        }

        /**
         * @param curve
         */
        public void add(Curve.D2 curve) {
            if (curve == null) throw new NullPointerException("curve == null");
            this.curves.add(curve);
        }

        /**
         *
         */
        public void closePath() {
            Vector2 startPoint = this.curves.get(0).getPointAt(0.0f);
            Vector2 endPoint = this.curves.get(this.curves.size() - 1).getPointAt(
                    1.0f);

            if (!startPoint.equals(endPoint)) {
                this.curves.add(new LineCurve.D2(endPoint, startPoint));
            }
        }

        @Override
        public Vector2 getPointAt(float t) {
            float d = t * this.getLength();
            float[] lengths = this.getLengths();

            for (int i = 0; i < lengths.length; i++) {
                if (lengths[i] >= d) {
                    float diff = lengths[i] - d;
                    Curve.D2 curve = this.curves.get(i);
                    float u = 1.0f - diff / curve.getLength();
                    return curve.getPointAt(u);
                }
            }

            return null;
        }

        @Override
        public float getLength() {
            float[] lengths = this.getLengths();
            return lengths[lengths.length - 1];
        }

        @Override
        public float[] getLengths() {
            int size = this.curves.size();
            float[] lengths = new float[size];
            float sum = 0.0f;
            for (int i = 0; i < size; i++) {
                sum += this.curves.get(i).getLength();
                lengths[i] = sum;
            }

            return lengths;
        }

    }

    /**
     * @author Ilyas Shafigin <ilyas174<@>gmail.com>
     */
    public static class D3 extends Curve.D3 implements CurvePath {

        /**  */
        private List<Curve.D3> curves = new ArrayList<>();

        /**
         *
         */
        public D3() {
        }

        /**
         * @param curve
         */
        public void add(Curve.D3 curve) {
            if (curve == null) throw new NullPointerException("curve == null");
            this.curves.add(curve);
        }

        /**
         *
         */
        public void closePath() {
            Vector3 startPoint = this.curves.get(0).getPointAt(0.0f);
            Vector3 endPoint = this.curves.get(this.curves.size() - 1).getPointAt(1.0f);

            if (!startPoint.equals(endPoint)) {
                this.curves.add(new LineCurve.D3(endPoint, startPoint));
            }
        }

        @Override
        public Vector3 getPointAt(float t) {
            float d = t * this.getLength();
            float[] lengths = this.getLengths();

            for (int i = 0; i < lengths.length; i++) {
                if (lengths[i] >= d) {
                    float diff = lengths[i] - d;
                    Curve.D3 curve = this.curves.get(i);
                    float u = 1.0f - diff / curve.getLength();
                    return curve.getPointAt(u);
                }
            }

            return null;
        }

        @Override
        public float getLength() {
            float[] lengths = this.getLengths();
            return lengths[lengths.length - 1];
        }

        /**
         * @return
         */
        @Override
        public float[] getLengths() {
            int size = this.curves.size();
            float[] lengths = new float[size];
            float sum = 0.0f;
            for (int i = 0; i < size; i++) {
                sum += this.curves.get(i).getLength();
                lengths[i] = sum;
            }

            return lengths;
        }

    }


}
