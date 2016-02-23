package mathlib;

import ray.Ray;

/**
 * Handle all transforming method and
 * transforms all gemetries
 *
 * @author group raspi, CG1, Beuth-Hochschule
 * @version 1.0
 */
public final class Transform implements Comparable<Transform> {
    /**
     * The 4x4 matrix as transformation matrix
     */
    public final Mat4x4 m;

    /**
     * The inverse transformation matrix of this transformation
     */
    public final Mat4x4 i;


    /**
     * public constructor creates a new transformation object initializing the unit matrix as transformation matrix
     */
    public Transform(double factor) {

        this.m = new Mat4x4(factor, 0, 0, 0, 0, factor, 0, 0, 0, 0, factor, 0, 0, 0, 0, factor);
        this.i = new Mat4x4(factor, 0, 0, 0, 0, factor, 0, 0, 0, 0, factor, 0, 0, 0, 0, factor);
    }
    
    /**
     * public constructor creates a new transformation object initializing the unit matrix as transformation matrix
     */
    public Transform() {

        this.m = new Mat4x4(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
        this.i = new Mat4x4(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
    }

    /**
     * private constructor creates a new transformation object initializing the unit matrix as transformation matrix
     */
    private Transform(final Mat4x4 m, final Mat4x4 i) {

        this.m = m;
        this.i = i;
    }

    /**
     * method translate given x, y, and z values with current transformation and return a new transformation object.
     *
     * @param x value for the translation
     * @param y value for the translation
     * @param z value for the translation
     * @return new Transformation object with the appended translation
     */
    public Transform translate(final double x, final double y, final double z) {

        Transform t = new Transform(
                new Mat4x4( 1.0, 0.0, 0.0, x,
                            0.0, 1.0, 0.0, y,
                            0.0, 0.0, 1.0, z,
                            0.0, 0.0, 0.0, 1.0),
                new Mat4x4( 1.0, 0.0, 0.0, -x,
                            0.0, 1.0, 0.0, -y,
                            0.0, 0.0, 1.0, -z,
                            0.0, 0.0, 0.0, 1.0));

        return new Transform(m.mul(t.m), t.i.mul(i));
    }

    /**
     * method translate given x, y, and z values with current transformation and return a new transformation object.
     *
     * @param point3 value for the translation
     * @return new Transformation object with the appended translation
     */
    public Transform translate(final Point3 point3) {

        Transform t = new Transform(
                new Mat4x4( 1.0, 0.0, 0.0, point3.x,
                            0.0, 1.0, 0.0, point3.y,
                            0.0, 0.0, 1.0, point3.z,
                            0.0, 0.0, 0.0, 1.0),
                new Mat4x4( 1.0, 0.0, 0.0, -point3.x,
                            0.0, 1.0, 0.0, -point3.y,
                            0.0, 0.0, 1.0, -point3.z,
                            0.0, 0.0, 0.0, 1.0));

        return new Transform(m.mul(t.m), t.i.mul(i));
    }
    
    /**
     * method translate given x, y, and z values with current transformation and return a new transformation object.
     *
     * @param x scale factor for the x axis
     * @param y scale factor for the y axis
     * @param z scale factor for the z axis
     * @return a new transformation object with scale transformation
     */
    public Transform scale(final double x, final double y, final double z) {
        Transform t = new Transform(
                new Mat4x4( x, 0.0, 0.0, 0.0,
                            0.0, y, 0.0, 0.0,
                            0.0, 0.0, z, 0.0,
                            0.0, 0.0, 0.0, 1.0),
                new Mat4x4( 1.0 / x, 0.0, 0.0, 0.0,
                            0.0, 1.0 / y, 0.0, 0.0,
                            0.0, 0.0, 1.0 / z, 0.0,
                            0.0, 0.0, 0.0, 1.0));

        return new Transform(m.mul(t.m), t.i.mul(i));
    }

    /**
     * method appends a rotation arround the X axis to translate given x, y, and z values and return a new transformation object.
     *
     * @param angle the angle of rotation
     * @return a new transformation object with a rotation around the X axis
     */
    public Transform rotateX(final double angle) {
        Transform t = new Transform(
                new Mat4x4( 1.0, 0.0, 0.0, 0.0,
                            0.0, Math.cos(angle), -Math.sin(angle), 0.0,
                            0.0, Math.sin(angle), Math.cos(angle), 0.0,
                            0.0, 0.0, 0.0, 1.0),
                new Mat4x4( 1.0, 0.0, 0.0, 0.0,
                            0.0, Math.cos(angle), Math.sin(angle), 0.0,
                            0.0, -Math.sin(angle), Math.cos(angle), 0.0,
                            0.0, 0.0, 0.0, 1.0));

        return new Transform(m.mul(t.m), t.i.mul(i));
    }

    /**
     * method appends a rotation arround the Y axis to translate given x, y, and z values and return a new transformation object.
     *
     * @param angle the angle of rotation
     * @return a new transformation object with a rotation around the Y axis
     */
    public Transform rotateY(final double angle) {
        Transform t = new Transform(
                new Mat4x4( Math.cos(angle), 0.0, Math.sin(angle), 0.0,
                            0.0, 1.0, 0.0, 0.0,
                            -Math.sin(angle), 0.0, Math.cos(angle), 0.0,
                            0.0, 0.0, 0.0, 1.0),
                new Mat4x4( Math.cos(angle), 0.0, -Math.sin(angle), 0.0,
                            0.0, 1.0, 0.0, 0.0,
                            Math.sin(angle), 0.0, Math.cos(angle), 0.0,
                            0.0, 0.0, 0.0, 1.0));

        return new Transform(m.mul(t.m), t.i.mul(i));
    }

    /**
     * method appends a rotation arround the Z axis to translate given x, y, and z values and return a new transformation object.
     *
     * @param angle the angle of rotation
     * @return a new transformation object with a rotation around the Z axis
     */
    public Transform rotateZ(final double angle) {
        Transform t = new Transform(
                new Mat4x4( Math.cos(angle), -Math.sin(angle), 0.0, 0.0,
                            Math.sin(angle), Math.cos(angle), 0.0, 0.0,
                            0.0, 0.0, 1.0, 0.0,
                            0.0, 0.0, 0.0, 1.0),
                new Mat4x4( Math.cos(angle), Math.sin(angle), 0.0, 0.0,
                            -Math.sin(angle), Math.cos(angle), 0.0, 0.0,
                            0.0, 0.0, 1.0, 0.0,
                            0.0, 0.0, 0.0, 1.0));

        return new Transform(m.mul(t.m), t.i.mul(i));
    }

    /**
     * method transforms a ray by multiplication with the inverse of the transformation matrix
     *
     * @param ray the ray to transform
     * @return a transformed ray
     */
    public Ray mul(final Ray ray) {
        return new Ray(i.mul(ray.o), i.mul(ray.d));
    }

    /**
     * method transforms a normal by multiplication with transposed inverse of the transformation matrix
     *
     * @param n the normal to transform
     * @return a transformed normal
     */
    public Normal3 mul(final Normal3 n) {
        return (i.transposed().mul(new Vector3(n.x, n.y, n.z)).normalized().asNormal());
    }

    @Override
    public String toString() {
        return "Transform{" +
                "m=" + m +
                ", i=" + i +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transform)) return false;

        Transform transform = (Transform) o;

        if (i != null ? !i.equals(transform.i) : transform.i != null) return false;
        if (m != null ? !m.equals(transform.m) : transform.m != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = m != null ? m.hashCode() : 0;
        result = 31 * result + (i != null ? i.hashCode() : 0);
        return result;
    }

    /**
     * Comparable Method for Interface Comparable
     *
     * @param t incoming Transform-Object
     * @return int value ( 0 if all attributes are equal,
     * -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     * 1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(final Transform t) {
        if (this.m.compareTo(t.m) != 0) return this.m.compareTo(t.m);
        return this.i.compareTo(t.i);
    }
}

