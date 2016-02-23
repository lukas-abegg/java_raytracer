package testing.image.basics;

import geometry.*;
import image.basics.RayTracer;
import camera.PerspectiveCamera;
import color.Color;
import material.*;
import mathlib.Normal3;
import mathlib.Transform;
import sampling.SamplingPattern;
import texture.SingleColorTexture;
import world.World;
import light.DirectionalLight;
import light.PointLight;
import light.SpotLight;
import mathlib.Point3;
import mathlib.Vector3;

import java.util.ArrayList;

/**
 * Created by Me on 16.12.2014.
 */
public class TestRefraction {
    
    /**
     * Main Class, tests functions from class ImageCreator_Saver
     *
     * @param args
     */
    public static void main(String[] args) {
        PerspectiveCamera camera = new PerspectiveCamera(new Point3(8, 8, 8), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4, new SamplingPattern(1, 1));

        // --- Bereich 1 Plane --- //
        Point3 a  = new Point3(0, 0, 0);
        Normal3 n = new Normal3(0,1,0);
        Plane plane = new Plane(a, n, new ReflectiveMaterial(new SingleColorTexture(new Color(1, 1, 1)), new SingleColorTexture(new Color(1, 1, 1)), 10, new SingleColorTexture(new Color(1, 1, 1))));

        // --- Bereich 1 Spheres--- //
        double r = 0.5;
        Point3 pS1 = new Point3(0, 1, 0);
        Sphere s1 = new Sphere(pS1, r, new ReflectiveMaterial(new SingleColorTexture(new Color(1, 0, 0)), new SingleColorTexture(new Color(1, 1, 1)), 10, new SingleColorTexture(new Color(1, 0.5, 0.5))));

        Point3 pS2 = new Point3(-1.5, 1, 0);
        Sphere s2 = new Sphere(pS2, r, new ReflectiveMaterial(new SingleColorTexture(new Color(0, 1, 0)), new SingleColorTexture(new Color(1, 1, 1)), 10, new SingleColorTexture(new Color(1, 0.5, 0.5))));

        Point3 pS3 = new Point3(1.5, 1, 0);
        Sphere s3 = new Sphere(pS3, r, new ReflectiveMaterial(new SingleColorTexture(new Color(0, 0, 1)), new SingleColorTexture(new Color(1, 1, 1)), 10, new SingleColorTexture(new Color(1, 0.5, 0.5))));

        Point3 pS4 = new Point3(0, 1, -1.5);
        Sphere s4 = new Sphere(pS4, r, new ReflectiveMaterial(new SingleColorTexture(new Color(0, 1, 1)), new SingleColorTexture(new Color(1, 1, 1)), 10, new SingleColorTexture(new Color(1, 0.5, 0.5))));

        Point3 pS5 = new Point3(-1.5, 1, -1.5);
        Sphere s5 = new Sphere(pS5, r, new ReflectiveMaterial(new SingleColorTexture(new Color(1, 0, 1)), new SingleColorTexture(new Color(1, 1, 1)), 10, new SingleColorTexture(new Color(1, 0.5, 0.5))));

        Point3 pS6 = new Point3(1.5, 1, -1.5);
        Sphere s6 = new Sphere(pS6, r, new ReflectiveMaterial(new SingleColorTexture(new Color(1, 1, 0)), new SingleColorTexture(new Color(1, 1, 1)), 10, new SingleColorTexture(new Color(1, 0.5, 0.5))));

        
        // --- Bereich 2 Spheres --- //
        Point3 pS7 = new Point3(0, 2, 1.5);
        Sphere s7 = new Sphere(pS7, r, new TransparentMaterial(1.33));

        Point3 pS8 = new Point3(-1.5, 2, 1.5);
        Sphere s8 = new Sphere(pS8, r, new TransparentMaterial(1.33));

        Point3 pS9 = new Point3(1.5, 2, 1.5);
        Sphere s9 = new Sphere(pS9, r, new TransparentMaterial(1.33));
        
        // --- Bereich 2 AAB + Triangle --- //
        Point3 lbf = new Point3(-0.5, 0, 3);
        Point3 run = new Point3( 0.5, 1, 4);
        AxisAlignedBox aab = new AxisAlignedBox(lbf, run, new TransparentMaterial(1.33));

        Point3 ta = new Point3(0.7, 0.5, 3);
        Point3 tb = new Point3(1.3, 0.5, 3);
        Point3 tc = new Point3(0.7, 0.5, 4);
        Triangle triangle = new Triangle(ta, tb, tc, new PhongMaterial(new SingleColorTexture(new Color(0, 1, 0)), new SingleColorTexture(new Color(0, 1, 0)), 20));


        // --- light --- //
        Color ambientLight = new Color(0.1, 0.1, 0.1);

        World world = new World(ambientLight, null, null, 1.0);
        RayTracer tracer1 = new RayTracer(world, camera);

        tracer1.world.geoList.clear();
        tracer1.world.geoList.add(plane);
        tracer1.world.geoList.add(s1);
        tracer1.world.geoList.add(s2);
        tracer1.world.geoList.add(s3);
        tracer1.world.geoList.add(s4);
        tracer1.world.geoList.add(s5);
        tracer1.world.geoList.add(s6);
        
        tracer1.world.geoList.add(s7);
        tracer1.world.geoList.add(s8);
        tracer1.world.geoList.add(s9);
        
        tracer1.world.geoList.add(aab);
        tracer1.world.geoList.add(triangle);

        tracer1.world.lights.clear();
        tracer1.world.lights.add(new PointLight(new Color(.3, .3, .3), new Point3(5, 5, -10), true));
        tracer1.world.lights.add(new SpotLight(new Color(.3, .3, .3), new Point3(0, 5, -10), new Vector3(0, -1, 0), Math.PI / 8, true));
        tracer1.world.lights.add(new DirectionalLight(new Color(.3, .3, .3), new Vector3(1, -1, 0), true));

        tracer1.createImage();
    }
}