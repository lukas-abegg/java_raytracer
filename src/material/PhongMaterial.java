package material;

import color.Color;
import geometry.Hit;
import texture.Texture;
import world.World;
import light.Light;
import mathlib.Normal3;
import mathlib.Point3;
import mathlib.Vector3;

/**
 * Class represents a Material
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe3 2014-11-25
 */
public class PhongMaterial extends Material {

    /* diffuse light */
    public final Texture diffuse;
    /* specular light */
    public final Texture specular;
    /* exponent of light points */
    public final int exponent;

    /**
     * creates an instance of a phong material
     *
     * @param diffuse
     * @param specular
     * @param exponent
     */
    public PhongMaterial(final Texture diffuse, final Texture specular, final int exponent) {
        this.diffuse = diffuse;
        this.specular = specular;
        this.exponent = exponent;

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
        Color c = this.diffuse.getColor(hit.texCoord2.u, hit.texCoord2.v).mul(world.ambientLight);
        Point3 hitPoint = hit.ray.at(hit.t);

        for (Light light : world.lights) {

            if (light.illuminates(hitPoint, world)) {
                Vector3 l = light.directionFrom(hitPoint);
                Vector3 r = l.reflectedOn(hitN);

                double max = Math.max(0.0, l.dot(hitN));
                double max2 = Math.pow(Math.max(0.0, hit.ray.d.mul(-1).dot(r)), this.exponent);

                c = c.add(this.diffuse.getColor(hit.texCoord2.u, hit.texCoord2.v).mul(light.color).mul(max)).add(this.specular.getColor(hit.texCoord2.u, hit.texCoord2.v).mul(light.color).mul(max2));
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
        return "diffuse texture: \n" +
                this.diffuse.toString() +
                "\nspecular texture: \n" + this.specular.toString() +
                "\nexponent: \n" + this.exponent;
    }

    /**
     * Method builds an evenly distributed hash value for the Material instance
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode() {
        int result;
        long hash;

        hash = Double.doubleToLongBits(exponent);
        result = (int) (hash ^ (hash >>> 32));
        result = 31 * result + this.specular.hashCode();
        result = 31 * result + this.diffuse.hashCode();

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
        if (o == null || !(o instanceof PhongMaterial)) return false;

        final PhongMaterial pm = (PhongMaterial) o;
        return this.diffuse .equals(pm.diffuse)
            && this.specular.equals(pm.specular)
            && this.exponent ==     pm.exponent;
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

        final PhongMaterial pm = (PhongMaterial) m;
        if (this.diffuse .compareTo(pm.diffuse)  != 0) return this.diffuse .compareTo(pm.diffuse);
        if (this.specular.compareTo(pm.specular) != 0) return this.specular.compareTo(pm.specular);
        return (int) Math.signum((double)(this.exponent-pm.exponent));
    }
}
