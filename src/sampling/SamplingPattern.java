package sampling;


import mathlib.Point2;

import java.util.ArrayList;
import java.util.List;

/**
 * Sampler calculates sharpness of geometries
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe Erweiterung Kat 0 2014-12-21
 */
public class SamplingPattern implements Comparable<SamplingPattern> {

    public final List<Point2> points;

    public SamplingPattern(List<Point2> points) {

        for (Point2 p : points) {
            // x and y of each point must be between -0.5 and 0.5
            if (!(p.x >= -0.5 && p.x <= 0.5 && p.y >= -0.5 && p.y <= 0.5)) {
                points.remove(p);
            }
        }
        this.points = points;
    }

    public SamplingPattern(final int x, final int y) {

        double xStep = 0;
        if (x > 1) {
            xStep = 1.0 / (x - 1.0);
        } else {
            xStep = 0.0;
        }

        double yStep = 0;
        if (y > 1) {
            yStep = 1.0 / (y - 1.0);
        } else {
            yStep = 0.0;
        }

        double xStart = 0;
        if (x > 1) {
            xStart = -0.5;
        } else {
            xStart = 0.0;
        }

        double yStart = 0;
        if (y > 1) {
            yStart = -0.5;
        } else {
            yStart = 0.0;
        }

        List<Point2> samplingPoints = new ArrayList<Point2>();

        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                samplingPoints.add(new Point2(xStart + i * xStep, yStart + j * yStep));
            }
        }

        this.points = new ArrayList<Point2>();

        for (Point2 p : samplingPoints) {
            // x and y of each point must be between -0.5 and 0.5
            if ((p.x >= -0.5 && p.x <= 0.5 && p.y >= -0.5 && p.y <= 0.5)) {
                this.points.add(p);
            }
        }
    }

    /**
     * This method maps the sampling points to a unit disc.
     *
     * @return The sampling points mapped to a unit disc.
     */
    public List<Point2> asDisc() {
        List<Point2> discPoints = new ArrayList<Point2>();

        for (Point2 p : points) {
            double x = 2.0 * p.x;
            double y = 2.0 * p.y;

            double r = 0;
            double a = 0;
            if (x > -y) {
                if (x > y) {
                    r = x;
                    a = y / x;
                } else {
                    r = y;
                    a = 2.0 - x / y;
                }
            } else {
                if (x < y) {
                    r = -x;
                    a = 4 + y / x;
                } else {
                    r = -y;
                    if (y != 0) {
                        a = 6 - x / y;
                    } else {
                        a = 0;
                    }
                }
            }

            double phi = a * Math.PI / 4.0;
            discPoints.add(new Point2(r * Math.cos(phi), r * Math.sin(phi) ));
        }
        return discPoints;
    }

    /**
     * Method builds an evenly distributed hash value for the Light
     *
     * @return new hash code as int
     */
    @Override
    public String toString() {
        return "SamplingPattern{" +
                "points=" + points +
                '}';
    }

    /**
     * Method builds an evenly distributed hash value for the Light instance
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode() {
        return points != null ? points.hashCode() : 0;
    }

    /**
     * Overridden equals method: indicates whether the values of the given Object are the same or not
     *
     * @param o representing the Object to compare with
     * @return boolean value of the result
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SamplingPattern)) return false;

        SamplingPattern that = (SamplingPattern) o;

        if (points != null ? !points.equals(that.points) : that.points != null) return false;

        return true;
    }

    /**
     * Comparable Method for Interface Comparable
     *
     * @param samplingPattern incoming Texture-Object
     * @return int value (      0 if all attributes are equal,
     * -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     * 1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(final SamplingPattern samplingPattern) {
        return 0;
    }
}
