package texture;


import color.Color;

/**
 * SingleColorTexture for getting Color of texture
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe Erweiterung Kat A 2014-12-21
 */
public class SingleColorTexture implements Texture {

    public final Color color;

    public SingleColorTexture(final Color color) {
        this.color = color;
    }

    /**
     * Get Color of texture coordinations
     */
    public Color getColor(final double u, final double v) {
        return this.color;
    }

    /**
     * Method builds an evenly distributed hash value for the Light
     *
     * @return new hash code as int
     */
    @Override
    public String toString() {
        return "color: \n" + this.color;
    }

    /**
     * Method builds an evenly distributed hash value for the Light instance
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode() {
        int result;

        result = this.color.hashCode();
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

        SingleColorTexture texture = (SingleColorTexture) o;
        return (this.color.equals(texture.color));
    }

    /**
     * Comparable Method for Interface Comparable
     *
     * @param texture incoming Texture-Object
     * @return int value (      0 if all attributes are equal,
     * -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     * 1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public int compareTo(final Texture texture) {
        if (this.getColor(1, 1).compareTo(texture.getColor(1, 1)) != 0)
            return this.getColor(1, 1).compareTo(texture.getColor(1, 1));
        return 0;
    }
}
