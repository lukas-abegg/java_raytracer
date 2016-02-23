package image.basics;

import java.awt.image.BufferedImage;

/**
 * An interface for image generator used in class ImageCreator_Saver
 *
 * @author group raspi, CG1, Beuth-Hochschule
 * @version 1.0
 */
public interface IF_ImageGenerator {

    /**
     * class to generate images
     *
     * @param width  width of generated image
     * @param height height of generated image
     * @return       generated new BufferedImage
     */
    public abstract BufferedImage generateImage(int width, int height);
}
