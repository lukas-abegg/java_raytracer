package light;

import color.Color;
import geometry.Hit;
import ray.Ray;
import world.World;
import mathlib.Point3;
import mathlib.Vector3;

/**
 * Class SpotLight
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe 3 2014-11-25
 */
public class SpotLight extends Light {

    /**
     * direction of light(spot is a pointed light)
     */
    public final Vector3 direction;
    /**
     * position of the sun
     */
    public final Point3 position;
    /**
     * open lense angle of spotlight
     */
    public double halfAngle;

    /**
     * constructor instanciates a Spotlight
     *
     * @param color     color of the light
     * @param position  position of the light
     * @param direction direction of the light
     * @param halfAngle open lense angle of the light
     * @param castShadows
     */
    public SpotLight(final Color color, final Point3 position, final Vector3 direction, final double halfAngle, final boolean castShadows) {
        super(color, castShadows);
        this.position = position;
        this.direction = direction;
        this.halfAngle = halfAngle;
    }

    /**
     * this method generates a ray from postion and point of light(position)
     *
     * @param point point of intersection on groundfloor(point of view)
     * @param world to check if light is between object and world
     * @return boolean if there is a hit or not
     */
    @Override
    public boolean illuminates(final Point3 point, final World world) {
            // calculate length of vector
            double t = (this.position.sub(point).magnitude) / (directionFrom(point).magnitude);

            // initiate ray and get hits
            Ray ray = new Ray(point, directionFrom(point));
            Hit hit = world.hit(ray);

            // check if angle is smaller than input angle from geometry
            if (Math.asin(point.sub(position).normalized().x(direction).magnitude) <= (halfAngle * 1.5)) {
                if(!this.castShadows){
                    return true;
                }
                if (hit != null) {
                    // check if hit is beyond the light
                    if (hit.t < t) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            } else {
                return false;
            }
    }

    /**
     * calculates the vector where the light comes from
     *
     * @param point point the point to check the direction of light from
     * @return vector which points to source of light
     */
    @Override
    public Vector3 directionFrom(final Point3 point) {
        return this.position.sub(point).normalized();
    }

    /**
     * Method builds an evenly distributed hash value for the Light
     *
     * @return new hash code as int
     */
    @Override
    public String toString() {
        return "color: \n" +
                color.toString() +
                "\nposition of light: \n" + this.position.toString() +
                "\ndirection of light: \n" + this.direction.toString() +
                "\nhalf angle: \n" + this.halfAngle;
    }

    /**
     * Method builds an evenly distributed hash value for the Light instance
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode() {
        int result;
        long hash;

        hash = Double.doubleToLongBits(this.halfAngle);
        result = (int) (hash ^ (hash >>> 32));
        result = 31 * result + this.position.hashCode();
        result = 31 * result + this.direction.hashCode();
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
        if (o == null || this == o || o.getClass() != this.getClass()) return false;

        Light light = (Light) o;
        SpotLight spotLight = (SpotLight) o;

        if (!super.equals(light)) return false;
        if (!this.direction.equals(spotLight.direction)) return false;
        if (Double.compare(this.halfAngle, spotLight.halfAngle) != 0) return false;
        return this.position.equals(spotLight.position);
    }

    /**
     * Comparable Method for Interface Comparable
     *
     * @param l incoming object
     * @return int value ( 0 if all attributes are equal,
     * -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     * 1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(final Light l) {
        SpotLight sLight = (SpotLight) l;
        if ( !(this.direction.equals(sLight.direction))) return this.direction.compareTo(sLight.direction);
        if ( !(this.position.equals(sLight.position))) return this.position.compareTo(sLight.position);
        if ( Double.compare(this.halfAngle,sLight.halfAngle) != 0) return Double.compare(this.halfAngle, sLight.halfAngle);
        return 0;
    }
}
