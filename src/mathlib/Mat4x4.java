package mathlib;

/**
 * Mat4x4 includes all mathematical functions for
 * 4x4 Matrices
 *
 * @author group raspi, CG1, Beuth-Hochschule
 * @version 1.0
 */
public final class Mat4x4 implements Comparable<Mat4x4> {
    public final double m11, m12, m13, m14,
                        m21, m22, m23, m24,
                        m31, m32, m33, m34,
                        m41, m42, m43, m44;


    /**
     * constructor creates a matrix without a determinant as a parameter
     *
     * @param m11 is in colum 1 and row 1 of Mat4x4
     * @param m12 is in colum 1 and row 2 of Mat4x4
     * @param m13 is in colum 1 and row 3 of Mat4x4
     * @param m14 is in colum 1 and row 4 of Mat4x4
     * @param m21 is in colum 2 and row 1 of Mat4x4
     * @param m22 is in colum 2 and row 2 of Mat4x4
     * @param m23 is in colum 2 and row 3 of Mat4x4
     * @param m24 is in colum 2 and row 4 of Mat4x4
     * @param m31 is in colum 3 and row 1 of Mat4x4
     * @param m32 is in colum 3 and row 2 of Mat4x4
     * @param m33 is in colum 3 and row 3 of Mat4x4
     * @param m34 is in colum 3 and row 4 of Mat4x4
     * @param m41 is in colum 4 and row 1 of Mat4x4
     * @param m42 is in colum 4 and row 2 of Mat4x4
     * @param m43 is in colum 4 and row 3 of Mat4x4
     * @param m44 is in colum 4 and row 4 of Mat4x4
     */
    public Mat4x4(final double m11, final double m12, final double m13, final double m14,
                  final double m21, final double m22, final double m23, final double m24,
                  final double m31, final double m32, final double m33, final double m34,
                  final double m41, final double m42, final double m43, final double m44) {
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m14 = m14;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m24 = m24;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
        this.m34 = m34;
        this.m41 = m41;
        this.m42 = m42;
        this.m43 = m43;
        this.m44 = m44;
    }

    /**
     * multiplication of a matrix with a matrix
     *
     * @param m is a 4x4 matrix
     * @return new 4x4 matrix
     */
    public Mat4x4 mul(final Mat4x4 m) {

        final double a11 = m11 * m.m11 + m12 * m.m21 + m13 * m.m31 + m14 * m.m41;
        final double a12 = m11 * m.m12 + m12 * m.m22 + m13 * m.m32 + m14 * m.m42;
        final double a13 = m11 * m.m13 + m12 * m.m23 + m13 * m.m33 + m14 * m.m43;
        final double a14 = m11 * m.m14 + m12 * m.m24 + m13 * m.m34 + m14 * m.m44;

        final double a21 = m21 * m.m11 + m22 * m.m21 + m23 * m.m31 + m24 * m.m41;
        final double a22 = m21 * m.m12 + m22 * m.m22 + m23 * m.m32 + m24 * m.m42;
        final double a23 = m21 * m.m13 + m22 * m.m23 + m23 * m.m33 + m24 * m.m43;
        final double a24 = m21 * m.m14 + m22 * m.m24 + m23 * m.m34 + m24 * m.m44;

        final double a31 = m31 * m.m11 + m32 * m.m21 + m33 * m.m31 + m34 * m.m41;
        final double a32 = m31 * m.m12 + m32 * m.m22 + m33 * m.m32 + m34 * m.m42;
        final double a33 = m31 * m.m13 + m32 * m.m23 + m33 * m.m33 + m34 * m.m43;
        final double a34 = m31 * m.m14 + m32 * m.m24 + m33 * m.m34 + m34 * m.m44;

        final double a41 = m41 * m.m11 + m42 * m.m21 + m43 * m.m31 + m44 * m.m41;
        final double a42 = m41 * m.m12 + m42 * m.m22 + m43 * m.m32 + m44 * m.m42;
        final double a43 = m41 * m.m13 + m42 * m.m23 + m43 * m.m33 + m44 * m.m43;
        final double a44 = m41 * m.m14 + m42 * m.m24 + m43 * m.m34 + m44 * m.m44;

        return new Mat4x4(  a11, a12, a13, a14,
                            a21, a22, a23, a24,
                            a31, a32, a33, a34,
                            a41, a42, a43, a44);
    }

    /**
     * multiplication of a matrix with a vector
     *
     * @param v Vector3 instance to be added
     * @return new Vector3 instance
     */
    public Vector3 mul(Vector3 v) {
        
        final double x = m11 * v.x + m12 * v.y + m13 * v.z;
        final double y = m21 * v.x + m22 * v.y + m23 * v.z;
        final double z = m31 * v.x + m32 * v.y + m33 * v.z;
        
        return new Vector3(x, y, z);
    }

