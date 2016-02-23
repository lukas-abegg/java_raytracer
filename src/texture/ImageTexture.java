package texture;


import color.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * ImageTexture for getting Color of texture
 *
 * @author Marco Kollosche, Lukas Abegg, András Bucsi
 * @version Aufgabe Erweiterung Kat 0 2014-12-21
 */
public class ImageTexture implements Texture {

    public final static String IMAGE_DIR = "src/assets";
    public String imageURL;
    public BufferedImage image;

    public ImageTexture(final String imageUrl) {
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
        double coordinateU = u % 1.0;
        double coordinateV = v % 1.0;

        if (coordinateU < 0.0) {
            coordinateU += 1.0;
        }

        if (coordinateV < 0.0) {
            coordinateV += 1.0;
        }

        final double x = (this.image.getWidth() - 1) * coordinateU;
        //final double y = (this.image.getHeight() - 1)  * coordinateV;
        //System.out.println( "(" + u + "/" + v + ")" );
        final double y = (this.image.getHeight() - 1) - ((this.image.getHeight()-1) * coordinateV);

        int argb = this.image.getRGB((int) Math.round(x), (int) Math.round(y));

        int r = (argb >>> 16) & 0xFF;
        int g = (argb >>> 8) & 0xFF;
        int b = (argb >>> 0) & 0xFF;

        return new Color( r/255.0 , g/255.0 , b/255.0 );
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

        ImageTexture texture = (ImageTexture) o;
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
