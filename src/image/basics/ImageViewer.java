package image.basics;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * ImageViewer is a class to view images as a normal
 * picture previewer does. The image can be chosen,
 * by calling showImage method. Pictures are allowed
 * only in PNG and JPG format.
 *
 * @version 1.0
 * @author group raspi, CG1, Beuth-Hochschule
 */
public final class ImageViewer extends Canvas {

    final private JFrame f = new JFrame();
    /** image Buffer for loaded image */
    private BufferedImage image;
    /** loaded image */
    private File imageFile;

    private static final String JPG = "jpg";
    private static final String PNG = "png";

    /**
     * File explorer for choosing image from file system
     *
     * @exception java.lang.Exception if chosen file can't be read
     * @return boolean, file is chosen or not?
     */
    public boolean chooseFile() {
        final JFileChooser explorer = new JFileChooser();
        // file filter to allow only JPG and PNG images
        final FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Images", JPG, PNG);

        // set file filter
        explorer.setFileFilter(imageFilter);
        explorer.setAcceptAllFileFilterUsed(false);

        //open file explorer for choosing image, if image is approved, load file information
        if (explorer.showOpenDialog(ImageViewer.this) == JFileChooser.APPROVE_OPTION) {
            this.imageFile = explorer.getSelectedFile();

            try {
                // read image buffer from file
                this.image = ImageIO.read(this.imageFile);
            } catch (Exception e) {
                System.err.println("Can't load selected file see error below:");
                System.err.print(e.getMessage());
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Initialize JFrame an set default settings for image viewer
     *
     */
    public void showImage() {
        // choose file and load JFrame if image is chosen
        if (chooseFile()) {
            this.setSize(this.image.getWidth(), this.image.getHeight());

            f.add(this);
            f.pack(); //set size from Canvas
            f.setResizable(false);
            f.setLocationRelativeTo(null); //set JFrame in middle of window
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setTitle("Image Viewer: " + this.imageFile.getName());
            f.setVisible(true);
        }
    }

    /**
     * Draw image in image viewer for chosen file
     *
     * @param g Graphics object for drawing image
     */
    public void paint(final Graphics g) {
        g.drawImage(this.image, 0, 0, this);
    }


}