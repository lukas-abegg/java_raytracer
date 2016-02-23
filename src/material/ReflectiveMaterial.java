package material;

import color.Color;
import geometry.Hit;
import ray.Ray;
import texture.Texture;
import world.World;
import light.Light;
import mathlib.Point3;
import mathlib.Vector3;

/**
 * Class represents a reflective Material
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Zusatz C3 2014-12-18
 */
public class ReflectiveMaterial extends Material{
    private final Texture diffuse;
    private final Texture specular;
    private final int exponent;
    private final Texture reflection;

    /**
     * Constructor for Reflective Material
     * @param diffuse
     * @param specular
     * @param exponent
     * @param reflection
     */
    public ReflectiveMaterial(final Texture diffuse, final Texture specular, final int exponent, final Texture reflection){
        this.diffuse    = diffuse;
        this.specular   = specular;
        this.exponent   = exponent;
        this.reflection = reflection;
    }

    /**
     * Method returns color for current reflection.
     * It delegates the hit and world to the rayFor method with a different method signature (addidtional Tracer instance with recursion depth)
     * @param hit   the hit with an object
     * @param world the world is needed to find the lights
     * @return the color of reflection
     */
    @Override
    public Color colorFor(Hit hit, World world) {
        return colorFor(hit, world, new Tracer(world, 6));
    }

    /**
     * Method returns color for current reflection.
     * @param hit   the hit with an object
     * @param world the world is needed to find the lights
     * @param tracer Tracer that helps with recursion
     * @return the color of reflection
     */
    @Override
    public Color colorFor(final Hit hit, final World world, final Tracer tracer){
        Color materialColor = world.ambientLight.mul(this.diffuse.getColor(hit.texCoord2.u, hit.texCoord2.v));
        Point3 hitPoint     = hit.ray.at(hit.t);
        double cosPhi       = hit.ray.d.mul(-1).dot(hit.n);
        Vector3 rD          = hit.ray.d.add(hit.n.mul(2*cosPhi)).normalized();

        for (Light light : world.lights) {
            if (light.illuminates(hitPoint, world)) {
                Vector3 l = light.directionFrom(hitPoint);
                Vector3 r = l.reflectedOn(hit.n);
                double max1 = Math.max(0.0, l.dot(hit.n));
                double max2 = Math.pow(Math.max(0.0, hit.ray.d.mul(-1).dot(r)), this.exponent);
                materialColor = materialColor.add(light.color.mul(this.diffuse.getColor(hit.texCoord2.u, hit.texCoord2.v)).mul(max1)).add(light.color.mul(this.specular.getColor(hit.texCoord2.u, hit.texCoord2.v).mul(max2)));
            }
        }
        Color reflectedColor = tracer.colorFor(new Ray(hitPoint, rD));
        return materialColor.add(reflection.getColor(hit.texCoord2.u, hit.texCoord2.v).mul((reflectedColor)));
    }

    /**
     * Overridden toString Method
     * @return toString string
     */
    @Override
    public String toString(){
        return "Reflective Material:\n"
                + "diffuse texture: \n"    + this.diffuse.toString()
                + "\nspecular texture: \n" + this.specular.toString()
                + "\nreflection texture: \n" + this.reflection.toString()
                + "\nexponent: \n"       + this.exponent;
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
        result = 31 * result + this.reflection.hashCode();

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
        if (o == null || !(o instanceof ReflectiveMaterial)) return false;
        final ReflectiveMaterial rm = (ReflectiveMaterial) o;
        return this.diffuse       .equals(rm.diffuse)
                && this.specular  .equals(rm.specular)
                && this.exponent ==       rm.exponent
                && this.reflection.equals(rm.reflection);
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
        final ReflectiveMaterial rm = (ReflectiveMaterial) m;
        if (this.diffuse   .compareTo(rm.diffuse)    != 0) return this.diffuse   .compareTo(rm.diffuse);
        if (this.specular  .compareTo(rm.specular)   != 0) return this.specular  .compareTo(rm.specular);
        if (this.reflection.compareTo(rm.reflection) != 0) return this.reflection.compareTo(rm.reflection);
        return (int)Math.signum((double)(this.exponent-rm.exponent));
    }
}
