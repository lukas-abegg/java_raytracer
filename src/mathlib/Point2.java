package mathlib;

import com.sun.istack.internal.NotNull;

/**
 * Point2 includes all mathematical functions for
 * 3 point objects
 *
 * @author group raspi, CG1, Beuth-Hochschule
 * @version 1.0
**/
public final class Point2 implements Comparable<Point2>{
    public final double x,y;

    /**
     * Constructor creates a new Point2 instance with it´s coordinates x, y
     *
     * @param x representing the x-coordinate of the new Point2 instance
     * @param y representing the y-coordinate of the new Point2 instance
     */
    public Point2(final double x, final double y){

        this.x=x;
        this.y=y;
    }

    /**
     * shows the point as a string
     *
     * @return a string with the x, y, z coordinates of a point
     */
    @Override
    public String toString() {
        return "Point2{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * builds the hash code for the Point2 instance
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * compares two Point2 objects for equality
     *
     * @param o comparable object for checking equality
     * @return boolean, if object o is equal to instance object or not?
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point2)) return false;

        Point2 point2 = (Point2) o;

        if (Double.compare(point2.x, x) != 0) return false;
        if (Double.compare(point2.y, y) != 0) return false;

        return true;
    }

    /**
     * Comparable Method for Interface Comparable
     * @param p incoming Point2-Object
     * @return  int value ( 0 if all attributes are equal,
     *                    -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     *                     1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */

    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(@NotNull final Point2 p) {
        if (Double.compare(this.x, p.x) != 0) return (int) Math.signum(this.x - p.x);
        if (Double.compare(this.y, p.y) != 0) return (int) Math.signum(this.y - p.y);
        return 0;
    }
}