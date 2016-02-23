package material;

import color.Color;
import geometry.Hit;
import world.World;

/**
 * Class represents a Material
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe3 2014-11-25
 */
public abstract class Material implements Comparable<Material> {

    /**
     * calculates the color of hit point
     *
     * @param hit   the hit with an object
     * @param world the world is needed to find the lights
     * @return the color for a hit point
     */
    public abstract Color colorFor(final Hit hit, final World world);

    /**
     * calculates the color of hit point
     *
     * @param hit   the hit with an object
     * @param world the world is needed to find the lights
     * @param tracer the tracer for recursion purposes
     * @return the color for a hit point
     */
    public abstract Color colorFor(final Hit hit, final World world, final Tracer tracer);

}
