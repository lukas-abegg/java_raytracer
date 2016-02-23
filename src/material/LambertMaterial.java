package material;

import color.Color;
import geometry.Hit;
import texture.Texture;
import world.World;
import mathlib.Normal3;
import mathlib.Point3;
import light.Light;
import mathlib.Vector3;

/**
 * Class represents a Material
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe3 2014-11-25
 */
public class LambertMaterial extends Material {
    /**
     * the Color attribute
     */
    public final Texture texture;

    /**
     * creates an instance of a lambert material
     *
     * @param texture the texture of the material
     */
    public LambertMaterial(final Texture texture) {
        this.texture = texture;
    }

    /**
     * calculates the color of hit point
     *
     * @param hit   the hit with an object
     * @param world the world is needed to find the lights
     * @return the color for a hit point
     */
    @Override
    public Color colorFor(final Hit hit, final World world) {
        final Normal3 hitN = hit.n;
        Color c = this.texture.getColor(hit.texCoord2.u, hit.texCoord2.v).mul(world.ambientLight);
        Point3 hitPoint = hit.ray.at(hit.t);

        for (Light light : world.lights) {

            if (light.illuminates(hitPoint, world)) {
                Vector3 l = light.directionFrom(hitPoint);
                double max = Math.max(0.0, l.dot(hitN));

                c = c.add(this.texture.getColor(hit.texCoord2.u, hit.texCoord2.v).mul(light.color).mul(max));
            }
        }
        return c;
    }

    @Override
    public Color colorFor(Hit hit, World world, Tracer tracer) {
        return null;
    }

    /**
     * Method builds an evenly distributed hash value for the Material
     *
     * @return new hash code as int
     */
    @Override
    public String toString() {
        return "texture: \n" +
                this.texture.toString();
    }

    /**
     * Method builds an evenly distributed hash value for the Material instance
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode() {
        return this.texture.hashCode();
    }

    /**
     * Overridden equals method: indicates whether the values of the given Object are the same or not
     *
     * @param o representing the Object to compare with
     * @return boolean value of the result
     */
    @Override
    public boolean equals(final Object o) {
        if (o == null || !(o instanceof LambertMaterial)) return false;

        LambertMaterial material = (LambertMaterial) o;
        return this.texture.equals(material.texture);
    }

    /**
     * Comparable Method for Interface Comparable
     *
     * @param m incoming object
     * @return int value ( 0 if all attributes are equal,
     * -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     * 1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(final Material m) {
        LambertMaterial sm = (LambertMaterial) m;
        return this.texture.compareTo(sm.texture);
    }
}
