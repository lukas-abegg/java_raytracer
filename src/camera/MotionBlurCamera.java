package camera;

import mathlib.Point2;
import mathlib.Point3;
import mathlib.Vector3;
import ray.Ray;
import sampling.SamplingPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents a perspective camera for the ray tracer
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe2 2014-11-25
 */
public class MotionBlurCamera extends Camera {
    public final double angle;
    public final double focalTime;
    public final double stepLength;

    /**
     * Constructor for PerspectiveCamera
     *
     * @param e          eye-position            (Point3)
     * @param g          gaze-direction          (Vector3)
     * @param t          up-vector               (Vector3)
     * @param angle      angle                   (double)
     * @param focalTime  focal time              (double)
     * @param stepLength step length for motion  (double)
     */
    public MotionBlurCamera(final Point3 e, final Vector3 g, final Vector3 t, final double angle, final double focalTime, final double stepLength, final SamplingPattern samplingPattern) {
        super(e, g, t, samplingPattern);
        this.angle = angle;
        this.focalTime = focalTime;
        this.stepLength = stepLength;
    }

    /**
     * Method returns a Ray for the given parameters x and y
     *
     * @param widthI  width of the picture
     * @param heightI height of the picture
     * @param xI      x-coordinate
     * @param yI      y-coordinate
     * @return calculated Ray
     */
    @Override
    public List<Ray> rayFor(final int widthI, final int heightI, final int xI, final int yI) {
        List<Ray> rays = new ArrayList<Ray>();

        double x = (double) xI;
        double y = (double) yI;
        double width = (double) widthI;
        double height = (double) heightI;

        for (Point2 p : this.samplingPattern.points) {
            Point3 o = this.e;                                                                              // origin is eye-position

            double factorA = (height / 2) / Math.tan(angle / 2);                                              // calculation of 1st factor for ray direction calculation
            double factorB = x + p.x - (width - 1) / 2;                                                           // calculation of 2nd factor for ray direction calculation
            double factorC = y + p.y - (height - 1) / 2;                                                          // calculation of 3rd factor for ray direction calculation

            // Motion Blur Effect
            for (int i = 0; i < this.focalTime; i++) {
                double move = (double) i / this.stepLength;

                Vector3 r = this.w.mul(-1).mul(factorA).add(this.u.mul(factorB)).add(this.v.mul(factorC)).sub(new Vector3(move, 0, 0));      // ray direction calculation using factorA, factorB & factorC
                Vector3 d = r.normalized();                                                                                                 // normalisation of r

                rays.add(new Ray(o, d));
            }
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
        return "MotionBlurCamera{" +
                "angle=" + angle +
                ", focalTime=" + focalTime +
                ", stepLength=" + stepLength +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MotionBlurCamera)) return false;
        if (!super.equals(o)) return false;

        MotionBlurCamera that = (MotionBlurCamera) o;

        if (Double.compare(that.angle, angle) != 0) return false;
        if (Double.compare(that.focalTime, focalTime) != 0) return false;
        if (Double.compare(that.stepLength, stepLength) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(angle);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(focalTime);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(stepLength);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Comparable Method for Interface Comparable
     *
     * @param cam incoming Camera-Object
     * @return int value ( 0 if all attributes are equal,
     * -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     * 1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */
    @Override
    public int compareTo(final Camera cam) {
        MotionBlurCamera pCam = (MotionBlurCamera) cam;
        if (!(super.equals(cam))) return super.compareTo(cam);
        if ((Double.compare(this.angle, pCam.angle) != 0)) return (int) Math.signum(this.angle - pCam.angle);
        if ((Double.compare(this.focalTime, pCam.focalTime) != 0)) return (int) (this.focalTime - pCam.focalTime);
        if ((Double.compare(this.stepLength, pCam.stepLength) != 0)) return (int) (this.stepLength - pCam.stepLength);
        return 0;
    }
}
