package color;

/**
 * Class Color represent a color object of a geometry
 * and as background color of the world
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe2 2014-11-25
 */
public class Color implements Comparable<Color> {

    public final double r;
    public final double g;
    public final double b;

    public final int rgbIntVal;

    /**
     * constructor creates an instance of color
     *
     * @param r the value of red channel
     * @param g the value of green channel
     * @param b the value of blue channel
     */
    public Color(final double r, final double g, final double b) {

        this.r = Math.max(0.0, Math.min(1.0, r));
        this.g = Math.max(0.0, Math.min(1.0, g));
        this.b = Math.max(0.0, Math.min(1.0, b));
        this.rgbIntVal = getIntegerValofRGB(this.r, this.g, this.b);
    }

    /**
     * create an instance of color with java.awt.Color
     * @param color java.awt.Color
     */
    public Color (java.awt.Color color) {
        this.r = color.getRed() / 255.0;
        this.g = color.getGreen() / 255.0;
        this.b = color.getBlue() / 255.0;
        this.rgbIntVal = color.getRGB();
    }

    /**
     * calculates an int value from RGB values of a Color object
     *
     * @param r red channel
     * @param g green channel
     * @param b blue channel
     * @return new int value of RGB
     */
    public int getIntegerValofRGB(double r, double g, double b) {
        return (0xff << 24 | (((int) (r * 255)) & 0xff) << 16 | (((int) (g * 255)) & 0xff) << 8 | (((int) (b * 255)) & 0xff));
        //return (int)(256*256*r*255+256*g*255+b*255);
        //             Bits 23-16    Bits 15-8 Bits 7-0
    }

    /**
     * add a Color object to the existing Color object
     *
     * @param c Color to be added
     * @return new Color object
     */
    public Color add(final Color c) {
        return new Color(this.r + c.r, this.g + c.g, this.b + c.b);
    }

    /**
     * subtract a Color object from the existing Color object
     *
     * @param c Color to be substracted
     * @return new Color object
     */
    public Color sub(final Color c) {
        return new Color(this.r - c.r, this.g - c.g, this.b - c.b);
    }

    /**
     * multiplies a Color object with the existing Color object
     *
     * @param c Color to be multiplied
     * @return new Color object
     */
    public Color mul(final Color c) {
        return new Color(this.r * c.r, this.g * c.g, this.b * c.b);
    }

    /**
     * multiplies a double dot-product object with the existing Color object
     *
     * @param d double to be multiplied
     * @return new Color object
     */
    public Color mul(final double d) {
        return new Color(this.r * d, this.g * d, this.b * d);
    }

    /**
     * shows the Color instance as String
     *
     * @return a String with double r, double g and double b representations of this Color
     */
    @Override
    public String toString() {
        return "Color instance:" +
                "\ndouble r: " + r +
                "\ndouble g: " + g +
                "\ndouble b: " + b;
    }

    /**
     * Methods build an evenly distributed hash value for the Color instance
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode() {
        int result;
        long hash;

        hash = Double.doubleToLongBits(r);
        result = (int) (hash ^ (hash >>> 32));
        hash = Double.doubleToLongBits(g);
        result = 31 * result + (int) (hash ^ (hash >>> 32));
        hash = Double.doubleToLongBits(b);
        result = 31 * result + (int) (hash ^ (hash >>> 32));

        return result;
    }

    /**
     * Overridden equals Method: indicates whether the values of the given Object are the same or not
     *
     * @param o representing the Object to compare with
     * @return boolean value of the result
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Color)) return false;

        Color color = (Color) o;
        return (Double.compare(this.r, color.r) == 0)
                && (Double.compare(this.g, color.g) == 0)
                && (Double.compare(this.b, color.b) == 0);
    }

    /**
     * Comparable Method for Interface Comparable
     *
     * @param c incoming Color-Object
     * @return int value ( 0 if all attributes are equal,
     * -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     * 1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(final Color c) {
        if (Double.compare(this.r, c.r) != 0) return (int) Math.signum(this.r - c.r);
        if (Double.compare(this.g, c.g) != 0) return (int) Math.signum(this.g - c.g);
        if (Double.compare(this.b, c.b) != 0) return (int) Math.signum(this.b - c.b);
        return 0;
    }
}
