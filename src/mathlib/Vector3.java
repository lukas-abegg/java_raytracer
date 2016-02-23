package mathlib;

/**
 * Class represents a 3D-Vector with x-, y- and z-value
 *
 * @author group raspi, CG1, Beuth-Hochschule
 * @version 1.0
 */
public final class Vector3 implements Comparable<Vector3> {

    /**
     * x, y, z representing the coordinates of the Vector3
     */
    public final double x, y, z, magnitude;

    /**
     * Constructor instantiates a new Vector3, representing a 3D vector
     *
     * @param x representing the x-value of the new Vector3 instance
     * @param y representing the y-value of the new Vector3 instance
     * @param z representing the z-value of the new Vector3 instance
     */
    public Vector3(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.magnitude = calcMagnitude(this);
    }

    /**
     * Method calculates the magnitude of the Vector3 instance attributes x,y,z
     *
     * @param v Vector3 instance to calculation magnitude
     * @return magnitude of the Vector3 (length)
     */
    private double calcMagnitude(final Vector3 v) {
        return Math.sqrt(Math.pow(v.x, 2)
                + Math.pow(v.y, 2)
                + Math.pow(v.z, 2));
    }

    /**
     * Method adds a Vector3 to the current Vector3 instance and returns a new Vector3 instance
     *
     * @param v Vector3 instance to be added
     * @return new Vector3 instance
     */
    public Vector3 add(final Vector3 v) {
        return new Vector3(x + v.x,
                y + v.y,
                z + v.z);
    }

    /**
     * Method adds a Normal3 (normalized Vector3) to the current Vector3 instance and returns a new Vector3 instance
     *
     * @param n Normal3 instance to be added
     * @return new Vector3 instance
     */
    public Vector3 add(final Normal3 n) {
        return new Vector3(x + n.x,
                y + n.y,
                z + n.z);
    }

    /**
     * Method subtracts a Normal3 from the current Vector3 instance and returns the result as a new Vector3 instance
     *
     * @param n normalized vector
     * @return new Vector3 instance
     */
    public Vector3 sub(final Normal3 n) {
        return new Vector3(x - n.x,
                y - n.y,
                z - n.z);
    }

    /**
     * Method subtracts a Vector3 from the current Vector3 instance and returns the result as a new Vector3 instance
     *
     * @param v Vector3
     * @return new Vector3 instance
     */
    public Vector3 sub(final Vector3 v) {
        return new Vector3(x - v.x,
                y - v.y,
                z - v.z);
    }

    /**
     * Method multiplies the current Vector3 with the factor of parameter
     *
     * @param c factor to be multiplied with
     * @return new multiplied Vector3 instance
     */
    public Vector3 mul(final double c) {
        return new Vector3(x * c,
                y * c,
                z * c);
    }

    /**
     * Method calculates the dot product (scalar) of the current Vector3 instance and the parameter v
     *
     * @param v Vector3 to be multiplied
     * @return returns the calculated dot product (of type double)
     */
    public double dot(final Vector3 v) {
        return x * v.x +
                y * v.y +
                z * v.z;
    }

    /**
     * Method calculates the dot-product (scalar) of the current Vector3 instance and the parameter v
     *
     * @param n Vector3 to be multiplied
     * @return returns the calculated dot product (of type double)
     */
    public double dot(final Normal3 n) {
        return x * n.x +
                y * n.y +
                z * n.z;
    }

    /**
     * Method normalizes a Vector3 (1/magnitude of a vector v)* vector v = normalized vector vn
     *
     * @return new Vector3 instance with normalized values for x, y and z
     */
    public Vector3 normalized() {
        return mul(1 / magnitude);
    }

    /**
     * Changes Vector3 instance into Normal3 instance
     * !! Preconditional: call normalized()
     *
     * @return new Normal3 instance
     */
    public Normal3 asNormal() {
        return new Normal3(this.x, this.y, this.z);
    }

    /**
     * Method calculates a reflection of a Vector3 (3D vector) on a Normal3 (normalized vector)
     *
     * @param n Normal3 instance on which the current vector should be reflected on
     * @return new Vector3 instance with the reflected values of x, y, z
     */
    public Vector3 reflectedOn(final Normal3 n) {
        return new Vector3( 2 * this.dot(n) * n.x - this.x,
                            2 * this.dot(n) * n.y - this.y,
                            2 * this.dot(n) * n.z - this.z);
    }

    /**
     * Method calculates the cross-product of two vectors
     *
     * @param v Vector3 to be crossed with the current instance of Vector3
     * @return new Vector3 instance
     */
    public Vector3 x(final Vector3 v) {
        return new Vector3(
                y * v.z - z * v.y,
                (-x) * v.z + z * v.x,
                x * v.y - y * v.x);
    }

    /**
     * Overridden toString-method
     *
     * @return a String representing all attributes and values of the Vector3 instance
     */
    @Override
    public String toString() {
        return "Vector3 instance: \nx: " + x +
                "\ny: " + y +
                "\nz: " + z +
                "\nmagnitude: " + magnitude;
    }

    /**
     * Overridden hashCode Method builds the hash code for the Vector3 instance
     *
     * @return an int value representing the hashCode of the Vector3
     */
    @Override
    public int hashCode() {
        int result;
        long hash;

        hash = Double.doubleToLongBits(x);
        result = (int) (hash ^ (hash >>> 32));
        hash = Double.doubleToLongBits(y);
        result = 31 * result + (int) (hash ^ (hash >>> 32));
        hash = Double.doubleToLongBits(z);
        result = 31 * result + (int) (hash ^ (hash >>> 32));
        return result;
    }

    /**
     * Overridden equals method: indicates whether the values of the given Object are the same or not
     *
     * @param o representing the Object to compare with
     * @return boolean value of the result
     */
    @Override
    public boolean equals(final Object o) {
        if (o == null || !(o instanceof Vector3)) return false;
        Vector3 v = (Vector3) o;
        return (Double.compare(v.x, this.x) == 0)
                && (Double.compare(v.y, this.y) == 0)
                && (Double.compare(v.z, this.z) == 0);
    }

    /**
     * Comparable Method for Interface Comparable
     *
     * @param v incoming Vector3-Object
     * @return int value ( 0 if all attributes are equal,
     * -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     * 1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(final Vector3 v) {
        if (Double.compare(this.x, v.x) != 0) return (int) Math.signum(this.x - v.x);
        if (Double.compare(this.y, v.y) != 0) return (int) Math.signum(this.y - v.y);
        if (Double.compare(this.z, v.z) != 0) return (int) Math.signum(this.z - v.z);
        return 0;
    }
}

