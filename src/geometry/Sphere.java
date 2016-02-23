package geometry;

import material.Material;
import ray.Ray;
import texture.TexCoord2;
import mathlib.Normal3;
import mathlib.Point3;
import mathlib.Vector3;

/**
 * Class represents a 3-dimensional Sphere (ball)
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe2 2014-11-25
 */
public class Sphere extends Geometry {

    public final Point3 c;
    public final double r;

    /**
     * Constructor for Sphere
     *
     * @param material Material of this Sphere      (Material)
     */
    public Sphere(final Material material) {
        super(material);
        this.c = new Point3(0,0,0);
        this.r = 1;
    }

    /**
     * Alternative Constructor for Sphere
     *
     * @param c center (Point3)
     * @param r radius (double)
     * @param material Material of this Sphere      (Material)
     */
    public Sphere( final Point3 c, final double r, final Material material) {
        super(material);
        this.c = c;
        this.r = r;
    }

    /**
     * Method returns a result of an intersection calculation with a Ray-instance
     *
     * @param ray the Ray to calculate the intersections with this Sphere
     * @return the intersections of Ray and this Sphere (returns the closest intersection if there are more than one, null if there are none)
     */
    @Override
    public final Hit hit(final Ray ray) {
        double a = ray.d.dot(ray.d);
        double b = ray.d.dot((ray.o.sub(this.c)).mul(2));
        double cN = ((ray.o.sub(this.c)).dot(ray.o.sub(this.c))) - (Math.pow(r, 2));
        double d = (Math.pow(b, 2)) - (4 * a * cN);

        double t = super.EPSILON;

        if (d < super.EPSILON) {
            return null;
        } else if (d == super.EPSILON) {
            t = (-b) / (2 * a);
        } else {
            double t1 = ((-b) + Math.sqrt(d)) / (2 * a);
            double t2 = ((-b) - Math.sqrt(d)) / (2 * a);

            if (t1 >= super.EPSILON && t2 >= super.EPSILON) {
                if (t1 < t2) {
                    t = t1;
                } else {
                    t = t2;
                }
            } else if (t1 >= super.EPSILON) {
                t = t1;
            } else if (t2 >= super.EPSILON) {
                t = t2;
            }
        }

        if (t > super.EPSILON) {
            final Normal3 n = ray.at(t).sub(this.c).normalized().asNormal();
            return new Hit(t, ray, this, n, getTexCoord(ray.at(t)));
        }
        return null;
    }

    /**
     * calculate texture coordinates in 2D
     *
     * @param p3 hit point
     * @return new texture coordinates
     */
    private TexCoord2 getTexCoord(final Point3 p3) {

        final double theta = Math.acos(p3.y);
        final double phi = Math.atan2(p3.x, p3.z);
        //System.out.println( p3.toString() + " -> "  + new TexCoord2(phi / (Math.PI * 2), -(theta / Math.PI))  );
        //System.out.println( new TexCoord2(phi / (Math.PI * 2), -(theta / Math.PI)) );
        return new TexCoord2(phi / (Math.PI * 2) , -(theta / Math.PI) );
    }

    /**
     * shows the Plane as String
     *
     * @return a String with Color color, Point3 c and double r representations of this Sphere
     */
    @Override
    public String toString() {
        return "Sphere instance:\n" +
                super.toString() +
                "\ncentral point c: \n" + c.toString() +
                "\nradius r: \n" + r;
    }

    /**
     * Method builds an evenly distributed hash value for the Sphere instance
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode() {
        int result;
        long hash;

        result = c.hashCode();
        hash = Double.doubleToLongBits(r);
        result = 31 * result + (int) (hash ^ (hash >>> 32));
        result = 31 * result + super.hashCode();

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
        if (o == null || !(o instanceof Sphere)) return false;

        Sphere sphere = (Sphere) o;
        return (super.equals(sphere))
                && (this.c.equals(sphere.c))
                && (Double.compare(sphere.r, r) == 0);
    }

    /**
     * Comparable Method for Interface Comparable
     *
     * @param geo incoming Geometry-Object
     * @return int value ( 0 if all attributes are equal,
     * -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     * 1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */
    @Override
    public int compareTo(final Geometry geo) {
        Sphere sphere = (Sphere) geo;
        if (!(this.material.equals(geo.material))) return super.compareTo(geo);
        if (!(this.c.equals(sphere.c))) return this.c.compareTo(sphere.c);
        if ((Double.compare(this.r, sphere.r) != 0)) return (int) Math.signum(this.r - sphere.r);
        return 0;
    }
}
