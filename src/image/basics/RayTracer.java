package image.basics;

import camera.Camera;
import geometry.Hit;
import material.Tracer;
import ray.Ray;
import world.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * RayTracer is the class to generate an image
 * based on the initiated camera view and
 * the initiated world
 *
 * @author group raspi, CG1, Beuth-Hochschule
 * @version Aufgabe2 2014-11-25
 */
public final class RayTracer implements IF_ImageGenerator {

    /**
     * ImageCreator_Saver to show and save image
     */
    public final ImageCreator_Saver imageCreator_saver;
    /**
     * geometry in the used world
     */
    public final World world;
    /**
     * camera attributes
     */
    public final Camera camera;

    /**
     * multithreading
     */
    public final int count_processors;

    /**
     * constructor for RayTracer initiate all RayTracer attributes used for generate image
     *
     * @param world  big earth
     * @param camera type of camera view
     */
    public RayTracer(final World world, final Camera camera) {
        this.world = world;
        this.camera = camera;
        this.imageCreator_saver = new ImageCreator_Saver(this);
        this.count_processors = Runtime.getRuntime().availableProcessors();
    }

    /**
     * constructor for RayTracer initiate all RayTracer attributes used for generate image
     *
     * @param world            big earth
     * @param camera           type of camera view
     * @param count_processors multithreading
     */
    public RayTracer(final World world, final Camera camera, final int count_processors) {
        this.world = world;
        this.camera = camera;
        this.imageCreator_saver = new ImageCreator_Saver(this);
        this.count_processors = count_processors;
    }

    /**
     * method createImage call the imageCreator_saver class to show generated images
     */
    public void createImage() {
        imageCreator_saver.createImage();
    }

    /**
     * Generator method to create the image based on the hits in the world and the
     * camera view.
     *
     * @param width  width of generated image
     * @param height height of generated image
     * @return generate a new BufferedImage
     */
    @Override
    public BufferedImage generateImage(final int width, final int height) {
        // defines how many threads will be in thread pool depending on maximum of usable processors in system
        final ExecutorService exec = Executors.newFixedThreadPool(this.count_processors);
        final Collection<Rectangle> rasterCollection = this.getRasterCollection(width, height);


        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // get raster and color model for configuring raster pixel color
        final WritableRaster raster = image.getRaster();
        final ColorModel colorModel = image.getColorModel();


        // run all thread tasks
        for (final Rectangle rectangle : rasterCollection) {
            // take a thread out of executer thread pool and run new thread task
            exec.execute(new Runnable() {
                public void run() {
                    renderPartial(rectangle, width, height, raster, colorModel);
                }
            });
        }

        exec.shutdown();
        try {
            exec.awaitTermination(2000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

        // return generated image
        return image;
    }

    /**
     * Render partial of picture with raytracer
     * <p/>
     * x and y of rectangle are start position defined and width and height of rectangle shows raster size
     */
    private void renderPartial(final Rectangle rectangle, final int width, final int height, final WritableRaster raster, final ColorModel colorModel) {
        // define black and red pixel color
        final int bgColor = this.world.BACKGROUND_COLOR.rgbIntVal;

        // generate all pixel started on left top side and ends on the right bottom side
        for (int x = rectangle.x; x <= rectangle.width - 1; x++) {
            for (int y = rectangle.y; y <= rectangle.height - 1; y++) {

                // get the ray for every pixel based on the camera view
                List<Ray> rays = camera.rayFor(width, height, x, y);
                color.Color sampColor = this.world.BACKGROUND_COLOR;
                
                for (Ray ray : rays) {
                    // get the shortest hit of the world with method hit
                    Hit hit = this.world.hit(ray);
                    
                    if (hit != null) {
                        final color.Color hitColor = this.colorFor(hit, this.world);
                        if (hitColor != null) {
                            sampColor = sampColor.add(hitColor.mul(1F / rays.size()));
                        }
                    }
                }

                // if any geometric object is hit, paint color of object, else paint color of background
                if (sampColor != null) {
                    raster.setDataElements(x, height - 1 - y, colorModel.getDataElements(sampColor.rgbIntVal, null));
                } else {
                    raster.setDataElements(x, height - 1 - y, colorModel.getDataElements(bgColor, null));
                }
            }
        }
    }

    private color.Color colorFor(Hit hit, World world){
        return hit.geo.material.colorFor(hit, world);
    }

    /**
     * set collection of picture partials for picture raster with width and height as rectangles
     *
     * @param fullWidth     image width
     * @param fullHeight    image height
     * @return collection of rectangles
     */
    private Collection<Rectangle> getRasterCollection(final int fullWidth, final int fullHeight) {

        final int parts = 10;
        final int restOfWidth = fullWidth % parts;
        final int restOfHeight = fullHeight % parts;
        final int widthPartMax = fullWidth - restOfWidth;
        final int heightPartMax = fullHeight - restOfHeight;

        java.util.List<Rectangle> listOfRectangles = new ArrayList<Rectangle>();

        for (int x = 0; x <= widthPartMax; x += parts) {
            for (int y = 0; y <= heightPartMax; y += parts) {
                Rectangle rectangle;
                if (x == widthPartMax && y == heightPartMax) {
                    rectangle = new Rectangle(x, y, x + restOfWidth, y + restOfHeight);
                } else if (x == widthPartMax) {
                    rectangle = new Rectangle(x, y, x + restOfWidth, y + parts);
                } else if (y == heightPartMax) {
                    rectangle = new Rectangle(x, y, x + parts, y + restOfHeight);
                } else {
                    rectangle = new Rectangle(x, y, x + parts, y + parts);
                }
                listOfRectangles.add(rectangle);
            }
        }
        return listOfRectangles;
    }


}
