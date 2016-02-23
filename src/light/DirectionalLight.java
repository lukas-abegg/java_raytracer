package light;

import color.Color;
import geometry.Hit;
import ray.Ray;
import world.World;
import mathlib.Point3;
import mathlib.Vector3;

/**
 * Class DirectionalLight creates directional light
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe 3 2014-11-25
 */
public class DirectionalLight extends Light {

    /**
     * direction of Light
     */
    public final Vector3 direction;

    public DirectionalLight(final Color color, final Vector3 direction, final boolean castShadows) {
        super(color, castShadows);
        this.direction = direction;
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
        if (this.castShadows) {
            // initiate ray and get hits
            Ray ray = new Ray(point, directionFrom(point));
            Hit hit = world.hit(ray);

            // if there is not hit, world is illuminated, else there is a shadow
            if (hit == null) {
                return true;
            } else {
                return false;
            }
        } else {
            // return false and do nothing if there is no shadow
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
        return this.direction.mul(-1).normalized();
    }

    /**
     * Method builds an evenly distributed hash value for the Light
     *
     * @return new hash code as int
     */
    @Override
    public String toString() {
        return "color: \n" +
                this.color.toString() +
                "\ndirection of light a: \n" + this.direction.toString();
    }

    /**
     * Method builds an evenly distributed hash value for the Light instance
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode() {
        int result;

        result = this.direction.hashCode();
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
        DirectionalLight dirLight = (DirectionalLight) o;

        return super.equals(light) && this.direction.equals(dirLight.direction);
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
        DirectionalLight dLight = (DirectionalLight) l;
        return this.direction.compareTo(dLight.direction);
    }
}
