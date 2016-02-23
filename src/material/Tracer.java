package material;

import color.Color;
import geometry.Hit;
import ray.Ray;
import world.World;

/**
 * Created by Me on 16.12.2014.
 */
public class Tracer {
    private final World world;
    private final int recursionDepth;

    public Tracer(final World world, final int recursionDepth) {
        this.world = world;
        this.recursionDepth = recursionDepth;
    }

    public Color colorFor(final Ray r) {
        if (recursionDepth < 0) {
            return World.BACKGROUND_COLOR;
        } else {
            Hit hit = world.hit(r);
            if (hit != null) {
                final Color color = hit.geo.material.colorFor(hit, this.world, new Tracer(this.world, this.recursionDepth - 1));

                if (color != null) {
                    return color;
                } else {
                    return World.BACKGROUND_COLOR;

                }
            } else {
                return World.BACKGROUND_COLOR;
            }
        }
    }

    /**
     * Overridden toString Method
     *
     * @return toString string
     */
    @Override
    public String toString() {
        return "Tracer Instance:\n"
                + "World: \n" + this.world.toString()
                + "\nrecursionDepth: \n" + this.recursionDepth;
    }

    /**
     * Method builds an evenly distributed hash value for the Tracer instance
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode() {
        int result;
        long hash;

        hash = Double.doubleToLongBits(recursionDepth);
        result = (int) (hash ^ (hash >>> 32));
        result = 31 * result + this.world.hashCode();

        return result;
    }

    /**
     * Overridden equals method: indicates whether the values of the given Object are the same or not
     *
     * @param o representing the Object to compare with
     * @return boolean value of the result
     */
    @Override
    public boolean equals(final Object o) {
        if (o == null || !(o instanceof Tracer)) return false;
        final Tracer tr = (Tracer) o;
        return this.world.equals(tr.world)
                && this.recursionDepth == tr.recursionDepth;
    }
}