    /**
     * multiplication of a matrix with a point
     *
     * @param p Point3 instance to be added
     * @return new Point3 instance
     */
    public Point3 mul(Point3 p) {
        
        final double x = m11 * p.x + m12 * p.y + m13 * p.z  + m14;
        final double y = m21 * p.x + m22 * p.y + m23 * p.z  + m24;
        final double z = m31 * p.x + m32 * p.y + m33 * p.z  + m34;
        
        return new Point3(x, y, z);
    }

    /**
     * transposed matrix
     *
     * @return new Mat4x4
     */
    public Mat4x4 transposed() {

        final double a11 = m11;
        final double a21 = m12;
        final double a31 = m13;
        final double a41 = m14;

        final double a12 = m21;
        final double a22 = m22;
        final double a32 = m23;
        final double a42 = m24;

        final double a13 = m31;
        final double a23 = m32;
        final double a33 = m33;
        final double a43 = m34;

        final double a14 = m41;
        final double a24 = m42;
        final double a34 = m43;
        final double a44 = m44;

        return new Mat4x4(a11, a12, a13, a14, a21, a22, a23, a24, a31, a32, a33, a34, a41, a42, a43, a44);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mat4x4)) return false;

        Mat4x4 mat4x4 = (Mat4x4) o;

        if (Double.compare(mat4x4.m11, m11) != 0) return false;
        if (Double.compare(mat4x4.m12, m12) != 0) return false;
        if (Double.compare(mat4x4.m13, m13) != 0) return false;
        if (Double.compare(mat4x4.m14, m14) != 0) return false;
        if (Double.compare(mat4x4.m21, m21) != 0) return false;
        if (Double.compare(mat4x4.m22, m22) != 0) return false;
        if (Double.compare(mat4x4.m23, m23) != 0) return false;
        if (Double.compare(mat4x4.m24, m24) != 0) return false;
        if (Double.compare(mat4x4.m31, m31) != 0) return false;
        if (Double.compare(mat4x4.m32, m32) != 0) return false;
        if (Double.compare(mat4x4.m33, m33) != 0) return false;
        if (Double.compare(mat4x4.m34, m34) != 0) return false;
        if (Double.compare(mat4x4.m41, m41) != 0) return false;
        if (Double.compare(mat4x4.m42, m42) != 0) return false;
        if (Double.compare(mat4x4.m43, m43) != 0) return false;
        if (Double.compare(mat4x4.m44, m44) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(m11);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m12);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m13);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m14);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m21);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m22);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m23);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m24);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m31);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m32);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m33);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m34);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m41);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m42);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m43);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(m44);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Mat4x4{" +
                "m11=" + m11 +
                ", m12=" + m12 +
                ", m13=" + m13 +
                ", m14=" + m14 +
                ", m21=" + m21 +
                ", m22=" + m22 +
                ", m23=" + m23 +
                ", m24=" + m24 +
                ", m31=" + m31 +
                ", m32=" + m32 +
                ", m33=" + m33 +
                ", m34=" + m34 +
                ", m41=" + m41 +
                ", m42=" + m42 +
                ", m43=" + m43 +
                ", m44=" + m44 +
                '}';
    }

    /**
     * Comparable Method for Interface Comparable
     *
     * @param m incoming Mat4x4-Object
     * @return int value ( 0 if all attributes are equal,
     * -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     * 1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(final Mat4x4 m) {
        if (Double.compare(this.m11, m.m11) != 0) return (int) Math.signum(this.m11 - m.m11);
        if (Double.compare(this.m12, m.m12) != 0) return (int) Math.signum(this.m12 - m.m12);
        if (Double.compare(this.m13, m.m13) != 0) return (int) Math.signum(this.m13 - m.m13);
        if (Double.compare(this.m14, m.m14) != 0) return (int) Math.signum(this.m14 - m.m14);
        if (Double.compare(this.m21, m.m21) != 0) return (int) Math.signum(this.m21 - m.m21);
        if (Double.compare(this.m22, m.m22) != 0) return (int) Math.signum(this.m22 - m.m22);
        if (Double.compare(this.m23, m.m23) != 0) return (int) Math.signum(this.m23 - m.m23);
        if (Double.compare(this.m24, m.m24) != 0) return (int) Math.signum(this.m24 - m.m24);
        if (Double.compare(this.m31, m.m31) != 0) return (int) Math.signum(this.m31 - m.m31);
        if (Double.compare(this.m32, m.m32) != 0) return (int) Math.signum(this.m32 - m.m32);
        if (Double.compare(this.m33, m.m33) != 0) return (int) Math.signum(this.m33 - m.m33);
        if (Double.compare(this.m34, m.m34) != 0) return (int) Math.signum(this.m34 - m.m34);
        if (Double.compare(this.m41, m.m41) != 0) return (int) Math.signum(this.m41 - m.m41);
        if (Double.compare(this.m42, m.m42) != 0) return (int) Math.signum(this.m42 - m.m42);
        if (Double.compare(this.m43, m.m43) != 0) return (int) Math.signum(this.m43 - m.m43);
        if (Double.compare(this.m44, m.m44) != 0) return (int) Math.signum(this.m44 - m.m44);
        return 0;
    }
}

