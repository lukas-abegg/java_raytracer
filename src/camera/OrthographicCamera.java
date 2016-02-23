package camera;

import mathlib.Point2;
import ray.Ray;
import mathlib.Point3;
import mathlib.Vector3;
import sampling.SamplingPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents an orthographic camera for the ray tracer
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe2 2014-11-25
 */
public class OrthographicCamera extends Camera {
    public final double s;

    /**
     * Constructor for OrthographicCamera
     *
     * @param e     eye-position    (Point3)
     * @param g     gaze-direction  (Vector3)
     * @param t     up-vector       (Vector3)
     * @param s     scale           (double)
     */
    public OrthographicCamera(final Point3 e, final Vector3 g, final Vector3 t, final double s, final SamplingPattern samplingPattern) {
        super(e, g, t, samplingPattern);
        this.s = s;
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
    public List<Ray> rayFor(int widthI, int heightI, int xI, int yI) {
        List<Ray> rays = new ArrayList<Ray>();

        double x = (double) xI;
        double y = (double) yI;
        double width = (double) widthI;
        double height = (double) heightI;
        
        for(Point2 p: this.samplingPattern.points) {
            Vector3 d = this.w.mul(-1);                                                     // direction for Ray

            double a = width / height;                                                      // aspect-ratio
            double factorA = this.s * a * (x + p.x - (width - 1) / 2) / (width - 1);              // calculation of 1st factor for origin calculation
            double factorB = this.s * (y + p.y - (height - 1) / 2) / (height - 1);                // calculation of 2nd factor for origin calculation
            Point3 o = this.e.add(this.u.mul(factorA)).add(this.v.mul(factorB));            // origin calculation using factorA and factorB

            rays.add(new Ray(o, d));
        }
        return rays;
    }

    /**
     * Method builds an evenly distributed hash value for the OrthographicCamera
     *
     * @return new hash code as int
     */
    @Override
    public String toString(){
        return "OrthographicCamera instance: " +
                super.toString()               +
                "\ns: " + s;
    }

    /**
     * Method builds an evenly distributed hash value for the OrthographicCamera instance
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode(){
        int result;
        long hash;

        result = super.hashCode();
        hash   = Double.doubleToLongBits(s);
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
        if (o == null || !(o instanceof OrthographicCamera)) return false;

        OrthographicCamera oCamera = (OrthographicCamera) o;

        return (super.equals(oCamera))
            && (Double.compare(oCamera.s,this.s) == 0);
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
        OrthographicCamera oCam = (OrthographicCamera) cam;
        if ( !(super.equals(cam)) ) return super.compareTo(cam);
        if (  (Double.compare(this.s, oCam.s)!=0) ) return (int) Math.signum(this.s - oCam.s);
        return 0;
    }
}
