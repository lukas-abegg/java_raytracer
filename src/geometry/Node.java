package geometry;

import material.Material;
import mathlib.Transform;
import ray.Ray;

import java.util.List;


/**
 * Class represents a 3-dimensional plane
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe2 2014-11-25
 */
public class Node extends Geometry {

    public final Transform transform;
    public final List<Geometry> geoList;

    /**
     * Constructor for Node
     *
     * @param transform    transformation object
     * @param geoList list of geometries
     */
    public Node(final List<Geometry> geoList, final Transform transform) {
        super(null);
        this.transform = transform;
        this.geoList = geoList;
    }

    /**
     * Method is checking if there is a hit between transformed ray and the geometries of the list
     *
     * @param ray the Ray to calculate the intersections with this Plane
     * @return the intersections of Ray and this Plane (returns the closest intersection if there are more than one, null if there are none)
     */
    @Override
    public Hit hit(Ray ray) {
        Ray transRay = transform.mul(ray);
        Hit minimalHit = null;
        Double hitDistance = Double.MAX_VALUE;

        for (Geometry geo : geoList) {
            Hit hit = geo.hit(transRay);
            if (hit == null) continue;
            if ((hit.t < hitDistance && hit.t > Geometry.EPSILON)) {
                    hitDistance = hit.t;
                    minimalHit = hit;

            }
        }
        if (minimalHit != null) {
            return new Hit(minimalHit.t, ray, minimalHit.geo, transform.mul(minimalHit.n), minimalHit.texCoord2);
        }
        return null;
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
        Node node = (Node) geo;
        if (!(this.transform.equals(node.transform))) return this.transform.compareTo(node.transform);
        if (!(this.geoList.equals(node.geoList))) return 1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        if (!super.equals(o)) return false;

        Node node = (Node) o;

        if (geoList != null ? !geoList.equals(node.geoList) : node.geoList != null) return false;
        if (transform != null ? !transform.equals(node.transform) : node.transform != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (transform != null ? transform.hashCode() : 0);
        result = 31 * result + (geoList != null ? geoList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Node{" +
                "transform=" + transform +
                ", geoList=" + geoList +
                '}';
    }
}
