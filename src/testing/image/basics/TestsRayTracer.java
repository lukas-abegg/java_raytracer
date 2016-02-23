package testing.image.basics;

import image.basics.RayTracer;
import camera.OrthographicCamera;
import camera.PerspectiveCamera;
import color.Color;
import geometry.AxisAlignedBox;
import geometry.Plane;
import geometry.Sphere;
import geometry.Triangle;
import material.LambertMaterial;
import material.Material;
import material.PhongMaterial;
import sampling.SamplingPattern;
import texture.SingleColorTexture;
import world.World;
import light.DirectionalLight;
import light.PointLight;
import light.SpotLight;
import mathlib.Normal3;
import mathlib.Point3;
import mathlib.Vector3;
import light.Light;

/**
 * TestsImageCreator_Saver is a class to
 * generate images and save theme to local
 * file system.
 *
 * @author group raspi, CG1, Beuth-Hochschule
 * @version 1.0
 */
public class TestsRayTracer {

    public static Color   bgColor = new Color(0, 0, 0);
    public static Point3  e       = new Point3(4, 4, 4);
    public static Point3  e1      = new Point3(8, 8, 8);
    public static Vector3 g       = new Vector3(-1, -1, -1);
    public static Vector3 t       = new Vector3(0, 1, 0);
    public static double  s       = 3;
    public static double  angle   = Math.PI / 4;


    // Cameras
    public static PerspectiveCamera perspectiveCamera   = new PerspectiveCamera( e, g, t, angle, new SamplingPattern(1,1));
    public static PerspectiveCamera perspectiveCamera1  = new PerspectiveCamera( e1, g, t, angle, new SamplingPattern(1,1));
    public static OrthographicCamera orthographicCamera = new OrthographicCamera( e, g, t, s, new SamplingPattern(1,1));

    //World
//    public static World world1 = new World( bgColor, null, null );
//    public static World world2 = new World( bgColor, null, null );
//    public static World world3 = new World( bgColor, null, null );
//    public static World world4 = new World( bgColor, null, null );
//    public static World world5 = new World( bgColor, null, null );
//    public static World world6 = new World( bgColor, null, null );
//    public static World world7 = new World( bgColor, null, null );
//    public static World world8 = new World( bgColor, null, null );

    //Plane Data
    public static Point3 planePoint_a             = new Point3 (0, 0, 0);
    public static Normal3 planeNormal_n           = new Normal3(0, 1, 0);
    public static Material planeLambert_material  = new LambertMaterial(new SingleColorTexture( new Color(1, 0, 0)));
    public static Material planeLambert_material1 = new LambertMaterial(new SingleColorTexture( new Color(0.8, 0.8, 0.8)));
    public static Material planePhong_material    = new PhongMaterial( new SingleColorTexture( new Color( 1, 0, 0 )), new SingleColorTexture( new Color( 1, 1, 1 )), 64 );

    //Sphere Data
    public static Point3 spherePoint_c            = new Point3(1, 1, 1);
    public static double sphereDouble_r           = 0.5;
    public static Material sphereLambert_material = new LambertMaterial(new SingleColorTexture( new Color(0, 1, 0)));
    public static Material spherePhong_material   = new PhongMaterial(new SingleColorTexture( new Color(0, 1, 0)), new SingleColorTexture( new Color(1, 1, 1)), 64);

    //AAB Data
    public static Point3 aabPoint_lbf             = new Point3(-1.5, 0.5, 0.5);
    public static Point3 aabPoint_run             = new Point3(-0.5, 1.5, 1.5);
    public static Material aabLambert_material    = new LambertMaterial(new SingleColorTexture( new Color(0, 0, 1)));
    public static Material aabPhong_material      = new PhongMaterial(new SingleColorTexture( new Color(0, 0, 1)), new SingleColorTexture( new Color(1, 1, 1)), 64);

    //Triangle Data
    public static Point3 a = new Point3(0, 0, -1);
    public static Point3 b = new Point3(1, 0, -1);
    public static Point3 c = new Point3(1, 1, -1);
    public static Normal3 an = b.sub(a).x(c.sub(a)).mul(1 / (b.sub(a).x(c.sub(a))).magnitude).normalized().asNormal();
    public static Normal3 bn = a.sub(b).x(c.sub(b)).mul(1 / (a.sub(b).x(c.sub(b))).magnitude).normalized().asNormal();
    public static Normal3 cn = a.sub(c).x(b.sub(c)).mul(1 / (a.sub(c).x(b.sub(c))).magnitude).normalized().asNormal();
    public static Material triangleLambert_material = new LambertMaterial(new SingleColorTexture( new Color(1, 1, 0)));
    public static Material trianglePhong_material   = new PhongMaterial(new SingleColorTexture( new Color(1, 1, 0)), new SingleColorTexture( new Color(1, 1, 1)), 64);

