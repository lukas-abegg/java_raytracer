package testing.image.basics;

import image.basics.DrawLine;
import image.basics.ImageCreator_Saver;

/**
 * TestsImageCreator_Saver is a class to
 * generate images and save theme to local
 * file system.
 *
 * @author group raspi, CG1, Beuth-Hochschule
 * @version 1.0
 */
public class TestsImageCreator_Saver {

    /**
     * Main Class, tests functions from class ImageCreator_Saver
     *
     * @param args
     */
    public static void main(String[] args) {
        DrawLine drawLine = new DrawLine();
        ImageCreator_Saver ics = new ImageCreator_Saver(drawLine);

        //System.out.println("Test: generate file and save it on local file system");
        ics.createImage();
    }
}