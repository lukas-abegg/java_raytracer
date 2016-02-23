package camera;

import mathlib.Point2;
import ray.Ray;
import mathlib.Point3;
import mathlib.Vector3;
import sampling.SamplingPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents a perspective camera for the ray tracer
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe2 2014-11-25
 */
public class PerspectiveCamera extends Camera {
    public final double angle;

    /**
     * Constructor for PerspectiveCamera
     *
     * @param e     eye-position    (Point3)
     * @param g     gaze-direction  (Vector3)
     * @param t     up-vector       (Vector3)
     * @param angle angle           (double)
     */
    public PerspectiveCamera(final Point3 e, final Vector3 g, final Vector3 t, final double angle, final SamplingPattern samplingPattern) {
        super(e, g, t, samplingPattern);
        this.angle = angle;
    }

    /**
     * Method returns a Ray for the given parameters x and y
     *
     * @param widthI width of the picture
     * @param heightI height of the picture
     * @param xI x-coordinate
     * @param yI y-coordinate
     * @return calculated Ray
     */
    @Override
    public List<Ray> rayFor(final int widthI, final int heightI, final int xI, final int yI) {
        List<Ray> rays = new ArrayList<Ray>();

        double x = (double) xI;
        double y = (double) yI;
        double width = (double) widthI;
        double height = (double) heightI;
        
        for(Point2 p: this.samplingPattern.points) {
            Point3 o = this.e;                                                                              // origin is eye-position

            double factorA = (height / 2) / Math.tan(angle / 2);                                              // calculation of 1st factor for ray direction calculation
            double factorB = x + p.x - (width - 1) / 2;                                                           // calculation of 2nd factor for ray direction calculation
            double factorC = y + p.y - (height - 1) / 2;                                                          // calculation of 3rd factor for ray direction calculation
            Vector3 r = this.w.mul(-1).mul(factorA).add(this.u.mul(factorB)).add(this.v.mul(factorC));      // ray direction calculation using factorA, factorB & factorC
            Vector3 d = r.normalized();                                                                     // normalisation of r

            rays.add(new Ray(o, d));
        }

        return rays;
    }

    /**
     * Method builds an evenly distributed hash value for the PerspectiveCamera
     *
     * @return new hash code as int
     */
    @Override
    public String toString() {
        return "PerspectiveCamera instance: " +
                super.toString()              +
                "\nangle: " + angle;
    }

    /**
     * Methods builds a evenly distributed hash value for the PerspectiveCamera instance
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode(){
        int result;
        long hash;

        result = super.hashCode();
        hash   = Double.doubleToLongBits( angle );
        result = 31 * result + (int) ( hash ^ ( hash >>> 32 ) );

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
        if (o == null || !(o instanceof PerspectiveCamera)) return false;

        PerspectiveCamera pCamera = (PerspectiveCamera) o;
        return (super.equals(pCamera))
            && (Double.compare(this.angle,pCamera.angle) == 0);
    }

    /**
     * Comparable Method for Interface Comparable
     * @param cam incoming Camera-Object
     * @return  int value ( 0 if all attributes are equal,
     *                    -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     *                     1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */
    @Override
    public int compareTo(final Camera cam) {
        PerspectiveCamera pCam = (PerspectiveCamera) cam;
        if ( !(super.equals(cam)) ) return super.compareTo(cam);
        if (  (Double.compare(this.angle, pCam.angle)!=0) ) return (int) Math.signum(this.angle - pCam.angle);
        return 0;
    }
}
