package ray;

import mathlib.Point3;
import mathlib.Vector3;

/**
 * Ray describes a ray with it´s origin and direction
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe2 2014-11-25
 */
public class Ray implements Comparable<Ray>{
    public final Point3 o;
    public final Vector3 d;

    /**
     * constructor creates a ray with a point and a vector
     *
     * @param o the origin of the ray
     * @param d is the direction of the ray
     */
    public Ray(final Point3 o, final Vector3 d) {
        this.o = o;
        this.d = d.normalized();
    }

    /**
     * returns a point which lies on the parametrical line
     *
     * @param t is factor for length
     * @return new Point3 instance
     */
    public Point3 at(final double t) {
        Vector3 v = d.mul(t);
        return o.add(v);
    }

    /**
     * returns the distance of a point on the ray
     *
     * @param o is a point on the ray
     * @return the length
     */
    public double tOf(final Point3 o) {
        return o.sub(o).magnitude / this.d.magnitude;
    }

    @Override
    public String toString() {
        return "Ray instance: \n" +
                "o: " + o.toString() + "\n" +
                "d: " + d.toString();
    }

    /**
     * Overridden hashCode Method builds the hash code for the Ray instance
     *
     * @return an int value representing the hashCode of the Ray
     */
    @Override
    public int hashCode() {
        int result;

        result = o.hashCode();
        result = 31 * result + d.hashCode();
        return result;
    }

    /**
     * Overridden equals method: indicates whether the values of the given Object are the same or not
     *
     * @param o representing the Object to compare with
     * @return boolean value of the result
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Ray)) return false;

        Ray ray = (Ray) o;
        return (this.o.equals(ray.o))
            && (this.d.equals(ray.d));
    }

    /**
     * Comparable Method for Interface Comparable
     * @param r incoming Geometry-Object
     * @return  int value ( 0 if all attributes are equal,
     *                    -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     *                     1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(final Ray r) {
        if ( !(this.o.equals(r.o)) ) return this.o.compareTo(r.o);
        if ( !(this.d.equals(r.d)) ) return this.d.compareTo(r.d);
        return 0;
    }

}




