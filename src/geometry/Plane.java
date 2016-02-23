package geometry;

import material.Material;
import ray.Ray;
import texture.TexCoord2;
import mathlib.Normal3;
import mathlib.Point3;


/**
 * Class represents a 3-dimensional plane
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe2 2014-11-25
 */
public class Plane extends Geometry {

    public final Point3 a;
    public final Normal3 n;

    /**
     * Constructor for Plane
     *
     * @param material  Material of this Plane (Material)
     */
    public Plane(final Material material) {
        super(material);
        this.a = new Point3(0,0,0);
        this.n = new Normal3(0,1,0);

    }

    /**
     * Constructor for Plane
     *
     * @param material  Material of this Plane (Material)
     */
    public Plane( final Point3 a, final Normal3 n, final Material material) {
        super(material);
        this.a = a;
        this.n = n;

    }

    /**
     * Method returns a result of an intersection calculation with a Ray-instance
     *
     * @param ray the Ray to calculate the intersections with this Plane
     * @return the intersections of Ray and this Plane (returns the closest intersection if there are more than one, null if there are none)
     */
    @Override
    public Hit hit(Ray ray) {
        try {
            double t = this.a.sub(ray.o).dot(this.n) / ray.d.dot(n);
            if (t >= super.EPSILON) {
                return new Hit(t, ray, this, n, new TexCoord2(ray.at(t).x, -(ray.at(t).z)));
            }
            return null;
        } catch (ArithmeticException ae) {
            // Division by zero ray located parallel to or onto this Plane
            return null;
        }
    }

    /**
     * shows the Plane as String
     *
     * @return a String with Color color, Point3 a and Normal3 n representations of this Plane
     */
    @Override
    public String toString() {
        return "Plane instance:\n" +
                super.toString() +
                "\npoint on Plane a: \n" + a.toString() +
                "\nnormalized vector n: \n" + n.toString();
    }

    /**
     * Method builds an evenly distributed hash value for the Sphere instance
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode() {
        int result;

        result = n.hashCode();
        result = 31 * result + a.hashCode();
        result = 31 * result + super.hashCode();

        return result;
    }

    /**
     * Overridden equals Method: indicates whether the values of the given Object are the same or not
     *
     * @param o representing the Object to compare with
     * @return boolean value of the result
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Plane)) return false;

        Plane plane = (Plane) o;
        return (super.equals(plane))
            && (this.a.equals(plane.a))
            && (this.n.equals(plane.n));
    }

    /**
     * Comparable Method for Interface Comparable
     * @param geo incoming Geometry-Object
     * @return  int value ( 0 if all attributes are equal,
     *                    -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     *                     1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */
    @Override
    public int compareTo(final Geometry geo) {
        Plane plane = (Plane) geo;
        if (!(this.material.equals(geo.material))) return super.compareTo(geo);
        if ( !(this.a.    equals(plane.a))   ) return this.a.compareTo(plane.a);
        if ( !(this.n.    equals(plane.n))   ) return this.n.compareTo(plane.n);
        return 0;
    }
}
