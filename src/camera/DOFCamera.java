package camera;

import mathlib.Point2;
import mathlib.Point3;
import mathlib.Vector3;
import ray.Ray;
import sampling.SamplingPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents a sharpen deepness for the ray tracer
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe2 2014-11-25
 */
public class DOFCamera extends Camera {

    public final double a;
    public final double focalLength;
    public final double lensRadius;
    public final List<Point2> lensSamplingPoints;


    /**
     * Constructor for OrthographicCamera
     *
     * @param e           eye-position                            (Point3)
     * @param g           gaze-direction                          (Vector3)
     * @param t           up-vector                               (Vector3)
     * @param a           The half angle of view                  (double)
     * @param focalLength The distance to the focus plane         (double)
     * @param lensRadius  The radius of the simulated lense       (double)
     */
    public DOFCamera(final Point3 e, final Vector3 g, final Vector3 t, double a, double focalLength, double lensRadius, final SamplingPattern samplingPattern) {
        super(e, g, t, samplingPattern);
        this.a = a;
        this.focalLength = focalLength;
        this.lensRadius = lensRadius;
        this.lensSamplingPoints = samplingPattern.asDisc();
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
    public List<Ray> rayFor(int widthI, int heightI, int xI, int yI) {
        List<Ray> rays = new ArrayList<Ray>();

        double x = (double) xI;
        double y = (double) yI;
        double width = (double) widthI;
        double height = (double) heightI;

        final Vector3 mw = this.w.mul(-1);   // direction for Ray
        final double one = height / 2 / Math.tan(a / 2);
        final double two = (width - 1) / 2;
        final double three = (height - 1) / 2;

        for (Point2 p : this.samplingPattern.points) {
            double px = x - two + p.x;
            double py = y - three + p.y;

            double fx = px * focalLength / one;
            double fy = py * focalLength / one;

            Point3 fp = e.add(mw.mul(focalLength)).add(u.mul(fx)).add(v.mul(fy));

            for (Point2 lensP : lensSamplingPoints) {
                Point3 o = e.add(u.mul(lensP.x).mul(lensRadius)).add(v.mul(lensP.y).mul(lensRadius));
                Vector3 d = (fp.sub(o)).normalized();

                rays.add(new Ray(o, d));
            }
        }
        return rays;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(a);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(focalLength);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lensRadius);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Method builds an evenly distributed hash value for the OrthographicCamera
     *
     * @return new hash code as int
     */
    @Override
    public String toString() {
        return "DOFCamera{" +
                "a=" + a +
                ", focalLength=" + focalLength +
                ", lensRadius=" + lensRadius +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DOFCamera)) return false;
        if (!super.equals(o)) return false;

        DOFCamera dofCamera = (DOFCamera) o;

        if (Double.compare(dofCamera.a, a) != 0) return false;
        if (Double.compare(dofCamera.focalLength, focalLength) != 0) return false;
        if (Double.compare(dofCamera.lensRadius, lensRadius) != 0) return false;

        return true;
    }
}
