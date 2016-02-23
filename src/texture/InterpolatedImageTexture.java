package texture;


import color.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * SingleColorTexture for getting Color of texture
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe Erweiterung Kat A 2014-12-21
 */
public class InterpolatedImageTexture implements Texture {

    public final static String IMAGE_DIR = "src/assets";
    public String imageURL;
    public BufferedImage image;

    public InterpolatedImageTexture(final String imageUrl) {
        this.imageURL = null;
        this.image = null;
        try {
            this.image = ImageIO.read(new File(IMAGE_DIR + "/" + imageUrl));
            this.imageURL = IMAGE_DIR + "/" + imageUrl;
        } catch (IOException ex) {
            System.err.println("Can't read image");

        }
    }

    /**
     * Get Color of texture coordinations
     */
    public Color getColor(final double u, final double v) {

        final double coordinateU = this.getRoundValOfDouble(u);
        final double coordinateV = this.getRoundValOfDouble(v);

        final double x = (this.image.getWidth() - 1) * coordinateU;
        final double y = (this.image.getHeight() - 1) - ((this.image.getHeight()-1) * coordinateV);

        final double xInterpolated = x - Math.floor(x);
        final double yInterpolated = y - Math.floor(y);

        final Color colorFF = this.getColorOfImagePosition((int) Math.floor(x), (int) Math.floor(y));
        final Color colorFC = this.getColorOfImagePosition((int) Math.ceil(x), (int) Math.floor(y));
        final Color colorCF = this.getColorOfImagePosition((int) Math.floor(x), (int) Math.ceil(y));
        final Color colorCC = this.getColorOfImagePosition((int) Math.ceil(x), (int) Math.ceil(y));

        final Color colorFFFC = colorFF.mul(1.0 - xInterpolated).add(colorFC.mul(xInterpolated));
        final Color colorCFCC = colorCF.mul(1.0 - xInterpolated).add(colorCC.mul(xInterpolated));

        return colorFFFC.mul(1.0 - yInterpolated).add(colorCFCC.mul(yInterpolated));

    }

    private double getRoundValOfDouble(final double input) {
        double out = input % 1.0;
        if (out < 0.0) {
            out = out + 1.0;
        }
        return out;
    }

    /**
     * Get Color of image on texture coordinates
     */
    private final Color getColorOfImagePosition(final int x, final int y) {
//        final java.awt.Color colorRGB = new java.awt.Color(this.image.getRGB(x,y));
//        return new Color(colorRGB);

        int argb = this.image.getRGB(x,y);
        double r = (argb & 0xff0000) >> 16;
        double g = (argb & 0xff00) >> 8;
        double b = argb & 0xff;
        return new Color( r/255.0, g/255.0, b/255.0 );
    }

    /**
     * Method builds an evenly distributed hash value for the Light
     *
     * @return new hash code as int
     */
    @Override
    public String toString() {
        return "Image: \n" + this.imageURL;
    }

    /**
     * Method builds an evenly distributed hash value for the Light instance
     *
     * @return new hash code as int
     */
    @Override
    public int hashCode() {
        int result;

        result = this.image.hashCode();
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

        InterpolatedImageTexture texture = (InterpolatedImageTexture) o;
        return (this.image.equals(texture.image));
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
        for (int x = 0; x < this.image.getWidth(); x++) {
            for (int y = 0; y < this.image.getHeight(); y++) {

            }
        }
        if (this.getColor(1, 1).compareTo(texture.getColor(1, 1)) != 0)
            return this.getColor(1, 1).compareTo(texture.getColor(1, 1));
        return 0;
    }
}
