package mathlib;

/**
 * Mat3x3 includes all mathematical functions for
 * 3x3 Matrices
 *
 * @author group raspi, CG1, Beuth-Hochschule
 * @version 1.0
 */
public final class Mat3x3 implements Comparable<Mat3x3>{
    public final double m11, m12, m13, m21, m22, m23, m31, m32, m33, determinant;

    /**
     * constructor creates a matrix with a determinant
     *
     * @param m11         is in column 1 and row 1 of Mat3x3
     * @param m12         is in column 1 and row 2 of Mat3x3
     * @param m13         is in column 1 and row 3 of Mat3x3
     * @param m21         is in column 2 and row 1 of Mat3x3
     * @param m22         is in column 2 and row 2 of Mat3x3
     * @param m23         is in column 2 and row 3 of Mat3x3
     * @param m31         is in column 3 and row 1 of Mat3x3
     * @param m32         is in column 3 and row 2 of Mat3x3
     * @param m33         is in column 3 and row 3 of Mat3x3
     * @param determinant is the matrix determinant
     */
    public Mat3x3(final double m11, final double m12, final double m13, final double m21, final double m22, final double m23, final double m31, final double m32, final double m33, final double determinant) {
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
        this.determinant = determinant;
    }

    /**
     * constructor creates a matrix without a determinant as a parameter
     *
     * @param m11 is in column 1 and row 1 of Mat3x3
     * @param m12 is in column 1 and row 2 of Mat3x3
     * @param m13 is in column 1 and row 3 of Mat3x3
     * @param m21 is in column 2 and row 1 of Mat3x3
     * @param m22 is in column 2 and row 2 of Mat3x3
     * @param m23 is in column 2 and row 3 of Mat3x3
     * @param m31 is in column 3 and row 1 of Mat3x3
     * @param m32 is in column 3 and row 2 of Mat3x3
     * @param m33 is in column 3 and row 3 of Mat3x3
     */
    public Mat3x3(final double m11, final double m12, final double m13, final double m21, final double m22, final double m23, final double m31, final double m32, final double m33) {
        this.m11 = m11;
        this.m12 = m12;
        this.m13 = m13;
        this.m21 = m21;
        this.m22 = m22;
        this.m23 = m23;
        this.m31 = m31;
        this.m32 = m32;
        this.m33 = m33;
        this.determinant = (m11 * m22 * m33) + (m12 * m23 * m31) + (m13 * m21 * m32) - (m31 * m22 * m13) - (m32 * m23 * m11) - (m33 * m21 * m12);
    }

    /**
     * multiplication of a matrix with a matrix
     *
     * @param m is a 3x3 matrix
     * @return new 3x3 matrix
     */
    public Mat3x3 mul(final Mat3x3 m) {

        double a11 = m11 * m.m11 + m12 * m.m21 + m13 * m.m31;
        double a12 = m11 * m.m12 + m12 * m.m22 + m13 * m.m32;
        double a13 = m11 * m.m13 + m12 * m.m23 + m13 * m.m33;

        double a21 = m21 * m.m11 + m22 * m.m21 + m23 * m.m31;
        double a22 = m21 * m.m12 + m22 * m.m22 + m23 * m.m32;
        double a23 = m21 * m.m13 + m22 * m.m23 + m23 * m.m33;

        double a31 = m31 * m.m11 + m32 * m.m21 + m33 * m.m31;
        double a32 = m31 * m.m12 + m32 * m.m22 + m33 * m.m32;
        double a33 = m31 * m.m13 + m32 * m.m23 + m33 * m.m33;

        return new Mat3x3(a11, a12, a13, a21, a22, a23, a31, a32, a33);
    }

    /**
     * multiplication of a matrix with a vector
     *
     * @param v Vector3 instance to be added
     * @return new Vector3 instance
     */
    public Vector3 mul(Vector3 v) {
        final double x = m11 * v.x + m12 * v.x + m13 * v.x;
        final double y = m21 * v.y + m22 * v.y + m23 * v.y;
        final double z = m31 * v.z + m32 * v.z + m33 * v.z;
        return new Vector3(x, y, z);

    }

    /**
     * multiplication of a matrix with a point
     *
     * @param p Point3 instance to be added
     * @return new Point3 instance
     */
    public Point3 mul(Point3 p) {
        final double x = m11 * p.x + m12 * p.x + m13 * p.x;
        final double y = m21 * p.y + m22 * p.y + m23 * p.y;
        final double z = m31 * p.z + m32 * p.z + m33 * p.z;
        return new Point3(x, y, z);
    }

    /**
     * replaces the values of the first column
     *
     * @param v Vector3 instance to be added
     * @return new Mat3x3 instance
     */
    public Mat3x3 changeCol1(Vector3 v) {
        final double a11 = v.x;
        final double a21 = v.y;
        final double a31 = v.z;
        return new Mat3x3(a11, m12, m13, a21, m22, m23, a31, m32, m33);
    }

