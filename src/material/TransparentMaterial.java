package material;

import color.Color;
import geometry.Geometry;
import geometry.Hit;
import ray.Ray;
import world.World;
import mathlib.Normal3;
import mathlib.Vector3;


/**
 * Represents the transparent material.
 */
public class TransparentMaterial extends Material {
    public final double indexOfRefraction;

    public TransparentMaterial(final double indexOfRefraction) {
        this.indexOfRefraction = indexOfRefraction;
    }

    @Override
    public Color colorFor(final Hit hit, final World world) {
        Normal3 normal = hit.n;

        final Vector3 e = hit.ray.d.mul(-1).reflectedOn(normal);

        // rate of refraction
        final boolean inside = (e.dot(normal)) < 0;
        double eta = 0;
        if (inside) {
            eta = indexOfRefraction / world.indexOfRefraction;
            normal = normal.mul(-1);
        } else {
            eta = world.indexOfRefraction / indexOfRefraction;
        }

        final Tracer tracer = new Tracer(world, (int) indexOfRefraction);

        final double cosThetaI = normal.dot(e);

        final double h = (1 - (Math.pow(eta, 2.0) * (1 - Math.pow(cosThetaI, 2.0))));
        if (h < 0) {
            return tracer.colorFor(new Ray(hit.ray.at(hit.t - Geometry.EPSILON), e));
        } else {

            final double cosThetaT = Math.sqrt(h);
            final Vector3 t = hit.ray.d.mul(eta).sub(normal.mul(cosThetaT - eta * cosThetaI));

            // Schlick
            final double r0 = Math.pow((world.indexOfRefraction - indexOfRefraction) / (world.indexOfRefraction + indexOfRefraction), 2);
            final double r = r0 + (1 - r0) * Math.pow(1 - cosThetaI, 5);
            final double T = 1 - r;

            // R and T are multiplied with the colors of the raytraced rays
            return tracer.colorFor(new Ray(hit.ray.at(hit.t - Geometry.EPSILON), e)).mul(r)
                    .add(tracer.colorFor(new Ray(hit.ray.at(hit.t + Geometry.EPSILON), t)).mul(T));
        }
    }

    @Override
    public Color colorFor(final Hit hit, final World world, final Tracer tracer) {
        Normal3 normal = hit.n;

        final Vector3 e = hit.ray.d.mul(-1).reflectedOn(normal);

        // rate of refraction
        final boolean inside = (e.dot(normal)) < 0;
        double eta = 0;
        if (inside) {
            eta = indexOfRefraction / world.indexOfRefraction;
            normal = normal.mul(-1);
        } else {
            eta = world.indexOfRefraction / indexOfRefraction;
        }

        final double cosThetaI = normal.dot(e);

        final double h = (1 - (Math.pow(eta, 2.0) * (1 - Math.pow(cosThetaI, 2.0))));
        if (h < 0) {
            return tracer.colorFor(new Ray(hit.ray.at(hit.t - Geometry.EPSILON), e));
        } else {

            final double cosThetaT = Math.sqrt(h);
            final Vector3 t = hit.ray.d.mul(eta).sub(normal.mul(cosThetaT - eta * cosThetaI));

            // Schlick
            final double r0 = Math.pow((world.indexOfRefraction - indexOfRefraction) / (world.indexOfRefraction + indexOfRefraction), 2);
            final double r = r0 + (1 - r0) * Math.pow(1 - cosThetaI, 5);
            final double T = 1 - r;

            // R and T are multiplied with the colors of the raytraced rays
            return tracer.colorFor(new Ray(hit.ray.at(hit.t - Geometry.EPSILON), e)).mul(r)
                    .add(tracer.colorFor(new Ray(hit.ray.at(hit.t + Geometry.EPSILON), t)).mul(T));
        }
    }

    @Override
    public String toString() {
        return "TransparentMaterial{" +
                "indexOfRefraction=" + indexOfRefraction +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransparentMaterial)) return false;

        TransparentMaterial that = (TransparentMaterial) o;

        if (Double.compare(that.indexOfRefraction, indexOfRefraction) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(indexOfRefraction);
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public int compareTo(Material o) {
        return 0;
    }
}