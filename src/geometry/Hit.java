package geometry;

import ray.Ray;
import texture.TexCoord2;
import mathlib.Normal3;

/**
 * Class represents a intersection of a Geometry
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe2 2014-11-25
 */
public class Hit implements Comparable<Hit>{

    public final double t;
    public final Ray ray;
    public final Geometry geo;
    public final Normal3 n;
    public final TexCoord2 texCoord2;

    /**
     * constructor creates a hit between ray and geometry
     *
     * @param t   the length where the ray intersects    (double)
     * @param ray ray to calculate the intersection from (Vector3)
     * @param geo geo to calculate the intersection from (Geometry)
     * @param n   normal to calculate the intersection from (Normal)
     * @param texCoord2 texture coordinates to calculate the intersection from (TexCoord2)
     */
    public Hit(final double t, final Ray ray, final Geometry geo, final Normal3 n, final TexCoord2 texCoord2) {
        this.t   = t;
        this.ray = ray;
        this.geo = geo;
        this.n = n;
        this.texCoord2 = texCoord2;
    }

    /**
     * shows the Hit instance as String
     *
     * @return a String with double t, Ray ray and Geometry geo representations of this Hit
     */
    @Override
    public String toString(){
        return  "Hit instance: "                  +
                "\ndouble t: "   + t              +
                "\nRay ray: "    + ray.toString() +
                "\nGeometry geo: " + geo.toString() +
                "\nNormal n: " +     n.toString() +
                "\nTexCoord2 texCoord2: " + texCoord2.toString();

    }

    /**
     * Method builds an evenly distributed hash value for the Hit instance
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode(){
        int result;
        long hash;

        hash = Double.doubleToLongBits(t);
        result = (int) ( hash ^ ( hash >>> 32 ) );
        result = 31 * result + ray.hashCode();
        result = 31 * result + geo.hashCode();
        result = 31 * result + n.hashCode();
        result = 31 * result + texCoord2.hashCode();
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
        if (o == null || !(o instanceof Hit)) return false;

        Hit hit = (Hit) o;
        return (Double.compare(hit.t, this.t) == 0)
            && (this.ray.equals(hit.ray))
            && (this.geo.equals(hit.geo))
            && (this.n.equals(hit.n))
            && (this.texCoord2.equals(hit.texCoord2));
    }

    /**
     * Comparable Method for Interface Comparable
     * @param h incoming Hit-Object
     * @return  int value ( 0 if all attributes are equal,
     *                    -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     *                     1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(final Hit h) {
        if ( Double.compare(this.t, h.t)!=0 ) return (int) Math.signum(this.t - h.t);
        if ( !(this.ray.equals(h.ray)) ) return this.ray.compareTo(h.ray);
        if ( !(this.geo.equals(h.geo)) ) return this.geo.compareTo(h.geo);
        if ( !(this.texCoord2.equals(h.texCoord2)) ) return this.texCoord2.compareTo(h.texCoord2);
        return 0;
    }
}