    /**
     * replaces the values of the second column
     *
     * @param v Vector3 instance to be added
     * @return new Mat3x3 instance
     */
    public Mat3x3 changeCol2(Vector3 v) {
        final double a12 = v.x;
        final double a22 = v.y;
        final double a32 = v.z;
        return new Mat3x3(m11, a12, m13, m21, a22, m23, m31, a32, m33);
    }

    /**
     * replaces the values of the third column
     *
     * @param v Vector3 instance to be added
     * @return new Mat3x3 instance
     */
    public Mat3x3 changeCol3(Vector3 v) {
        final double a13 = v.x;
        final double a23 = v.y;
        final double a33 = v.z;
        return new Mat3x3(m11, m12, a13, m21, m22, a23, m31, m32, a33);
    }

    /**
     * shows all components of the Mat3x3 as a String
     *
     * @return a string with all components of the Mat3x3 instance
     */
    @Override
    public String toString() {
        return "Mat3x3\n"
                + "m11: " + m11 + " m12: " + m12 + " m13: " + m13 + "\n"
                + "m21: " + m21 + " m22: " + m22 + " m23: " + m23 + "\n"
                + "m31: " + m31 + " m32: " + m32 + " m33: " + m33 + "\n"
                + "Determinante: " + determinant;
    }

    /**
     * builds the hash code for the Mat3x3 instance
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode() {
        int result;
        long hash;

        hash = Double.doubleToLongBits(m11);
        result = (int) (hash ^ (hash >>> 32));
        hash = Double.doubleToLongBits(m12);
        result = 31 * result + (int) (hash ^ (hash >>> 32));
        hash = Double.doubleToLongBits(m13);
        result = 31 * result + (int) (hash ^ (hash >>> 32));
        hash = Double.doubleToLongBits(m21);
        result = 31 * result + (int) (hash ^ (hash >>> 32));
        hash = Double.doubleToLongBits(m22);
        result = 31 * result + (int) (hash ^ (hash >>> 32));
        hash = Double.doubleToLongBits(m23);
        result = 31 * result + (int) (hash ^ (hash >>> 32));
        hash = Double.doubleToLongBits(m31);
        result = 31 * result + (int) (hash ^ (hash >>> 32));
        hash = Double.doubleToLongBits(m32);
        result = 31 * result + (int) (hash ^ (hash >>> 32));
        hash = Double.doubleToLongBits(m33);
        result = 31 * result + (int) (hash ^ (hash >>> 32));
        return result;
    }

    /**
     * compares two matrices object for equality
     *
     * @param o comparable object for checking equality
     * @return boolean, if object o is equal to instance object or not?
     */
    @Override
    public boolean equals(final Object o) {
        if (o == null || !(o instanceof Mat3x3)) return false;

        Mat3x3 mat3x3 = (Mat3x3) o;
        return (Double.compare(mat3x3.m11, this.m11) == 0)
            && (Double.compare(mat3x3.m12, this.m12) == 0)
            && (Double.compare(mat3x3.m13, this.m13) == 0)
            && (Double.compare(mat3x3.m21, this.m21) == 0)
            && (Double.compare(mat3x3.m22, this.m22) == 0)
            && (Double.compare(mat3x3.m23, this.m23) == 0)
            && (Double.compare(mat3x3.m31, this.m31) == 0)
            && (Double.compare(mat3x3.m32, this.m32) == 0)
            && (Double.compare(mat3x3.m33, this.m33) == 0)
            && (Double.compare(mat3x3.determinant, determinant) == 0);
    }

    /**
     * Comparable Method for Interface Comparable
     * @param m incoming Mat3x3-Object
     * @return  int value ( 0 if all attributes are equal,
     *                    -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     *                     1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(final Mat3x3 m) {
        if (Double.compare(this.m11, m.m11) != 0) return (int) Math.signum(this.m11 - m.m11);
        if (Double.compare(this.m12, m.m12) != 0) return (int) Math.signum(this.m12 - m.m12);
        if (Double.compare(this.m13, m.m13) != 0) return (int) Math.signum(this.m13 - m.m13);
        if (Double.compare(this.m21, m.m21) != 0) return (int) Math.signum(this.m21 - m.m21);
        if (Double.compare(this.m22, m.m22) != 0) return (int) Math.signum(this.m22 - m.m22);
        if (Double.compare(this.m23, m.m23) != 0) return (int) Math.signum(this.m23 - m.m23);
        if (Double.compare(this.m31, m.m31) != 0) return (int) Math.signum(this.m31 - m.m31);
        if (Double.compare(this.m32, m.m32) != 0) return (int) Math.signum(this.m32 - m.m32);
        if (Double.compare(this.m33, m.m33) != 0) return (int) Math.signum(this.m33 - m.m33);
        if (Double.compare(this.determinant, m.determinant) != 0) return (int) Math.signum(this.determinant - m.determinant);
        return 0;
    }
}

