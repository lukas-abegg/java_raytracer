package light;

import color.Color;
import world.World;
import mathlib.Point3;
import mathlib.Vector3;

/**
 * Class Light
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe 3 2014-11-25
 */
public abstract class Light implements Comparable<Light> {

    /**
     * Color object of the light
     */
    public final Color color;
    public final boolean castShadows;

    /**
     * constructor for instantiate a new Light
     *
     * @param color of the light
     */
    public Light(final Color color, final boolean castShadows) {
        this.color = color;
        this.castShadows = castShadows;
    }

    /**
     * this method generates a ray from postion and point of light(position)
     *
     * @param position point of intersection on groundfloor(point of view)
     * @param world to check if light is between object and world
     * @return boolean if there is a hit or not
     */
    public abstract boolean illuminates(final Point3 position, final World world);

    public abstract Vector3 directionFrom(final Point3 position);

    /**
     * Method builds an evenly distributed hash value for the Light
     *
     * @return new hash code as int
     */
    @Override
    public String toString() {
        return "color: \n" +
                color.toString();
    }


    /**
     * Method builds an evenly distributed hash value for the Light instance
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode() {
        return this.color.hashCode();
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
        return this.color.equals(light.color);
    }
}
