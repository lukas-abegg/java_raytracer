package testing.image.basics;

import image.basics.ImageViewer;

/**
 * TestsImageViewer is a class to
 * load an image from file system and
 * display in JFrame
 *
 * @author group raspi, CG1, Beuth-Hochschule
 * @version 1.0
 */
public class TestsImageViewer {

    /**
     * Main Class, tests functions from class ImageViewer
     *
     * @param args
     */
    public static void main(String[] args) {
        ImageViewer iv = new ImageViewer();

        System.out.println("Test: choose file from local file system and save in PNG or JPG format");
        iv.showImage();
    }
}