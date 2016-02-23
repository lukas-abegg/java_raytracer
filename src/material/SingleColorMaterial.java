package material;

import color.Color;
import geometry.Hit;
import texture.Texture;
import world.World;

/**
 * Class represents a Material
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe3 2014-11-25
 */
public class SingleColorMaterial extends Material {

    public final Texture texture;

    /**
     *
     * creates an instance of a single color  material
     *
     * @param texture Texture of Material
     */
    public SingleColorMaterial(final Texture texture) {
        this.texture = texture;
    }

    @Override
    public Color colorFor(final Hit hit, final World world) {
        return this.texture.getColor(hit.texCoord2.u, hit.texCoord2.v);
    }

    @Override
    public Color colorFor(Hit hit, World world, Tracer tracer) {
        return this.texture.getColor(hit.texCoord2.u, hit.texCoord2.v);
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
        if (o == null || !(o instanceof SingleColorMaterial)) return false;

        SingleColorMaterial material = (SingleColorMaterial) o;
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
        SingleColorMaterial sm = (SingleColorMaterial) m;
        return this.texture.compareTo(sm.texture);
    }
}
