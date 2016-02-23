package testing.image.basics;

import image.basics.RayTracer;
import camera.PerspectiveCamera;
import color.Color;
import geometry.AxisAlignedBox;
import geometry.Plane;
import material.LambertMaterial;
import material.Material;
import sampling.SamplingPattern;
import texture.SingleColorTexture;
import texture.Texture;
import world.World;
import light.Light;
import light.PointLight;
import mathlib.Normal3;
import mathlib.Point3;
import mathlib.Vector3;

/**
 * TestsImageCreator_Saver is a class to
 * generate images and save theme to local
 * file system.
 *
 * @author group raspi, CG1, Beuth-Hochschule
 * @version 1.0
 */
public class TestsShadow {

    public static Color   bgColor = new Color(0, 0, 0);
    public static Point3  e      = new Point3(8, 8, 8);
    public static Vector3 g       = new Vector3(-1, -1, -1);
    public static Vector3 t       = new Vector3(0, 1, 0);
    public static double  angle   = Math.PI / 4;

    // Cameras
    public static PerspectiveCamera perspectiveCamera   = new PerspectiveCamera( e, g, t, angle, new SamplingPattern(1,1));

    //World
//    public static World world1 = new World( bgColor, null, null );

    //Plane Data
    public static Point3 planePoint_a             = new Point3 (0, 0, 0);
    public static Normal3 planeNormal_n           = new Normal3(0, 1, 0);
    public static Material planeLambert_material = new LambertMaterial(new SingleColorTexture(new Color(0.8, 0.8, 0.8)));

    //AAB Data
    public static Point3 aabPoint_lbf             = new Point3(-0.5, 0, -0.5);
    public static Point3 aabPoint_run             = new Point3(0.5, 1, 0.5);
    public static Material aabLambert_material    = new LambertMaterial(new SingleColorTexture(new Color(0, 0, 1)));


    //Lights
    public static boolean castShadows = true;
    public static Color lightColor       = new Color(1, 1, 1);
    public static Point3 lightPosition = new Point3(8, 8, 0);
    public static Light pointLight = new PointLight(lightColor, lightPosition, castShadows);


    /**
     * Main Class, tests functions from class ImageCreator_Saver
     *
     * @param args
     */
    public static void main(String[] args) {
        // Test 1
//        RayTracer tracer1 = new RayTracer( world1, perspectiveCamera );
//        tracer1.world.geoList.clear();
//        tracer1.world.geoList.add(new Plane(planePoint_a, planeNormal_n, planeLambert_material));
//        tracer1.world.geoList.add( new AxisAlignedBox( aabPoint_lbf, aabPoint_run, aabLambert_material ) );
//        tracer1.world.lights.clear();
//        tracer1.world.lights.add( pointLight );
//        runTest1(tracer1);

    }

    public static void runTest1(RayTracer tracer1) {
        //Test 1 mit pCam
        tracer1.createImage();
    }
}