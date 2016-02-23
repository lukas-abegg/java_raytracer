package image.basics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * DrawLine is a class to generate an image
 * with a diagonal line in the middle
 *
 * @author group raspi, CG1, Beuth-Hochschule
 * @version 1.0
 */
public class DrawLine implements IF_ImageGenerator {

    /**
     * DrawLine is the method to generate an image
     * with a diagonal line in the middle
     *
     * @param width     set width of the generated image
     * @param height    set height of the generated image
     * @return          generated image as new BufferedImage
     */
    public BufferedImage generateImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // get raster and color model for configuring raster pixel color
        final WritableRaster raster = image.getRaster();
        final ColorModel colorModel = image.getColorModel();

        // define black and red pixel color
        final int blackVal = Color.black.getRGB();
        final int redVal = Color.red.getRGB();

        // initialize all pixels in color black
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                raster.setDataElements(i, j, colorModel.getDataElements(blackVal, null));
            }
        }

        // set diagonal line pixel in color red
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                if (i == j) {
                    raster.setDataElements(i, j, colorModel.getDataElements(redVal, null));
                }
            }
        }
        return image;
    }
}

