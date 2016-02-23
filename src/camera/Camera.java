package camera;

import ray.Ray;
import mathlib.Point3;
import mathlib.Vector3;
import sampling.SamplingPattern;

import java.util.List;

/**
 * Class represents an abstract model of a camera for the ray tracer
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe2 2014-11-25
 */
public abstract class Camera implements Comparable<Camera>{
    public final Point3 e;
    public final Vector3 g, t, u, v, w;
    public final SamplingPattern samplingPattern;

    /**
     * Constructor for Camera
     *
     * @param e eye-position    (Point3)
     * @param g gaze-direction  (Vector3)
     * @param t up-vector       (Vector3)
     */
    public Camera(final Point3 e, final Vector3 g, final Vector3 t, final SamplingPattern samplingPattern) {
        this.e = e;
        this.g = g;
        this.t = t;
        this.samplingPattern = samplingPattern;

        w = calculateW(g);      // z-axis of Camera
        u = calculateU(t, w);   // x-axis of Camera
        v = calculateV(w, u);   // y-axis of Camera

    }

    /**
     * Method calculates the Vector3 w for z-axis of the Camera
     *
     * @param gaze gaze direction of the Camera
     * @return calculated Vector3 w
     */
    private Vector3 calculateW(final Vector3 gaze) {
        return gaze.normalized().mul(-1);
    }

    /**
     * Method calculates the Vector u for x-axis of the Camera
     *
     * @param t is the up-vector (Vector3)
     * @param w is the w-vector (Vector3) for the Camera
     * @return calculated Vector3 u
     */
    private Vector3 calculateU(final Vector3 t, final Vector3 w) {
        return t.x(w).normalized();
    }

    /**
     * Method calculates the Vector v for y-axis of the Camera
     *
     * @param w is the w-vector (Vector3) for the Camera
     * @param u is the u-vector (Vector3) for the Camera
     * @return calculated Vector3 v
     */
    private Vector3 calculateV(final Vector3 w, final Vector3 u) {
        return w.x(u);
    }

    /**
     * Method returns a Ray for the given parameters x and y
     *
     *
     * @param width width of the picture
     * @param height height of the picture
     * @param x x-coordinate
     * @param y y-coordinate
     * @return calculated Ray (by implementation)
     */
    public abstract List<Ray> rayFor(final int width, final int height, final int x, final int y);

    /**
     * Method builds an evenly distributed hash value for the Camera
     *
     * @return new hash code as int
     */
    @Override
    public String toString() {
        return "\neye-position e:\n" + e.toString() +
                "\ngaze-direction g:\n" + g.toString() +
                "\nup-vector t:\n" + t.toString();
    }

    /**
     * Method builds an evenly distributed hash value for the Camera
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode() {
        int result;

        result = e.hashCode();
        result = g.hashCode() + 31 * result;
        result = t.hashCode() + 31 * result;

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
        if (o == null || !(o instanceof Camera)) return false;

        Camera camera = (Camera) o;
        return (this.e.equals(camera.e))
            && (this.g.equals(camera.g))
            && (this.t.equals(camera.t));
    }

    /**
     * Comparable Method for Interface Comparable
     * @param cam incoming Camera-Object
     * @return  int value ( 0 if all attributes are equal,
     *                    -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     *                     1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(final Camera cam) {
        if ( !(this.e.equals(cam.e)) ) return this.e.compareTo(cam.e);
        if ( !(this.g.equals(cam.g)) ) return this.g.compareTo(cam.g);
        if ( !(this.t.equals(cam.t)) ) return this.t.compareTo(cam.t);

        return 0;
    }
}
