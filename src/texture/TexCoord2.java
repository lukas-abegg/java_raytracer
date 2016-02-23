package texture;


/**
 * Class TexCoord2 for texturing
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe Erweiterung Kat A 2014-12-21
 */
public class TexCoord2 implements Comparable<TexCoord2> {


    public final double u;
    public final double v;

    /**
     * constructor for instantiate a new Texture
     */
    public TexCoord2(final double u, final double v) {
        this.u = u;
        this.v = v;
    }

    /**
     * Addition of texture coordinates
     *
     * @param t TexCoord2
     * @return new TexCoord2
     */
    public TexCoord2 add(final TexCoord2 t) {
        return new TexCoord2(u + t.u, v + t.v);
    }

    /**
     * Multiplication with double n
     *
     * @param n factor
     * @return new TexCoord2
     */
    public TexCoord2 mul(final double n) {
        return new TexCoord2(u * n, v * n);
    }


    @Override
    public String toString() {
        return "TexCoord2{" +
                "u=" + u +
                ", v=" + v +
                '}';
    }

    /**
     * Method builds an evenly distributed hash value for the Light instance
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode() {
        int result;
        long hash;

        hash = Double.doubleToLongBits(u);
        result = (int) (hash ^ (hash >>> 32));
        hash = Double.doubleToLongBits(v);
        result = 31 * result + (int) (hash ^ (hash >>> 32));
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
        if (o == null || this == o || o.getClass() != this.getClass()) return false;

        TexCoord2 texCoord2 = (TexCoord2) o;
        return (Double.compare(texCoord2.u, this.u) == 0)
                && (Double.compare(texCoord2.v, this.v) == 0);
    }

    /**
     * Comparable Method for Interface Comparable
     *
     * @param tC2 incoming TexCoord2-Object
     * @return int value (      0 if all attributes are equal,
     *                          -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     *                          1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(final TexCoord2 tC2) {
        if (Double.compare(this.u, tC2.u) != 0) return (int) Math.signum(this.u - tC2.u);
        if (Double.compare(this.v, tC2.v) != 0) return (int) Math.signum(this.v - tC2.v);
        return 0;
    }
}