    //Lights
    public static boolean castShadows = true;
    public static Color lightColor       = new Color(1, 1, 1);
    public static Point3 lightPosition   = new Point3(4, 4, 4);
    public static Point3 lightPosition2   = new Point3(-4, 4, 4);
    public static Point3 lightPosition1 = new Point3(8, 8, 0);
    public static Vector3 lightDirection = new Vector3(-1, -1, -1);
    public static double lightHalfAngle  = Math.PI / 14;

    public static Light pointLight = new PointLight(lightColor, lightPosition, castShadows);
    public static Light pointLight1 = new PointLight(lightColor, lightPosition1, castShadows);
    public static Light directionalLight = new DirectionalLight(lightColor, lightDirection, castShadows);
    public static Light spotLight = new SpotLight(lightColor, lightPosition, lightDirection, lightHalfAngle, castShadows);
    public static Light pointLight2        = new PointLight(lightColor, lightPosition2, castShadows);


    /**
     * Main Class, tests functions from class ImageCreator_Saver
     *
     * @param args
     */
    public static void main(String[] args) {

//        // Test 1
//        RayTracer tracer1 = new RayTracer( world1, perspectiveCamera );
//        tracer1.world.geoList.clear();
//        tracer1.world.geoList.add( new Plane( planePoint_a,  planeNormal_n,  planeLambert_material ) );
//        tracer1.world.geoList.add( new Sphere( spherePoint_c, sphereDouble_r, sphereLambert_material ) );
//        tracer1.world.geoList.add( new AxisAlignedBox( aabPoint_lbf, aabPoint_run, aabLambert_material ) );
//        tracer1.world.geoList.add( new Triangle( a, b, c,  triangleLambert_material ) );
//        tracer1.world.lights.clear();
//        tracer1.world.lights.add( pointLight );
//        runTest1(tracer1);
//
//        //Test 2 mit pCam
//        RayTracer tracer2 = new RayTracer( world2, perspectiveCamera );
//        tracer2.world.geoList.clear();
//        tracer2.world.geoList.add( new Plane( planePoint_a,  planeNormal_n,  planeLambert_material ) );
//        tracer2.world.geoList.add( new Sphere( spherePoint_c, sphereDouble_r, sphereLambert_material ) );
//        tracer2.world.geoList.add( new AxisAlignedBox( aabPoint_lbf, aabPoint_run, aabLambert_material ) );
//        tracer2.world.geoList.add( new Triangle( a, b, c,  triangleLambert_material ) );
//        tracer2.world.lights.clear();
//        tracer2.world.lights.add( pointLight );
//        runTest2(tracer2);
//
//        //Test 3 mit pCam
//        RayTracer tracer3 = new RayTracer( world3, perspectiveCamera );
//        tracer3.world.geoList.clear();
//        tracer3.world.geoList.add( new Plane( planePoint_a,  planeNormal_n,  planePhong_material ) );
//        tracer3.world.geoList.add( new Sphere( spherePoint_c, sphereDouble_r, spherePhong_material ) );
//        tracer3.world.geoList.add( new AxisAlignedBox( aabPoint_lbf, aabPoint_run, aabPhong_material ) );
//        tracer3.world.geoList.add( new Triangle( a, b, c,  trianglePhong_material ) );
//        tracer3.world.lights.clear();
//        tracer3.world.lights.add( pointLight );
//        runTest3(tracer3);
//
//        //Test 4 mit pCam
//        RayTracer tracer4 = new RayTracer( world4, perspectiveCamera );
//        tracer4.world.geoList.clear();
//        tracer4.world.geoList.add( new Plane( planePoint_a,  planeNormal_n,  planePhong_material ) );
//        tracer4.world.geoList.add( new Sphere( spherePoint_c, sphereDouble_r, spherePhong_material ) );
//        tracer4.world.geoList.add( new AxisAlignedBox( aabPoint_lbf, aabPoint_run, aabPhong_material ) );
//        tracer4.world.geoList.add( new Triangle( a, b, c, trianglePhong_material ) );
//        tracer4.world.lights.clear();
//        tracer4.world.lights.add( directionalLight );
//        runTest4(tracer4);
//
//        //Test 5 mit pCam
//        RayTracer tracer5 = new RayTracer( world5, perspectiveCamera );
//        tracer5.world.geoList.clear();
//        tracer5.world.geoList.add( new Plane( planePoint_a,  planeNormal_n,  planePhong_material ) );
//        tracer5.world.geoList.add( new Sphere( spherePoint_c, sphereDouble_r, spherePhong_material ) );
//        tracer5.world.geoList.add( new AxisAlignedBox( aabPoint_lbf, aabPoint_run, aabPhong_material ) );
//        tracer5.world.geoList.add( new Triangle( a, b, c,  trianglePhong_material ) );
//        tracer5.world.lights.clear();
//        tracer5.world.lights.add( spotLight );
//        runTest5(tracer5);
//
//        //Test 6 mit pCam
//        RayTracer tracer6 = new RayTracer( world6, perspectiveCamera );
//        tracer6.world.geoList.clear();
//        tracer6.world.geoList.add( new Plane( planePoint_a,  planeNormal_n,  planePhong_material ) );
//        tracer6.world.geoList.add( new Sphere( spherePoint_c, sphereDouble_r, spherePhong_material ) );
//        tracer6.world.geoList.add( new AxisAlignedBox( aabPoint_lbf, aabPoint_run, aabPhong_material ) );
//        tracer6.world.geoList.add( new Triangle( a, b, c,  trianglePhong_material ) );
//        tracer6.world.lights.clear();
//        tracer6.world.lights.add( spotLight );
//        tracer6.world.ambientLight = new Color(0.25, 0.25, 0.25);
//        runTest6(tracer6);
//
//        //Test 7 mit pCam
//        RayTracer tracer7 = new RayTracer( world7, perspectiveCamera );
//        tracer7.world.geoList.clear();
//        tracer7.world.geoList.add( new Plane( planePoint_a,  planeNormal_n,  planePhong_material ) );
//        tracer7.world.geoList.add( new Sphere( spherePoint_c, sphereDouble_r, spherePhong_material ) );
//        tracer7.world.geoList.add( new AxisAlignedBox( aabPoint_lbf, aabPoint_run, aabPhong_material ) );
//        tracer7.world.geoList.add( new Triangle( a, b, c,  trianglePhong_material ) );
//        tracer7.world.lights.clear();
//        tracer7.world.lights.add( pointLight2 );
//        tracer7.world.ambientLight = new Color(0.25, 0.25, 0.25);
//        runTest7(tracer7);
//
//        //Test 8 Aufgabe 4 World mit schwarzem Backgground
//        RayTracer tracer8 = new RayTracer(world8, perspectiveCamera1);
//        tracer8.world.geoList.add(new Plane(planePoint_a, planeNormal_n, planeLambert_material1));
//        tracer8.world.geoList.add(new AxisAlignedBox(new Point3(-0.5, 0, -0.5), new Point3(0.5, 1, 0.5), aabPhong_material));
//        tracer8.world.lights.clear();
//        tracer8.world.lights.add(pointLight1);
//        runTest8(tracer8);
    }

    public static void runTest1(RayTracer tracer1) {
        //Test 1 mit pCam
        tracer1.createImage();
    }

    public static void runTest2(RayTracer tracer2) {
        //Test 2 mit pCam
        tracer2.createImage();
    }

    public static void runTest3(RayTracer tracer3) {
        //Test 3 mit pCam
        tracer3.createImage();
    }

    public static void runTest4(RayTracer tracer4) {
        //Test 4 mit pCam
        tracer4.createImage();
    }

    public static void runTest5(RayTracer tracer5) {
        //Test 5 mit pCam
        tracer5.createImage();
    }

    public static void runTest6(RayTracer tracer6) {
        //Test 6 mit pCam
        tracer6.createImage();
    }

    public static void runTest7(RayTracer tracer7) {
        //Test 7 mit pCam
        tracer7.createImage();
    }

    public static void runTest8(RayTracer tracer8) {
        //Test 8 mit pCam
        tracer8.createImage();
    }
}