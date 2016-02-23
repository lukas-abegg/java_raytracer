package geometry;

import material.Material;
import ray.Ray;

/**
 * abstract object geometry
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe2 2014-11-25
 */
public abstract class Geometry implements Comparable<Geometry>{

    public final Material material;

    /**
     * threshold for the edge detection
     */
    public static final double EPSILON = 0.0001;

    /**
     * constructor creates an instance of Geometry
     *
     * @param material the Material of Geometry
     */
    public Geometry(final Material material) {
        this.material = material;
    }

    /**
     * Method calculates the intersection
     *
     * @param r is the ray
     * @return a new Hit instance, or null if no intersection
     */
    public abstract Hit hit(final Ray r);


    /**
     * Method builds an evenly distributed hash value for the Geometry
     *
     * @return new hash code as int
     */
    @Override
    public String toString() {
        return "material: \n" +
                material.toString();
    }

    /**
     * Method builds an evenly distributed hash value for the Geometry instance
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode(){
        return this.material.hashCode();
    }

    /**
     * Overridden equals method: indicates whether the values of the given Object are the same or not
     *
     * @param o representing the Object to compare with
     * @return boolean value of the result
     */
    @Override
    public boolean equals(Object o){
        if (o == null || !(o instanceof Geometry)) return false;

        Geometry geo = (Geometry) o;
        return this.material.equals(geo.material);
    }

    /**
     * Comparable Method for Interface Comparable
     * @param geo incoming Geometry-Object
     * @return  int value ( 0 if all attributes are equal,
     *                    -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     *                     1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(final Geometry geo) {
        return this.material.compareTo(geo.material);
    }
}
