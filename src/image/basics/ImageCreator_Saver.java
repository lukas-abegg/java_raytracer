package image.basics;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

/**
 * ImageCreator_Saver is a class to create pictures with a centered diagonal red line
 * in the middle. It's possible to resize this picture and to save it if you want.
 * Image is painted in method paint(). The created image will be loaded in JFrame by
 * calling method createImage(). Saving is only allowed as a picture in PNG or JPG format.
 *
 * @author group raspi, CG1, Beuth-Hochschule
 * @version 1.0
 */
public final class ImageCreator_Saver extends Canvas implements ActionListener {

    /**
     * panel attributes
     */
    private final JFrame f = new JFrame();

    /**
     * image generator
     */
    private final IF_ImageGenerator imageGenerator;

    /**
     * image Buffer for generated image
     */
    private BufferedImage image;

    /**
     * global constants used in method
     */
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private static final String SAVE_IMAGE = "SAVE_IMAGE";
    private static final String JPG = "jpg";
    private static final String PNG = "png";

    public ImageCreator_Saver(IF_ImageGenerator imageGenerator) {
        this.imageGenerator = imageGenerator;
    }

    /**
     * Draw picture
     *
     * @param g Graphics object to draw picture
     */
    @Override
    public void paint(final Graphics g) {
        super.paint(g);

        this.image = imageGenerator.generateImage(this.getWidth(), this.getHeight());
        g.drawImage(this.image, 0, 0, this);
    }

    /**
     * Initialize JFrame parameter and menu
     */
    public void createImage() {
        f.setJMenuBar(addJMenuBar());

        this.setSize(WIDTH, HEIGHT);
        f.add(this);
        f.pack(); //set size from JPanel
        f.setLocationRelativeTo(null); //set JFrame in middle of window
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Image Saver");
        f.setVisible(true);
    }

    /**
     * Create JMenu and initialize event handling for menu items
     *
     * @return new JMenuBar for using in JFrame f
     */
    private JMenuBar addJMenuBar() {
        final JMenuBar menuBar = new JMenuBar();
        final JMenu menuFunctions = new JMenu("File");
        final JMenuItem itemSave = new JMenuItem("Save");

        // set action handling
        itemSave.addActionListener(this);
        itemSave.setActionCommand(SAVE_IMAGE);

        // add Menu
        menuFunctions.add(itemSave);
        menuBar.add(menuFunctions);
        return menuBar;
    }

    /**
     * Action Events for JFrame
     *
     * @param event action event for handling image saving and other menu actions
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        // check action command
        // handling method for image saving process
        if (event.getActionCommand().equals(SAVE_IMAGE)) saveImage();
    }

    /**
     * Save image process with exception handling
     *
     * @throws java.io.IOException for handling exceptions by trying to save image file
     */
    public void saveImage() {
        final JFileChooser explorer = new JFileChooser();
        final FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Images", JPG, PNG);

        // set file filter
        explorer.setFileFilter(imageFilter);
        explorer.setAcceptAllFileFilterUsed(false);
        explorer.setFileSelectionMode(JFileChooser.FILES_ONLY);


        if (explorer.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String fileSaveMSG;
            try {
                final File fileSaveIn = explorer.getSelectedFile();
                // Check if file is jpg or png format
                if (fileSaveIn.getName().endsWith(PNG)) {
                    //write selected File
                    ImageIO.write(this.image, PNG, fileSaveIn);
                    fileSaveMSG = "File successful saved in: " + fileSaveIn.getAbsolutePath();
                } else if (fileSaveIn.getName().endsWith(JPG)) {
                    //write selected File
                    ImageIO.write(this.image, JPG, fileSaveIn);
                    fileSaveMSG = "File successful saved in: " + fileSaveIn.getAbsolutePath();
                } else {
                    fileSaveMSG = "Only JPG and PNG format is allowed";
                }
            } catch (IOException e) {
                fileSaveMSG = "Can't save file";
                System.err.println(fileSaveMSG + " see error below:");
                System.err.print(e.getMessage());
            }

            // Popup save success or not message
            JOptionPane.showMessageDialog(null, fileSaveMSG);
        }
    }

}
