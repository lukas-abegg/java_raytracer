package texture;


import color.Color;

/**
 * Interface Texture for getting Color of texture
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe Erweiterung Kat A 2014-12-21
 */
public interface Texture extends Comparable<Texture> {
    
    /**
     * Get Color of texture coordinations
     */
    public Color getColor(final double u, final double v);

    /**
     * Method builds an evenly distributed hash value for the Light
     *
     * @return new hash code as int
     */
    @Override
    public String toString();

    /**
     * Method builds an evenly distributed hash value for the Light instance
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode();

    /**
     * Overridden equals method: indicates whether the values of the given Object are the same or not
     *
     * @param o representing the Object to compare with
     * @return boolean value of the result
     */
    @Override
    public boolean equals(Object o);

    /**
     * Comparable Method for Interface Comparable
     *
     * @param texture incoming Texture-Object
     * @return int value (      0 if all attributes are equal,
     * -1 if one of the attributes is smaller than the corresponding attribute of the incoming object,
     * 1 if one of the attributes is greater than the corresponding attribute of the incoming object)
     */
    @SuppressWarnings("NullableProblems")
    public int compareTo(final Texture texture);
}
